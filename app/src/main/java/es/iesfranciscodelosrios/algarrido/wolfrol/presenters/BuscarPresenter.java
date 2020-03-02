package es.iesfranciscodelosrios.algarrido.wolfrol.presenters;


import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import es.iesfranciscodelosrios.algarrido.wolfrol.interfaces.BuscarInterface;
import es.iesfranciscodelosrios.algarrido.wolfrol.models.PersonajeModel;

public class BuscarPresenter implements BuscarInterface.Presenter {

    private BuscarInterface.View view;
    private PersonajeModel personaje;

    public BuscarPresenter(BuscarInterface.View view) {
        this.view = view;
        personaje = PersonajeModel.getInstance();
    }

    @Override
    public void botonVolver() {
        view.volverListado();
    }


    @Override
    public ArrayList<String> getAllRazas() {
        return personaje.mostrarRazas();
    }
    public void pestaña4() {
        view.lanzarAyuda();
    }

    public void filtrar(String resultadoNombre,
                        String resultadoFecha,
                        String resultadoSpinner,
                        EditText nombre, EditText etFecha, Spinner spinner) {
        if (nombre.getText().length() == 0) resultadoNombre = "%";
        else resultadoNombre = "%" + nombre.getText().toString() + "%";

        if (etFecha.getText().length() == 0) resultadoFecha = "%";
        else resultadoFecha = etFecha.getText().toString();

        if (spinner.getSelectedItem().toString().length() == 0) resultadoSpinner = "%";
        else resultadoSpinner = spinner.getSelectedItem().toString();

    }

}


