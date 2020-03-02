package es.iesfranciscodelosrios.algarrido.wolfrol.interfaces;


import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;


public interface BuscarInterface {

    public interface View {
        void lanzarAyuda();

        void volverListado();

    }

    public interface Presenter {

        void botonVolver();
        void pestaña4();

        ArrayList<String> getAllRazas();

        void filtrar(String resultadoNombre,
                     String resultadoFecha,
                     String resultadoSpinner,
                     EditText nombre, EditText etFecha, Spinner spinner);
    }

}
