package com.tablesoft.tecache;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //Declaracion de variables
    Button Bot1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buscar por id
        Bot1=(Button)findViewById(R.id.botonini);

        //escuchar
        Bot1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if(view.getId()==R.id.botonini){
            try{
                Intent intent=new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);


            }catch (Exception e){

            }

        }//fin del if
    }

}
