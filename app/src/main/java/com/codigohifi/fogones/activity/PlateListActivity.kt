package com.codigohifi.fogones.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.codigohifi.fogones.R
import com.codigohifi.fogones.fragment.PlateListFragment

class PlateListActivity : AppCompatActivity(), PlateListFragment.OnRecyclerViewClickedListener {

    val PLATE_SELECTED_REQUEST = 1

    companion object {

        val EXTRA_TABLE = "EXTRA_TABLE"

        fun intent(context: Context, tableIndex: Int): Intent {
            val intent = Intent(context, PlateListActivity::class.java)
            intent.putExtra(EXTRA_TABLE, tableIndex)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plate_list)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tableIndex:Int = intent.getIntExtra(PlateListActivity.EXTRA_TABLE, 0)

        // Creo si hace falta el PlateListFragment
        if (supportFragmentManager.findFragmentById(R.id.plate_list_fragment) == null) {
            // Hay hueco, y habiendo hueco metemos el fragment
            val fragment: PlateListFragment = PlateListFragment.newInstance(tableIndex)

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

    override fun onRecyclerClicked(tableIndex: Int, plateIndex: Int) {
        val intent = DetailPlateActivity.intent(this, plateIndex, tableIndex)
        startActivityForResult(intent, PLATE_SELECTED_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val tableIndex = data?.extras?.getInt(DetailPlateActivity.EXTRA_TABLE_INDEX)
        val result = Intent()
        result.putExtra(DetailPlateActivity.EXTRA_TABLE_INDEX, tableIndex)
        setResult(Activity.RESULT_OK, result)
    }

}
