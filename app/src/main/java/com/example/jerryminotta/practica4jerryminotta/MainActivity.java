package com.example.jerryminotta.practica4jerryminotta;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView et_ingresarNombre;
    TextView et_ingresarTel;
    Switch sw_sexo;
    Button btn_agregar;
    ListView lv_contactos;

    ContactoAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_ingresarNombre= findViewById(R.id.et_ingresarNombre);
        et_ingresarTel= findViewById(R.id.et_ingresarTelefono);
        sw_sexo= findViewById(R.id.sw_sexo);
        btn_agregar= findViewById(R.id.btn_agregar);
        lv_contactos= findViewById(R.id.lv_contactos);


        customAdapter= new com.example.jerryminotta.practica4jerryminotta.ContactoAdapter(this);

        lv_contactos.setAdapter(customAdapter);




        //AQUI VA LA VUELTA DEL LIST VIEW


        //AGREGAR CONTACTO
        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreNew = et_ingresarNombre.getText().toString();
                String numeroNew = et_ingresarTel.getText().toString();
                String sexoNew;


                if(sw_sexo.isChecked()){
                    sexoNew= "F";
                     }else{
                    sexoNew= "M";
                }

                Contacto contactoNew= new Contacto(nombreNew, sexoNew, numeroNew);

                if(nombreNew.length()>0 && numeroNew.length()>0) {
                    customAdapter.agregarContacto(contactoNew);
                }else{
                    Toast.makeText(MainActivity.this, "Complete los campos.",Toast.LENGTH_SHORT).show();
                }

                et_ingresarNombre.setText("");
                et_ingresarTel.setText("");
                sw_sexo.setChecked(false);

                ocultarTeclado();

            }
        });
    }

    public void ocultarTeclado(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}
