package es.iesfranciscodelosrios.algarrido.wolfrol.interfaces;

import android.content.Context;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.iesfranciscodelosrios.algarrido.wolfrol.models.Personaje;
import es.iesfranciscodelosrios.algarrido.wolfrol.views.PersonajeAdapter;

public interface ListadoInterface {

    public interface View{

        void lanzarSobreMi();
        void lanzarAyuda();
        void lanzarBuscar();
        void lanzarFormulario(int id);
        void swipeRecyclerView();
    }

    public interface Presenter{
        void botonAñadir();
        void pestaña3();
        void pestaña4();
        void pestañaBuscar();
        ArrayList<Personaje> getAllPersonaje();
        void onClickRecyclerView(int id);
        void initRecyclerView(RecyclerView recyclerView, PersonajeAdapter adaptador, ItemTouchHelper.SimpleCallback itemTouchHelperCallback, Context c);
        ArrayList<Personaje> filtrar(String nombre, String fecha, String raza);
        int eliminar(int id);
    }
}
