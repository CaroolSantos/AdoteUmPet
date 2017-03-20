package ufrpe.carolina.adoteumpet.other;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ufrpe.carolina.adoteumpet.entity.Pet;
import ufrpe.carolina.adoteumpet.entity.Shelter;

/**
 * Created by AnaCarolina on 13/03/2017.
 */

public class ApiHttp {
    private static String BASE_URL = "http://adoteumpetapi.azurewebsites.net/api";
    private Context mContext;

    public ApiHttp(Context ctx){
        mContext = ctx;
    }

    private HttpURLConnection abrirConexao(String url, String metodo,boolean doOutput) throws Exception{

        URL urlCon = new URL(url);
        HttpURLConnection conexao = (HttpURLConnection)urlCon.openConnection();
        conexao.setReadTimeout(15000);
        conexao.setConnectTimeout(15000);
        conexao.setRequestMethod(metodo);
        conexao.setDoInput(true);
        conexao.setDoOutput(doOutput);

        if(doOutput){
            conexao.addRequestProperty("Content-Type","application/json");
        }

        conexao.connect();
        return conexao;
    }

    public boolean login(String Email, String Password) throws Exception{
        String url = BASE_URL + "/Login";
        HttpURLConnection conexao = abrirConexao(url,"POST",true);

        OutputStream os = conexao.getOutputStream();
        os.write(loginToJsonBytes(Email,Password));
        os.flush();
        os.close();

        int responseCode = conexao.getResponseCode();
        Log.d("Response API LOGIN",String.valueOf(responseCode));

        if(responseCode == HttpURLConnection.HTTP_OK){
            InputStream is = conexao.getInputStream();
            String s = streamToString(is);
            is.close();

            JSONObject json = new JSONObject(s);
            Log.d("API RESPONSE",json.toString());
            String Code = json.getString("Code");
            String IdUserApp = json.getString("Id");
            String Msg = json.getString("Msg");
            //TODO salvar Id em sharedPreferences

            if(Code.equals("OK")){
                return true;
            }
            return false;
        }else{
            throw new RuntimeException("Erro");
        }
    }

    public boolean acessarComFacebook(Context ctx,String Nome, String Email,String Sexo,String IdFacebook) throws Exception{
        String url = BASE_URL + "/LoginFacebook";
        HttpURLConnection conexao = abrirConexao(url,"POST",true);

        OutputStream os = conexao.getOutputStream();
        os.write(facebookUserToJsonBytes(Nome,Email,Sexo,IdFacebook));
        os.flush();
        os.close();

        int responseCode = conexao.getResponseCode();
        Log.d("Response API FACE LOGIN",String.valueOf(responseCode));

        if(responseCode == HttpURLConnection.HTTP_OK){
            InputStream is = conexao.getInputStream();
            String s = streamToString(is);
            is.close();

            JSONObject json = new JSONObject(s);
            String Code = json.getString("Code");
            String IdUserApp = json.getString("Id");
            String Msg = json.getString("Msg");

            if(Code.equals("OK")){
                AdoteUmPetSharedPreferences.setUserId(ctx,IdUserApp);
                return true;
            }else{
                return false;
            }

        }else{
            throw new RuntimeException("Erro");
        }


    }

    public boolean registrarUserApp(Context ctx,String Name, String Email, String Phone, String Password,String Birthday, String State, String City,String Gender) throws Exception{
        String url = BASE_URL + "/RegisterUserApp";
        HttpURLConnection conexao = abrirConexao(url,"POST",true);

        OutputStream os = conexao.getOutputStream();
        os.write(registerDataToJsonBytes(Name,Email,Phone,Password,Birthday,State,City,Gender));
        os.flush();
        os.close();

        int responseCode = conexao.getResponseCode();
        Log.d("Response API REGISTER",String.valueOf(responseCode));

        if(responseCode == HttpURLConnection.HTTP_OK){
            InputStream is = conexao.getInputStream();
            String s = streamToString(is);
            is.close();

            JSONObject json = new JSONObject(s);
            Log.d("API RESPONSE",json.toString());
            String Code = json.getString("Code");
            String IdUserApp = json.getString("Id");
            String Msg = json.getString("Msg");

            if(Code.equals("OK")){
                AdoteUmPetSharedPreferences.setUserId(ctx,IdUserApp);
                return true;
            }
            return false;
        }else{
            throw new RuntimeException("Erro");
        }
    }

    public boolean registrarPet(Context ctx, String specie, String status, String gender, String age, String breed, String size, String name, String description, String phone, String email, String idUserApp) throws Exception{
        String url = BASE_URL + "/RegisterPet";
        HttpURLConnection conexao = abrirConexao(url,"POST",true);

        OutputStream os = conexao.getOutputStream();
        os.write(registerPetToJsonBytes(specie,status,gender,age,
                breed,
                size,
                name,
                description,
                phone,
                email,
                idUserApp));
        os.flush();
        os.close();

        int responseCode = conexao.getResponseCode();
        Log.d("Response API REGISTER",String.valueOf(responseCode));

        if(responseCode == HttpURLConnection.HTTP_OK){
            InputStream is = conexao.getInputStream();
            String s = streamToString(is);
            is.close();

            JSONObject json = new JSONObject(s);
            Log.d("API RESPONSE",json.toString());
            String Code = json.getString("Code");
            String IdPet = json.getString("Id");
            String Msg = json.getString("Msg");

            if(Code.equals("OK")){
                return true;
            }
            return false;
        }else{
            throw new RuntimeException("Erro");
        }
    }

    public boolean registrarAbrigo(Context ctx, String name, String phone, String email, String address, String idUserApp) throws Exception{
        String url = BASE_URL + "/RegisterShelter";
        HttpURLConnection conexao = abrirConexao(url,"POST",true);

        OutputStream os = conexao.getOutputStream();
        os.write(registerShelterToJsonBytes(name,
                phone,
                email,
                address,
                idUserApp));
        os.flush();
        os.close();

        int responseCode = conexao.getResponseCode();
        Log.d("Response API REGISTER",String.valueOf(responseCode));

        if(responseCode == HttpURLConnection.HTTP_OK){
            InputStream is = conexao.getInputStream();
            String s = streamToString(is);
            is.close();

            JSONObject json = new JSONObject(s);
            Log.d("API RESPONSE",json.toString());
            String Code = json.getString("Code");
            String IdShelter = json.getString("Id");
            String Msg = json.getString("Msg");

            if(Code.equals("OK")){
                return true;
            }
            return false;
        }else{
            throw new RuntimeException("Erro");
        }
    }

    private byte[] registerShelterToJsonBytes(String name, String phone, String email, String address, String idUserApp) {
        try{
            JSONObject jsonShelterViewModel = new JSONObject();
            jsonShelterViewModel.put("Name",phone);
            jsonShelterViewModel.put("Phone",phone);
            jsonShelterViewModel.put("Email",email);
            jsonShelterViewModel.put("Address",address);
            jsonShelterViewModel.put("IdUserApp",idUserApp);

            String json = jsonShelterViewModel.toString();
            Log.d("shelterToJsonBytes",json);
            return json.getBytes();

        }catch (JSONException e){
            e.printStackTrace();
        }

        return null;
    }

    private byte[] registerPetToJsonBytes(String specie, String status, String gender, String age, String breed, String size, String name, String description, String phone, String email, String idUserApp) {
        try{
            JSONObject jsonPetViewModel = new JSONObject();
            jsonPetViewModel.put("Specie",specie);
            jsonPetViewModel.put("Status",status);
            jsonPetViewModel.put("Gender",gender);
            jsonPetViewModel.put("Age",age);
            jsonPetViewModel.put("Breed",breed);
            jsonPetViewModel.put("Size",size);
            jsonPetViewModel.put("Name",name);
            jsonPetViewModel.put("Description",description);
            jsonPetViewModel.put("Phone",phone);
            jsonPetViewModel.put("Email",email);
            jsonPetViewModel.put("IdUserApp",idUserApp);

            String json = jsonPetViewModel.toString();
            Log.d("UserToJsonBytes",json);
            return json.getBytes();

        }catch (JSONException e){
            e.printStackTrace();
        }

        return null;
    }

    private byte[] registerDataToJsonBytes(String name, String email, String phone, String password, String birthday, String state, String city, String gender) {
        try{
            JSONObject jsonRegisterUserAppViewModel = new JSONObject();
            jsonRegisterUserAppViewModel.put("Name",name);
            jsonRegisterUserAppViewModel.put("Email",email);
            jsonRegisterUserAppViewModel.put("Phone",phone);
            jsonRegisterUserAppViewModel.put("Password",password);
            jsonRegisterUserAppViewModel.put("Birthday",birthday);
            jsonRegisterUserAppViewModel.put("State",state);
            jsonRegisterUserAppViewModel.put("City",city);
            jsonRegisterUserAppViewModel.put("Gender",gender);

            String json = jsonRegisterUserAppViewModel.toString();
            Log.d("RegisterDataToJsonBytes",json);
            return json.getBytes();

        }catch (JSONException e){
            e.printStackTrace();
        }

        return null;
    }

    private byte[] facebookUserToJsonBytes(String Nome, String Email,String Sexo,String IdFacebook){
        try{
            JSONObject jsonLoginFacebookViewModel = new JSONObject();
            jsonLoginFacebookViewModel.put("Nome",Nome);
            jsonLoginFacebookViewModel.put("Email",Email);
            jsonLoginFacebookViewModel.put("Sexo",Sexo);
            jsonLoginFacebookViewModel.put("IdFacebook",IdFacebook);

            String json = jsonLoginFacebookViewModel.toString();
            Log.d("UserToJsonBytes",json);
            return json.getBytes();

        }catch (JSONException e){
            e.printStackTrace();
        }

        return null;
    }

    private byte[] loginToJsonBytes(String Email,String Password){
        try{
            JSONObject jsonLoginViewModel = new JSONObject();
            jsonLoginViewModel.put("Email",Email);
            jsonLoginViewModel.put("Password",Password);

            String json = jsonLoginViewModel.toString();
            Log.d("UserToJsonBytes",json);
            return json.getBytes();

        }catch (JSONException e){
            e.printStackTrace();
        }

        return null;
    }


    private List<Pet> getPets() throws Exception{
        String url = BASE_URL + "/Pets";
        HttpURLConnection conexao = abrirConexao(url,"GET",false);
        List<Pet> pets = new ArrayList<Pet>();

        if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){
            String jsonString = streamToString(conexao.getInputStream());
            JSONArray json = new JSONArray(jsonString);

            /*for (int i = 0; i < json.length(); i++){
                JSONObject petJSON = json.getJSONObject(i);
                Pet s = new Pet(petJSON.getInt("Id"),
                        petJSON.getString("Name"),
                        petJSON.getString("Phone"),
                        petJSON.getString("Email"),
                        petJSON.getString("Address"),
                        petJSON.getString("PhotoUrl"),
                        petJSON.getInt("IdUserApp"),
                        petJSON.getString("RegisterData"));
                pets.add(s);
            }*/
        }

        return pets;
    }

    //private Pet getPet(int Id){ }

    private List<Shelter> getShelters() throws Exception{
        String url = BASE_URL + "/Shelters";
        HttpURLConnection conexao = abrirConexao(url,"GET",false);
        List<Shelter> shelters = new ArrayList<Shelter>();

        if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){
            String jsonString = streamToString(conexao.getInputStream());
            JSONArray json = new JSONArray(jsonString);

            for (int i = 0; i < json.length(); i++){
                JSONObject shelterJSON = json.getJSONObject(i);
                Shelter s = new Shelter(shelterJSON.getInt("Id"),
                        shelterJSON.getString("Name"),
                        shelterJSON.getString("Phone"),
                        shelterJSON.getString("Email"),
                        shelterJSON.getString("Address"),
                        shelterJSON.getString("PhotoUrl"),
                        shelterJSON.getInt("IdUserApp"),
                        shelterJSON.getString("RegisterData"));
                shelters.add(s);
            }
        }

        return shelters;
    }

    //private Shelter getShelter(int Id){

    //}

    private String streamToString(InputStream is) throws IOException{
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while((lidos = is.read(bytes))>0){
            baos.write(bytes,0,lidos);;
        }
        return new String(baos.toByteArray());
    }



}
