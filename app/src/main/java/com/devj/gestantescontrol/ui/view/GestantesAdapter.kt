package com.devj.gestantescontrol.ui.view

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.core.createBitmapFromImageUri
import com.devj.gestantescontrol.core.getResizeBitmap
import com.devj.gestantescontrol.core.setImageFromBitmap
import com.devj.gestantescontrol.data.model.CalculadoraEg
import com.devj.gestantescontrol.databinding.GestanteItemBinding
import com.devj.gestantescontrol.domain.Gestante


class GestantesAdapter(private val fn: (gestante: Gestante) -> Unit) :
    ListAdapter<Gestante, GestantesAdapter.GestantesViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GestantesViewHolder {
        return GestantesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.gestante_item, parent, false)
        )

    }

    override fun onBindViewHolder(holder: GestantesViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { fn(getItem(position)) }
    }

    class GestantesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = GestanteItemBinding.bind(view)


        @SuppressLint("SetTextI18n")
        fun bind(gestante: Gestante) {
            with(binding) {
                try {
                    if (gestante.foto != "") {
                        setImageFromBitmap(
                            getResizeBitmap(
                                createBitmapFromImageUri(
                                    binding.root.context,
                                    Uri.parse(gestante.foto)
                                ),
                                100,
                                100,
                                true
                            ),
                            iVGestante
                        )
                    } else {
                        iVGestante.setImageResource(R.drawable.ic_baseline_person_pin_24)
                    }
                } catch (e: Exception) {
                    iVGestante.setImageResource(R.drawable.ic_baseline_person_pin_24)
                }
                tvNombre.text = "${gestante.nombre} ${gestante.apellidos}"
                tvSemanas.text = if (gestante.fumConfiable) {
                    CalculadoraEg(gestante.fum ?: "").calcularPorFUM()
                } else {
                    CalculadoraEg(
                        gestante.fug ?: "",
                        gestante.cantSemanasUG!!.toInt(),
                        gestante.cantDiasUG!!.toInt()
                    ).calcularPorUSG()
                }
            }
        }
    }
}

private object DiffUtilCallback : DiffUtil.ItemCallback<Gestante>() {
    override fun areItemsTheSame(oldItem: Gestante, newItem: Gestante): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: Gestante, newItem: Gestante): Boolean {
        return (oldItem == newItem)
    }


}