package com.example.paraguayweather.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paraguayweather.R;

import org.json.JSONException;

import java.text.DecimalFormat;

import models.Weather;
import networking.WeatherClient;
import utils.JSONparse;

public class AsuncionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private TextView cityText;
    private TextView sensacionTermica;
    private TextView temp;
    private TextView temp_min;
    private TextView temp_max;

    private ImageView imgView;

    String city = "3439389"; //Asuncion
    String unidad = "metric";


    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asuncion);
        String ciudad = getIntent().getStringExtra("CIUDAD");
        city = ciudad;

        cityText = (TextView) findViewById(R.id.cityText);
        sensacionTermica = (TextView) findViewById(R.id.sensacionTermica);
        temp = (TextView) findViewById(R.id.temp);
        temp_min = (TextView) findViewById(R.id.temp_min);
        temp_max = (TextView) findViewById(R.id.temp_max);

        Spinner spinner = findViewById(R.id.spinnerUnidad);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.unidades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city,unidad});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String value = (String) parent.getItemAtPosition(position);

        if (value.equals("Celsius")  && unidad.equals("imperial")){

            temp.setText(fahrenToCelsius(temp.getText().toString()));
            sensacionTermica.setText(fahrenToCelsius(sensacionTermica.getText().toString()));
            temp_min.setText(fahrenToCelsius(temp_min.getText().toString()));
            temp_max.setText(fahrenToCelsius(temp_max.getText().toString()));

            unidad = "metric";
        } else if (value.equals("Celsius") && unidad.equals("")){
            temp.setText(kelvinToCelsius(temp.getText().toString()));
            sensacionTermica.setText(kelvinToCelsius(sensacionTermica.getText().toString()));
            temp_min.setText(kelvinToCelsius(temp_min.getText().toString()));
            temp_max.setText(kelvinToCelsius(temp_max.getText().toString()));

            unidad = "metric";

        } else if (value.equals("Kelvin") && unidad.equals("metric")) {
            temp.setText(celsiusToKelvin(temp.getText().toString()));
            sensacionTermica.setText(celsiusToKelvin(sensacionTermica.getText().toString()));
            temp_min.setText(celsiusToKelvin(temp_min.getText().toString()));
            temp_max.setText(celsiusToKelvin(temp_max.getText().toString()));

            unidad = "";
        } else if (value.equals("Kelvin") && unidad.equals("imperial")) {
            temp.setText(fahrenToKelvin(temp.getText().toString()));
            sensacionTermica.setText(fahrenToKelvin(sensacionTermica.getText().toString()));
            temp_min.setText(fahrenToKelvin(temp_min.getText().toString()));
            temp_max.setText(fahrenToKelvin(temp_max.getText().toString()));

            unidad = "";
        } else if (value.equals("Fahrenheit") && unidad.equals("metric")) {
            temp.setText(celsiusToFahren(temp.getText().toString()));
            sensacionTermica.setText(celsiusToFahren(sensacionTermica.getText().toString()));
            temp_min.setText(celsiusToFahren(temp_min.getText().toString()));
            temp_max.setText(celsiusToFahren(temp_max.getText().toString()));

            unidad = "imperial";
        } else if (value.equals("Fahrenheit") && unidad.equals("")) {
            temp.setText(kelvinToFahren(temp.getText().toString()));
            sensacionTermica.setText(kelvinToFahren(sensacionTermica.getText().toString()));
            temp_min.setText(kelvinToFahren(temp_min.getText().toString()));
            temp_max.setText(kelvinToFahren(temp_max.getText().toString()));

            unidad = "imperial";
        }


    }

    public String celsiusToKelvin(String s) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        Double numero = Double.valueOf(s);
        numero = (numero + 273.15);
        return String.valueOf(numberFormat.format(numero));
    }

    public String kelvinToCelsius(String s) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        Double numero = Double.valueOf(s);
        numero = (numero - 273.15);
        return String.valueOf(numberFormat.format(numero));
    }

    public String fahrenToCelsius(String s) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        Double numero = Double.valueOf(s);
        numero = (numero - 32)*0.55;
        return String.valueOf(numberFormat.format(numero));
    }

    public String celsiusToFahren(String s) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        Double numero = Double.valueOf(s);
        numero = (numero*1.8) + 32;
        return String.valueOf(numberFormat.format(numero));
    }

    public String kelvinToFahren(String s) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        Double numero = Double.valueOf(s);
        numero = (numero*1.8) - 459.67;
        return String.valueOf(numberFormat.format(numero));
    }

    public String fahrenToKelvin(String s) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        Double numero = Double.valueOf(s);
        numero = ((numero - 32)*0.55 + 273.15);
        return String.valueOf(numberFormat.format(numero));
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = ( (new WeatherClient()).getWeatherData(params[0],params[1]));

            try {
                weather = JSONparse.getWeather(data);

                // Let's retrieve the icon
                weather.iconData = ( (new WeatherClient()).getImage(weather.currentCondition.getIcon()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;

        }



        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            if (weather.iconData != null && weather.iconData.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
                imgView.setImageBitmap(img);
            }

            cityText.setText(weather.location.getCity() + "," + weather.location.getCountry());
            temp.setText("" + Math.round((weather.temperature.getTemp())));
            sensacionTermica.setText("" + weather.temperature.getFeelsLike());
            temp_min.setText("" + weather.temperature.getMinTemp());
            temp_max.setText("" + weather.temperature.getMaxTemp());



        }



    }
}
