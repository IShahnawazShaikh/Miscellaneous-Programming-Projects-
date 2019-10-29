package com.example.oasisNav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.valdesekamdem.library.mdtoast.MDToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Service_Detail extends AppCompatActivity implements LocationListener {

    EditText modelId,modelName,Discription;
    Button submitservice;
    LocationManager locationManager;
    String address,currentdate;
    ProgressBar progressBar;
    public static String SERVICE_DETAIL="https://ingratiating-jacks.000webhostapp.com/Oasis/service_detail.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service__detail);
        progressBar=findViewById(R.id.progress);
        modelId=findViewById(R.id.modelid);
        modelName=findViewById(R.id.modelname);
        Discription=findViewById(R.id.discription);
        submitservice=findViewById(R.id.submitservice);
        submitservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationFetch();
                dateFetch();
                if(address!=null) {
                    if(serviceDetail()) {
                        progressBar.setVisibility(View.VISIBLE);
                        MDToast.makeText(getApplicationContext(), "Submitted Successfully", MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
                        Intent intent = new Intent(getApplicationContext(), ServicemanHome.class);
                        startActivity(intent);
                        modelId.setText("");
                        modelName.setText("");
                        Discription.setText("");
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }
        });

    }
    private  boolean serviceDetail(){
        final String modelid=modelId.getText().toString();
        final String modelname=modelName.getText().toString();
        final String discription=Discription.getText().toString();
        boolean sign=false;
        if(TextUtils.isEmpty(modelid)) {
            MDToast.makeText(getApplicationContext(), "model id cannot be empty", MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
            sign=false;
        }
        else if(TextUtils.isEmpty(modelname)){
            MDToast.makeText(getApplicationContext(), "model name cannot be empty", MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
            sign=false;
        }
        else if(TextUtils.isEmpty(discription)){
            MDToast.makeText(getApplicationContext(), "Discription cannot be empty", MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
            sign=false;
        }
        else{
            StringRequest request = new StringRequest(Request.Method.POST, SERVICE_DETAIL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Toast.makeText(getApplicationContext(),""+ jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }
                    catch (JSONException e) {

                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Login Error"+e.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Login Error"+error.toString(),Toast.LENGTH_SHORT).show();

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    params.put("model_id", modelid);
                    params.put("model_name", modelname);
                    params.put("discription", discription);
                    params.put("address",address);
                    params.put("date",currentdate);
                    return params;

                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
            sign=true;
        }
        return sign;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    //locationfetch method implementation
    private void locationFetch(){
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        try{
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, Service_Detail.this);
            Criteria criteria = new Criteria();
            String bestProvider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(bestProvider);
            if (location == null) {
                MDToast.makeText(getApplicationContext(),"GPS not found ON GPS location and re-enter the detail ",MDToast.LENGTH_LONG,MDToast.TYPE_ERROR).show();
                finish();
                startActivity(getIntent());
            }
            if (location != null) {
                Log.e("locatin", "location--" + location);

                Log.e("latitude at beginning",
                        "@@@@@@@@@@@@@@@" + location.getLatitude());
                onLocationChanged(location);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void onLocationChanged(Location location) {

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        Log.e("latitude", "latitude--" + latitude);
        try {
            Log.e("latitude", "inside latitude--" + latitude);
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                address = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                // String postalCode = addresses.get(0).getPostalCode();
                //  String knownName = addresses.get(0).getFeatureName();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
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

    //current date fetch method implementation
    private void dateFetch(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        currentdate = df.format(c);
    }
}
