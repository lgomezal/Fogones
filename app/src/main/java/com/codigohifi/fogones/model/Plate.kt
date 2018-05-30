package com.codigohifi.fogones.model

import android.widget.ImageView
import java.io.Serializable

class Plate (val plateNumber: Int, val typeOfPlate: String, val description: String, val price: Float, val icons: Array<Int>?, var plateImage: Int, val largeDesc: String): Serializable {

    override fun toString() = description
}