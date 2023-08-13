package com.sami.features.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sami.features.dashboard.domain.dto.DashBoardItemModel
import com.sami.features.dashboard.domain.usecase.GetDashBoardDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class)
class DashboardViewModel @Inject constructor(
    private val getDashBoardDataUseCase: GetDashBoardDataUseCase
) : ViewModel() {

    private val _progressFlow = MutableStateFlow(false)
    val progressFlow = _progressFlow.asStateFlow()

    private val _errorState = MutableSharedFlow<Throwable?>()
    val errorState = _errorState.asSharedFlow()

    private val _dashboardData = MutableStateFlow<List<DashBoardItemModel>?>(null)
    val dashboardData = _dashboardData.asStateFlow()

    private val searchQuery = MutableStateFlow("")
    private val sortDescending = MutableStateFlow(false)
    private var dashBoardDataList: List<DashBoardItemModel> = emptyList()

    init {
        getDashboardData()
    }

    fun getDashboardData() = viewModelScope.launch(Dispatchers.IO) {
        _progressFlow.emit(true)
        try {
            dashBoardDataList = getDashBoardDataUseCase()
            _dashboardData.emit(dashBoardDataList)
            filterDataWithQuery()
        } catch (e: Exception) {
            _errorState.emit(e)
        }
        _progressFlow.emit(false)
    }

    private fun filterDataWithQuery() {
        searchQuery.combine(sortDescending) { searchQuery, sortDec ->
            val sortedList = if (sortDec) {
                dashBoardDataList.sortedByDescending { it.dateLong }
            } else {
                dashBoardDataList.sortedBy { it.dateLong }
            }

            if (searchQuery.isNotBlank()) {
                val filteredItems = sortedList.filter { dashboardItem ->
                    dashboardItem.title.contains(searchQuery, ignoreCase = true)
                }
                _dashboardData.emit(filteredItems)
            } else {
                _dashboardData.emit(sortedList)
            }

        }
            .debounce(300)
            .distinctUntilChanged()
            .launchIn(viewModelScope)
    }

    fun updateSearchQuery(query: String) = viewModelScope.launch {
        searchQuery.emit(query)
    }

    fun sortList() = viewModelScope.launch {
        sortDescending.emit(!sortDescending.value)
    }

}