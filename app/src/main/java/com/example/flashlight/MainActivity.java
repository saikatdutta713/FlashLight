package com.example.flashlight;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ToggleButton rearFlash, frontFlash;
    private CameraManager cameraManager;
    private String rearCameraId, frontCameraId;
    private boolean rearFlashState, frontFlashState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        rearFlash = findViewById(R.id.rearFlash);
        frontFlash = findViewById(R.id.frontFlash);

        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);

        rearFlash.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                toggleRearFlash();
            }
        });

        frontFlash.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                toggleFrontFlash();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void toggleRearFlash(){
        try {
            rearCameraId = cameraManager.getCameraIdList()[0];
            rearFlashState = cameraManager.getCameraCharacteristics(rearCameraId).get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
            System.out.println(rearFlashState);
            if (rearFlash.isChecked()){
                try {
                    cameraManager.setTorchMode(rearCameraId, true);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    cameraManager.setTorchMode(rearCameraId, false);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void toggleFrontFlash(){
        try {
            frontCameraId = cameraManager.getCameraIdList()[1];
            frontFlashState = cameraManager.getCameraCharacteristics(frontCameraId).get(CameraCharacteristics.FLASH_INFO_AVAILABLE);

            if (frontFlash.isChecked()){
                try {
                    cameraManager.setTorchMode(frontCameraId, true);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    cameraManager.setTorchMode(frontCameraId, false);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}