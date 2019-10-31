package ufrpe.carolina.adoteumpet.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.share.model.ShareLinkContent;
import com.google.firebase.appindexing.FirebaseAppIndex;

import org.w3c.dom.Text;

import java.util.List;

import ufrpe.carolina.adoteumpet.R;
import ufrpe.carolina.adoteumpet.adapter.PetAdapter;
import ufrpe.carolina.adoteumpet.entity.Pet;
import ufrpe.carolina.adoteumpet.entity.ProfilePet;
import ufrpe.carolina.adoteumpet.other.ApiHttp;
import ufrpe.carolina.adoteumpet.other.CircleTransform;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ProfilePetActivity extends AppCompatActivity {

    private TextView pet_profile_name;
    private TextView Txt_InformacoesCadastrais;
    private TextView profile_pet_description;
    private TextView txtView_sexopet;
    private TextView txtView_idadepet;
    private TextView txtView_portepet;
    private TextView txtView_racapet;
    private TextView txtView_telefone;
    private TextView txtView_email;
    private ProgressDialog pdia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Bundle b = getIntent().getExtras();
        int value = -1; // or other values
        if(b != null)
            value = b.getInt("Id");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.actionBarProfilePet_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pet_profile_name = (TextView) findViewById(R.id.pet_profile_name);
        Txt_InformacoesCadastrais = (TextView) findViewById(R.id.Txt_InformacoesCadastrais);
        profile_pet_description = (TextView) findViewById(R.id.profile_pet_description);
        txtView_sexopet = (TextView) findViewById(R.id.txtView_sexopet);
        txtView_idadepet = (TextView) findViewById(R.id.txtView_idadepet);
        txtView_portepet = (TextView) findViewById(R.id.txtView_portepet);
        txtView_racapet = (TextView) findViewById(R.id.txtView_racapet);
        txtView_telefone = (TextView) findViewById(R.id.txtView_telefone);
        txtView_email = (TextView) findViewById(R.id.txtView_email);


        ImageView sharepet = (ImageView) findViewById(R.id.share_pet);
        sharepet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("info click", "clique no share");


            }

            ;
        });
        ImageView imagemProfile = (ImageView) findViewById(R.id.pet_profile_photo);
        Glide.with(this).load(R.drawable.logo_new)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imagemProfile);
        pdia = new ProgressDialog(this);
        pdia.setMessage(getResources().getString(R.string.carregando));
        pdia.show();
        CarregarProfilePet task = new CarregarProfilePet();
        task.execute(Integer.toString(value));



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                this.finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class CarregarProfilePet extends AsyncTask<String,String,ProfilePet> {
        private Exception exception;

        protected ProfilePet doInBackground(String... args) {
            try {

                Log.d("TASK","REGISTER");
                String Id = args[0];
                ApiHttp api = new ApiHttp(getApplicationContext());

                try {
                    ProfilePet pet = api.getProfilePet(Integer.parseInt(Id));

                    return pet;
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
        protected void onPostExecute(ProfilePet result){
            //super.onPostExecute(result);
            pet_profile_name.setText(result.Name);
            if(result.Status == "true"){
                Txt_InformacoesCadastrais.setText(getResources().getString(R.string.para_adocao) + " - " + result.ContactName);
            }else{
                Txt_InformacoesCadastrais.setText(getResources().getString(R.string.perdido) + " - " + result.ContactName);
            }

            profile_pet_description.setText(result.Description);

            if(result.Gender == "true"){
                txtView_sexopet.setText(getResources().getString(R.string.genero_macho));
            }else{
                txtView_sexopet.setText(getResources().getString(R.string.genero_femea));
            }


            txtView_idadepet.setText(result.Age);
            if(result.Size == "0"){
                txtView_portepet.setText(getResources().getString(R.string.porte_pequeno));
            }else if(result.Size == "1"){
                txtView_portepet.setText(getResources().getString(R.string.porte_medio));
            }else{
                txtView_portepet.setText(getResources().getString(R.string.porte_grande));
            }

            txtView_racapet.setText(result.Breed);
            txtView_telefone.setText(result.ContactPhone);
            txtView_email.setText(result.ContactEmail);

            pdia.dismiss();
        }
    }

}
