package es.iesfranciscodelosrios.algarrido.wolfrol.views;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
        if(activity_ayuda.equals("listado")) {
            webview.loadUrl("https://algarrido.github.io/AndroidStudio/");
        }
        if(activity_ayuda.equals("formulario")) {
            webview.loadUrl("https://algarrido.github.io/AndroidStudio/");
        }
        if(activity_ayuda.equals("buscar")) {
            webview.loadUrl("https://algarrido.github.io/AndroidStudio/");
        }

        FloatingActionButton fab = findViewById(R.id.floatingVolver);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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