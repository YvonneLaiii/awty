package edu.us.ischool.hlai98.arewethereyet

import android.app.AlarmManager
import android.app.AlarmManager.RTC_WAKEUP
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn_start)
        val msg = findViewById<EditText>(R.id.message)
        val phone = findViewById<EditText>(R.id.phone)
        val minutes = findViewById<EditText>(R.id.minutes)
        val alarmManager: AlarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this@MainActivity, StartActivity::class.java)

        msg.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })
        phone.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })
        minutes.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })
        btn.setOnClickListener{
            if (!(msg.text.isNotEmpty() && phone.text.isNotEmpty() && minutes.text.isNotEmpty())) {
                Toast.makeText(this@MainActivity, "Please fill all the information!", Toast.LENGTH_SHORT).show()
            } else if (!(minutes.text.toString().toInt() > 0)) {
                Toast.makeText(this@MainActivity, "Please enter valid minute value (integers that are greater than 0)", Toast.LENGTH_SHORT).show()
            } else if (!(phone.text.toString().length == 10)) {
                Toast.makeText(this@MainActivity, "Please enter valid phone number!", Toast.LENGTH_SHORT).show()
            } else {
                if (btn.text == "Start") {
                    btn.text = "Stop"
                    intent.putExtra("msg", msg.text.toString())
                    intent.putExtra("phone", phone.text.toString())
                    val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, intent,PendingIntent.FLAG_CANCEL_CURRENT)
                    alarmManager!!.setRepeating(RTC_WAKEUP, System.currentTimeMillis(),(minutes.text.toString().toLong() * 60 * 1000), pendingIntent)
                } else {
                    btn.text = "Start"
                    alarmManager!!.cancel(PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT))
                }
            }
        }
    }

}
