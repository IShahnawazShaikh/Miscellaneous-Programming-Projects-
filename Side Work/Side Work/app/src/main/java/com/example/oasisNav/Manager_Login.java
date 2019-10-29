package com.example.oasisNav;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class Manager_Login extends AppCompatActivity {
    CheckBox checkBox;
    Button loginbutton;
    EditText username, password;
    ProgressBar progressBar;
    String userName ,passWord;
    static String URL_LOGIN="https://ingratiating-jacks.000webhostapp.com/Oasis/Manager_Login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager__login);
        loginbutton = findViewById(R.id.signin);
        checkBox = findViewById(R.id.checkbox);
        username = findViewById(R.id.Id);
        password = findViewById(R.id.Password);
        progressBar=findViewById(R.id.progress);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());

                } else {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = username.getText().toString().trim();
                passWord = password.getText().toString().trim();

                if(userName.isEmpty() || passWord.isEmpty())
                {
                    // Toast.makeText(getApplicationContext(),"Field can't be empty",Toast.LENGTH_LONG).show();
                    Toast toast = Toast.makeText(getApplicationContext(), "filed cant be empty", Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    v.setTextColor(Color.RED);
                    v.setBackgroundColor(Color.WHITE);
                    toast.show();
                }
                else {
                    login();
                }
            }
        });
    }

    private void login() {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    String success=jsonObject.getString("success");
                    JSONArray jsonArray=jsonObject.getJSONArray("login");
                    if(success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                           /*  String name = jsonObject1.getString("id");
                             String pass = jsonObject1.getString("password");
                             Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
                             Toast.makeText(getApplicationContext(),pass,Toast.LENGTH_LONG).show();*/
                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Navigation.class));
                            progressBar.setVisibility(View.GONE);
                        }
                        username.setText("");
                        password.setText("");

                    }
                    else if(success.equals("0")){
                        Toast.makeText(Manager_Login.this, "Invalid Username and Password", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }catch (JSONException exception){
                    exception.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Invalid Username and Password",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Login Error2"+error.toString(),Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id",userName);
                params.put("password",passWord);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
