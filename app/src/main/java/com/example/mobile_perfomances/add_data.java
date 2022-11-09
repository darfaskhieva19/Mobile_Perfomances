package com.example.mobile_perfomances;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;


public class add_data extends AppCompatActivity {

    EditText Title, Genre, Producer;
    ImageView Photo;
    Bitmap Img = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Title = findViewById(R.id.Title);
        Genre = findViewById(R.id.Genre);
        Producer = findViewById(R.id.Producer);
        Photo = findViewById(R.id.Photo);
    }

    @Override
    protected void onActivityResult(int request, int result, @Nullable Intent data) {
        try {
            super.onActivityResult(request, result, data);
            if (request == 1 && data != null && data.getData() != null) {
                if (result == RESULT_OK) {
                    Log.d("MyLog", "Image URI : " + data.getData());
                    Photo.setImageURI(data.getData());
                    Bitmap bitmap = ((BitmapDrawable) Photo.getDrawable()).getBitmap();
                    encodeImg(bitmap);
                }
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(add_data.this,"Ошибка", Toast.LENGTH_LONG).show();
        }
    }

    public void getImage(View v)
    {
        Intent intentChooser= new Intent();
        intentChooser.setType("image/*");
        intentChooser.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentChooser,1);
    }

    public String encodeImg(Bitmap bitmap) {
        int prevW = 500;
        int prevH = bitmap.getHeight() * prevW / bitmap.getWidth();
        Bitmap b = Bitmap.createScaledBitmap(bitmap, prevW, prevH, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Img = Base64.getEncoder().encodeToString(bytes);
            //return Img;
        }
        return null;
        // //Img = "";
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onAdd(View view) {
        try {
            String title = Title.getText().toString();
            String genre = Genre.getText().toString();
            String producer = Producer.getText().toString();
            //post(title, genre, producer, img, view);
        }
        catch(Exception ex){
            Toast.makeText(add_data.this,"Ошибка", Toast.LENGTH_LONG).show();
        }
    }

    private void post(String title, String genre, String producer, String img, View view)
    {
       /*Retrofit retrofit = new Builder().baseUrl("https://ngknn.ru:5001/NGKNN/ФасхиеваДР/api/").addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Perfom modal = new Perfom(null, title, genre, producer, img);
        Call<Perfom> call = retrofitAPI.createPost(modal);
        call.enqueue(new Call.Callback<Perfom>() {
            @Override
            public void onResponse(Call<Perfom> call, Response<Perfom> response) {
                Toast.makeText(add_data.this, "Успешное добавление!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Perfom> call, Throwable t) {
                Toast.makeText(add_data.this, "Что-то пошло не так!", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}