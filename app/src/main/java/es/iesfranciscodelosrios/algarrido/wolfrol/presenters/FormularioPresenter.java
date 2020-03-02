package es.iesfranciscodelosrios.algarrido.wolfrol.presenters;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import java.util.ArrayList;
import java.util.regex.Pattern;

import es.iesfranciscodelosrios.algarrido.wolfrol.R;
import es.iesfranciscodelosrios.algarrido.wolfrol.interfaces.FormularioInterface;
import es.iesfranciscodelosrios.algarrido.wolfrol.models.Personaje;
import es.iesfranciscodelosrios.algarrido.wolfrol.models.PersonajeModel;

public class FormularioPresenter implements FormularioInterface.Presenter {

    private FormularioInterface.View view;
    private PersonajeModel personaje;

    String TAG = "WolfRol/FormularioPresenter";

    public FormularioPresenter(FormularioInterface.View view) {
        this.view = view;
        personaje = PersonajeModel.getInstance();
    }


    public interface Callback {
        public void onOk();

        public void onError(String errMsg);
    }

    @Override
    public void botonVolver() {
        view.volverListado();
    }


    @Override
    public void guardarFormulario(Personaje p, Callback callback) {
        //Simular logica guardado ok y ko
        boolean guardado = true;
        if (personaje.addNewPersonaje(p)) {

            view.cerrarFormulario();
            callback.onOk();
        } else {
            callback.onError("Error...");
        }
    }

    @Override
    public void guardarRaza(String raza) {
        personaje.addNewRaza(raza);
    }

    @Override
    public void eliminarFormulario() {

    }

    public static final String REGEX_LETRAS = "[A-Za-z]*";
    public static final String REGEX_FECHA = "^(?:3[01]|[12][0-9]|0?[1-9])([\\-/.])(0?[1-9]|1[1-2])\\1\\d{4}$";

    @Override
    public void validacionCampoPeso(boolean hasFocus, TextInputLayout nombreInputLayout, TextInputEditText n, Button b) {
        Pattern patron = Pattern.compile(REGEX_LETRAS);

        String stCampoLetra = n.getText().toString().trim();

        if (!hasFocus) {
            Log.d("AppCRUD", n.getText().toString());
            if (patron.matcher(stCampoLetra).matches() && stCampoLetra.isEmpty()) {
                nombreInputLayout.setError("Peso inválido");
                b.setEnabled(false);

            } else {
                nombreInputLayout.setError("");
                b.setEnabled(true);
            }

        }

    }

    @Override
    public void validacionCampoGenero(boolean hasFocus, TextInputLayout nombreInputLayout, TextInputEditText n, Button b) {
        Pattern patron = Pattern.compile(REGEX_LETRAS);

        String stCampoLetra = n.getText().toString().trim();

        if (!hasFocus) {
            Log.d("AppCRUD", n.getText().toString());
            if (!patron.matcher(stCampoLetra).matches()) {
                nombreInputLayout.setError("Genero inválido");
                b.setEnabled(false);

            } else {
                nombreInputLayout.setError("");
                b.setEnabled(true);
            }

        }

    }

    @Override
    public void pestaña4() {
        view.lanzarAyuda();
    }

    @Override
    public void validacionCampoNombre(boolean hasFocus, TextInputLayout nombreInputLayout, TextInputEditText n, Button b) {
        Pattern patron = Pattern.compile(REGEX_LETRAS);

        String stCampoLetra = n.getText().toString().trim();

        if (!hasFocus) {
            Log.d("AppCRUD", n.getText().toString());
            if (!patron.matcher(stCampoLetra).matches()) {
                nombreInputLayout.setError("Nombre inválido");
                b.setEnabled(false);

            } else {
                nombreInputLayout.setError("");
                b.setEnabled(true);
            }

        }

    }

    @Override
    public void validacionCampoFecha(boolean hasFocus, EditText editText) {

        Pattern patron2 = Pattern.compile(REGEX_FECHA);
        String stCampoLetra = editText.getText().toString().trim();

        if (!hasFocus) {
            Log.d("AppCRUD", editText.getText().toString());

            if (patron2.matcher(stCampoLetra).matches()) {
                Log.d(TAG, "Campo fecha correcto");

            } else {
                editText.setError("Introduzca éste formato: DD/MM/YYYY");
            }
        }
    }

    @Override
    public void onClickImage(Context c) {
        int ReadPermission = ContextCompat.checkSelfPermission(c, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (ReadPermission != PackageManager.PERMISSION_GRANTED) {
            // Permiso denegado

            view.requestPermision();

        } else {

            view.selectPicture();
        }
    }

    @Override
    public void resultPermission(int result, Callback callback) {
        if (result == PackageManager.PERMISSION_GRANTED) {
            // Permiso aceptado
            Log.d(TAG, "Permiso aceptado");
            view.selectPicture();


        } else {
            // Permiso rechazado
            Log.d(TAG, "Permiso denegado");
            callback.onError("");

        }
    }

    @Override
    public void galeria(ImageView i, ImageView iv, Bitmap bmp) {
        if (i.getDrawable() == null) {
            i.setImageResource(R.drawable.logo);
        }
        //ImageView iv= findViewById(R.id.imageViewPersonaje);
        BitmapDrawable bmDr = (BitmapDrawable) iv.getDrawable();
        if (bmDr != null) {
            bmp = bmDr.getBitmap();
        } else {
            bmp = null;
        }
    }

    @Override
    public ArrayList<String> getAllRazas() {
        return personaje.mostrarRazas();
    }

    @Override
    public void eliminar(int id) {
        personaje.eliminar(id);
    }

    @Override
    public void editar(Personaje p) {
        personaje.actualizar(p);
        view.cerrarFormulario();
    }

}






















