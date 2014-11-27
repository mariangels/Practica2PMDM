package com.example.mariangeles.practica2pmdm;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Principal extends Activity {

    private EditText etTexto;
    File archivo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad);
        etTexto =(EditText)findViewById(R.id.etText);
        Uri url = getIntent().getData();
        leerArchivo(url);
    }

    public void leerArchivo(Uri ruta) {

        FileReader fr = null;
        BufferedReader br = null;
        String linea, texto="";
        try {
            archivo = new File(ruta.getPath());
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                texto += linea + "\n";
            }
            etTexto.setText(texto);

        } catch (IOException e) {
            Toast.makeText(this, "No se puede leer", Toast.LENGTH_SHORT).show();
        }
    }

    public void guardar(View view) {
        try {
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(archivo));
            out.write(etTexto.getText().toString());
            out.close();
            Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "No has guardado", Toast.LENGTH_SHORT).show();
        }
    }

}