package com.example.mobile_perfomances;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class update_delete_data extends AppCompatActivity {

    EditText Title, Genre, Producer;
    String Img = null;
    ImageView Photo;
    Perfom perfomance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Title = findViewById(R.id.Et_Title);
        Genre = findViewById(R.id.Et_Genre);
        Producer = findViewById(R.id.Et_Producer);
        Photo = findViewById(R.id.imPhoto);
        Title.setText(perfomance.getTitle());
        Genre.setText(perfomance.getGenre());
        Producer.setText(perfomance.getProducer());
        Photo.setImageBitmap(getImgBitmap(perfomance.getImage()));
    }

    private Bitmap getImgBitmap(String encodedImg) {
        if(encodedImg!=null&& !encodedImg.equals("null")) {
            byte[] bytes = new byte[0];
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                bytes = Base64.getDecoder().decode(encodedImg);
            }
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        else{
            return null;
                    //BitmapFactory.decodeResource(update_delete_data.this.getResources(), R.drawable.zaglushka);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && data!= null && data.getData()!= null)
        {
            if(resultCode==RESULT_OK)
            {
                Log.d("MyLog","Image URI : "+data.getData());
                Photo.setImageURI(data.getData());
                Bitmap bitmap = ((BitmapDrawable)Photo.getDrawable()).getBitmap();
                encodeImage(bitmap);
            }
        }
    }
    private String encodeImage(Bitmap bitmap) {
        int prevW = 150;
        int prevH = bitmap.getHeight() * prevW / bitmap.getWidth();
        Bitmap b = Bitmap.createScaledBitmap(bitmap, prevW, prevH, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Img=Base64.getEncoder().encodeToString(bytes);
            return Img;
        }
        return null;
    }

    public void Update(View view) {
    }

    public void GoBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Delete(View view) {
    }
}