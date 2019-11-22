package com.example.pemand;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.pemand.adapter.HomeAdapter;
import com.example.pemand.model.*;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import topsis.Topsis;

public class HomeActivity extends AppCompatActivity {
    TextView usernameView,dataView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    void init(){
        usernameView = findViewById(R.id.viewUsername);

        User user = PrefManager.getInstance(this).getUser();
        usernameView.setText(user.getUsername());

        DataHome dataHome = new DataHome();
        dataHome.execute();
    }

    public void logout(View view) {
        finish();
        PrefManager.getInstance(this).logout();
    }

    class DataHome extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            ArrayList<Data> arrData = new ArrayList<>();
            int[] w = {1,1,5};
            super.onPostExecute(s);
            try {
                JSONArray jsonArray = new JSONArray(s);
                String[] nama = new String[jsonArray.length()];
                int[] a = new int[jsonArray.length()];
                int[] b = new int[jsonArray.length()];
                int[] c = new int[jsonArray.length()];
                String[] gambar = new String[jsonArray.length()];
                for (int i = 0 ; i < jsonArray.length() ; i++){
                    nama[i] = jsonArray.getJSONObject(i).getString("NAMA");
                    a[i] = jsonArray.getJSONObject(i).getInt("A");
                    b[i] = jsonArray.getJSONObject(i).getInt("B");
                    c[i] = jsonArray.getJSONObject(i).getInt("C");
                    gambar[i] = jsonArray.getJSONObject(i).getString("GAMBAR");
                }
                Topsis topsis = new Topsis(a,b,c,nama,w,gambar);
                for (int i = 0; i < jsonArray.length() ; i++){
                    arrData.add(new Data(topsis.cetak()[i].getNilai(),topsis.cetak()[i].getNama(),topsis.cetak()[i].getGambar()));
                }
                GridView gridView = findViewById(R.id.grid);
                HomeAdapter homeAdapter = new HomeAdapter(HomeActivity.this,arrData);
                gridView.setAdapter(homeAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            return requestHandler.sendPostRequest(URLS.url_data);
        }
    }
}
