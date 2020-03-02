package es.iesfranciscodelosrios.algarrido.wolfrol;

import android.app.Instrumentation;
import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

import es.iesfranciscodelosrios.algarrido.wolfrol.models.Personaje;
import es.iesfranciscodelosrios.algarrido.wolfrol.models.PersonajeModel;


@RunWith(AndroidJUnit4.class)
public class PersonajeModelInstrumentedTest {
    private Personaje p;
    private Personaje p2;
    private PersonajeModel repositorio;

    Context appContext;

    @Before
    public void setUp() {
        p = new Personaje();
        p2 = new Personaje();
        InstrumentationRegistry.getTargetContext().deleteDatabase("rol.db");
        repositorio = PersonajeModel.getInstance(InstrumentationRegistry.getTargetContext());

    }

    @After
    public void setClose() {
        repositorio.close();
    }

    @Test
    public void addPersonajeDB() {
        p.setNombre("Lidia");
        p.setPeso("50");
        p.setGenero("Femenino");
        p.setHistoria("Lorem ipsu qwerty");
        p.setFecha("20/01/1999");
        p.setRaza("Orco");
        p.setPartida("En partida");
        assertEquals(true, repositorio.addNewPersonaje(p));
        //////////////////////////////////////////////////////////////
        ArrayList<Personaje> pers = repositorio.getAllPersonaje();
        ArrayList<String> raza = repositorio.mostrarRazas();
        assertEquals(11, pers.size());
        assertEquals("Lidia", p.getNombre());
        assertEquals("50", p.getPeso());
        assertEquals("Femenino", p.getGenero());
        assertEquals("Lorem ipsu qwerty", p.getHistoria());
        assertEquals("20/01/1999", p.getFecha());
        assertEquals("Orco", p.getRaza());
        assertEquals("En partida", p.isPartida());

    }

    @Test
    public void eliminarPersonajeDB() {
        p.setNombre("Lidia");
        p.setPeso("50");
        p.setGenero("Femenino");
        p.setHistoria("Lorem ipsu qwerty");
        p.setFecha("20/01/1999");
        p.setRaza("Orco");
        p.setPartida("En partida");
        assertEquals(true, repositorio.addNewPersonaje(p));
        //////////////////////////////////////////////////////////////
        ArrayList<Personaje> pers = repositorio.getAllPersonaje();
        assertEquals(true, repositorio.eliminar(pers.get(10).getId()));
        assertEquals(false, repositorio.eliminar(5));
    }

    @Test
    public void addRazaDB() {
        assertEquals(true, repositorio.addNewRaza("Elfo"));
        //este está hecho para buscar
    }


    @Test
    public void updatePersonajeDB() {
        p.setNombre("Lidia");
        p.setPeso("50");
        p.setGenero("Femenino");
        p.setHistoria("Lorem ipsu qwerty");
        p.setFecha("20/01/1999");
        p.setRaza("Orco");
        p.setPartida("En partida");
        assertEquals(true, repositorio.addNewPersonaje(p));
        ArrayList<Personaje> pers = repositorio.getAllPersonaje();
        assertEquals("Lidia", pers.get(10).getNombre());
        p2.setNombre("Lidio");
        p2.setPeso("51");
        p2.setGenero("Masculino");
        p2.setHistoria("Loremm ipsuuh");
        p2.setFecha("21/01/1999");
        p2.setRaza("Elfo");
        p2.setPartida("Fuera de partida");
        assertEquals(true, repositorio.actualizar(p2));
        pers = repositorio.getAllPersonaje();
        assertEquals(11, pers.size());
        assertEquals("Lidia", p.getNombre());
        assertEquals("50", p.getPeso());
        assertEquals("Femenino", p.getGenero());
        assertEquals("Lorem ipsu qwerty", p.getHistoria());
        assertEquals("20/01/1999", p.getFecha());
        assertEquals("Orco", p.getRaza());
        assertEquals("En partida", p.isPartida());
        assertEquals("Lidio", p2.getNombre());
        assertEquals("51", p2.getPeso());
        assertEquals("Masculino", p2.getGenero());
        assertEquals("Loremm ipsuuh", p2.getHistoria());
        assertEquals("21/01/1999", p2.getFecha());
        assertEquals("Elfo", p2.getRaza());
        assertEquals("Fuera de partida", p2.isPartida());
    }

    @Test
    public void searchPersonajeDB() {
        p.setNombre("Lidia");
        p.setPeso("50");
        p.setGenero("Femenino");
        p.setHistoria("All afjaij afa");
        p.setFecha("20/01/1999");
        p.setRaza("Orco");
        p.setPartida("En partida");
        assertEquals(true, repositorio.addNewPersonaje(p));
        ArrayList<Personaje> pers = repositorio.buscar(p.getNombre(), p.getFecha(), p.getRaza());
        assertEquals("Lidia", p.getNombre());
        assertEquals("20/01/1999", p.getFecha());
        assertEquals("Orco", p.getRaza());
        assertEquals(1, pers.size());
    }
}
