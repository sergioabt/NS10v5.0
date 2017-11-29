package com.sergiobt.ns10;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sergiobt.ns10.Objetos.Donante;
import com.sergiobt.ns10.Objetos.FirebaseReferences;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    DatabaseReference myRef;

    // TODO: Rename and change types of parameters

    private String mParam1;
    private String mParam2;
    private GoogleApiClient googleApiClient;

    private OnFragmentInteractionListener mListener;

    EditText eCedula, eNombre, eEdad, eTipos, eGenero, eAntecedentes, eImc, ePresion, eUltd ;
    Button bGuardar;

    String cedula, nombre, edad, tipos, genero, antecedentes, imc, presion, ultd;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        final PerfilActivity perfilActivity = (PerfilActivity) getActivity();

        eCedula = (EditText)view.findViewById(R.id.eCedula);
        eNombre = (EditText)view.findViewById(R.id.eNombre);
        eEdad = (EditText)view.findViewById(R.id.eEdad);
        eTipos = (EditText) view.findViewById(R.id.eTipos);
        eGenero= (EditText) view.findViewById(R.id.eGenero);
        eAntecedentes= (EditText) view.findViewById(R.id.eAntecedentes);
        eImc= (EditText) view.findViewById(R.id.eImc);
        ePresion= (EditText) view.findViewById(R.id.ePresion);
        eUltd= (EditText) view.findViewById(R.id.eUltd);
        bGuardar = view.findViewById(R.id.bGuardar);

        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();


                cedula = eCedula.getText().toString();
                nombre = eNombre.getText().toString();
                edad = eEdad.getText().toString();
                tipos = eTipos.getText().toString();
                genero = eGenero.getText().toString();
                antecedentes = eAntecedentes.getText().toString();
                imc = eImc.getText().toString();
                presion = ePresion.getText().toString();
                ultd = eUltd.getText().toString();

                perfilActivity.mostrarControl(cedula, nombre, edad, tipos, genero, antecedentes, imc, presion, ultd);
                myRef = database.getReference(FirebaseReferences.USUARIO_REFERENCES);
                Donante donante = new Donante(cedula,nombre,edad,tipos,genero,antecedentes,imc,presion,ultd);
                myRef.child(FirebaseReferences.DONANTE_REFERENCES).push().setValue(donante);
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
