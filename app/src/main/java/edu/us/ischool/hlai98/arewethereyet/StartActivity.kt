package edu.us.ischool.hlai98.arewethereyet

import android.app.IntentService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast

class StartActivity : IntentService("StartActivity") {

   // override fun onReceive(p0: Context?, p1: Intent?) {
      //  Log.i(TAG, "start sub")
      //  val msg = p1!!.getStringExtra("msg")
      //  val phone = p1!!.getStringExtra("phone")
      //  val message = "(" + phone.substring(0..2) + ")" + phone.substring(3..5) + "-" + phone.substring(6)
       // Toast.makeText(p0, "$message : $msg", Toast.LENGTH_LONG).show()
   // }
    private val myHandle = Handler()
    private var again = true

    override fun onDestroy() {
        again = false
        super.onDestroy()
    }
    override fun onHandleIntent(p0: Intent?) {
        var time : Int
        var msg: String
        var phone : String
        p0?.extras.apply{
            time = this!!.getInt("time")
            msg = this!!.getString("msg")
            phone = this!!.getString("phone")
            phone = phone.substring(0..2) + ")" + phone.substring(3..5) + "-" + phone.substring(6)
        }
        while (again) {
            myHandle.post {
                try {
                    Toast.makeText(this@StartActivity, "Sending", Toast.LENGTH_LONG).show()
                    val managerSMS = SmsManager.getDefault() as SmsManager
                    managerSMS.sendTextMessage(phone,null,msg,null,null)
                    Toast.makeText(this@StartActivity, "SMS sent to $phone ($msg)", Toast.LENGTH_LONG).show()
                } catch (e:Exception) {
                    Toast.makeText(this@StartActivity, "SMS failed to send.", Toast.LENGTH_LONG).show()
                }
            }
            try {
                Thread.sleep((time*60*1000).toLong())
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
    }

}