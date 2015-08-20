package com.example.colin.emergencyserviceapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Situation extends ActionBarActivity {

    TextView situationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation);
        situationText = (TextView) findViewById(R.id.textSituation);
        Log.i("SMSObject", "Situation activity received service: " + Integer.toString(SMSObject.getService()));
        Log.i("SMSObject", "Situation activity received location: " + (SMSObject.getLocation()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_situation, menu);
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

    public void onFireButton(View view) {
        situationText.append( "fire,");

    }

    public void onRescueButton(View view) {
        situationText.append( "rescue,");
    }

    public void onHazmatButton(View view) {
        situationText.append( "hazmat,");
    }

    public void startConfirmActivity(View view)
    {
        SMSObject.setDescription(situationText.getText().toString());
        Log.i("SMSObject", "Current description set to: " + SMSObject.getDescription());

        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.putExtra("Exit me", true);
        startActivity(intent);
        finish();
    }
}
