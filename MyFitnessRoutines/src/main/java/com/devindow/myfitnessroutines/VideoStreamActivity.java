package com.devindow.myfitnessroutines;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoStreamActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    VideoView videoView;
    //String URL = "https://mytaichiroutines.s3-us-west-2.amazonaws.com/flute.mp4";
    //String URL = "https://mytaichiroutines.s3-us-west-2.amazonaws.com/small.mp4";
    String URL = "https://mytaichiroutines.s3-us-west-2.amazonaws.com/Workout 1.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video_stream);

        if (ContextCompat.checkSelfPermission(VideoStreamActivity.this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(VideoStreamActivity.this,
                    new String[]{Manifest.permission.INTERNET},
                    1);
        }


        videoView = (VideoView)findViewById(R.id.videoView1);
        progressDialog = new ProgressDialog(VideoStreamActivity.this);
        progressDialog.setTitle("Video Streaming Demo");
        progressDialog.setMessage("Buffering...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        try {
            MediaController mediaController = new MediaController(VideoStreamActivity.this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            Uri parsedUri = Uri.parse(URL);
            videoView.setVideoURI(parsedUri);
            //videoView.start();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                Log.i("VideoView", "onPrepared()");
                progressDialog.dismiss();
                videoView.start();
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.i("VideoView", "Error " + what + " " + extra);
                progressDialog.setMessage("Error");
                return false;
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i("VideoView", "Completion");
                //VideoStreamActivity.this.finish();
            }
        });
    }
}
