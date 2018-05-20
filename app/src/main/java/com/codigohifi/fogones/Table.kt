package com.codigohifi.fogones

data class Table(val tableNumber: Int, val description: String, val persons: Int, val paidOut: Boolean, val icon: Int, var plates: Array<String>?) {
}