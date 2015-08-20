package com.example.colin.emergencyserviceapp;

import android.content.Context;
import android.content.Intent;
import android.location.*;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Dreads on 20/08/2015.
 */
public class LocationActivity extends ActionBarActivity implements LocationListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 300, 0, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location, menu);
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

    public void startSituation(View view)
    {
        TextView add1 = (TextView) findViewById(R.id.addressLine1);
        TextView add2 = (TextView) findViewById(R.id.addressLine2);
        TextView post = (TextView) findViewById(R.id.addressLine3);

        String ad1 = add1.getText().toString();
        String ad2 = add2.getText().toString();
        String pst = post.getText().toString();

        String locText = (ad1 + ", " + ad2 + ", " + pst);
        SMSObject.setLocation(locText);

        Intent intent = new Intent(this, Situation.class);
        startActivity(intent);
    }

    public android.location.Location getLocation()
    {

        return null;
    }
    @Override
    public void onLocationChanged(android.location.Location location)
    {
        double latitude = (double) (location.getLatitude());
        double longitude = (double)(location.getLongitude());
        Log.i("Latitude: ", latitude + ", Longitude: " + longitude);
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            String address = addresses.get(0).getAddressLine(0);
            String city = addresses.get(0).getLocality();
            String country = addresses.get(0).getCountryName();
            String postCode = addresses.get(0).getPostalCode();
            Log.i("Address: ", address + " " + city + " " + country + " " + postCode);
            TextView add1 = (TextView) findViewById(R.id.addressLine1);
            TextView add2 = (TextView) findViewById(R.id.addressLine2);
            TextView post = (TextView) findViewById(R.id.addressLine3);
            add1.setText(address + ", " + city);
            add2.setText(country);
            post.setText(postCode);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
