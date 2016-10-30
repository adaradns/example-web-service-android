package com.service.web.example.adara.examplwebservice;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button boton, salir;
    TextView texto;
    List<MyTask> taskList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);



        //Ocultamos el progressBar
        progressBar.setVisibility(View.INVISIBLE);

        boton = (Button) findViewById(R.id.boton);
        texto = (TextView) findViewById(R.id.texto);
        texto.setMovementMethod(new ScrollingMovementMethod());

        taskList = new ArrayList<>();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()){
                    pedirDatos();
                }else{
                    Toast.makeText(getApplicationContext(), "Sin conexion", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void cargarDatos(String datos){

        texto.append(datos + "\n");
    }

    //verifica si hay conexion
    public boolean isOnline(){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }

    }

    public void pedirDatos(){
        //Instanciamos la tarea
        MyTask task = new MyTask();
        //la ejecutamos
        task.execute((Runnable) AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private class MyTask extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cargarDatos("Inicio de carga...");

            if(taskList.size() == 0){
                //Hacemos visible el progressBar
                progressBar.setVisibility(View.VISIBLE);
            }



            //agregar la tarea  a la lista de tareas
            taskList.add(this);
        }

        @Override
        protected String doInBackground(String... params) {
            for(int i=0; i <= 10; i++){
                publishProgress("Numero: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Finish";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            cargarDatos(s);
            //Removemos el hilo
            taskList.remove(this);
            if(taskList.size() == 0){
                progressBar.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            cargarDatos(values[0]);

        }
    }
}
