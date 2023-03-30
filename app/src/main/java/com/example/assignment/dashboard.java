package com.example.assignment;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class dashboard extends AppCompatActivity {
    TextView t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        t1=findViewById(R.id.textview5);
        t2=findViewById(R.id.textview6);
        t3=findViewById(R.id.textview7);
        String url= "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=demo";
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject("Meta Data");
                    String info="1.Information: "+object.getString("1. Information")+"\n\n"+"2.Symbol: "+object.getString("2. Symbol")+"\n\n"+"3.Last Refreshed: "+object.getString("3. Last Refreshed");
                    t2.setText(info);
                    JSONObject obj=response.getJSONObject("Time Series (5min)");
                    JSONObject obj2=obj.getJSONObject("2023-03-28 20:00:00");
                    String info2="1.Open: "+obj2.getString("1. open")+"\n"+"2.High: "+obj2.getString("2. high")+"\n"+"3.Low: "+obj2.getString("3. low")+"\n"+"4.Close: "+obj2.getString("4. close")+"\n"+"5.Volume: "+obj2.getString("5. volume");

                    t3.setText(info2);

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(dashboard.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

}