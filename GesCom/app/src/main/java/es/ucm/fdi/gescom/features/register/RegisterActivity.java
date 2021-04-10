package es.ucm.fdi.gescom.features.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import es.ucm.fdi.gescom.R;

public class RegisterActivity extends AppCompatActivity implements RegisterView{
    private Spinner spinnerNumeroEdificios, spinnerNumeroPlantas, spinnerViviendasPlanta;
    private RegisterPresenter mRegisterPresenter;
    private Button mButton;
    private EditText mCommunityName, mUserName, mUserPassword, mUserPasswordRepeat;

    //TODO empezar por ver que necesitamos para registrar una comunidad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mRegisterPresenter = new RegisterPresenter(this);
        //
        mCommunityName = findViewById(R.id.registro_editText_nombre_comunidad);
        mUserName = findViewById(R.id.registro_editText_username_admin);
        mUserPassword = findViewById(R.id.registro_editText_password);
        mUserPasswordRepeat = findViewById(R.id.registro_editText_password_repeat);

        //
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

        mButton = findViewById(R.id.button_register);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegisterPresenter.validateRegister(String.valueOf(mCommunityName.getText()), String.valueOf(mUserName.getText()), String.valueOf(mUserPassword.getText()), String.valueOf(mUserPasswordRepeat.getText()));
            }
        });

    }

    @Override
    public void registerSuccessful() {
        //ver a donde vamos una vez creada la comunidad
        Toast toast = Toast.makeText(this, "SUCCESSFUL", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void registerExistingUser() {
        Toast toast = Toast.makeText(this, "Este usuario ya existe. Por favor introduzca otro usuario.", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void registerCommunityFailure() {
        Toast toast = Toast.makeText(this, "Esta comunidad ya existe. Por favor introduzca otro nombre.", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void fillingFailure() {
        Toast toast = Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void noMatchingPasswords() {
        Toast toast = Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_LONG);
        toast.show();
        mUserPassword.setText("");
        mUserPasswordRepeat.setText("");
    }

    @Override
    public void registerUserServerFailure() {
        Toast toast = Toast.makeText(this, "No se pudo registrar el usuario. \n Por favor inténtelo más tarde.", Toast.LENGTH_LONG);
        toast.show();
    }
}
