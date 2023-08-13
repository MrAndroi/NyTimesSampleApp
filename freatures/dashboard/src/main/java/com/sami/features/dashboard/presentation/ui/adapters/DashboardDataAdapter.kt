package com.sami.features.dashboard.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sami.features.dashboard.databinding.RowDashboardBinding
import com.sami.features.dashboard.domain.dto.DashBoardItemModel

class DashboardDataAdapter(private val onClick: (link: String) -> Unit) :
    ListAdapter<DashBoardItemModel, DashboardDataAdapter.ViewHolder>(DashboardDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowDashboardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: RowDashboardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: DashBoardItemModel) {
            binding.model = model
            itemView.setOnClickListener { onClick(model.url) }
            binding.executePendingBindings()
        }
    }

    private object DashboardDiffer : DiffUtil.ItemCallback<DashBoardItemModel>() {

        override fun areItemsTheSame(
            oldItem: DashBoardItemModel,
            newItem: DashBoardItemModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DashBoardItemModel,
            newItem: DashBoardItemModel
        ): Boolean {
            return oldItem == newItem
        }

    }

}