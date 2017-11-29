package com.sergiobt.ns10;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ControlFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "cedula";
    private static final String ARG_PARAM2 = "nombre";
    private static final String ARG_PARAM3 = "edad";
    private static final String ARG_PARAM4 = "tipos";
    private static final String ARG_PARAM5 = "genero";
    private static final String ARG_PARAM6 = "antecedentes";
    private static final String ARG_PARAM7 = "imc";
    private static final String ARG_PARAM8 = "presion";
    private static final String ARG_PARAM9 = "ultd";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;
    private String mParam5;
    private String mParam6;
    private String mParam7;
    private String mParam8;
    private String mParam9;

    private OnFragmentInteractionListener mListener;

    View view;
    TextView tCedula, tNombre, tEdad, tTipos, tGenero, tAntecedentes, tImc, tPresion, tUltd;

    public ControlFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param cedula Parameter 1.
     * @param nombre Parameter 2.
     * @param edad Parameter 3.
     * @return A new instance of fragment ControlFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ControlFragment newInstance(String cedula, String nombre, String edad, String tipos, String genero, String antecedentes, String imc, String presion, String ultd) {
        ControlFragment fragment = new ControlFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, cedula);
        args.putString(ARG_PARAM2, nombre);
        args.putString(ARG_PARAM3, edad);
        args.putString(ARG_PARAM4, tipos);
        args.putString(ARG_PARAM5, genero);
        args.putString(ARG_PARAM6, antecedentes);
        args.putString(ARG_PARAM7, imc);
        args.putString(ARG_PARAM8, presion);
        args.putString(ARG_PARAM9, ultd);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
            mParam5 = getArguments().getString(ARG_PARAM5);
            mParam6 = getArguments().getString(ARG_PARAM6);
            mParam7 = getArguments().getString(ARG_PARAM7);
            mParam8 = getArguments().getString(ARG_PARAM8);
            mParam9 = getArguments().getString(ARG_PARAM9);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_control, container, false);
        tCedula = view.findViewById(R.id.tCedula);
        tNombre = view.findViewById(R.id.tNombre);
        tEdad = view.findViewById(R.id.tEdad);
        tTipos = view.findViewById(R.id.tTipos);
        tGenero = view.findViewById(R.id.tGenero);
        tAntecedentes = view.findViewById(R.id.tAntecedentes);
        tImc = view.findViewById(R.id.tImc);
        tPresion = view.findViewById(R.id.tPresion);
        tUltd = view.findViewById(R.id.tUltd);



        tCedula.setText(mParam1);
        tNombre.setText(mParam2);
        tEdad.setText(mParam3);
        tTipos.setText(mParam4);
        tGenero.setText(mParam5);
        tAntecedentes.setText(mParam6);
        tImc.setText(mParam7);
        tPresion.setText(mParam8);
        tUltd.setText(mParam9);


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
