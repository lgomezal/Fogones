package com.codigohifi.fogones

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import kotlinx.android.synthetic.main.fragment_table.*

class TableFragment: Fragment() {

    var table: Table? = null
        set(value) {
            if (value != null) {
                table_image.setImageResource(value.icon)
                table_description.text = value.description
                persons.text = getString(R.string.persons_format, value.persons)
                // TODO sumar del listview los importes de los platos que llevan elegidos
                billText.text = getString(R.string.bill_format, 145.75F)
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_table, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Creamos la mesa en dummy
        table = Table(
                1,
                "MESA 1",
                4,
                R.drawable.tableimage,
                null)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.table_activity, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_show_bill -> {

                val description = table_description.text.toString()
                val netBill: Float = 145.75F
                // Lanzamos la pantalla de la cuenta
                startActivity(BillActivity.intent( context!!, description, netBill))

                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }

}