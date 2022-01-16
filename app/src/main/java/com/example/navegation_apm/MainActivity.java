package com.example.navegation_apm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtContraseña;
    private RequestQueue requeque;
    private String usuario ;
    private int resulId;
    String url="https://my-json-server.typicode.com/dennissezambrano2017/demo_json/db";
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsuario = findViewById(R.id.txtUser);
        txtContraseña = findViewById(R.id.txtPassword);
        resulId=0;
        requeque= Volley.newRequestQueue(this);
    }
    public void btnAcceder(View view){
        Intent intent = new Intent(MainActivity.this, Navegation.class);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Usuarios");
                            Log.d("usuario", txtUsuario.getText().toString());
                            Log.d("contraseña", txtContraseña.getText().toString());
                            for (int i=0; i <jsonArray.length();i++){
                                JSONObject data = jsonArray.getJSONObject(i);
                                if(txtUsuario.getText().toString().equals(data.getString("usario"))){
                                    if(txtContraseña.getText().toString().equals(data.getString("Contraseña"))) {
                                        resulId = data.getInt("id");
                                        Log.d("id", String.valueOf(resulId));
                                    }
                                }

                            }
                            if (resulId!= 0){
                                String message ="Has ingresado";
                                intent.putExtra(EXTRA_MESSAGE, message);
                                startActivity(intent);
                            }else{
                                Context context = getApplicationContext();
                                CharSequence text ="¡Has ingresado mal el usuario o contraseña! ";
                                int duration = Toast.LENGTH_LONG;
                                Toast toast =Toast.makeText(context,text,duration);
                                toast.setGravity(Gravity.TOP|Gravity.LEFT,80,700);
                                toast.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requeque.add(request);
    }
}