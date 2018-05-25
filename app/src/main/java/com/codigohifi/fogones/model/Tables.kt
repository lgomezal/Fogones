package com.codigohifi.fogones.model

import com.codigohifi.fogones.R

class Tables {

    private val tables: List<Table> = listOf(
            Table(0,"MESA 1", 4, R.drawable.tableimage, null),
            Table(1,"MESA 2", 6, R.drawable.tableimage, null),
            Table(2,"MESA 3", 5, R.drawable.tableimage, null),
            Table(3,"MESA 4", 4, R.drawable.tableimage, null),
            Table(4,"MESA 5", 2, R.drawable.tableimage, null),
            Table(5,"MESA 6", 2, R.drawable.tableimage, null),
            Table(6,"MESA 7", 8, R.drawable.tableimage, null),
            Table(7,"MESA 8", 5, R.drawable.tableimage, null),
            Table(8,"MESA 9", 4, R.drawable.tableimage, null),
            Table(9,"MESA 10", 12, R.drawable.tableimage, null)
    )

    val count
        get() = tables.size

    fun getTable(index: Int) = tables[index]

    operator fun get(index: Int) = tables[index]

    fun toArray() = tables.toTypedArray()

}