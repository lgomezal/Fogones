package com.codigohifi.fogones.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.codigohifi.fogones.R
import com.codigohifi.fogones.fragment.TableListFragment
import com.codigohifi.fogones.fragment.TablePagerFragment
import com.codigohifi.fogones.model.Table

class TableActivity : AppCompatActivity(), TableListFragment.OnTableSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        // Averiguamos que interfaz hemos cargado
        // Eso lo averiguamos preguntando si en la interfaz tenemos un Framlayout concreto
        if (findViewById<ViewGroup>(R.id.table_list_fragment) != null) {
            // Hemos cargado una interfaz que tiene hueco para el fragment TableListFragment
            // Comprobamos primero que no tenemos ya añadido el fragment a nuestra jerarquía
            if (supportFragmentManager.findFragmentById(R.id.table_list_fragment) == null) {

                // Añadimos el fragment de forma dinámica
                val fragment: TableListFragment = TableListFragment.newInstance()

                supportFragmentManager.beginTransaction()
                        .add(R.id.table_list_fragment, fragment)
                        .commit()
            }
        }

        if (findViewById<ViewGroup>(R.id.view_pager_fragment) != null) {
            // Hemos cargado una interfaz que tiene el hueco para el TablePagerFragment
            if (supportFragmentManager.findFragmentById(R.id.view_pager_fragment) == null) {
                supportFragmentManager.beginTransaction()
                        .add(R.id.view_pager_fragment, TablePagerFragment.newInstance(0))
                        .commit()
            }
        }
    }

    override fun onTableSelected(table: Table, position: Int) {
        val intent = TablePagerActivity.intent(this, position)
        startActivity(intent)
    }

}
