package ufrpe.carolina.adoteumpet.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ufrpe.carolina.adoteumpet.R;

public class RegisterActivity extends AppCompatActivity
        implements View.OnClickListener{

    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegistrar = (Button) findViewById(R.id.btn_registrar);

        btnRegistrar.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
            Intent it = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(it);
    }
}
