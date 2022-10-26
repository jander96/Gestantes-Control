package com.devj.gestantescontrol.framework.ui.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.core.getBitmapFromFile
import com.devj.gestantescontrol.domain.CalculadoraEG
import com.devj.gestantescontrol.databinding.GestanteItemBinding
import com.devj.gestantescontrol.domain.Gestante
import com.devj.gestantescontrol.framework.CalcDatesImpl
import com.devj.gestantescontrol.usescases.CalcularEGXFUMUseCase
import com.devj.gestantescontrol.usescases.CalcularEGXUGUseCase


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
                        iVGestante.setImageBitmap(
                            getBitmapFromFile(
                                binding.root.context,
                                gestante.foto
                            )
                        )
                    } else {
                        iVGestante.setImageResource(R.drawable.iconpregnantdefault)
                    }
                } catch (e: Exception) {
                    iVGestante.setImageResource(R.drawable.iconpregnantdefault)
                }
                tvNombre.text = "${gestante.nombre} ${gestante.apellidos}"
                tvSemanas.text = if (gestante.fumConfiable) {
                    CalcularEGXFUMUseCase(
                        CalculadoraEG(gestante.fum!!, calculadoraFechas = CalcDatesImpl())
                    ).calcularEGXFUM()

                } else {
                    CalcularEGXUGUseCase(
                        CalculadoraEG(
                            gestante.fug!!,
                            gestante.cantSemanasUG!!.toInt(),
                            gestante.cantDiasUG!!.toInt(),
                            CalcDatesImpl()
                        )
                    ).calcularEGXUG()

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