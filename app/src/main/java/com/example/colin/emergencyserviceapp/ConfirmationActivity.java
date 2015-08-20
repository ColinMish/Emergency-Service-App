package com.example.colin.emergencyserviceapp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ConfirmationActivity extends ActionBarActivity {

    TextView confirmServiceView;
    TextView confirmLocationView;
    TextView confirmDescriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Log.i("SMSObject", "Confirmation activity received service: " + Integer.toString(SMSObject.getService()));
        Log.i("SMSObject", "Confirmation activity received location: " + (SMSObject.getLocation()));
        Log.i("SMSObject", "Confirmation activity received description: " + (SMSObject.getDescription()));

        confirmServiceView = (TextView) findViewById(R.id.confirmServiceText);
        confirmLocationView = (TextView) findViewById(R.id.confirmLocationText);
        confirmDescriptionView = (TextView) findViewById(R.id.confirmDescriptionText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onConfirmButton(View view)
    {
        try
        {
            String phoneNo = "07889246988";
            String message = SMSObject.getService() + ", " + SMSObject.getLocation() + ", " + SMSObject.getDescription();
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            System.exit(0);
        }
        catch(Exception e)
        {

        }

    }

}
