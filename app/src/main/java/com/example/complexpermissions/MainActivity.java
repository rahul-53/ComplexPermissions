package com.example.complexpermissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mBtnPermission;
    private static int REQ_CODE=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPermission();
    }



    private void initPermission(){
        mBtnPermission=findViewById(R.id.tvBtnPermission);
        mBtnPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
                ActivityCompat.requestPermissions(MainActivity.this, permissions, REQ_CODE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED)
            showToast("Camera and Storage permissions granted");

        else if(grantResults[0]== PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_DENIED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,permissions[1]))
                showToast("Camera granted but storage denied");
            else
                showToast("Storage permission denied by checking do not show it again");
        }
        else if(grantResults[0]== PackageManager.PERMISSION_DENIED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,permissions[0]))
                showToast("Storage granted but Camera denied");
            else
                showToast("Camera permission denied by checking do not show it again");
        }
        else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,permissions[0]) && ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,permissions[1]))
                showToast("Camera and Storage permissions denied");
            else
                showToast("Camera and Storage permissions denied by checking do not show it again");

        }
    }



    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

    }
}