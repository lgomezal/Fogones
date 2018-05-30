package com.codigohifi.fogones.model

import com.codigohifi.fogones.R

object Plates {

    private val plates: List<Plate> = listOf(
            Plate(0, "Entrante", "Rollitos de salmón con queso Philadelphia",
                    7.00F, null, R.drawable.plato0,
                    "Rollitos de salmón ahumado noruego sobre base de queso Philadelphia y tosta integral con toques de eneldo"),
            Plate(1, "Entrante", "Hojaldres de atún con tomate",
                    6.50F, null, R.drawable.plato1,
                    "Pequeños hojaldres de atún con tomate frito natural horneados en su punto acompañados de salsa de queso suave"),
            Plate(2, "Entrante", "Tartaletas de pisto horneadas",
                    5.00F, null, R.drawable.plato2,
                    "Pequeñas tartaletas de masa brie rellenas de pisto manchego con tomate, berenjenas, cebolla y calabacín"),
            Plate(3, "Primero", "Lentejas estofadas con chorizo",
                    5.50F, null, R.drawable.plato3,
                    "Lentejas estodafas con chorizo y lacón gallego arregladas con pimentón de Candeleda"),
            Plate(4, "Primero", "Ensalada Cesar",
                    7.00F, null, R.drawable.plato4,
                    "Ensalada Cesar con lechuga, tomate, huevo cocido, pollo a la brasa, queso, picatostes de pan y su famosa salsa"),
            Plate(5, "Primero", "Tallarines con langostimos",
                    9.00F, null, R.drawable.plato5,
                    "Tallarines con langostinos y trazas de espinacas con un toque de guindilla"),
            Plate(6, "Segundo", "Bacalao caramelizado con ensalada",
                    15.00F, null, R.drawable.plato6,
                    "Lomo de bacalao caramelizado al horno acompañado de ensalada de canónigos, escarola y tomate"),
            Plate(7, "Segundo", "Brochetas de pollo de corral con piña",
                    14.00F, null, R.drawable.plato7,
                    "Brochetas de pollo de corral a la brasa con piña natural"),
            Plate(8, "Segundo", "Solomillo ibérico con foie",
                    18.00F, null, R.drawable.plato8,
                    "Solomillo de cerdo ibérico a la plancha con foie de oca en su propio jugo"),
            Plate(9, "Postre", "Tarta de moras rojas",
                    5.00F, null, R.drawable.plato9,
                    "Tarta de moras silvestres en tartaleta de fino hojaldre con crema catalana"),
            Plate(10, "Postre", "Helado de vino tinto",
                    4.50F, null, R.drawable.plato10,
                    "Helado de vino tinto de Ribera del Duero Crianza"),
            Plate(11, "Postre", "Brocheta de fruta preparada",
                    4.00F, null, R.drawable.plato11,
                    "Brocheta de fruta natural con fresas, piña, ciruelas y kiwi")
    )

    val count
        get() = plates.size

    fun getPlate(index: Int) = plates[index]

    operator fun get(index: Int) = plates[index]

    fun toArray() = plates.toTypedArray()
}