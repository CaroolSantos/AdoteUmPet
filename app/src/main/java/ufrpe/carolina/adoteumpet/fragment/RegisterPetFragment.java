package ufrpe.carolina.adoteumpet.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ufrpe.carolina.adoteumpet.R;
import ufrpe.carolina.adoteumpet.activity.RegisterActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterPetFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterPetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterPetFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner slctEspecie;
    private RadioGroup statusPet;
    private RadioGroup rgGender;
    private TextView edtIdadePet;
    private TextView edtRacaPet;
    private RadioGroup rgPorte;
    private TextView edtNomePet;
    private TextView edtDescricao;
    private TextView edtPhone;
    private TextView edtEmail;
    private Button btnsalvar;


    private OnFragmentInteractionListener mListener;

    public RegisterPetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterPetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterPetFragment newInstance(String param1, String param2) {
        RegisterPetFragment fragment = new RegisterPetFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastrar_pet, container, false);

        slctEspecie = (Spinner) view.findViewById(R.id.especiepet);
        statusPet = (RadioGroup) view.findViewById(R.id.AdocaoOuPerdido);
        rgGender = (RadioGroup) view.findViewById(R.id.sexopet);
        edtIdadePet = (EditText) view.findViewById(R.id.idadepet);
        edtRacaPet = (EditText) view.findViewById(R.id.racapet);
        rgPorte = (RadioGroup) view.findViewById(R.id.portepet);
        edtNomePet = (EditText) view.findViewById(R.id.edt_nomepet);
        edtDescricao = (EditText) view.findViewById(R.id.descricaopet);
        edtPhone = (EditText) view.findViewById(R.id.txtphone);
        edtEmail = (EditText) view.findViewById(R.id.txtmail);
        // Spinner click listener
        slctEspecie.setOnItemSelectedListener(this);

        List<String> especies = new ArrayList<String>();
        especies.add(getResources().getString(R.string.select));
        especies.add(getResources().getString(R.string.especie_cachorro));
        especies.add(getResources().getString(R.string.especie_gato));
        especies.add(getResources().getString(R.string.especie_passaro));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, especies);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        slctEspecie.setAdapter(dataAdapter);

        btnsalvar = (Button) view.findViewById(R.id.btn_salvar);
        btnsalvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(statusPet.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getActivity(),getResources().getString(R.string.status_vazio),Toast.LENGTH_LONG).show();
                    return;
                }
                String statusAdocao = "false";

                if(statusPet.getCheckedRadioButtonId() == R.id.PetPerdido){
                    statusAdocao = "true";
                }

                if(rgGender.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getActivity(),getResources().getString(R.string.sexo_vazio),Toast.LENGTH_LONG).show();
                    return;
                }
                String gender = "false";

                if(rgGender.getCheckedRadioButtonId() == R.id.sexomacho){
                    gender = "true";
                }

                if(edtRacaPet.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),getResources().getString(R.string.raca_vazio),Toast.LENGTH_LONG).show();
                    return;
                }

                if(rgPorte.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getActivity(),getResources().getString(R.string.porte_vazio),Toast.LENGTH_LONG).show();
                    return;
                }
                String rgPorte = "false";

                if(statusPet.getCheckedRadioButtonId() == R.id.portepequeno){
                    rgPorte = "pequeno";
                } else if (statusPet.getCheckedRadioButtonId() == R.id.portemedio) {
                    rgPorte = "medio";
                } else {
                    rgPorte = "grande";
                }

                if(edtNomePet.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),getResources().getString(R.string.nomepet_vazio),Toast.LENGTH_LONG).show();
                    return;
                }

                if(edtDescricao.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),getResources().getString(R.string.descricao_vazio),Toast.LENGTH_LONG).show();
                    return;
                }

                if(edtPhone.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),getResources().getString(R.string.telefone_vazio),Toast.LENGTH_LONG).show();
                    return;
                }

                if(edtEmail.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),getResources().getString(R.string.email_vazio),Toast.LENGTH_LONG).show();
                    return;
                }
                Register register = new Register();
                register.execute(
                        slctEspecie.getSelectedItem().toString(),
                        statusAdocao,
                        gender,
                        edtRacaPet.getText().toString(),
                        rgPorte,
                        edtNomePet.getText().toString(),
                        edtDescricao.getText().toString(),
                        edtPhone.getText().toString(),
                        edtEmail.getText().toString()
                );
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
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
