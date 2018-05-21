package com.codigohifi.fogones

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.table_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_show_bill -> {
                // Lanzamos la pantalla de la cuenta
                startActivity(BillActivity.intent(this))

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
