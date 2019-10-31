package ufrpe.carolina.adoteumpet.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import ufrpe.carolina.adoteumpet.R;
import ufrpe.carolina.adoteumpet.activity.MainActivity;
import ufrpe.carolina.adoteumpet.other.AdoteUmPetSharedPreferences;
import ufrpe.carolina.adoteumpet.other.ApiHttp;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterShelterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterShelterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterShelterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView imgvAvatar;
    private Button btnAvatar;
    private EditText edtName;
    private  EditText edtPhone;
    private EditText edtEmail;
    private EditText edtAddress;
    private Button btnSave;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    private ProgressDialog pdia;

    private OnFragmentInteractionListener mListener;

    public RegisterShelterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterShelterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterShelterFragment newInstance(String param1, String param2) {
        RegisterShelterFragment fragment = new RegisterShelterFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_shelter, container, false);

        imgvAvatar = (ImageView) view.findViewById(R.id.shelterAvatar);
        btnAvatar = (Button) view.findViewById(R.id.btn_abrigo);
        edtName = (EditText) view.findViewById(R.id.nome_abrigo);
        edtPhone = (EditText) view.findViewById(R.id.txtphone);
        edtEmail = (EditText) view.findViewById(R.id.txtmail);
        edtAddress = (EditText) view.findViewById(R.id.end_abrigo);
        btnSave = (Button) view.findViewById(R.id.btn_salvar);
        pdia = new ProgressDialog(getActivity());
        pdia.setMessage(getResources().getString(R.string.carregando));

        btnAvatar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,
                        CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(edtName.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),getResources().getString(R.string.abrigo_nome_vazio),Toast.LENGTH_LONG).show();
                    return;
                }

                if(edtPhone.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),getResources().getString(R.string.abrigo_telefone_vazio),Toast.LENGTH_LONG).show();
                    return;
                }

                if(edtEmail.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),getResources().getString(R.string.abrigo_email_vazio),Toast.LENGTH_LONG).show();
                    return;
                }

                if(edtAddress.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),getResources().getString(R.string.abrigo_endereco_vazio),Toast.LENGTH_LONG).show();
                    return;
                }

                Log.i("REGISTER FIELDS","OK - API");
                pdia.show();
                RegisterShelter registerShelter = new RegisterShelter();
                registerShelter.execute(
                        edtName.getText().toString(),
                        edtPhone.getText().toString(),
                        edtEmail.getText().toString(),
                        edtAddress.getText().toString()
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                // convert byte array to Bitmap

                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                        byteArray.length);

                imgvAvatar.setImageBitmap(bitmap);

            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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

    class RegisterShelter extends AsyncTask<String,String,String> {
        private Exception exception;


        protected String doInBackground(String... args) {
            try {
                //Post para API

                String name = args[0];
                String phone = args[1];
                String email = args[2];
                String address = args[3];

                Log.d("TASK","REGISTER SHELTER");

                ApiHttp api = new ApiHttp(getApplicationContext());

                try {
                    boolean registrado = api.registrarAbrigo(getApplicationContext(),
                            name,
                            phone,
                            email,
                            address,
                            AdoteUmPetSharedPreferences.getUserId(getApplicationContext()));

                    if(registrado){
                        getActivity().runOnUiThread(new Runnable(){

                            @Override
                            public void run(){
                                Toast.makeText(getActivity(),getResources().getString(R.string.abrigo_cadastro_sucesso),Toast.LENGTH_LONG).show();
                            }
                        });
                        Intent it = new Intent(getActivity(), MainActivity.class);
                        startActivity(it);
                    }else{

                        getActivity().runOnUiThread(new Runnable(){

                            @Override
                            public void run(){
                                Toast.makeText(getActivity(),getResources().getString(R.string.erro_registrar),Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }


            } catch (Exception e) {
                this.exception = e;

                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            pdia.dismiss();
        }
    }
}
