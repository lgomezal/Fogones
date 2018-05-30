package com.codigohifi.fogones.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.codigohifi.fogones.R
import com.codigohifi.fogones.model.Plates
import kotlinx.android.synthetic.main.fragment_plate.*

class DetailPlateActivity : AppCompatActivity() {

    companion object {

        val EXTRA_PLATE_INDEX = "EXTRA_PLATE_INDEX"

        fun intent(context: Context, plateIndex: Int): Intent {
            val intent = Intent(context, DetailPlateActivity::class.java)
            intent.putExtra(EXTRA_PLATE_INDEX, plateIndex)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_plate)

        // Sacamos los datos con los que configurar la interfaz
        val plate = Plates[intent.getIntExtra(EXTRA_PLATE_INDEX, 0)]

        // Actualizamos la interfaz
        plate_image.setImageResource(plate.plateImage)
        plate_type.text = plate.typeOfPlate
        plate_price.text = getString(R.string.plate_price_format, plate.price)
        plate_description.text = plate.description

    }

}
