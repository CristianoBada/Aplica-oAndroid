package com.cristianobadalotti.aplicacaograjas.Forms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.cristianobadalotti.aplicacaograjas.Entidades.Corte;
import com.cristianobadalotti.aplicacaograjas.R;

public class SplashActivity extends AppCompatActivity {

    private int code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Processando dados...");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        verificaParametros();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (code == 0) {
                    startActivity(new Intent(getBaseContext(), LoginActivity.class));
                }
                if (code == 1) {
                    Intent intent = new Intent(SplashActivity.this, EditarCorteActivity.class);
                    startActivityForResult(intent, 0);
                }
                finish();
            }
        }, 5000);
    }

    private void verificaParametros() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if ((bundle != null) && (bundle.containsKey("CODE"))) {
                code = (int) bundle.getSerializable("CODE");
            }
        }
    }
}
