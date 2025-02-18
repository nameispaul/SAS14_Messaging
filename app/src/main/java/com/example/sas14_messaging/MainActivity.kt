package com.example.sas14_messaging

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val etPhoneNumber = findViewById<EditText>(R.id.etPhoneNumber)
        val btnSendSMS = findViewById<Button>(R.id.btnSendSMS)
        
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 1)
        }

        btnSendSMS.setOnClickListener {
            val name = etName.text.toString().trim()
            val phoneNumber = etPhoneNumber.text.toString().trim()

            if (name.isEmpty() || phoneNumber.isEmpty()) {
                Toast.makeText(this, "Please enter name and phone number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val message = "Mabuhay $name!"

            try {
                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(phoneNumber, null, message, null, null)
                Toast.makeText(this, "SMS Sent Successfully!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Failed to send SMS: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
