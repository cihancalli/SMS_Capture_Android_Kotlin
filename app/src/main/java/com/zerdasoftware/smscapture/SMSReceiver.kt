package com.zerdasoftware.smscapture

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import android.widget.Toast

class SMSReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val b = intent?.extras

        if (b!=null){
            val pdusObj = b.get("pdus") as Array<Any>
            for (i in pdusObj.indices){
                val currentMessage:SmsMessage
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    val format = b.getString("format")
                    currentMessage = SmsMessage.createFromPdu(pdusObj[i] as ByteArray,format)
                }else{
                    currentMessage = SmsMessage.createFromPdu(pdusObj[i] as ByteArray)
                }

                val phoneNumber = currentMessage.displayOriginatingAddress
                val message = currentMessage.displayMessageBody
            }
        }
        Toast.makeText(context,"SMS Received",Toast.LENGTH_LONG).show()
    }
}