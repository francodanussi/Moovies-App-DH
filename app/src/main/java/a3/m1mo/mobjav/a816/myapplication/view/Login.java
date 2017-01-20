package a3.m1mo.mobjav.a816.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import a3.m1mo.mobjav.a816.myapplication.R;

public class Login extends AppCompatActivity {
    TextInputLayout textInputLayoutNombre;
    TextInputLayout textInputLayoutPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputLayoutNombre = (TextInputLayout) findViewById(R.id.TextInputLayoutNombre);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.TextInputLayoutpassword);
    }

    public void EntrarAMoovie(View view){
        EditText editTextMensaje = (EditText)findViewById(R.id.editTextNombreUsuario);
        EditText editTextRegalo = (EditText)findViewById(R.id.editTextPassword);
        String nombreIngresado = editTextMensaje.getText().toString();
        String passwordIngresado = editTextRegalo.getText().toString();

        String mensajeSinEspacios = nombreIngresado.replace(" ","");


        if (mensajeSinEspacios.length() != 0){
            textInputLayoutNombre.setError(null);
            textInputLayoutPassword.setError(null);

            Intent unIntent = new Intent(this, MainScreenPelicula.class);

            Bundle unBundle = new Bundle();
            unBundle.putString(MainScreenPelicula.KEY_USER, nombreIngresado);
            unBundle.putString(MainScreenPelicula.KEY_PASSWORD, passwordIngresado);

            unIntent.putExtras(unBundle);

            startActivity(unIntent);

        } else {
            textInputLayoutNombre.setError("Tiene que ingresar un usuario");
            textInputLayoutPassword.setError("Tiene que ingresar una contraseña");
            editTextMensaje.setText("");
        }
    }
}
