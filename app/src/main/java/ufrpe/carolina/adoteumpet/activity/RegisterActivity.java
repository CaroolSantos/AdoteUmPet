package ufrpe.carolina.adoteumpet.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import ufrpe.carolina.adoteumpet.R;
import ufrpe.carolina.adoteumpet.other.ApiHttp;

public class RegisterActivity extends AppCompatActivity
        implements View.OnClickListener{

    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPhone;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private EditText edtBirthdayDate;
    private EditText edtCity;
    private EditText edtState;
    private RadioGroup rgGender;
    private RadioButton rbSelectedGender;
    private Button btnRegistrar;
    private int DATEINIT_DIALOG = 999;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.actionBar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtName = (EditText) findViewById(R.id.edt_nome);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPhone = (EditText) findViewById(R.id.edt_telefone);
        edtPassword = (EditText) findViewById(R.id.edt_senha);
        edtConfirmPassword = (EditText) findViewById(R.id.edt_confirmacaosenha);
        edtBirthdayDate = (EditText) findViewById(R.id.edt_dt_nascimento);
        edtState = (EditText) findViewById(R.id.edt_estado);
        edtCity = (EditText) findViewById(R.id.edt_cidade);
        rgGender = (RadioGroup) findViewById(R.id.opcoes_sexo);



        edtBirthdayDate.setInputType(InputType.TYPE_NULL);
        edtBirthdayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDialog(DATEINIT_DIALOG);

            }
        });

        btnRegistrar = (Button) findViewById(R.id.btn_registrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(edtName.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.nome_vazio),Toast.LENGTH_SHORT).show();
                return;
            }
            if(edtEmail.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.email_vazio),Toast.LENGTH_LONG).show();
                return;
            }
            if(edtPhone.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.telefone_vazio),Toast.LENGTH_LONG).show();
                return;
            }
            if(edtPassword.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.senha_vazio),Toast.LENGTH_LONG).show();
                return;
            }
            if(edtConfirmPassword.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.confirma_senha_vazio),Toast.LENGTH_LONG).show();
                return;
            }else{
                if(!edtConfirmPassword.getText().toString().equals(edtPassword.getText().toString())){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.confirma_senha_errado),Toast.LENGTH_LONG).show();
                    return;
                }
            }
            if(edtBirthdayDate.getText().toString().isEmpty()){
                Toast.makeText(RegisterActivity.this, getResources().getString(R.string.datanascimento_vazio), Toast.LENGTH_SHORT).show();
                return;
            }
            if(edtState.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.estado_vazio),Toast.LENGTH_LONG).show();
                return;
            }
            if(edtCity.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.cidade_vazio),Toast.LENGTH_LONG).show();
                return;
            }

            if(rgGender.getCheckedRadioButtonId() == -1){
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.sexo_vazio),Toast.LENGTH_LONG).show();
                return;
            }
            String gender = "false";
            Log.i("GENDER",Integer.toString(rgGender.getCheckedRadioButtonId()));

                if(rgGender.getCheckedRadioButtonId() == 1){
                    gender = "true";
                }

            Register register = new Register();
                register.execute(
                        edtName.getText().toString(),
                        edtEmail.getText().toString(),
                        edtPhone.getText().toString(),
                        edtPassword.getText().toString(),
                        edtBirthdayDate.getText().toString(),
                        edtState.getText().toString(),
                        edtCity.getText().toString(),
                        gender
                );


            }
        });


    }
    @Override
    protected Dialog onCreateDialog (int id){
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        if(id == DATEINIT_DIALOG){
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3){
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day){
        edtBirthdayDate.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }

    @Override
    public void onClick(View view) {
            Intent it = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(it);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                this.finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class Register extends AsyncTask<String,Void,Void> {
        private Exception exception;

        protected Void doInBackground(String... args) {
            try {
                //Post para API

                String name = args[0];
                String email = args[1];
                String phone = args[2];
                String password = args[3];
                String birthday = args[4];
                String state = args[5];
                String city = args[6];
                String gender = args[7];

                Log.d("TASK","REGISTER");

                ApiHttp api = new ApiHttp(getApplicationContext());

                try {
                    boolean registrado = api.registrarUserApp(name,email,phone,password,birthday,state,city,gender);

                    if(registrado){
                        Intent it = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(it);
                    }else{
                        runOnUiThread(new Runnable(){

                            @Override
                            public void run(){
                                Toast.makeText(RegisterActivity.this,getResources().getString(R.string.erro_registrar),Toast.LENGTH_LONG).show();
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
    }
}
