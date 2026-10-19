package com.example.styczen2023;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String wybraneStanowisko = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.mySpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.stanowiska,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        EditText imie = findViewById(R.id.editTextText3);
        EditText nazwisko = findViewById(R.id.editTextText4);
        EditText iloscZnakow = findViewById(R.id.editTextText5);

        CheckBox maleDuze = findViewById(R.id.checkBox);
        CheckBox cyfry = findViewById(R.id.checkBox2);
        CheckBox znakiSpecjalne = findViewById(R.id.checkBox3);

        Button generujHaslo = findViewById(R.id.button);
        Button zatwierdz = findViewById(R.id.button2);

        generujHaslo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dlugoscStr = iloscZnakow.getText().toString();
                if (dlugoscStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Wpisz długość hasła!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int dlugosc = Integer.parseInt(dlugoscStr);
                StringBuilder pulaZnakow = new StringBuilder();

                if (maleDuze.isChecked()) {
                    pulaZnakow.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
                }
                if (cyfry.isChecked()) {
                    pulaZnakow.append("0123456789");
                }
                if (znakiSpecjalne.isChecked()) {
                    pulaZnakow.append("!@#$%^&*()_+-=[]{}|;':\",./<>?");
                }

                if (pulaZnakow.length() == 0) {
                    Toast.makeText(MainActivity.this, "Zaznacz przynajmniej jedną opcję znaków!", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuilder wygenerowaneHaslo = new StringBuilder();
                Random random = new Random();
                for (int i = 0; i < dlugosc; i++) {
                    int index = random.nextInt(pulaZnakow.length());
                    wygenerowaneHaslo.append(pulaZnakow.charAt(index));
                }

                Toast.makeText(MainActivity.this, "Wygenerowane hasło: " + wygenerowaneHaslo.toString(), Toast.LENGTH_LONG).show();
            }
        });

        zatwierdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String daneImie = imie.getText().toString();
                String daneNazwisko = nazwisko.getText().toString();

                if (daneImie.isEmpty() || daneNazwisko.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Wypełnij imię i nazwisko!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String komunikat = daneImie + " " + daneNazwisko + ", Stanowisko: " + wybraneStanowisko;
                Toast.makeText(MainActivity.this, komunikat, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        wybraneStanowisko = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        wybraneStanowisko = "";
    }
}
