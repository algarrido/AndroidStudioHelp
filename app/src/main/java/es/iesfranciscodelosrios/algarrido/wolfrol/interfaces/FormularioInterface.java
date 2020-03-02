package es.iesfranciscodelosrios.algarrido.wolfrol.interfaces;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import es.iesfranciscodelosrios.algarrido.wolfrol.models.Personaje;
import es.iesfranciscodelosrios.algarrido.wolfrol.presenters.FormularioPresenter;

public interface FormularioInterface {
    public interface View{
        void volverListado();
        void requestPermision();
        void selectPicture();
        void lanzarAyuda();
        void cerrarFormulario();
    }

    public interface Presenter{
        void botonVolver();
        void guardarFormulario(Personaje p,FormularioPresenter.Callback callback);
        void guardarRaza(String raza);
        void eliminarFormulario();
        void validacionCampoPeso(boolean hasFocus, TextInputLayout nombreInputLayout, TextInputEditText n,Button b);
        void validacionCampoFecha(boolean hasFocus,EditText editText);
        void validacionCampoNombre(boolean hasFocus, TextInputLayout nombreInputLayout, TextInputEditText n,Button b);
        void validacionCampoGenero(boolean hasFocus, TextInputLayout nombreInputLayout, TextInputEditText n,Button b);
        void pestaña4();

        void onClickImage(Context c);
        void resultPermission(int result, FormularioPresenter.Callback callback);
        void galeria(ImageView i, ImageView iv, Bitmap bmp);
        ArrayList<String> getAllRazas();
        void eliminar(int id);
        void editar(Personaje p);
    }
}
