package com.codigohifi.fogones.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.codigohifi.fogones.R
import com.codigohifi.fogones.fragment.PlateListFragment
import com.codigohifi.fogones.fragment.TableFragment
import com.codigohifi.fogones.fragment.TableListFragment
import com.codigohifi.fogones.fragment.TablePagerFragment
import com.codigohifi.fogones.model.Plate
import com.codigohifi.fogones.model.Table
import com.codigohifi.fogones.model.Tables
import kotlinx.android.synthetic.main.fragment_table.*

class TableActivity : AppCompatActivity(), TableListFragment.OnTableSelectedListener, TableFragment.OnAddButtonClickedListener {

    val PLATE_SELECTED_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        // Averiguamos que interfaz hemos cargado
        // Eso lo averiguamos preguntando si en la interfaz tenemos un Framelayout concreto
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
        val tablePagerFragment = supportFragmentManager.findFragmentById(R.id.view_pager_fragment) as? TablePagerFragment

        if (tablePagerFragment != null) {
            // Estamos en una interfaz donde existe TablePagerFragment, le decimos que nos mueva a una mesa
            tablePagerFragment.moveToTable(position)
        }
        else {
            val intent = TablePagerActivity.intent(this, position)
            startActivity(intent)
        }

    }

    override fun onAddButtonClicked(table: Table) {
        val tableIndex = table.tableNumber
        val plateListFragment = supportFragmentManager.findFragmentById(R.id.plate_list_fragment) as? PlateListFragment
        if (plateListFragment == null) {
            val intent = PlateListActivity.intent(this, tableIndex)
            startActivityForResult(intent, PLATE_SELECTED_REQUEST)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PLATE_SELECTED_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                val tableIndex = data?.extras?.getInt(DetailPlateActivity.EXTRA_TABLE_INDEX)
                if (tableIndex != null) {
                    val tablePagerFragment = supportFragmentManager.findFragmentById(R.id.view_pager_fragment) as? TablePagerFragment
                    if (tablePagerFragment != null) {
                        // Estamos en una interfaz donde existe TablePagerFragment, le decimos que nos mueva a una mesa
                        tablePagerFragment.moveToTable(tableIndex)
                    }
                }
            }
        }
    }
}
