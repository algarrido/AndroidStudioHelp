package es.iesfranciscodelosrios.algarrido.wolfrol.views;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import es.iesfranciscodelosrios.algarrido.wolfrol.R;
import es.iesfranciscodelosrios.algarrido.wolfrol.interfaces.BuscarInterface;
import es.iesfranciscodelosrios.algarrido.wolfrol.interfaces.FormularioInterface;
import es.iesfranciscodelosrios.algarrido.wolfrol.models.Personaje;
import es.iesfranciscodelosrios.algarrido.wolfrol.presenters.FormularioPresenter;


public class FormularioActivity extends AppCompatActivity implements FormularioInterface.View {
    String TAG = "WolfRol/FormularioActivity";
    private FormularioInterface.Presenter presenter;

    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private FloatingActionButton button;
    private DatePicker u;
    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final int REQUEST_SELECT_IMAGE = 201;
    //Calendario para obtener fecha
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    private Bitmap bmp;
    //Widgets
    EditText etFecha;
    Button ibObtenerFecha;
    String iid = "";
    TextInputEditText nombree, pesoo, generoo;
    EditText historiaa;
    String razas;
    View v;
    TextInputLayout pesoInputLayout;
    TextInputLayout nombreInputLayout;
    TextInputLayout generoInputLayout;
    final Context context = this;
    ImageView gallery;
    final private int CODE_READ_EXTERNAL_STORAGE_PERMISSION = 123;
    private Uri uri;
    Button guardar;
    Switch partida;
    Button btn;
    Button editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new FormularioPresenter(this);
        presenter.botonVolver();
//-----------------------------------VALIDACION DE CAMPOS-----------------------------------------
        guardar = (Button) findViewById(R.id.ButtonGuardar);
        pesoInputLayout = (TextInputLayout) findViewById(R.id.TextPeso);
        nombreInputLayout = (TextInputLayout) findViewById(R.id.TextNombre);
        generoInputLayout = (TextInputLayout) findViewById(R.id.TextGenero);

        final TextInputEditText p = (TextInputEditText) findViewById(R.id.peso);
        final TextInputEditText n = (TextInputEditText) findViewById(R.id.nombre);
        final TextInputEditText g = (TextInputEditText) findViewById(R.id.genero);

        p.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                presenter.validacionCampoPeso(hasFocus, pesoInputLayout, p, guardar);


            }
        });
        final EditText f = (EditText) findViewById(R.id.editTextFechaF);
        f.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                presenter.validacionCampoFecha(hasFocus, f);
            }
        });

        n.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                presenter.validacionCampoNombre(hasFocus, nombreInputLayout, n, guardar);


            }
        });
        g.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                presenter.validacionCampoGenero(hasFocus, generoInputLayout, g, guardar);


            }
        });
        // Definición de la lista de opciones------------------------------------
        ArrayList<String> items = new ArrayList<String>();

        items = presenter.getAllRazas();

        // Definición del Adaptador que contiene la lista de opciones
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        // Definición del Spinner
        spinner = (Spinner) findViewById(R.id.spinnerRaza);
        spinner.setAdapter(adapter);

        // Definición de la acción del botón
        button = (FloatingActionButton) findViewById(R.id.floatingActionButtonAdd);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Recuperación de la vista del AlertDialog a partir del layout de la Actividad
                LayoutInflater layoutActivity = LayoutInflater.from(context);
                View viewAlertDialog = layoutActivity.inflate(R.layout.alert_dialog, null);

                // Definición del AlertDialog
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                // Asignación del AlertDialog a su vista
                alertDialog.setView(viewAlertDialog);

                // Recuperación del EditText del AlertDialog
                final EditText dialogInput = (EditText) viewAlertDialog.findViewById(R.id.dialogInput);

                // Configuración del AlertDialog
                alertDialog
                        .setCancelable(false)
                        // Botón Añadir
                        .setPositiveButton(getResources().getString(R.string.add),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        adapter.add(dialogInput.getText().toString());
                                        presenter.guardarRaza(dialogInput.getText().toString());
                                        spinner.setSelection(adapter.getPosition(dialogInput.getText().toString()));
                                    }
                                })
                        // Botón Cancelar
                        .setNegativeButton(getResources().getString(R.string.cancel),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                })
                        .create()
                        .show();
            }
        });


        //----------------------DECLARACION PARA LA FECHA-----------------------------------------------
        //Widget EditText donde se mostrara la fecha obtenida
        etFecha = (EditText) findViewById(R.id.editTextFechaF);
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        ibObtenerFecha = (Button) findViewById(R.id.ButtonDatePicket);
        //Evento setOnClickListener - clic
        ibObtenerFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerFecha();
            }
        });

//--------------------------------------------BOTON GUARDAR-------------------------------------------
        Button fab = findViewById(R.id.ButtonGuardar);
        nombree = (TextInputEditText) findViewById(R.id.nombre);
        pesoo = (TextInputEditText) findViewById(R.id.peso);
        generoo = (TextInputEditText) findViewById(R.id.genero);
        historiaa = (EditText) findViewById(R.id.editTextHistoria);
        partida = (Switch) findViewById(R.id.switchEnPartida);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Personaje p = new Personaje();
                p.setNombre(nombree.getText().toString());
                p.setPeso(pesoo.getText().toString());
                p.setGenero(generoo.getText().toString());
                p.setHistoria(historiaa.getText().toString());
                p.setRaza(spinner.getSelectedItem().toString());
                p.setFecha(etFecha.getText().toString());
                // p.setImagen(gallery.getDrawable().toString());
                if (partida.isChecked()) {
                    p.setPartida(partida.getText().toString());
                } else {
                    p.setPartida("Fuera de partida");
                }

                presenter.guardarFormulario(p, new FormularioPresenter.Callback() {
                    @Override
                    public void onOk() {
                        Toast.makeText(FormularioActivity.this, "Guardando el formulario...", Toast.LENGTH_SHORT).show(); //Correcto
                    }

                    @Override
                    public void onError(String errMsg) {
                        Toast.makeText(FormularioActivity.this, errMsg, Toast.LENGTH_SHORT).show(); //error
                    }
                });
            }
        });
        //------------------------------BOTON ELIMINAR---------------------------------------------------
        // final String i= getIntent().getExtras().getString("ID").toString();

        btn = findViewById(R.id.ButtonEliminar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vieww) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(FormularioActivity.this);
                dialog.setTitle(R.string.Borrado);
                dialog.setMessage(R.string.mensaje);
                dialog.setCancelable(true);
                // Log.d("eliminar", id);
                dialog.setPositiveButton(
                        "Sí",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int
                                    id) {
                                //no implementado
                                presenter.eliminar(1);

                                Log.d("eliminar", "pasa");

                                finish();
                               /* Intent intent = new Intent(FormularioActivity.this, ListadoActivity.class);
                                startActivity(intent);
*/
                            }
                        });
                dialog.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = dialog.create();
                alert.show();
            }

        });
        editar = (Button) findViewById(R.id.ButtonEditar);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vieww) {

                Personaje perso = new Personaje();

                perso.setNombre(nombree.getText().toString());
                perso.setPeso(pesoo.getText().toString());
                perso.setGenero(generoo.getText().toString());
                perso.setHistoria(historiaa.getText().toString());
                //String i = gallery.getDrawable().toString();
                perso.setFecha(etFecha.getText().toString());
                perso.setRaza(spinner.getSelectedItem().toString());
                perso.setPartida(partida.getText().toString());

                Log.d("editar", n + " dentro del boton editar");


                presenter.editar(perso);

            }
        });


        //////////////////////////////////PERMISO PARA GALERIA////////////////////////////////////////////
        gallery = (ImageView) findViewById(R.id.imageViewPersonaje);
        gallery.setClickable(true);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View vev) {
                presenter.onClickImage(context);
            }
        });
        ///////////////////////////////////////////////////////////////////////////


        ImageView iv = findViewById(R.id.imageViewPersonaje);
        presenter.galeria(gallery, iv, bmp);

        //////////////////////

        try {

            this.nombree.setText(getIntent().getExtras().getString("NOMBRE").toString());

            // this.pesoo.setText(getIntent().getExtras().getString("PESO").toString());
            // perso.setPeso(String.valueOf(pesoo));
            // this.generoo.setText(getIntent().getExtras().get("GENERO").toString());
            //perso.setGenero(String.valueOf(generoo));
            //this.historiaa.setText(getIntent().getExtras().get("HISTORIA").toString());
            //perso.setHistoria(String.valueOf(historiaa));
            //this.etFecha.setText(getIntent().getExtras().get("FECHA").toString());
            //perso.setFecha(String.valueOf(etFecha));
//            //this.gallery.get(getIntent().getExtras().get("IMAGEN").toString());
            // this.spinner.getSelectedItem(Integer.parseInt(getIntent().getExtras().get("RAZA").toString()));
            //this.partida.setText(getIntent().getExtras().get("PARTIDA").toString());

            this.guardar.setVisibility(View.INVISIBLE);

        } catch (Exception e) {
            this.btn.setVisibility(View.INVISIBLE);
            this.editar.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public void selectPicture() {
        // Se le pide al sistema una imagen del dispositivo
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, getResources().getString(R.string.seleccion)),
                REQUEST_SELECT_IMAGE);
    }

    @Override
    public void cerrarFormulario() {
        finish();
        Intent intent = new Intent(FormularioActivity.this, ListadoActivity.class);
        // startActivity(intent);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case (REQUEST_SELECT_IMAGE):
                if (resultCode == FormularioActivity.RESULT_OK) {
                    // Se carga la imagen desde un objeto Bitmap
                    Uri selectedImage = data.getData();
                    String selectedPath = selectedImage.getPath();

                    if (selectedPath != null) {
                        // Se leen los bytes de la imagen
                        InputStream imageStream = null;
                        try {
                            imageStream = getContentResolver().openInputStream(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        // Se transformam los bytes de la imagen a un Bitmap
                        Bitmap bmp = BitmapFactory.decodeStream(imageStream);

                        // Se carga el Bitmap en el ImageView
                        ImageView imageView = findViewById(R.id.imageViewPersonaje);
                        imageView.setImageBitmap(bmp);
                    }
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_menu_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_ayudaF:
                Log.i(TAG, "ayuda...");
                ;
                presenter.pestaña4();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
    public void lanzarAyuda() {
        Intent intent = new Intent(getBaseContext(), AyudaActivity.class);
        intent.putExtra("activity_ayuda", "formulario");
        startActivity(intent);
    }

    @Override
    public void volverListado() {
        Log.d(TAG, "Volviendo a Listado...");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void requestPermision() {
        ActivityCompat.requestPermissions(FormularioActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CODE_READ_EXTERNAL_STORAGE_PERMISSION);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CODE_READ_EXTERNAL_STORAGE_PERMISSION:

                presenter.resultPermission(grantResults[0], new FormularioPresenter.Callback() {
                    @Override
                    public void onOk() {
                        Log.d(TAG, getResources().getString(R.string.permisoOk));
                    }

                    @Override
                    public void onError(String errMsg) {
                        CoordinatorLayout coordinatorLayout = findViewById(R.id.CoordinatorLayout);

                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, getResources().getString(R.string.permiso), Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                });

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed...");
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Log.d(TAG, "onSupportnavigateUp...");
        return super.onSupportNavigateUp();
    }


}

