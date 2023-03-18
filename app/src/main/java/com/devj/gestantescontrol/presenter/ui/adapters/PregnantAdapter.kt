package com.devj.gestantescontrol.presenter.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.databinding.GestanteItemBinding
import com.devj.gestantescontrol.domain.model.Pregnant


class PregnantAdapter(private val fn: (pregnant: Pregnant) -> Unit) :
    ListAdapter<Pregnant, PregnantAdapter.PregnantViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PregnantViewHolder {
        return PregnantViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.gestante_item, parent, false)
        )

    }

    override fun onBindViewHolder(holder: PregnantViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { fn(getItem(position)) }
    }

    class PregnantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = GestanteItemBinding.bind(view)
        fun bind(pregnant: Pregnant) {

        }
    }
}
private object DiffUtilCallback : DiffUtil.ItemCallback<Pregnant>() {
    override fun areItemsTheSame(oldItem: Pregnant, newItem: Pregnant): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: Pregnant, newItem: Pregnant): Boolean {
        return (oldItem == newItem)
    }


}