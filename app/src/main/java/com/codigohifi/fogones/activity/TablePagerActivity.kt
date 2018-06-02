package com.codigohifi.fogones.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.codigohifi.fogones.R
import com.codigohifi.fogones.fragment.PlateListFragment
import com.codigohifi.fogones.fragment.TableFragment
import com.codigohifi.fogones.fragment.TablePagerFragment
import kotlinx.android.synthetic.main.activity_table_pager.*

class TablePagerActivity : AppCompatActivity() {

    companion object {

        val EXTRA_TABLE = "EXTRA_TABLE"

        fun intent(context: Context, tableIndex: Int): Intent {
            val intent = Intent(context, TablePagerActivity::class.java)
            intent.putExtra(EXTRA_TABLE, tableIndex)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_pager)

        //toolbar.setLogo(R.mipmap.ic_launcher)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val initialTableIndex:Int = intent.getIntExtra(EXTRA_TABLE, 0)

        // Creo si hace falta el TablePagerFragment pasándole la mesa inicial
        if (supportFragmentManager.findFragmentById(R.id.view_pager_fragment) == null) {
            // Hay hueco, y habiendo hueco metemos el fragment
            val fragment: TablePagerFragment = TablePagerFragment.newInstance(initialTableIndex)
            supportFragmentManager.beginTransaction()
                    .add(R.id.view_pager_fragment, fragment)
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
