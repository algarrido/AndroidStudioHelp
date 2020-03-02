package es.iesfranciscodelosrios.algarrido.wolfrol.views;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import es.iesfranciscodelosrios.algarrido.wolfrol.R;
import es.iesfranciscodelosrios.algarrido.wolfrol.interfaces.ListadoInterface;
import es.iesfranciscodelosrios.algarrido.wolfrol.models.Personaje;
import es.iesfranciscodelosrios.algarrido.wolfrol.presenters.ListadoPresenter;

public class ListadoActivity extends AppCompatActivity implements ListadoInterface.View {
    String TAG = "WolfRol/ListadoActivity";
    private ListadoInterface.Presenter presenter;
    private PersonajeAdapter adaptador;
    private ArrayList<Personaje> items;
    RecyclerView recyclerView;
    String url = "https://algarrido.github.io/AndroidStudio/";
    TextView contador;
    EditText etFecha;
    TextInputEditText etNombre;
    Spinner spinner;
    View vv;
    private final static int BUSCAR = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new ListadoPresenter(this);
        etNombre = (TextInputEditText) findViewById(R.id.nombre);
        //etHistoria = (TextView) findViewById(R.id.textViewHistoria);
        etFecha = (EditText) findViewById(R.id.editTextFechaF);
        contador = (TextView) findViewById(R.id.contadorTextView);
        spinner = (Spinner) findViewById(R.id.spinnerRaza);

        items = presenter.getAllPersonaje();
        recyclerView = (RecyclerView) findViewById(R.id.listadoReciclesView);

        int i = items.size();

        contador.setText(i + " resultados encontrados");
        adaptador = new PersonajeAdapter(items);
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al pulsar el elemento
                vv = v;
                int position = recyclerView.getChildAdapterPosition(v);
                Log.d(TAG, "Click RV: " + items.get(position).getId().toString());

                presenter.onClickRecyclerView(items.get(position).getId());


            }
        });
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //------------------------------------------------------------------------------------------------//
        FloatingActionButton fab = findViewById(R.id.listadoFb);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Pulsando boton flotante...");
                presenter.botonAñadir();
            }
        });
        swipeRecyclerView();
    }

    @Override
    public void swipeRecyclerView() {
        presenter.initRecyclerView(recyclerView, adaptador, itemTouchHelperCallback, this);
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            presenter.eliminar(viewHolder.getAdapterPosition() - 9);

            items.remove(viewHolder.getAdapterPosition());
            int i = items.size();
            contador.setText(i + " resultados encontrados");

            Log.d("eliminar", "pasa swiped");
            Log.d("eliminar", String.valueOf(viewHolder.getAdapterPosition() - 9) + " posicion restada");

            adaptador.notifyDataSetChanged();

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sobreMi:
                Log.i(TAG, "Sobre mi...");
                presenter.pestaña3();
                return true;
            case R.id.action_ordenar:
                Log.i(TAG, "Ordenar...");
                ;
                return true;

            case R.id.action_settings:
                Log.i(TAG, "Configuracion...");
                ;
                return true;
            case R.id.action_buscar:
                Log.i(TAG, "Buscar...");
                ;
                presenter.pestañaBuscar();
                return true;
            case R.id.action_ayuda:
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
    public void lanzarFormulario(int id) {
        Log.d(TAG, "Lanzando Formulario..");


        if (id == -1) {
            Intent intent = new Intent(ListadoActivity.this, FormularioActivity.class);
            // startActivity(intent);
            startActivityForResult(intent, 1);

        } else {
            Intent intent = new Intent(ListadoActivity.this, FormularioActivity.class);
            // startActivity(intent);
            int position = recyclerView.getChildAdapterPosition(vv);
            Log.d(TAG, "Click RV: " + items.get(position).getId().toString());
            //  intent.putExtra("ID", items.get(position).getId());
            Log.d(TAG, "editar " + items.get(position).getId().toString() + " este del listado");
            Log.d("editar", items.get(position).getPeso() + " peso este del listado");

            intent.putExtra("NOMBRE", items.get(position).getNombre());
            intent.putExtra("PESO", items.get(position).getPeso());
            intent.putExtra("ID", id);
            Log.d("editar", String.valueOf(id));
//          intent.putExtra("PESO", items.get(position).getPeso());
            intent.putExtra("GENERO", items.get(position).getGenero());
            intent.putExtra("HISTORIA", items.get(position).getHistoria());
//            //intent.putExtra("IMAGEN", items.get(position).getImagen());
            intent.putExtra("FECHA", items.get(position).getFecha());
            intent.putExtra("RAZA", items.get(position).getRaza());
            intent.putExtra("PARTIDA", items.get(position).isPartida());

            startActivityForResult(intent, 0);

            //BUNDLE
        }
    }


    @Override
    public void lanzarSobreMi() {
        Log.d(TAG, "Lanzando sobre mi...");
        Intent intent = new Intent(ListadoActivity.this, SobreMiActivity.class);
        startActivity(intent);
    }

    @Override
    public void lanzarAyuda() {
        Intent intent = new Intent(getBaseContext(), AyudaActivity.class);
        intent.putExtra("activity_ayuda", "listado");
        startActivity(intent);

    }


    @Override
    public void lanzarBuscar() {
        Log.d(TAG, "Lanzando buscar...");
        Intent i = new Intent(this, BuscarActivity.class);
        startActivityForResult(i, BUSCAR);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String resultadoNombre = "";
        String resultadoFecha = "";
        String resultadoSpinner = "";

        if (resultCode == RESULT_CANCELED) {

            Toast.makeText(this, "Resultado cancelado", Toast.LENGTH_SHORT)
                    .show();
        } else {
            try {
                switch (requestCode) {
                    case BUSCAR:
                        resultadoNombre = data.getExtras().getString("RESULTADONOMBRE");
                        resultadoFecha = data.getExtras().getString("RESULTADOFECHA");
                        resultadoSpinner = data.getExtras().getString("RESULTADOSPINNER");
                        Log.d("---------", "drctfvgybhunijsxdrc");
                        Log.d("Buscar", resultadoNombre + " este es el nombre");
                        Log.d("Buscar", resultadoFecha + " esto la fecha");
                        Log.d("Buscar", resultadoSpinner + " esto el spinner");
                        this.items = presenter.filtrar(resultadoNombre, resultadoFecha, resultadoSpinner);
                        //  adaptador = new PersonajeAdapter(this.items);
                        int i = items.size();
                        contador.setText(i + " resultados encontrados");
                        adaptador = new PersonajeAdapter(items);
                        recyclerView.setAdapter(adaptador);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));

                        break;


                }

            } catch (Exception e) {
                resultadoNombre = "";
                resultadoFecha = "";
                resultadoSpinner = "";
                Log.d("Buscar", "Error del buscar en listado");
            }


        }
    }
}

