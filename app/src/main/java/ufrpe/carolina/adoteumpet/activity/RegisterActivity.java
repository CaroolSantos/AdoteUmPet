package ufrpe.carolina.adoteumpet.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import ufrpe.carolina.adoteumpet.R;

public class RegisterActivity extends AppCompatActivity
        implements View.OnClickListener{

    private Button btnRegistrar;
    private EditText edtxt_DataNacimento;
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

        btnRegistrar = (Button) findViewById(R.id.btn_registrar);
        btnRegistrar.setOnClickListener(this);

        edtxt_DataNacimento = (EditText) findViewById(R.id.edt_dt_nascimento);

        edtxt_DataNacimento.setInputType(InputType.TYPE_NULL);
        edtxt_DataNacimento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDialog(DATEINIT_DIALOG);
              
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
        edtxt_DataNacimento.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }

    @Override
    public void onClick(View view) {
            Intent it = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(it);
    }
}
