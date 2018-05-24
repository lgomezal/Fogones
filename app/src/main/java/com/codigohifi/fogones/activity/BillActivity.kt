package com.codigohifi.fogones.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.codigohifi.fogones.R
import kotlinx.android.synthetic.main.activity_bill.*

class BillActivity : AppCompatActivity() {

    companion object {

        val EXTRA_TABLE_DES = "EXTRA_TABLE_DES"
        val EXTRA_NET_BILL = "EXTRA_NET_BILL"

        fun intent(context: Context, tableDescription: String, netBill: Float): Intent {
            val intent = Intent(context, BillActivity::class.java)

            intent.putExtra(EXTRA_TABLE_DES, tableDescription)
            intent.putExtra(EXTRA_NET_BILL, netBill)

            return intent
        }
    }

    val tableDescription by lazy {
        intent.getStringExtra(EXTRA_TABLE_DES)
    }

    val netBill by lazy {
        intent.getFloatExtra(EXTRA_NET_BILL, 0.0F)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill)

        ok_btn.setOnClickListener { acceptBill() }

        // Cargamos los textviews
        tableBillText.text = getString(R.string.bill_text, tableDescription)
        netBillText.text = getString(R.string.net_bill_text, netBill)
        val iva = (netBill * 10) / 100
        taxBillText.text = getString(R.string.tax_bill_text, iva)
        val total = netBill + iva
        totalBillText.text = getString(R.string.total_bill_text, total)

    }

    private fun acceptBill() {
        finish()
    }

}
