package ufrpe.carolina.adoteumpet.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import ufrpe.carolina.adoteumpet.R;
import ufrpe.carolina.adoteumpet.other.ApiHttp;

import static android.support.v7.appcompat.R.id.info;

public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener{

    private Button btnCadastro;
    private LoginButton facebookLoginButton;
    private CallbackManager callbackManager;
    private Button btnLogin;
    private EditText edtEmail;
    private EditText edtPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edtEmail = (EditText)findViewById(R.id.editTextEmail);
                edtPassword = (EditText)findViewById(R.id.editTextPassword);

                if(edtEmail.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,getResources().getString(R.string.email_ou_senha_invalidos).toString(),Toast.LENGTH_SHORT).show();
                }else{
                    Login login = new Login();
                    login.execute(edtEmail.getText().toString(),edtPassword.getText().toString());
                }

            }
        });

        btnCadastro = (Button) findViewById(R.id.cadastro);
        btnCadastro.setOnClickListener(this);

        facebookLoginButton = (LoginButton) findViewById(R.id.login_button);
        facebookLoginButton.setReadPermissions(Arrays.asList("public_profile","email"));

        // Callback registration para botão de login do facebook
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //em caso de sucesso recolhe informações necessárias e passa para API
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        FacebookRequestError error = response.getError();
                        if (error != null) {
                            Log.d("ERRO",error.getErrorMessage());
                            String face_login_error = getResources().getString(R.string.face_login_error);
                            Toast.makeText(LoginActivity.this, face_login_error, Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Log.i("LoginActivity", response.toString());
                        // Dados do facebook para login

                        Bundle bFacebookData = getFacebookData(object);

                        FacebookLogin r = new FacebookLogin();
                        r.execute(bFacebookData.getString("name"),
                                bFacebookData.getString("email"),
                                bFacebookData.getString("gender"),
                                bFacebookData.getString("idFacebook")
                                );


                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, name, locale, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();

                Log.d("Face INFO",
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );


            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(LoginActivity.this,"Cancel",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(LoginActivity.this,"Erro",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Bundle getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=200");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("name"))
                bundle.putString("name", object.getString("name"));
            if (object.has("locale"))
                bundle.putString("locale", object.getString("locale"));
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

            return bundle;
        } catch (JSONException e) {
            Log.d("teste", "Error parsing JSON");
            return null;
        }

    }

    @Override
    public void onClick(View view) {
        Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(it);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    class FacebookLogin extends AsyncTask<String,String,String> {

        private Exception exception;
        protected ProgressDialog pdia;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pdia = new ProgressDialog(LoginActivity.this);
            pdia.setMessage(getResources().getString(R.string.carregando));
            pdia.show();
        }

        protected String doInBackground(String... args) {
            try {
                //Post para API

                String name = args[0];
                String email = args[1];
                String gender = args[2];
                String idFacebook = args[3];


                Log.d("TASK","doInBack1");

                ApiHttp api = new ApiHttp(getApplicationContext());

                try {
                    boolean logado = api.acessarComFacebook(getApplicationContext(),
                            name,
                            email,
                            gender,
                            idFacebook);

                    if(logado){
                        Intent it = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(it);
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

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            pdia.dismiss();
        }
    }

    class Login extends AsyncTask<String,String,String>{
        private Exception exception;
        private ProgressDialog pdia;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pdia = new ProgressDialog(LoginActivity.this);
            pdia.setMessage(getResources().getString(R.string.carregando));
            pdia.show();
        }

        protected String doInBackground(String... args) {
            try {
                //Post para API

                String email = args[0];
                String password = args[1];

                Log.d("TASK","LOGIN");

                ApiHttp api = new ApiHttp(getApplicationContext());

                try {
                    boolean logado = api.login(email,password);

                    if(logado){
                        Intent it = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(it);
                    }else{
                        runOnUiThread(new Runnable(){

                            @Override
                            public void run(){
                                Toast.makeText(LoginActivity.this,getResources().getString(R.string.dados_invalidos),Toast.LENGTH_SHORT).show();
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

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            pdia.dismiss();
        }
    }
}