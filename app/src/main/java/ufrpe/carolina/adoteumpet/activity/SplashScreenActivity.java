package ufrpe.carolina.adoteumpet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ufrpe.carolina.adoteumpet.other.AdoteUmPetSharedPreferences;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(AdoteUmPetSharedPreferences.getUserId(SplashScreenActivity.this).length() == 0)
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Log.i("ID USUARIO",AdoteUmPetSharedPreferences.getUserId(this));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }
}