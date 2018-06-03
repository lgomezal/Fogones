package com.codigohifi.fogones.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.codigohifi.fogones.R
import com.codigohifi.fogones.model.Plates
import com.codigohifi.fogones.model.Tables
import kotlinx.android.synthetic.main.activity_detail_plate.*
import kotlinx.android.synthetic.main.fragment_plate.*

class DetailPlateActivity : AppCompatActivity() {

    companion object {

        val EXTRA_PLATE_INDEX = "EXTRA_PLATE_INDEX"
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun intent(context: Context, plateIndex: Int, tableIndex: Int): Intent {
            val intent = Intent(context, DetailPlateActivity::class.java)
            intent.putExtra(EXTRA_PLATE_INDEX, plateIndex)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_plate)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        addButton.setOnClickListener {
            val table = Tables[intent.getIntExtra(EXTRA_TABLE_INDEX, 0)]
            val plate = Plates[intent.getIntExtra(EXTRA_PLATE_INDEX, 0)]
            val tableIndex = intent.getIntExtra(EXTRA_TABLE_INDEX, 0)
            table.plates.add(plate)

            val result = Intent()
            result.putExtra(EXTRA_TABLE_INDEX, tableIndex)
            setResult(Activity.RESULT_OK, result)

            finish()
        }

        // Sacamos los datos con los que configurar la interfaz
        val plate = Plates[intent.getIntExtra(EXTRA_PLATE_INDEX, 0)]

        // Actualizamos la interfaz
        plate_image.setImageResource(plate.plateImage)
        plate_type.text = plate.typeOfPlate
        plate_price.text = getString(R.string.plate_price_format, plate.price)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            plate_description.text = plate.largeDesc
        } else {
            plate_description.text = plate.description
            plate_long_description.text = plate.largeDesc
        }

        // Recorremos el array de los alérgenos

        val array = plate.icons

        if (array != null) {
            var index = 0
            for (item in array) {
                when (index) {
                    0 -> alergen_0.setImageResource(plate.icons[index])
                    1 -> alergen_1.setImageResource(plate.icons[index])
                    2 -> alergen_2.setImageResource(plate.icons[index])
                    3 -> alergen_3.setImageResource(plate.icons[index])
                    4 -> alergen_4.setImageResource(plate.icons[index])
                    5 -> alergen_5.setImageResource(plate.icons[index])
                }
                index += 1
            }
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
