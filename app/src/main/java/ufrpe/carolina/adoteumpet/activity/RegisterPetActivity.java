package ufrpe.carolina.adoteumpet.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ufrpe.carolina.adoteumpet.R;

public class RegisterPetActivity extends AppCompatActivity {
    private String tipo_pet[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet);

        tipo_pet = new String[5];
        tipo_pet[0]="Cachorro";
        tipo_pet[1]="Gato";
        tipo_pet[2]="Ave";
        tipo_pet[3]="Reptil";
        tipo_pet[4]="Jabuti";
        Spinner s = (Spinner) findViewById(R.id.especiepet);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, tipo_pet);
        s.setAdapter(adapter);
    }

}
