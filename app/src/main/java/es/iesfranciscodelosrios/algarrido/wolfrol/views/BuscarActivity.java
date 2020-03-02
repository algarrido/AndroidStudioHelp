package es.iesfranciscodelosrios.algarrido.wolfrol.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import es.iesfranciscodelosrios.algarrido.wolfrol.R;
import es.iesfranciscodelosrios.algarrido.wolfrol.interfaces.BuscarInterface;
import es.iesfranciscodelosrios.algarrido.wolfrol.models.Personaje;
import es.iesfranciscodelosrios.algarrido.wolfrol.presenters.BuscarPresenter;

public class BuscarActivity extends AppCompatActivity implements BuscarInterface.View {
    String TAG = "WolfRol/BuscarActivity";
    private BuscarInterface.Presenter presenter;
    private DatePicker u;
    private static final String CERO = "0";
    private static final String BARRA = "/";
    private ArrayAdapter<String> adapter;
    private ArrayList<String> razas;
    private PersonajeAdapter adaptador;
    String url = "https://algarrido.github.io/AndroidStudio/";

    private Spinner spinner;
    //Calendario para obtener fecha
    public final Calendar c = Calendar.getInstance();
    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    //Widgets
    EditText etFecha;
    Button ibObtenerFecha;
    EditText nombre;
    ImageButton buttonAyuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //----------------------DECLARACION PARA LA FECHA-----------------------------------------------
        //Widget EditText donde se mostrara la fecha obtenida
        etFecha = (EditText) findViewById(R.id.editTextDate);
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        ibObtenerFecha = (Button) findViewById(R.id.buttonDatePicketB);
        //Evento setOnClickListener - clic
        ibObtenerFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerFecha();
            }
        });

        nombre = (EditText) findViewById(R.id.TextViewnombreB);

        presenter = new BuscarPresenter(this);
        presenter.botonVolver();
        ArrayList<String> items = new ArrayList<String>();

        //items.add("Orco");
        //items.add("Semi Elfo");
        //items.add("Humano");
        // items.add("");
        items = presenter.getAllRazas();

        // Definición del Adaptador que contiene la lista de opciones
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        // adaptador = new PersonajeAdapter(items);
        // Definición del Spinner
        spinner = (Spinner) findViewById(R.id.spinnerbuscar);
        spinner.setAdapter(adapter);

//BUSCAR
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String resultadoNombre = "";
                String resultadoFecha = "";
                String resultadoSpinner = "";
                if (nombre.getText().length() == 0) resultadoNombre = "%";
                else resultadoNombre = "%" + nombre.getText().toString() + "%";

                if (etFecha.getText().length() == 0) resultadoFecha = "%";
                else resultadoFecha = etFecha.getText().toString();

                if (spinner.getSelectedItem().toString().length() == 0) resultadoSpinner = "%";
                else resultadoSpinner = spinner.getSelectedItem().toString();
                Intent i = getIntent();

                i.putExtra("RESULTADONOMBRE", resultadoNombre);
                i.putExtra("RESULTADOFECHA", resultadoFecha);
                i.putExtra("RESULTADOSPINNER", resultadoSpinner);
                // presenter.filtrar(resultadoNombre,resultadoFecha,resultadoSpinner,nombre,etFecha,spinner);

                setResult(RESULT_OK, i);

                finish();
            }
        });
        buttonAyuda = (ImageButton) findViewById(R.id.imageButtonAyuda); // your image button

        // click event on your button
        buttonAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarAyuda();
            }
        });
    }

    private void obtenerFecha() {
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10) ? CERO + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10) ? CERO + String.valueOf(mesActual) : String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);

            }

        }, anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_menu_buscar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_ayudaB:
                Log.i(TAG, "ayuda...");
                ;
                presenter.pestaña4();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop...");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy...");
    }

    @Override
    public void volverListado() {
        Log.d(TAG, "Volviendo a Listado...");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed...");
        finish();
    }

    public void lanzarAyuda() {
        Intent intent = new Intent(getBaseContext(), AyudaActivity.class);
        intent.putExtra("activity_ayuda", "buscar");
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Log.d(TAG, "onSupportnavigateUp...");
        return super.onSupportNavigateUp();
    }

}
