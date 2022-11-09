package com.example.mobile_perfomances;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Adapter pAdapter;
    private List<Perfom> listPer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lst = findViewById(R.id.BD_Perform);
        pAdapter = new Adapter(MainActivity.this, listPer);
        lst.setAdapter(pAdapter);
        new GPerfomances().execute();
    }

    public void onAdd(View view) {
        startActivity(new Intent(this, add_data.class));
    }

    class GPerfomances extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://ngknn.ru:5001/ngknn/ФасхиеваДР/api/Performances");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String str = "";

                while ((str = reader.readLine()) != null) {
                    result.append(str);
                }
                return result.toString();
            } catch (Exception exception) {
                return null;
            }
        }
        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            try
            {
                JSONArray tempArray = new JSONArray(s);
                for (int i = 0; i < tempArray.length(); i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    Perfom tempAnimal = new Perfom(
                            productJson.getInt("ID"),
                            productJson.getString("Title"),
                            productJson.getString("Genre"),
                            productJson.getString("Producer"),
                            productJson.getString("Image")
                    );
                    listPer.add(tempAnimal);
                    pAdapter.notifyDataSetInvalidated();
                }
            } catch (Exception ignored) {
            }
        }
    }
}