package com.example.appmovildoncurrulocilindro.ui;

import androidx.appcompat.app.AppCompatActivity;


import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.appmovildoncurrulocilindro.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inicio);

        /*VideoView mivdeo = findViewById(R.id.imageView3);
        String videop = "android.resource://" + getPackageName() + "/" + R.raw.video_negocio;
        Uri uri = Uri.parse(videop);
        mivdeo.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        mivdeo.setMediaController(mediaController);
        mediaController.setAnchorView(mivdeo);*/
    }
}