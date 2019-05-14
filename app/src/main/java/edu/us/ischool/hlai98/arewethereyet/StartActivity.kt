package edu.us.ischool.hlai98.arewethereyet

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class StartActivity : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val msg = p1!!.getStringExtra("msg")
        val phone = p1!!.getStringExtra("phone")
        val message = "(" + phone.substring(0..2) + ")" + phone.substring(3..5) + "-" + phone.substring(6)
        Toast.makeText(p0, "$message : $msg", Toast.LENGTH_LONG).show()
    }
}