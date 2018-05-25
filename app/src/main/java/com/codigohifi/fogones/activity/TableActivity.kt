package com.codigohifi.fogones.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.codigohifi.fogones.R
import com.codigohifi.fogones.fragment.TableListFragment

class TableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        // Comprobamos primero que no tenemos ya añadido el fragment a nuestra jerarquía
        if (supportFragmentManager.findFragmentById(R.id.table_list_fragment) == null) {

            // Añadimos el fragment de forma dinámica
            val fragment: TableListFragment = TableListFragment.newInstance()

            supportFragmentManager.beginTransaction()
                    .add(R.id.table_list_fragment, fragment)
                    .commit()
        }

    }

}
