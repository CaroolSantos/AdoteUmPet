package ufrpe.carolina.adoteumpet.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import ufrpe.carolina.adoteumpet.R;
import ufrpe.carolina.adoteumpet.adapter.ShelterAdapter;
import ufrpe.carolina.adoteumpet.entity.Shelter;
import ufrpe.carolina.adoteumpet.fragment.dummy.DummyContent.DummyItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ShelterFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ShelterFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ShelterFragment newInstance(int columnCount) {
        ShelterFragment fragment = new ShelterFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shelter, container, false);

        ListView listShelter = (ListView)view.findViewById(R.id.listViewShelter);

        Shelter lista_shelter[] = new Shelter[]{
                new Shelter("AATAN","Rua Maria Quitéria, 123, Cordeiro - Recife-PE","Cadastro realizado em 25/10/2016"),
        new Shelter("Abrigo Animal","Rua Maria Quitéria, 123, Cordeiro - Recife-PE","Cadastro realizado em 25/10/2016"),
        new Shelter("Abrigo da Lazica","Rua Maria Quitéria, 123, Cordeiro - Recife-PE","Cadastro realizado em 25/10/2016"),
        new Shelter("Abrigo Flora e Fauna","Rua Maria Quitéria, 123, Cordeiro - Recife-PE","Cadastro realizado em 25/10/2016"),
        new Shelter("Abrigo Salas","Rua Maria Quitéria, 123, Cordeiro - Recife-PE","Cadastro realizado em 25/10/2016"),
                new Shelter("Abrigo da Lazica","Rua Maria Quitéria, 123, Cordeiro - Recife-PE","Cadastro realizado em 25/10/2016")
        };

        ShelterAdapter adapter = new ShelterAdapter(getActivity(),R.layout.listview_shelter_item_row,lista_shelter);
        listShelter.setAdapter(adapter);

        /*// Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            //TODO corrigir isso
            //recyclerView.setAdapter(new MyShelterRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }*/
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
