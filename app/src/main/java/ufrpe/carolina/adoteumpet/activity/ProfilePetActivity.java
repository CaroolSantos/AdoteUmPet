package ufrpe.carolina.adoteumpet.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.share.model.ShareLinkContent;

import ufrpe.carolina.adoteumpet.R;
import ufrpe.carolina.adoteumpet.other.CircleTransform;

public class ProfilePetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.actionBarProfilePet_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView sharepet = (ImageView) findViewById(R.id.share_pet);
        sharepet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("info click", "clique no share");


            }

            ;
        });
        ImageView imagemProfile = (ImageView) findViewById(R.id.pet_profile_photo);
        Glide.with(this).load(R.drawable.toddy)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imagemProfile);

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

}
