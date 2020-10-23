package com.example.day9_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
         private Spinner spinner;
           DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



         db = new DatabaseHelper(this);

        db.addFood(new Food("Humus"));
        db.addFood(new Food("Sausage"));
        db.addFood(new Food("Worms"));
        db.addFood(new Food("Meat"));
        db.addFood(new Food("Sulphur"));


        spinner = findViewById(R.id.spinner);
        loadSpinnerData();
        spinner.setOnItemSelectedListener(this);




    }
    private void loadSpinnerData() {
        List<String> foodNames = new ArrayList<>();
        foodNames.clear();
        foodNames = db.getAllFoodNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, foodNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}