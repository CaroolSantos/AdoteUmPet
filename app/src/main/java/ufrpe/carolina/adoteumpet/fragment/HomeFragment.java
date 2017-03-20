package ufrpe.carolina.adoteumpet.fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ufrpe.carolina.adoteumpet.R;
import ufrpe.carolina.adoteumpet.activity.MainActivity;
import ufrpe.carolina.adoteumpet.activity.ProfilePetActivity;
import ufrpe.carolina.adoteumpet.adapter.PetAdapter;
import ufrpe.carolina.adoteumpet.entity.Pet;
import ufrpe.carolina.adoteumpet.other.AdoteUmPetSharedPreferences;
import ufrpe.carolina.adoteumpet.other.ApiHttp;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView listPet;
    private ProgressDialog pdia;

    private OnFragmentInteractionListener mListener;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        listPet = (ListView)v.findViewById(R.id.listViewPet);
        pdia = new ProgressDialog(getActivity());
        pdia.setMessage(getResources().getString(R.string.carregando));
        Pet lista_pet[] = new Pet[]{
                new Pet(null,"", "Toddy", "Cachorro", "Macho", "Para adoção"),
                new Pet(null,"", "Rock", "Cachorro", "Macho", "Pet perdido"),
                new Pet(null,"", "Miau", "Gato", "Macho", "Para adoção")
        };



        listPet.setTextFilterEnabled(true);

// Bind onclick event handler
        listPet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (isTablet()){
                    ProfileFragment perfilFragment = new ProfileFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.detalhe, perfilFragment);
                    ft.commit();
                    Toast.makeText(getApplicationContext(), "Tablet!!", Toast.LENGTH_LONG).show();
                }
                else{
                    Pet tmp=(Pet) parent.getItemAtPosition(position);
                    Log.i("SELECTED PET",tmp.Id.toString());

                    Intent it = new Intent(getActivity(), ProfilePetActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("Id", tmp.Id); //Your id
                    it.putExtras(b); //Put your id to your next Intent

                    startActivity(it);
                }

            }
        });
        pdia.show();
        CarregarListaPet task = new CarregarListaPet();
        task.execute();

        return v;
    }

    private boolean isTablet(){
        return getActivity().findViewById(R.id.detalhe) != null;
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

    class CarregarListaPet extends AsyncTask<String,String,Pet[]> {
        private Exception exception;

        protected Pet[] doInBackground(String... args) {
            try {

                Log.d("TASK","REGISTER");

                ApiHttp api = new ApiHttp(getApplicationContext());

                try {
                    List<Pet> pets = api.getPets();

                    Pet[] petsArr = new Pet[pets.size()];
                    petsArr = pets.toArray(petsArr);

                    for(Pet p : petsArr)
                        Log.i("PET",p.nome);

                    return petsArr;
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
        protected void onPostExecute(Pet[] result){
            //super.onPostExecute(result);
            PetAdapter adapter = new PetAdapter(getActivity(), R.layout.listview_item_row, result);
            listPet.setAdapter(adapter);
            pdia.dismiss();
        }
    }
}
