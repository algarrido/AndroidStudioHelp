package es.iesfranciscodelosrios.algarrido.wolfrol;

import org.junit.Test;

import es.iesfranciscodelosrios.algarrido.wolfrol.models.Personaje;

import static org.junit.Assert.assertEquals;

public class PersonajeUnitTest {
    Personaje p = new Personaje();

    @Test
    public void nombreTest() {

        assertEquals(true, p.setNombre("Lidia"));
        assertEquals(false, p.setNombre(""));
        assertEquals(false, p.setNombre("123"));
        assertEquals(false, p.setNombre("*"));
        assertEquals(false, p.setNombre("*Lidia"));
        assertEquals(false, p.setNombre("Lidia*"));
        assertEquals("Lidia", p.getNombre());

    }

    @Test
    public void fechaTest() {

        assertEquals(true, p.setFecha("10/01/2020"));
        assertEquals(false, p.setFecha(""));
        assertEquals(false, p.setFecha("*"));
        assertEquals(false, p.setFecha("qwe"));
        assertEquals(false, p.setFecha("1234"));
        assertEquals(false, p.setFecha("31/02/2020"));
        assertEquals(false, p.setFecha("1/1/20"));
        assertEquals(false, p.setFecha("10-10-2020"));
        assertEquals(false, p.setFecha("10.10.2020"));
        assertEquals(false, p.setFecha("10102020"));
        assertEquals(false, p.setFecha("2020/01/10"));
        assertEquals(false, p.setFecha("2020-01-10"));
        assertEquals(false, p.setFecha("2020.01.10"));
        assertEquals("10/01/2020", p.getFecha());

    }

    @Test
    public void pesoTest() {
        assertEquals(true, p.setPeso("120"));
        assertEquals(false, p.setPeso("201"));
        assertEquals(false, p.setPeso("-1"));
        assertEquals(false, p.setPeso("0"));
        assertEquals(false, p.setPeso(""));
        assertEquals(false, p.setPeso("qwe"));
        assertEquals(false, p.setPeso("1*"));
        assertEquals(false, p.setPeso("*1"));
        assertEquals(false, p.setPeso("*"));
        assertEquals("120", p.getPeso());

    }

    @Test
    public void generoTest() {
        assertEquals(true, p.setGenero("Femenino"));
        assertEquals(false, p.setGenero("123"));
        assertEquals(false, p.setGenero(""));
        assertEquals(false, p.setGenero("*"));
        assertEquals(false, p.setGenero("*Femenino"));
        assertEquals(false, p.setGenero("Femenino*"));
        assertEquals("Femenino", p.getGenero());

    }

    @Test
    public void razaTest() {
        assertEquals(true, p.setRaza("Orco"));
        assertEquals(false, p.setRaza("123"));
        assertEquals(false, p.setRaza(""));
        assertEquals(false, p.setRaza("*"));
        assertEquals(false, p.setRaza("*Orco"));
        assertEquals(false, p.setRaza("Orco*"));
        assertEquals("Orco", p.getRaza());

    }
    @Test
    public void historiaTest() {

        assertEquals(true, p.setHistoria("Lorem Ipsum is"));
        assertEquals(true, p.setHistoria("Lorem Ipsum is dummy,."));
        assertEquals(true, p.setHistoria("Lorem Ipsum is ¿simply?"));
        assertEquals(true, p.setHistoria("En el año 1234"));
        assertEquals(false, p.setHistoria(""));

        assertEquals("En el año 1234", p.getHistoria());



    }
}
