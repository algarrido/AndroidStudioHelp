package es.iesfranciscodelosrios.algarrido.wolfrol.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import es.iesfranciscodelosrios.algarrido.wolfrol.R;

public class LogoActivity extends Activity {
    String TAG="WolfRol/LogoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), ListadoActivity.class));
                finish();
            }
        }, 5000);
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"onStart...");

    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"onResume...");

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG,"onPause...");

    }
    @Override
    protected void onStop(){
        super.onStop();

        Log.d(TAG,"onStop...");

    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG,"onRestart...");

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy...");

    }

}
