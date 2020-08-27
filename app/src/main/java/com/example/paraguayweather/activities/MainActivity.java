package com.example.paraguayweather.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.paraguayweather.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void openAsuncion(android.view.View view) {
        Intent intentNewActivity = new Intent(MainActivity.this, AsuncionActivity.class);
        String ciudad = "3439389"; //Asuncion
        intentNewActivity.putExtra("CIUDAD", ciudad);
        startActivity(intentNewActivity);
    }

    public void openCDE(android.view.View view) {
        Intent intentNewActivity = new Intent(MainActivity.this, AsuncionActivity.class);
        String ciudad = "3439101";
        intentNewActivity.putExtra("CIUDAD", ciudad);
        startActivity(intentNewActivity);

    }
    public void openEncarnacion(android.view.View view) {
        Intent intentNewActivity = new Intent(MainActivity.this, AsuncionActivity.class);
        String ciudad = "3438735";
        intentNewActivity.putExtra("CIUDAD", ciudad);
        startActivity(intentNewActivity);
    }
    public void openLoma(android.view.View view) {
        Intent intentNewActivity = new Intent(MainActivity.this, AsuncionActivity.class);
        String ciudad = "3437824";
        intentNewActivity.putExtra("CIUDAD", ciudad);
        startActivity(intentNewActivity);
    }
    public void openVillarrica(android.view.View view) {
        Intent intentNewActivity = new Intent(MainActivity.this, AsuncionActivity.class);
        String ciudad = "3436714";
        intentNewActivity.putExtra("CIUDAD", ciudad);
        startActivity(intentNewActivity);
    }


}
