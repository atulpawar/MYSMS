package tutorialspoint.example.com.myapplication;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;

import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    Button reachedBtn;
    Button startingBtn;
    Button lateBtn;
    Button sendBtn;
    EditText txtphoneNo;
    EditText txtMessage;
    String phoneNo;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startingBtn = (Button) findViewById(R.id.btnStartingSMS);
        reachedBtn = (Button) findViewById(R.id.btnReachedSMS);
        lateBtn = (Button) findViewById(R.id.btnLate);
        txtphoneNo = (EditText) findViewById(R.id.editText);
        txtMessage = (EditText) findViewById(R.id.editText2);

        startingBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendStartingSMSMessage();
            }
        });

        reachedBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendReachedSMSMessage();
            }
        });

        lateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendLateSMSMessage();
            }
        });
    }

    protected void sendStartingSMSMessage() {
        phoneNo = txtphoneNo.getText().toString();
        message = txtMessage.getText().toString();
        if("".equalsIgnoreCase(message) || message == null)
        message = "Starting from office, let me know you if I need to bring anything at home....";

      //  if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
   //         if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
   //         } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
   //         }
     //   }
    }

    protected void sendReachedSMSMessage() {
        phoneNo = txtphoneNo.getText().toString();
        message = txtMessage.getText().toString();
        if("".equalsIgnoreCase(message) || message == null)
        message = "Reached to office ...";

        //  if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
        //         if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
        //         } else {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
        //         }
        //   }
    }

    protected void sendLateSMSMessage() {
        phoneNo = txtphoneNo.getText().toString();
        message = txtMessage.getText().toString();
        if("".equalsIgnoreCase(message) || message == null)
            message = "I will be coming late today, will let you know as soon as I start ...";

        //  if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
        //         if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
        //         } else {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
        //         }
        //   }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
}