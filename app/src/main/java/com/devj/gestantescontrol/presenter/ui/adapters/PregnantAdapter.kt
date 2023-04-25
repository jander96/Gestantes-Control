package com.devj.gestantescontrol.presenter.ui.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.databinding.GestanteItemBinding
import com.devj.gestantescontrol.presenter.model.PregnantUI


class PregnantAdapter (private val onPressed: (pregnant: PregnantUI) -> Unit) :
    ListAdapter<PregnantUI, PregnantAdapter.PregnantViewHolder>(DiffUtilCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PregnantViewHolder {
        return PregnantViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.gestante_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PregnantViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { onPressed(getItem(position)) }
    }

   class PregnantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = GestanteItemBinding.bind(view)
        fun bind(pregnant: PregnantUI) {
            binding.tvNombre.text = pregnant.name
            binding.tvSemanas.text = pregnant.gestationalAgeByFUM
            binding.iVGestante.setImageURI(Uri.parse(pregnant.photo))

        }
    }
}
private object DiffUtilCallback : DiffUtil.ItemCallback<PregnantUI>() {
    override fun areItemsTheSame(oldItem: PregnantUI, newItem: PregnantUI): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: PregnantUI, newItem: PregnantUI): Boolean {
        return (oldItem == newItem)
    }


}