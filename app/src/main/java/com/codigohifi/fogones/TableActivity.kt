package com.codigohifi.fogones

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tables.*

class TableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables)

        // Creamos las mesas en dummy
        val table1 = Table(
                1,
                "MESA 1",
                4,
                false,
                 R.drawable.tableimage,
                null)

        setTable(table1)

    }

    fun setTable(table: Table) {
        table_image.setImageResource(table.icon)
        table_description.text = table.description
        persons.text = getString(R.string.persons_format, table.persons)

        var paidString = "NO"
        if (table.paidOut == true) {
            paidString = "SI"
        }
        paidOut.text = getString(R.string.paidOut_format, paidString)
        bill.text = getString(R.string.bill_format, 100)

    }
}
