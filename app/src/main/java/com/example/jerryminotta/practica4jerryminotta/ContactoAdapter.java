package com.example.jerryminotta.practica4jerryminotta;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ContactoAdapter extends BaseAdapter  {

    ArrayList<Contacto> contactos;
    Activity activity;
    private static final int REQUEST_CALL= 1;


    public ContactoAdapter(Activity activity) {
        this.activity = activity;
        contactos= new ArrayList<Contacto>();
    }

    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Object getItem(int i) {
        return contactos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater= activity.getLayoutInflater();

        //ojoooooooooooooooooooooooo
        View renglon= inflater.inflate(R.layout.activity_renglon_contacto, null, false);

        TextView tv_nombreContacto = renglon.findViewById(R.id.tv_nombreGuardado);
        TextView tv_numeroContacto = renglon.findViewById(R.id.tv_numeroGuardado);
        ImageView btn_llamar= renglon.findViewById(R.id.btn_llamar);
        ImageView btn_eliminar= renglon.findViewById(R.id.btn_eliminar);

        ImageView iv_iconoSexo = renglon.findViewById(R.id.iv_iconoSexo);

        //cargar iconos
        btn_llamar.setImageResource(R.drawable.call);
        btn_eliminar.setImageResource(R.drawable.delete);
        //-------------


        tv_nombreContacto.setText(contactos.get(position).getNombre());
        tv_numeroContacto.setText(contactos.get(position).getNumero());
        String sexo = contactos.get(position).getSexo();

        final String numeroSacado = contactos.get(position).getNumero();

        if (sexo.equals("M")){
            iv_iconoSexo.setImageResource(R.drawable.male);
        }else{
            iv_iconoSexo.setImageResource(R.drawable.female);
        }
        //No se si aqui tambien se obtiene el sex para el cambio de la imagen

        btn_llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aqui es donde debo hacer la llamada
                hacerLlamada(numeroSacado);
            }
        });

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactos.remove(position);
                notifyDataSetChanged();
            }
        });


        return renglon;
    }

    public void agregarContacto(Contacto contacto){
        contactos.add(contacto);
        notifyDataSetChanged();

    }

    public void hacerLlamada(String number){

    if(number.trim().length()>0){
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);

        } else{
            String dial= "tel:" + number;
            activity.startActivity( new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }
    }

}
