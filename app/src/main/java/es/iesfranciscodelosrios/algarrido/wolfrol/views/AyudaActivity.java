package es.iesfranciscodelosrios.algarrido.wolfrol.views;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.iesfranciscodelosrios.algarrido.wolfrol.R;
import es.iesfranciscodelosrios.algarrido.wolfrol.interfaces.AyudaInterface;
import es.iesfranciscodelosrios.algarrido.wolfrol.presenters.AyudaPresenter;

public class AyudaActivity extends AppCompatActivity implements AyudaInterface.View {
    private WebView mWebview;
    String link = "";// global variable
    Resources res;// global variable
    private AyudaInterface.Presenter presenter;



    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);


        Toolbar toolbar = findViewById(R.id.toolbar_ayuda);
        setSupportActionBar(toolbar);
        presenter = new AyudaPresenter(this);

        presenter.botonVolver();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // recibirDatos();
        webview = (WebView) findViewById(R.id.webviewAyuda);
        webview.getSettings().setJavaScriptEnabled(true); // enable javascript

        String activity_ayuda = getIntent().getStringExtra("activity_ayuda");
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            if(activity_ayuda.equals("listado")) {
                webview.loadUrl("https://algarrido.github.io/AndroidStudioHelp/listado.html");
            }
            if(activity_ayuda.equals("formulario")) {
                webview.loadUrl("https://algarrido.github.io/AndroidStudioHelp/formulario.html");
            }
            if(activity_ayuda.equals("buscar")) {
                webview.loadUrl("https://algarrido.github.io/AndroidStudioHelp/buscar.html");
            } // Si hay conexión a Internet en este momento
        } else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "No hay conexión a internet, por favor vuelva a cargar la página", Toast.LENGTH_SHORT);

            toast1.show();
        }




    }

    /**
     * Opens the URL in a browser
     */
    public void volverListado() {
        Log.d("", "Volviendo a Listado...");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("", "onBackPressed...");
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Log.d("", "onSupportnavigateUp...");
        return super.onSupportNavigateUp();
    }

}