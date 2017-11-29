package com.sergiobt.ns10;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.support.v4.app.FragmentPagerAdapter;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends NavD implements GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener{

    private String usuario, contrasena,correo,usuarioR,contrasenaR, cedula, nombre, edad, tipos, genero, antecedentes, imc, presion, ultd;
    private int optLog;
    private GoogleApiClient googleApiClient;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    private SectionsPagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private MapFragment mapFragment;
    private ControlFragment controlFragment;

    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            usuarioR = extras.getString("usuario");
            contrasenaR = extras.getString("contrasena");


        }
        //setContentView(R.layout.activity_main);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedor_nav);
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);


        pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //Obteniendo EXTRAS
        Intent intent = getIntent();
        cedula = intent.getStringExtra(PerfilActivity.EXTRA_CEDULA);
        nombre = intent.getStringExtra(PerfilActivity.EXTRA_NOMBRE);
        edad = intent.getStringExtra(PerfilActivity.EXTRA_EDAD);
        tipos = intent.getStringExtra(PerfilActivity.EXTRA_TIPOS);
        genero = intent.getStringExtra(PerfilActivity.EXTRA_GENERO);
        antecedentes = intent.getStringExtra(PerfilActivity.EXTRA_ANTECEDENTES);
        imc = intent.getStringExtra(PerfilActivity.EXTRA_IMC);
        presion = intent.getStringExtra(PerfilActivity.EXTRA_PRESION);
        ultd = intent.getStringExtra(PerfilActivity.EXTRA_ULTD);

    }
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
                intent =  new Intent(MainActivity.this,
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
                            Intent intent =  new Intent(MainActivity.this,
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
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            String tag = null;
            switch (position) {
                case 0:
                    fragment = (MapFragment) getSupportFragmentManager().findFragmentByTag("");
                    if (fragment == null) {
                        fragment = new MapFragment().newInstance("","");
                    }
                    mapFragment = (MapFragment) fragment;
                    break;
                case 1:
                    fragment = (ControlFragment) getSupportFragmentManager().findFragmentByTag("");
                    if (fragment == null) {
                        fragment = new ControlFragment().newInstance(cedula,nombre,edad,tipos,genero,antecedentes,imc,presion,ultd);
                    }
                    controlFragment = (ControlFragment) fragment;
                    break;
                case 2:
                    fragment = (ControlFragment) getSupportFragmentManager().findFragmentByTag("");
                    if (fragment == null) {
                        fragment = new ControlFragment().newInstance(cedula,nombre,edad,tipos,genero,antecedentes,imc,presion,ultd);
                    }
                    controlFragment = (ControlFragment) fragment;
                    break;
            }

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if (fragment != null) {
                //ft.replace(R.id.main_content, fragment, tag);
                ft.addToBackStack(tag);
                ft.commit();
            }else {
            }

            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "¿Dónde Donar?";
                case 1:
                    return "Control";
            }
            return null;
        }

        public void cambiaTab(int position){
            String tag = null;
            switch (position) {
                case 0:
                    fragment = (MapFragment) getSupportFragmentManager().findFragmentByTag("");
                    if (fragment == null) {
                        fragment = new MapFragment().newInstance("","");
                    }
                    break;
                case 1:
                    fragment = (ControlFragment) getSupportFragmentManager().findFragmentByTag("");
                    if (fragment == null) {
                        fragment = new ControlFragment().newInstance(cedula,nombre,edad,tipos,genero,antecedentes,imc,presion,ultd);
                    }
                    controlFragment = (ControlFragment) fragment;
                    break;
                case 2:
                    fragment = (ControlFragment) getSupportFragmentManager().findFragmentByTag("");
                    if (fragment == null) {
                        fragment = new ControlFragment().newInstance(cedula,nombre,edad,tipos,genero,antecedentes,imc,presion,ultd);
                    }
                    controlFragment = (ControlFragment) fragment;
                    break;
            }

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if (fragment != null) {
                //ft.replace(R.id.main_content, fragment, tag);
                ft.addToBackStack(tag);
                ft.commit();
            }else {
            }
        }
    }
}
