package ufrpe.carolina.adoteumpet.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.FacebookSdk;

import ufrpe.carolina.adoteumpet.R;

public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener{

    private Button btnCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        btnCadastro = (Button) findViewById(R.id.cadastro);

        btnCadastro.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(it);
    }
}
