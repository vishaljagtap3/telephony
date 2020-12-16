package in.bitcode.telephony;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SmsManager smsManager;

    BroadcastReceiver brSms = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals("in.bitcode.demo.sms.SENT")) {
                Log.e("tag", "message sent!");
            }
            else {
                Log.e("tag", "message delivered!");
            }

        }
    };

    @SuppressLint({"MissingPermission", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager =
                (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);



        mt( "Call State: " + telephonyManager.getCallState() );
        mt("NW Type: " + telephonyManager.getNetworkType());
        mt("Data Network: " + telephonyManager.getDataNetworkType());
//        mt("Imei: " + telephonyManager.getImei());
        mt("Operator: " + telephonyManager.getNetworkOperator());
        mt("Data Act: " + telephonyManager.getDataActivity());
        mt("Data State: " + telephonyManager.getDataState());
        mt("line 1 number: " + telephonyManager.getLine1Number());
        //mt("Sim Sr. No: " + telephonyManager.getSimSerialNumber() );
        mt("Phone type: " + telephonyManager.getPhoneType() );

        PhoneStateListener listener = new PhoneStateListener();
        telephonyManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
        telephonyManager.listen(listener, PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);
        telephonyManager.listen(listener, PhoneStateListener.LISTEN_USER_MOBILE_DATA_STATE);


        //SmsManager.getDefault().sendMultimediaMessage();
        /*
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("in.bitcode.demo.sms.SENT");
        intentFilter.addAction("in.bitcode.demo.sms.DELIVERED");
        registerReceiver(brSms, intentFilter);


        PendingIntent sentIntent = PendingIntent.getBroadcast(
                this,
                0,
                new Intent("in.bitcode.demo.sms.SENT"),
                0
        );
        PendingIntent deliveredIntent = PendingIntent.getBroadcast(
                this,
                0,
                new Intent("in.bitcode.demo.sms.DELIVERED"),
                0
        );

        smsManager = SmsManager.getDefault();

        String [] numbers = {
                "7447297382",
                "9325277424",
                "9156327899",
                "9552050723",
                "9011488100"
        };

        for(String num : numbers) {
            smsManager.sendTextMessage(
                    num,
                    null,
                    "Hello from Android!",
                    sentIntent,
                    deliveredIntent
            );
        }

        smsManager.sendMultipartTextMessage();
        */
    }

    class MyPhoneStateListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String phoneNumber) {
            super.onCallStateChanged(state, phoneNumber);
            mt("Call state change: " + state + " " + phoneNumber);
        }

        @Override
        public void onDataConnectionStateChanged(int state) {
            super.onDataConnectionStateChanged(state);
            mt("Data Connection State: " + state);
        }

        @Override
        public void onUserMobileDataStateChanged(boolean enabled) {
            super.onUserMobileDataStateChanged(enabled);
            mt("user mobile Data Connection State: " + enabled);
        }
    }

    private void mt(String text) {
        Log.e("tag", text);
    }
}