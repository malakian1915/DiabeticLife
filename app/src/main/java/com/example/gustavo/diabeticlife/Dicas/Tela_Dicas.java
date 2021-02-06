package com.example.gustavo.diabeticlife.Dicas;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.gustavo.diabeticlife.R;

public class Tela_Dicas extends AppCompatActivity {

    ViewPager viewPager;
    CustomSwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__dicas);

        viewPager = (ViewPager)findViewById(R.id.vp_Dicas);
        adapter = new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
