package es.ucm.fdi.gescom.features.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import es.ucm.fdi.gescom.R;

public class RegisterActivity extends AppCompatActivity {
    private Spinner spinnerNumeroEdificios, spinnerNumeroPlantas, spinnerViviendasPlanta;

    //TODO empezar por ver que necesitamos para registrar una comunidad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinnerNumeroEdificios = findViewById(R.id.spinner_numero_edificios);
        ArrayAdapter adaptadorEdificios = ArrayAdapter.createFromResource(this, R.array.valores_spinner_numero_edificios, android.R.layout.simple_spinner_item);
        adaptadorEdificios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNumeroEdificios.setAdapter(adaptadorEdificios);

        spinnerNumeroEdificios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //TODO crear el array de valores para las plantas
        //TODO crear el layout para mostrar cada item

        spinnerNumeroPlantas = findViewById(R.id.spinner_plantas_edificio);
        ArrayAdapter adaptadorPlantas = ArrayAdapter.createFromResource(this, R.array.valores_spinner_numero_edificios, android.R.layout.simple_spinner_item);
        adaptadorPlantas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNumeroPlantas.setAdapter(adaptadorPlantas);

        spinnerNumeroPlantas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerViviendasPlanta = findViewById(R.id.spinner_viviendas_planta);
        ArrayAdapter adaptadorViviendas = ArrayAdapter.createFromResource(this, R.array.valores_spinner_numero_edificios, android.R.layout.simple_spinner_item);
        adaptadorViviendas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerViviendasPlanta.setAdapter(adaptadorViviendas);

        spinnerViviendasPlanta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
}
