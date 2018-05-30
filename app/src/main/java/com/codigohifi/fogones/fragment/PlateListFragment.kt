package com.codigohifi.fogones.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.codigohifi.fogones.R
import com.codigohifi.fogones.model.Plate
import com.codigohifi.fogones.model.Plates
import com.codigohifi.fogones.model.Table
import com.codigohifi.fogones.model.Tables
import kotlinx.android.synthetic.main.fragment_plate_list.*
import kotlinx.android.synthetic.main.fragment_table_list.*

class PlateListFragment: Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = PlateListFragment()

    }

    private var onPlateSelectedListener: OnPlateSelectedListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plate_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter<Plate>(
                activity,
                android.R.layout.simple_list_item_1,
                Plates.toArray())

        plate_list.adapter = adapter

        plate_list.setOnItemClickListener { _, _, position, _ ->
            // Avisamos al listener que un plato ha sido seleccionado
            onPlateSelectedListener?.onPlateSelected(Plates[position], position)
        }
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
