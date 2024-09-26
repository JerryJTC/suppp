package com.example.suppp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private GridView countryGridView;
    private ArrayList<String> countryList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryGridView = findViewById(R.id.countryGridView);
        countryList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countryList);
        countryGridView.setAdapter(adapter);

        fetchCountries();

        countryGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCountry = countryList.get(position);

                // Iniciar la nueva actividad y pasar el nombre del país
                Intent intent = new Intent(MainActivity.this, CountryInfoActivity.class);
                intent.putExtra("countryName", selectedCountry);
                startActivity(intent);
            }
        });
    }

    private void fetchCountries() {
        String url = "http://www.geognos.com/api/en/countries/info/all.json";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject results = response.getJSONObject("Results");
                            Iterator<String> keys = results.keys();

                            while (keys.hasNext()) {
                                String countryCode = keys.next();
                                JSONObject country = results.getJSONObject(countryCode);
                                String countryName = country.getString("Name");
                                countryList.add(countryName);
                            }

                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "Error fetching country list");
            }
        });

        queue.add(request);
    }
}