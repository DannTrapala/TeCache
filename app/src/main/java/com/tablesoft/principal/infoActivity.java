package com.tablesoft.principal;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.regex.Pattern;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class infoActivity extends AppCompatActivity
{

    DatabaseHelper myDB;
    Button btnAdd,btnView;
    EditText editDonde, editQuien, editQue, editMonto, editComentarios;
    Intent intent;
    String address;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_info);

        intent = new Intent(infoActivity.this, ViewListContents.class);

        if(savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if (extras == null)
            {
                address = null;
            }
            else
            {
                address = extras.getString("ADDRESS");
            }
        }
        else
        {
            address = (String) savedInstanceState.getSerializable("ADDRESS");
        }

        editDonde = (EditText) findViewById(R.id.editDonde);
        editDonde.setText(address);

        editQuien = (EditText) findViewById(R.id.editQuien);
        editQue = (EditText) findViewById(R.id.editQue);
        editMonto = (EditText) findViewById(R.id.editMonto);
        editComentarios = (EditText) findViewById(R.id.editComentarios);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        myDB = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String testDonde = editDonde.getText().toString();
                String testQuien = editQuien.getText().toString();
                String testQue = editQue.getText().toString();
                String testMonto = editMonto.getText().toString();
                String testComentarios = editComentarios.getText().toString();
                String newEntry = "¿Dónde? "+editDonde.getText().toString()+"\n"
                        +"¿Para quién? "+editQuien.getText().toString()+"\n"
                        +"¿Para qué? "+editQue.getText().toString()+"\n"
                        +"El monto fue de: "+editMonto.getText().toString()+"\n"
                        +"Comentarios: " +editComentarios.getText().toString();
                if(testDonde.length()!= 0 && testQuien.length()!=0 && testQue.length()!=0 && testMonto.length()!=0)
                {
                    AddData(newEntry);
                    editDonde.setText("");
                }
                else
                {
                    Toast.makeText(infoActivity.this, "Debe ingresar los datos", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });


    }

    public void AddData(String newEntry)
    {

        boolean insertData = myDB.addData(newEntry);

        if(insertData==true)
        {
            Toast.makeText(this, "¡Datos insertados correctamente!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "¡Hubo un error!", Toast.LENGTH_LONG).show();
        }
    }


}
