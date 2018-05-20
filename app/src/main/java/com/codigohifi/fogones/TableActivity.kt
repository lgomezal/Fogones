package com.codigohifi.fogones

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tables.*

class TableActivity : AppCompatActivity() {

    var table: Table? = null
        set(value) {
            if (value != null) {
                table_image.setImageResource(value.icon)
                table_description.text = value.description
                persons.text = getString(R.string.persons_format, value.persons)

                var paidString = "NO"
                if (value.paidOut == true) {
                    paidString = "SI"
                }
                paidOut.text = getString(R.string.paidOut_format, paidString)
                bill.text = getString(R.string.bill_format, 100)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables)

        // Creamos las mesas en dummy
        table = Table(
                1,
                "MESA 1",
                4,
                false,
                 R.drawable.tableimage,
                null)


    }

}
