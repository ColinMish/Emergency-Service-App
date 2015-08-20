package com.example.colin.emergencyserviceapp;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.DialogFragment;


public class EmergencyList extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_list);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        System.exit(1);
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.emergency_popup_message).setPositiveButton("Continue", dialogClickListener)
                .setNegativeButton("Cancel", dialogClickListener).show();

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_emergency_list, menu);

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

    public void onPoliceserviceButton(View view)
    {
        SMSObject.setService(1);
        Log.i("SMSObject", "Current service set to: " + SMSObject.getService());
        startLocation();
    }

    public void onAmbulanceserviceButton(View view)
    {
        SMSObject.setService(2);
        Log.i("SMSObject", "Current service set to: " + SMSObject.getService());
        startLocation();
    }

    public void onFireserviceButton(View view)
    {
        SMSObject.setService(3);
        Log.i("SMSObject", "Current service set to: " + SMSObject.getService());
        startLocation();
    }

    private void startLocation(){

        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
        finish();
    }
}
