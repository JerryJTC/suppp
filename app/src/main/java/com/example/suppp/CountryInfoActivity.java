package com.example.suppp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CountryInfoActivity extends AppCompatActivity {

    private TextView countryInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_info);

        countryInfoTextView = findViewById(R.id.countryInfoTextView);

        // Obtiene el nombre del país de la Intent
        String countryName = getIntent().getStringExtra("countryName");

        // Realiza la solicitud para obtener la información del país
        fetchCountryInfo(countryName);
    }

    private void fetchCountryInfo(String countryName) {
        // Aquí debes agregar la lógica para obtener la información del país
        // Puedes utilizar Volley o alguna otra librería como se explicó antes
        // Aquí simulamos los datos del país
        String capital = "Quito";  // Puedes obtener estos datos desde la API
        String isoCode = "EC";

        // Muestra la información del país
        countryInfoTextView.setText("País: " + countryName + "\nCapital: " + capital + "\nCódigo ISO: " + isoCode);
    }
}
