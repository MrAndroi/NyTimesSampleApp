package com.sami.features.dashboard.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sami.core.utils.openUrl
import com.sami.core.utils.showPrimaryMessage
import com.sami.features.dashboard.databinding.FragmentDashboardBinding
import com.sami.features.dashboard.domain.dto.DashBoardItemModel
import com.sami.features.dashboard.presentation.DashboardViewModel
import com.sami.features.dashboard.presentation.ui.adapters.DashboardDataAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.sami.core.R as coreRes

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels()

    private lateinit var dashboardDataAdapter: DashboardDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setUpCollectors()
        setUpClicks()
        setUpDashboardRecyclerView()
        setUpListeners()
    }

    private fun setUpCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { viewModel.progressFlow.collect(::handleLoading) }
                launch { viewModel.errorState.collect(::handleError) }
                launch { viewModel.dashboardData.collect(::handleSuccessDataList) }
            }
        }
    }

    private fun handleLoading(show: Boolean) {
        binding.showLoading = show
        binding.swipeToRefresh.isRefreshing = show
    }

    private fun handleError(error: Throwable?) {
        error?.let {
            binding.showError = true
            showPrimaryMessage(error.message ?: getString(coreRes.string.general_error))
        }
    }

    private fun handleSuccessDataList(dashboardData: List<DashBoardItemModel>?) {
        dashboardData?.let {
            binding.showError = it.isEmpty()
            dashboardDataAdapter.submitList(it)
            lifecycleScope.launch(Dispatchers.Main) {
                delay(300)
                binding.recyclerViewData.smoothScrollToPosition(0)
            }
        }
    }

    private fun setUpDashboardRecyclerView() {
        dashboardDataAdapter = DashboardDataAdapter { requireContext().openUrl(it) }
        binding.recyclerViewData.adapter = dashboardDataAdapter
    }

    private fun setUpClicks() {
        binding.textViewSort.setOnClickListener {
            viewModel.sortList()
        }
        binding.textInputLayout.setEndIconOnClickListener {
            viewModel.updateSearchQuery("")
            binding.editTextSearch.setText("")
        }
    }

    private fun setUpListeners() {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.getDashboardData()
        }
        binding.editTextSearch.doAfterTextChanged {
            viewModel.updateSearchQuery(it.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}