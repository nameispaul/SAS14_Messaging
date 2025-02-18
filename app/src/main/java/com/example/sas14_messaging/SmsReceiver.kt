package com.example.sas14_messaging

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.widget.Toast

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION == intent?.action) {
            for (message in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                val sender = message.displayOriginatingAddress
                val body = message.messageBody
                Toast.makeText(context, "Received from $sender: $body", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
