package com.codigohifi.fogones.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.codigohifi.fogones.R
import com.codigohifi.fogones.fragment.PlateListFragment
import com.codigohifi.fogones.fragment.TablePagerFragment
import kotlinx.android.synthetic.main.activity_table_pager.*

class PlateListActivity : AppCompatActivity() {

    companion object {

        fun intent(context: Context): Intent {
            val intent = Intent(context, PlateListActivity::class.java)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plate_list)

        // Creo si hace falta el PlateListFragment
        if (supportFragmentManager.findFragmentById(R.id.plate_list_fragment) == null) {
            // Hay hueco, y habiendo hueco metemos el fragment
            val fragment: PlateListFragment = PlateListFragment.newInstance()

                supportFragmentManager.beginTransaction()
                        .add(R.id.plate_list_fragment, fragment)
                        .commit()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            // Nos han llamado a la flecha de back
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
