package com.codigohifi.fogones.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.R.id.container
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.codigohifi.fogones.R
import com.codigohifi.fogones.adapter.PlateRecyclerViewAdapter
import com.codigohifi.fogones.model.Plate
import com.codigohifi.fogones.model.Plates
import kotlinx.android.synthetic.main.fragment_plate_list.*

class PlateListFragment: Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = PlateListFragment()

    }

    private var onPlateSelectedListener: OnPlateSelectedListener? = null

    var plates: List<Plate>? = null
        set(value) {
            if (value != null) {
                plate_list.adapter = PlateRecyclerViewAdapter(value)
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plate_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuramos el RecyclerView
        // Primero decimos como se visualizan sus elementos
        plate_list.layoutManager = LinearLayoutManager(activity)

        // Le decimos quién es el que anima el RecyclerView
        plate_list.itemAnimator = DefaultItemAnimator()

        val arrayPlates = Plates.toArray()
        plates = arrayPlates.toList()

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context as Activity)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity as Activity)
    }

    private fun commonAttach(activity: Activity) {
        if (activity is OnPlateSelectedListener) {
            onPlateSelectedListener = activity
        }
        else {
            onPlateSelectedListener = null
        }

    }

    override fun onDetach() {
        super.onDetach()
        onPlateSelectedListener = null
    }

    interface OnPlateSelectedListener {
        fun onPlateSelected(table: Plate, position: Int)
    }

}
