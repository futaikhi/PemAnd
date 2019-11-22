package com.example.pemand;

import com.example.pemand.model.User;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText usernameText,passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PrefManager prefManager = PrefManager.getInstance(MainActivity.this);
        if(prefManager.isLoggedIn()){
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
        }else {
            init();
        }
    }

    void init(){
        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);
        //if user presses on login calling the method login
        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
        //if user presses on not registered
        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open register screen
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });
    }

    private void userLogin() {
        //first getting the values
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
        //if everything is fine
        UserLogin ul = new UserLogin(username,password);
        ul.execute();
    }
    class UserLogin extends AsyncTask<Void , Void , String>{
        String username,password;
        String lala;

        UserLogin(String username,String password){
            this.username = username;
            this.password = password;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);

                if (!jsonObject.getBoolean("error")){
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                    JSONObject userJson = jsonObject.getJSONObject("user");

                    //creating a new user object
                    User user = new User(
                            userJson.getInt("id"),
                            userJson.getString("username")
                    );

                    //storing the user in shared preferences
                    PrefManager.getInstance(getApplicationContext()).setUserLogin(user);


                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));

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

            lala = requestHandler.sendPostRequest(URLS.url_login, params);

            return lala;
        }

    }

}


