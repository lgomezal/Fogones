package com.codigohifi.fogones.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Button
import com.codigohifi.fogones.R
import com.codigohifi.fogones.model.Table
import kotlinx.android.synthetic.main.content_table.*

class TableFragment: Fragment() {

    companion object {

        val ARG_TABLE = "ARG_TABLE"

        fun newInstance(table: Table): Fragment {
            // Nos creamos nuestro fragment
            val fragment = TableFragment()

            // Nos creamos los argumentos al fragment
            val arguments = Bundle()
            arguments.putSerializable(ARG_TABLE, table)

            // Asignamos los argumentos al fragment
            fragment.arguments = arguments

            // Devolvemos el fragment
            return fragment
        }

    }

    lateinit var root: View
    val button by lazy { root.findViewById<Button>(R.id.addPlate) }

    private var onAddButtonClickedListener: OnAddButtonClickedListener? = null

    private enum class VIEW_INDEX(val index: Int) {
        LOADING(0), TABLE(1)
    }

    var table: Table? = null
        set(value) {
            if (value != null) {
                table_image?.setImageResource(value.icon)
                table_description?.text = value.description
                persons?.text = getString(R.string.persons_format, value.persons)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        root = inflater.inflate(R.layout.fragment_table, container, false)

        button.setOnClickListener {
            onAddButtonClickedListener?.onAddButtonClicked(arguments!!.getSerializable(ARG_TABLE) as Table)
        }

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            table = arguments!!.getSerializable(ARG_TABLE) as Table
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context as? Activity)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    fun commonAttach(activity: Activity?) {
        if (activity is OnAddButtonClickedListener) {
            onAddButtonClickedListener = activity
        }
        else {
            onAddButtonClickedListener = null
        }
    }

    override fun onDetach() {
        super.onDetach()
        onAddButtonClickedListener = null
    }

    interface OnAddButtonClickedListener {
        fun onAddButtonClicked(table: Table)
    }
}