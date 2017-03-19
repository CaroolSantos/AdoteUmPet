package ufrpe.carolina.adoteumpet.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ufrpe.carolina.adoteumpet.R;
import ufrpe.carolina.adoteumpet.fragment.RegisterPetFragment;

public class FilterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        InitSpinnerEspecie();
        InitSpinnerPorte();
        InitSpinnerGenero();
        InitSpinnerLocalizacao();

    }

    private void InitSpinnerEspecie() {
        Spinner spinner_especie = (Spinner) findViewById(R.id.spinner_especie);
        // Spinner click listener
        spinner_especie.setOnItemSelectedListener(this);

        List<String> especies = new ArrayList<String>();
        especies.add(getResources().getString(R.string.select));
        especies.add(getResources().getString(R.string.especie_cachorro));
        especies.add(getResources().getString(R.string.especie_gato));
        especies.add(getResources().getString(R.string.especie_passaro));
        especies.add(getResources().getString(R.string.nao_restringir));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, especies);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_especie.setAdapter(dataAdapter);
    }

    private void InitSpinnerPorte() {
        Spinner spinner_porte = (Spinner) findViewById(R.id.spinner_porte);
        // Spinner click listener
        spinner_porte.setOnItemSelectedListener(this);

        List<String> portes = new ArrayList<String>();
        portes.add(getResources().getString(R.string.select));
        portes.add(getResources().getString(R.string.porte_pequeno));
        portes.add(getResources().getString(R.string.porte_medio));
        portes.add(getResources().getString(R.string.porte_grande));
        portes.add(getResources().getString(R.string.nao_restringir));


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, portes);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_porte.setAdapter(dataAdapter);
    }

    private void InitSpinnerGenero() {
        Spinner spinner_genero = (Spinner) findViewById(R.id.spinner_genero);
        // Spinner click listener
        spinner_genero.setOnItemSelectedListener(this);

        List<String> generos = new ArrayList<String>();
        generos.add(getResources().getString(R.string.select));
        generos.add(getResources().getString(R.string.genero_femea));
        generos.add(getResources().getString(R.string.genero_macho));
        generos.add(getResources().getString(R.string.nao_restringir));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, generos);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_genero.setAdapter(dataAdapter);
    }

    private void InitSpinnerLocalizacao() {
        Spinner spinner_localizacao = (Spinner) findViewById(R.id.spinner_localizacao);
        // Spinner click listener
        spinner_localizacao.setOnItemSelectedListener(this);

        List<String> localizacao = new ArrayList<String>();
        localizacao.add(getResources().getString(R.string.select));
        localizacao.add(getResources().getString(R.string.localizacao_cidade));
        localizacao.add(getResources().getString(R.string.localizacao_estado));
        localizacao.add(getResources().getString(R.string.nao_restringir));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, localizacao);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_localizacao.setAdapter(dataAdapter);
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
}
