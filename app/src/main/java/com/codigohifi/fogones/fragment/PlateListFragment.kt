package com.codigohifi.fogones.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.R.id.container
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.codigohifi.fogones.R
import com.codigohifi.fogones.activity.DetailPlateActivity
import com.codigohifi.fogones.adapter.PlateRecyclerViewAdapter
import com.codigohifi.fogones.model.Plate
import com.codigohifi.fogones.model.Plates
import kotlinx.android.synthetic.main.fragment_plate_list.*

class PlateListFragment: Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = PlateListFragment()

    }

    var plates: List<Plate>? = null
        set(value) {
            if (value != null) {
                val adapter = PlateRecyclerViewAdapter(value)
                // Si alguien pulsa una elemento, nos queremos enterar
                adapter.onClickListener = View.OnClickListener {
                    //Alguien ha pulsado un elemento del RecyclerView
                    val plateIndex = plate_list.getChildAdapterPosition(it)

                    startActivity(DetailPlateActivity.intent(activity!!, plateIndex))
                }

                plate_list.adapter = adapter
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plate_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Cargamos los valores fake de Plates
        val arrayPlates = Plates.toArray()
        plates = arrayPlates.toList()

        // Configuramos el RecyclerView
        // Primero decimos como se visualizan sus elementos
        plate_list.layoutManager = GridLayoutManager(activity, resources.getInteger(R.integer.plate_columns).toInt())

        // Le decimos quién es el que anima el RecyclerView
        plate_list.itemAnimator = DefaultItemAnimator()

    }

}
