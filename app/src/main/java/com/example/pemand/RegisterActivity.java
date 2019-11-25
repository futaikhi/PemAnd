package com.example.pemand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pemand.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameText,passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        PrefManager prefManager = PrefManager.getInstance(RegisterActivity.this);
        if(prefManager.isLoggedIn()){
            startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
        }else {
            usernameText = findViewById(R.id.username);
            passwordText = findViewById(R.id.password);
        }
    }


    public void signUp(View view) {
        userRegister();
    }

    public void userRegister(){
        final String username = this.usernameText.getText().toString();
        final String password = this.passwordText.getText().toString();
        //validating inputs
        if (TextUtils.isEmpty(username)) {
            this.usernameText.setError("Please enter username");
            this.passwordText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            this.usernameText.setError("Please enter password");
            this.passwordText.requestFocus();
            return;
        }
        UserRegister userRegister = new UserRegister(username,password);
        userRegister.execute();

    }

    public void signIn(View view) {
        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    class UserRegister extends AsyncTask<Void, Void, String>{

        String username,password;
        String lala;

        UserRegister(String username,String password){
            this.username = username;
            this.password = password;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);

                if (jsonObject.getBoolean("sukses")){
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                }else {
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            HashMap<String, String> params = new HashMap<>();
            params.put("username",this.username);
            params.put("password",this.password);

            lala = requestHandler.sendPostRequest(URLS.url_register, params);

            return lala;
        }
    }
}
