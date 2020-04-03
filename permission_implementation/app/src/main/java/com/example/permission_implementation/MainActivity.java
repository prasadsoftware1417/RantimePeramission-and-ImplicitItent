package com.example.permission_implementation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
    private Button cameraButton;
    private ImageView image;
    private static final int REQUEST_IMAGE_CAPTURE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraButton=findViewById(R.id.cameraButton);
        image=findViewById(R.id.imageView);
        if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {

                    android.Manifest.permission.CAMERA
            }, 100);

        }
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,  int resultCode, Intent data) {
      if(requestCode==100 ){

          Bitmap bitmap = (Bitmap) data.getExtras().get("data");
          image.setImageBitmap(bitmap);
      }
    }
}
