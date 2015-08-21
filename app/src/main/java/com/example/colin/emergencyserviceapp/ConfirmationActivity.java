package com.example.colin.emergencyserviceapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ConfirmationActivity extends ActionBarActivity
{
    TextView confirmServiceView;
    TextView confirmLocationView;
    TextView confirmDescriptionView;
    private AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        setTitle(R.string.confirmation_title);

        builder = new AlertDialog.Builder(ConfirmationActivity.this);
        builder.setTitle("Final Confirmation");
        builder.setMessage("Your Emergency Service Request will now be sent. We will send you a confirmation message once we receive your request, please allow up to 1 minute for a response.");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                try {
                    final String phoneNo = "07901550734";
                    final String message = SMSObject.getService() + ", " + SMSObject.getLocation() + ", " + SMSObject.getDescription();
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    System.exit(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                System.exit(0);
            }
        });

        Log.i("SMSObject", "Confirmation activity received service: " + Integer.toString(SMSObject.getService()));
        Log.i("SMSObject", "Confirmation activity received location: " + (SMSObject.getLocation()));
        Log.i("SMSObject", "Confirmation activity received description: " + (SMSObject.getDescription()));
        confirmServiceView = (TextView) findViewById(R.id.confirmServiceText);
        confirmLocationView = (TextView) findViewById(R.id.confirmLocationText);
        confirmDescriptionView = (TextView) findViewById(R.id.confirmDescriptionText);
        confirmDescriptionView.setMovementMethod(new ScrollingMovementMethod());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_confirmation, menu);
        int serviceEnum = SMSObject.getService();
        if (serviceEnum == 1)
        {
            confirmServiceView.setText("Police");
        }
        else if (serviceEnum == 2)
        {
            confirmServiceView.setText("Ambulance");
        }
        if (serviceEnum == 3)
        {
            confirmServiceView.setText("Fire & Rescue");
        }
        confirmLocationView.setText(SMSObject.getLocation());
        confirmDescriptionView.setText(SMSObject.getDescription());
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onConfirmButton(View view)
    {
        builder.show();
    }
}