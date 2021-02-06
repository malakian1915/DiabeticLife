package com.example.gustavo.diabeticlife.Splash_Screen;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.gustavo.diabeticlife.Dados_Usuario.BancoControllerUsuario;
import com.example.gustavo.diabeticlife.Dados_Usuario.Tela_Dados_Usuario;
import com.example.gustavo.diabeticlife.Inicio.Tela_Inicio;
import com.example.gustavo.diabeticlife.R;

public class Splash_Screen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        int SPLASH_TIME_OUT = 3000;

        BancoControllerUsuario crud = new BancoControllerUsuario(getBaseContext());

        final String result = crud.verificaSeCadastro();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(result.equals("sim")) {
                        Intent intent = new Intent(Splash_Screen.this, Tela_Inicio.class);
                        startActivity(intent);
                        finish();
                    }
                    else if(result.equals("não")){
                        Intent intent = new Intent(Splash_Screen.this, Tela_Dados_Usuario.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }, SPLASH_TIME_OUT);

    }

}
