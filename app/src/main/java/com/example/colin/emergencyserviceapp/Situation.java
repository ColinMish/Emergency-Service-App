package com.example.colin.emergencyserviceapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class Situation extends ActionBarActivity {

    TextView situationText;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation);
        situationText = (TextView) findViewById(R.id.textSituation);
        Log.i("SMSObject", "Situation activity received service: " + Integer.toString(SMSObject.getService()));
        Log.i("SMSObject", "Situation activity received location: " + (SMSObject.getLocation()));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
        situationText.append("House fire ");

    }

    public void onRescueButton(View view) {
        situationText.append("Car accident ");
    }

    public void onHazmatButton(View view) {
        situationText.append("Hazmat ");
    }

    public void onAssaultButton(View view) {
        situationText.append("Assault ");
    }

    public void onDisturbanceButton(View view) {
        situationText.append("Public disturbance ");
    }

    public void onUnconsciousButton(View view) {
        situationText.append("Unconscious & breathing ");
    }

    public void onNUnconsciousButton(View view) {
        situationText.append("Unconscious & not breathing ");
    }

    public void startConfirmActivity(View view)
    {
        boolean situEntered = true;
        String situText = situationText.getText().toString();
        if(situText.length() == 0)
        {
            situEntered = false;
        }
        if(situEntered) {
            SMSObject.setDescription(situText);
            Log.i("SMSObject", "Current description set to: " + SMSObject.getDescription());

            Intent intent = new Intent(this, ConfirmationActivity.class);
            intent.putExtra("Exit me", true);
            startActivity(intent);
            finish();
        }
    }
    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    situationText.append(result.get(0) + " ");
                }
                break;
            }

        }
    }

    public void onSpeechButton(View view) {
        promptSpeechInput();
    }

}
