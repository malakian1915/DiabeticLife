package com.example.gustavo.diabeticlife.Medicamentos;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.gustavo.diabeticlife.R;

public class Tela_Medicamentos extends AppCompatActivity {

    CheckBox chkAcarbose, chkCanagliflozina, chkEmpagliflozina, chkExenatida, chkGlibenclamida, chkGliclazida,
             chkGlimepirida, chkGlipizida, chkInsulina, chkLiraglutida, chkMetformina, chkMiglitol,
             chkRosigitazona, chkSaxagliptina, chkSitagliptina, chkVildagliptina;
    EditText edtMaisMedicamentos;
    Button btnSalvarMedicamentos, btnVerMedicamentos;

    String[] medicamentos = {"","","","","","","","","","","","","","","",""};

    int day, month, year, hora, minuto;
    String date = null, horario = null;

    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__medicamentos);

        chkAcarbose = (CheckBox)findViewById(R.id.chkAcarbose);
        chkCanagliflozina = (CheckBox)findViewById(R.id.chkCanagliflozina);
        chkEmpagliflozina = (CheckBox)findViewById(R.id.chkEmpagliflozina);
        chkExenatida = (CheckBox)findViewById(R.id.chkExenatida);
        chkGlibenclamida = (CheckBox)findViewById(R.id.chkGlibenclamida);
        chkGliclazida = (CheckBox)findViewById(R.id.chkGliclazida);
        chkGlimepirida = (CheckBox)findViewById(R.id.chkGlimepirida);
        chkGlipizida = (CheckBox)findViewById(R.id.chkGlipizida);
        chkInsulina = (CheckBox)findViewById(R.id.chkInsulina);
        chkLiraglutida = (CheckBox)findViewById(R.id.chkLiraglutida);
        chkMetformina = (CheckBox)findViewById(R.id.chkMetformina);
        chkMiglitol = (CheckBox)findViewById(R.id.chkMiglitol);
        chkRosigitazona = (CheckBox)findViewById(R.id.chkRosigitazona);
        chkSaxagliptina = (CheckBox)findViewById(R.id.chkSaxagliptina);
        chkSitagliptina = (CheckBox)findViewById(R.id.chkSitagliptina);
        chkVildagliptina = (CheckBox)findViewById(R.id.chkVildagliptina);

        edtMaisMedicamentos = (EditText)findViewById(R.id.edtMaisRemedios);
        btnSalvarMedicamentos = (Button)findViewById(R.id.btnSalvarMedicamentos);
        btnVerMedicamentos = (Button)findViewById(R.id.btnVerMedicamentos);

        verificaCheckBox();

        btnSalvarMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cont == 0 && edtMaisMedicamentos.getText().toString().isEmpty()){
                    Toast.makeText(Tela_Medicamentos.this, "Selecione ou escreva um medicamento para salvar", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder msg = new AlertDialog.Builder(Tela_Medicamentos.this);
                    final DatePicker datePicker = new DatePicker(Tela_Medicamentos.this);
                    msg.setView(datePicker);
                    msg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            day = datePicker.getDayOfMonth();
                            month = datePicker.getMonth() + 1;
                            year = datePicker.getYear();
                            date = day + "/" + month + "/" + year;
                            pegarTimePicker();
                        }
                    });
                    msg.setNegativeButton("CANCELAR", null);
                    msg.show();
                }
            }
        });

        btnVerMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Medicamentos.this, Tela_Ver_Medicamentos.class);
                startActivity(intent);
            }
        });

    }

    public void pegarTimePicker(){
        if(date != null){
            AlertDialog.Builder mess = new AlertDialog.Builder(Tela_Medicamentos.this);
            final TimePicker timePicker = new TimePicker(Tela_Medicamentos.this);
            timePicker.setIs24HourView(true);
            mess.setView(timePicker);
            mess.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    hora = timePicker.getCurrentHour();
                    minuto = timePicker.getCurrentMinute();
                    horario = hora+":"+minuto;
                    mandaCadastro();
                }
            });
            mess.setNegativeButton("CANCELAR", null);
            mess.show();
        }
    }

    public void mandaCadastro(){
        BancoControllerMedicamentos crud = new BancoControllerMedicamentos(getBaseContext());
        StringBuilder strMedicamentos = new StringBuilder();
        strMedicamentos.append(medicamentos[0] + medicamentos[1] + medicamentos[2] + medicamentos[3] + medicamentos[4] + medicamentos[5] + medicamentos[6] + medicamentos[7] + medicamentos[8] + medicamentos[9] + medicamentos[10] + medicamentos[11] + medicamentos[12] + medicamentos[13] + medicamentos[14] + medicamentos[15]);
        String maisMedicamentos = edtMaisMedicamentos.getText().toString();
        String resultado;
        resultado = crud.cadastrarMedicamentos(strMedicamentos.toString(), date, horario, maisMedicamentos);
        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void verificaCheckBox(){
        chkAcarbose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkAcarbose.isChecked()) {
                    chkAcarbose.setBackgroundColor(Color.LTGRAY);
                    medicamentos[0] = "Acarbose. ";
                    cont++;
                }else{
                    chkAcarbose.setBackgroundColor(Color.WHITE);
                    medicamentos[0] = "";
                    cont--;
                }
            }
        });
        chkCanagliflozina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkCanagliflozina.isChecked()) {
                    chkCanagliflozina.setBackgroundColor(Color.LTGRAY);
                    medicamentos[1] = "Canagliflozina. ";
                    cont++;
                }else{
                    chkCanagliflozina.setBackgroundColor(Color.WHITE);
                    medicamentos[1] = "";
                    cont--;
                }
            }
        });
        chkEmpagliflozina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkEmpagliflozina.isChecked()){
                    chkEmpagliflozina.setBackgroundColor(Color.LTGRAY);
                    medicamentos[2] = "Empagliflozina. ";
                    cont++;
                }else{
                    chkEmpagliflozina.setBackgroundColor(Color.WHITE);
                    medicamentos[2] = "";
                    cont--;
                }
            }
        });
        chkExenatida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkExenatida.isChecked()){
                    chkExenatida.setBackgroundColor(Color.LTGRAY);
                    medicamentos[3] = "Exenatida. ";
                    cont++;
                }else{
                    chkExenatida.setBackgroundColor(Color.WHITE);
                    medicamentos[3] = "";
                    cont--;
                }
            }
        });
        chkGlibenclamida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkGlibenclamida.isChecked()){
                    chkGlibenclamida.setBackgroundColor(Color.LTGRAY);
                    medicamentos[4] = "Glibenclamida. ";
                    cont++;
                }else{
                    chkGlibenclamida.setBackgroundColor(Color.WHITE);
                    medicamentos[4] = "";
                    cont--;
                }
            }
        });
        chkGliclazida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkGliclazida.isChecked()){
                    chkGliclazida.setBackgroundColor(Color.LTGRAY);
                    medicamentos[5] = "Gliclazida. ";
                    cont++;
                }else{
                    chkGliclazida.setBackgroundColor(Color.WHITE);
                    medicamentos[5] = "";
                    cont--;
                }
            }
        });
        chkGlimepirida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkGlimepirida.isChecked()){
                    chkGlimepirida.setBackgroundColor(Color.LTGRAY);
                    medicamentos[6] = "Glimepirida. ";
                    cont++;
                }else{
                    chkGlimepirida.setBackgroundColor(Color.WHITE);
                    medicamentos[6] = "";
                    cont--;
                }
            }
        });
        chkGlipizida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkGlipizida.isChecked()){
                    chkGlipizida.setBackgroundColor(Color.LTGRAY);
                    medicamentos[7] = "Glipizida. ";
                    cont++;
                }else{
                    chkGlipizida.setBackgroundColor(Color.WHITE);
                    medicamentos[7] = "";
                    cont--;
                }
            }
        });
        chkInsulina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkInsulina.isChecked()){
                    chkInsulina.setBackgroundColor(Color.LTGRAY);
                    medicamentos[8] = "Insulina. ";
                    cont++;
                }else{
                    chkInsulina.setBackgroundColor(Color.WHITE);
                    medicamentos[8] = "";
                    cont--;
                }
            }
        });
        chkLiraglutida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkLiraglutida.isChecked()){
                    chkLiraglutida.setBackgroundColor(Color.LTGRAY);
                    medicamentos[9] = "Liraglutida. ";
                    cont++;
                }else{
                    chkLiraglutida.setBackgroundColor(Color.WHITE);
                    medicamentos[9] = "";
                    cont--;
                }
            }
        });
        chkMetformina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkMetformina.isChecked()){
                    chkMetformina.setBackgroundColor(Color.LTGRAY);
                    medicamentos[10] = "Metformina. ";
                    cont++;
                }else{
                    chkMetformina.setBackgroundColor(Color.WHITE);
                    medicamentos[10] = "";
                    cont--;
                }
            }
        });
        chkMiglitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkMiglitol.isChecked()){
                    chkMiglitol.setBackgroundColor(Color.LTGRAY);
                    medicamentos[11] = "Miglitol. ";
                    cont++;
                }else{
                    chkMiglitol.setBackgroundColor(Color.WHITE);
                    medicamentos[11] = "";
                    cont--;
                }
            }
        });
        chkRosigitazona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkRosigitazona.isChecked()){
                    chkRosigitazona.setBackgroundColor(Color.LTGRAY);
                    medicamentos[12] = "Rosigitazona. ";
                    cont++;
                }else{
                    chkRosigitazona.setBackgroundColor(Color.WHITE);
                    medicamentos[12] = "";
                    cont--;
                }
            }
        });
        chkSaxagliptina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkSaxagliptina.isChecked()){
                    chkSaxagliptina.setBackgroundColor(Color.LTGRAY);
                    medicamentos[13] = "Saxagliptina. ";
                    cont++;
                }else{
                    chkSaxagliptina.setBackgroundColor(Color.WHITE);
                    medicamentos[13] = "";
                    cont--;
                }
            }
        });
        chkSitagliptina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkSitagliptina.isChecked()){
                    chkSitagliptina.setBackgroundColor(Color.LTGRAY);
                    medicamentos[14] = "Sitagliptina. ";
                    cont++;
                }else{
                    chkSitagliptina.setBackgroundColor(Color.WHITE);
                    medicamentos[14] = "";
                    cont--;
                }
            }
        });
        chkVildagliptina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkVildagliptina.isChecked()){
                    chkVildagliptina.setBackgroundColor(Color.LTGRAY);
                    medicamentos[15] = "Vildagliptina.";
                    cont++;
                }else{
                    chkVildagliptina.setBackgroundColor(Color.WHITE);
                    medicamentos[15] = "";
                    cont--;
                }
            }
        });
    }

}
