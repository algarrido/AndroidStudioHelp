package es.iesfranciscodelosrios.algarrido.wolfrol.models;

import java.util.regex.Pattern;

public class Personaje {

    String TAG = "WolfRol/Personaje";

    public static final String REGEX_LETRAS = "[A-Za-z]*";
    public static final String REGEX_HIS = "^([a-zA-Z]+[ -\\.]?+[ -\\,]?+[ -\\ñ]?+[\\!-\\¡]?+[\\¿ -\\?]?)*$";
    //antigua public static final String REGEX_FECHA = "^(?:3[01]|[12][0-9]|0?[1-9])([\\-/.])(0?[1-9]|1[1-2])\\1\\d{4}$";
    public static final String REGEX_FECHAF = "(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|((29|30|31)[\\/]" +
            "(0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|[2-9][0-9])\\d\\d$)|(^29[\\/]02[\\/]" +
            "(19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)\n";

    private Integer id;
    private String imagen = null;
    private String nombre = null;
    private String historia = null;
    private String peso = null;
    private String fecha = null;
    private String genero;
    private String raza;
    private String partida;

    public Personaje() {

    }

    public String isPartida() {
        return partida;
    }

    public void setPartida(String partida) {

        this.partida = partida;

    }

    public String getFecha() {
        return fecha;
    }

    public String getRaza() {
        return raza;
    }

    public boolean setRaza(String raza) {
        Pattern patron = Pattern.compile(REGEX_LETRAS);
        String r = raza.trim();
        if (raza.isEmpty() || !patron.matcher(r).matches()) {
            return false;
        } else {
            this.raza = raza;
            return true;
        }
    }

    public String getPeso() {
        return peso;
    }

    public boolean setFecha(String fecha) {
        Pattern patron = Pattern.compile(REGEX_FECHAF);
        String f = fecha.trim();
        if (fecha.isEmpty() || !patron.matcher(f).matches()) {
            return false;
        } else {
            this.fecha = fecha;
            return true;
        }
    }

    public boolean setPeso(String peso) {
        Pattern patron = Pattern.compile(REGEX_LETRAS);
        String p = peso.trim();
        boolean correcto = false;
        try {

            if (peso.isEmpty() || patron.matcher(p).matches() || Integer.parseInt(peso) >= 200 || Integer.parseInt(peso) <= 0) {
                // return false;
                correcto = false;
            } else {
                this.peso = peso;
                correcto = true;
                //return true;
            }
        } catch (Exception e) {
        }
        return correcto;
    }

    public String getGenero() {
        return genero;
    }

    public boolean setGenero(String genero) {
        Pattern patron = Pattern.compile(REGEX_LETRAS);
        String g = genero.trim();
        if (genero.isEmpty() || !patron.matcher(g).matches()) {
            return false;
        } else {
            this.genero = genero;
            return true;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public boolean setImagen(String imagen) {

        this.imagen = imagen;
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean setNombre(String nombre) {
        Pattern patron = Pattern.compile(REGEX_LETRAS);
        String n = nombre.trim();

        if (nombre.isEmpty() || !patron.matcher(n).matches()) {
            return false;
        } else {
            this.nombre = nombre;
            return true;
        }
    }

    public String getHistoria() {
        return historia;
    }

    public boolean setHistoria(String historia) {

        Pattern patron = Pattern.compile(REGEX_HIS);
        String h = historia.trim();

        if (historia.isEmpty() ) {
            return false;
        }  else {
            this.historia = historia;
            return true;
        }

    }
}
