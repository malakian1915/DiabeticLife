package com.example.gustavo.diabeticlife.Grafico;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gustavo.diabeticlife.Banco.BancoDeDados;
import com.example.gustavo.diabeticlife.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

public class Tela_Grafico extends AppCompatActivity {

    GraphView graphView;
    EditText edtY;
    Button btnInserir, btnApagar;

    float vY;
    float ultimoX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__grafico);

        graphView = (GraphView)findViewById(R.id.graph_view);
        edtY = (EditText)findViewById(R.id.edtY);
        btnInserir = (Button)findViewById(R.id.btnInserir);
        btnApagar = (Button)findViewById(R.id.btnApagar);

        // set manual X bounds
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(60);
        graphView.getViewport().setMaxY(255);

        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(1);
        graphView.getViewport().setMaxX(5000);

        // enable scaling
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScrollable(true);

        try{
            BancoControllerGrafico crud = new BancoControllerGrafico(getBaseContext());
            Cursor cursor = crud.retornaUltimoEixoX();
            if(cursor.equals(-1)){
                ultimoX = 1;
            }else{
                ultimoX = Float.parseFloat(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.EIXOXGRAFICO)));
            }
        }catch (Exception e){

        }

        consultar();

        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtY.getText().toString().isEmpty())
                    Toast.makeText(Tela_Grafico.this, "Preencha o campo com um valor", Toast.LENGTH_SHORT).show();
                else {
                    try {
                        BancoControllerGrafico crud = new BancoControllerGrafico(getBaseContext());
                        vY = Float.parseFloat(edtY.getText().toString());
                        if(vY >= 60 && vY <=255) {
                            String horario = android.text.format.DateFormat.format("dd/MM/yy hh:mm a", new java.util.Date()).toString();
                            initGraph();
                            edtY.setText(null);
                            crud.cadastrarDados(ultimoX, vY, horario);
                        }else{
                            Toast.makeText(Tela_Grafico.this, "Informe valores acima de 60 e abaixo de 255", Toast.LENGTH_SHORT).show();
                            edtY.setText(null);
                        }
                    }catch (Exception e){
                        Toast.makeText(Tela_Grafico.this, "Informe valores corretamente", Toast.LENGTH_SHORT).show();
                        edtY.setText(null);
                    }
                }
            }
        });

        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder msg = new AlertDialog.Builder(Tela_Grafico.this);
                msg.setTitle("ATENÇÃO");
                msg.setMessage("Deseja realmente apagar os dados?\nTodos registos serão excluídos");
                msg.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BancoControllerGrafico crud = new BancoControllerGrafico(getBaseContext());
                        crud.deletarTodosDados();
                        graphView.removeAllSeries();
                        Intent i = new Intent(Tela_Grafico.this, Tela_Grafico.class);
                        startActivity(i);
                        finish();
                    }
                });
                msg.setNegativeButton("NÃO", null);
                msg.show();
            }
        });

    }

    public void initGraph() {
        ultimoX++;
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(new DataPoint[]{new DataPoint(ultimoX,vY)});
        series.setColor(Color.RED);
        series.setSize(15);
        graphView.addSeries(series);
    }

    public void consultar(){
        try {
            BancoControllerGrafico crud = new BancoControllerGrafico(getBaseContext());
            Cursor cursor = crud.consultarDados();
            while(!cursor.isLast()){
                int valueX = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.EIXOXGRAFICO)));
                int valueY = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.EIXOYGRAFICO)));
                PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(new DataPoint[]{new DataPoint(valueX,valueY)});
                series.setColor(Color.RED);
                series.setSize(15);
                graphView.addSeries(series);
                cursor.moveToNext();
            }
            if(cursor.isLast()){
                int valueX = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.EIXOXGRAFICO)));
                int valueY = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.EIXOYGRAFICO)));
                PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(new DataPoint[]{new DataPoint(valueX,valueY)});
                series.setColor(Color.RED);
                series.setSize(15);
                graphView.addSeries(series);
            }
        } catch (Exception e){

        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
