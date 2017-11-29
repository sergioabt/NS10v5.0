package com.sergiobt.ns10;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class InfoCitaActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    private String usuarioR,contrasenaR;
    private GoogleApiClient googleApiClient;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        switch(id){
            case R.id.mPerfil:
                intent =  new Intent(InfoCitaActivity.this,
                        PerfilActivity.class);
                intent.putExtra("usuario", usuarioR);
                intent.putExtra("contrasena", contrasenaR);

                startActivity(intent);
                finish();
                break;
            case R.id.mCerrar:
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if (status.isSuccess()){
                            Intent intent =  new Intent(InfoCitaActivity.this,
                                    LoginActivity.class);
                            intent.putExtra("usuario", usuarioR);
                            intent.putExtra("contrasena", contrasenaR);

                            prefs = getSharedPreferences("SP", Context.MODE_PRIVATE);
                            editor = prefs.edit();

                            editor.putInt("optlog", 0);
                            editor.commit();

                            startActivity(intent);
                            finish();

                        }
                    }
                });
                break;



        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_cita);
        Bundle extras = getIntent().getExtras();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        if (extras != null) {
            usuarioR = extras.getString("usuario");
            contrasenaR = extras.getString("contrasena");


        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
