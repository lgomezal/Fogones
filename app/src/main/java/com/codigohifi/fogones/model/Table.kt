package com.codigohifi.fogones.model

import java.io.Serializable

data class Table(val tableNumber: Int, val description: String, val persons: Int, val icon: Int, var plates: Array<String>?): Serializable {
}