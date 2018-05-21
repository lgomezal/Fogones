package com.codigohifi.fogones

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_bill.*

class BillActivity : AppCompatActivity() {

    companion object {

        fun intent(context: Context): Intent {
            val intent = Intent(context, BillActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill)

        ok_btn.setOnClickListener { acceptBill() }
        cancel_btn.setOnClickListener { cancelBill() }

        paid_rg.check(R.id.no_paid_rb)

    }

    private fun acceptBill() {
        finish()
    }

    private fun cancelBill() {
        finish()
    }


}
