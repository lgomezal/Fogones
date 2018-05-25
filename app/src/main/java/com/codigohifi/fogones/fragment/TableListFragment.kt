package com.codigohifi.fogones.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.codigohifi.fogones.R
import com.codigohifi.fogones.model.Table
import com.codigohifi.fogones.model.Tables
import kotlinx.android.synthetic.main.fragment_table_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [TableListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class TableListFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = TableListFragment()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tables = Tables()

        val adapter = ArrayAdapter<Table>(
                activity,
                android.R.layout.simple_list_item_1,
                tables.toArray())

        table_list.adapter = adapter
    }


}
