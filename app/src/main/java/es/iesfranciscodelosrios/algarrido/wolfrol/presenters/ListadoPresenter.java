package es.iesfranciscodelosrios.algarrido.wolfrol.presenters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.iesfranciscodelosrios.algarrido.wolfrol.interfaces.ListadoInterface;
import es.iesfranciscodelosrios.algarrido.wolfrol.models.Personaje;
import es.iesfranciscodelosrios.algarrido.wolfrol.models.PersonajeModel;
import es.iesfranciscodelosrios.algarrido.wolfrol.utils.VerticalSpacingItemDecorator;
import es.iesfranciscodelosrios.algarrido.wolfrol.views.PersonajeAdapter;

public class ListadoPresenter implements ListadoInterface.Presenter {

    private ListadoInterface.View view;
    private PersonajeModel personaje;

    public ListadoPresenter(ListadoInterface.View view) {
        this.view = view;
        this.personaje = PersonajeModel.getInstance(); //esto ha cambiado
    }

    @Override
    public void botonAñadir() {
        view.lanzarFormulario(-1);
    }



    @Override
    public void pestaña3() {
        view.lanzarSobreMi();
    }
    @Override
    public void pestaña4() {
        view.lanzarAyuda();
    }
    @Override
    public void pestañaBuscar() {
        view.lanzarBuscar();
    }

    @Override
    public ArrayList<Personaje> getAllPersonaje() {

        return personaje.getAllPersonaje();
    }


    @Override
    public void onClickRecyclerView(int id) {
        view.lanzarFormulario(id);
    }

    @Override
    public void initRecyclerView(RecyclerView r, PersonajeAdapter a, ItemTouchHelper.SimpleCallback i, Context c) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(c);
        r.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        r.addItemDecoration(itemDecorator);
        new ItemTouchHelper(i).attachToRecyclerView(r);
        //adaptador = new RecyclerView(items, this);
        r.setAdapter(a);
    }

    public void recoger(String nombre, String fecha, String spinner) {
        personaje.buscar(nombre, fecha, spinner);
    }

    @Override
    public ArrayList<Personaje> filtrar(String nombre, String fecha, String raza) {

        return personaje.buscar(nombre, fecha, raza);

    }
    @Override
    public int eliminar(int id){
        personaje.eliminar(id);
        return id;
    }



}
