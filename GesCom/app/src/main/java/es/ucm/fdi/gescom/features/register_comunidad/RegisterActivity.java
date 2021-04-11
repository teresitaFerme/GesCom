package es.ucm.fdi.gescom.features.register_comunidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import es.ucm.fdi.gescom.R;
import es.ucm.fdi.gescom.features.register_comunidad.initialize_users.UserInitializationActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterView{
    private RegisterPresenter mRegisterPresenter;
    private EditText mCommunityName, mUserName, mUserPassword, mUserPasswordRepeat, mUserDni, mNumHouses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mRegisterPresenter = new RegisterPresenter(this);

        mCommunityName = findViewById(R.id.registro_editText_nombre_comunidad);
        mUserName = findViewById(R.id.registro_editText_username_admin);
        mUserPassword = findViewById(R.id.registro_editText_password);
        mUserPasswordRepeat = findViewById(R.id.registro_editText_password_repeat);
        mUserDni = findViewById(R.id.editText_dni_administrador);
        mNumHouses = findViewById(R.id.registro_editText_num_viviendas);

        Button mButton = findViewById(R.id.button_register);
        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mRegisterPresenter.validateRegister(String.valueOf(mCommunityName.getText()), String.valueOf(mUserName.getText()), String.valueOf(mUserPassword.getText()), String.valueOf(mUserPasswordRepeat.getText()), String.valueOf(mUserDni.getText()));
            }
        });

    }

    @Override
    public void registerSuccessful() {
        Toast toast = Toast.makeText(this, "SUCCESSFUL", Toast.LENGTH_LONG);
        toast.show();

        Intent intent = new Intent(this, UserInitializationActivity.class);
        intent.putExtra("numHouses", String.valueOf(mNumHouses.getText()));
        startActivity(intent);
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

    @Override
    public void invalidDniFormat() {
        //TODO: llamar a esta función cuando el formato de dni no sea válido
        Toast toast = Toast.makeText(this, "Formato de DNI inválido.", Toast.LENGTH_LONG);
        toast.show();
        mUserDni.setText("");
    }
}