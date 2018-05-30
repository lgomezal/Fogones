package com.codigohifi.fogones.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.codigohifi.fogones.R
import com.codigohifi.fogones.model.Plate

class PlateRecyclerViewAdapter(private val plates: List<Plate>): RecyclerView.Adapter<PlateRecyclerViewAdapter.PlateViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_plate, parent, false)

        // Le decimos a este view que cuando lo pulsen avise a nuestro onClickListener
        view.setOnClickListener(onClickListener)

        return PlateViewHolder(view)
    }

    override fun getItemCount() = plates.size

    override fun onBindViewHolder(holder: PlateViewHolder, position: Int) {
        holder.bindPlate(plates[position])
    }

    inner class PlateViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val plateImage = itemView.findViewById<ImageView?>(R.id.plate_image)
        val plateType = itemView.findViewById<TextView?>(R.id.plate_type)
        val platePrice = itemView.findViewById<TextView?>(R.id.plate_price)
        val plateDescription = itemView.findViewById<TextView?>(R.id.plate_description)
        val context = itemView.context

        fun bindPlate(plate: Plate) {
            // Actualizamos la vista con el modelo
            plateImage?.setImageResource(plate.plateImage)
            plateType?.text = plate.typeOfPlate
            platePrice?.text = context.getString(R.string.plate_price_format, plate.price)
            plateDescription?.text = plate.description
        }

    }

}