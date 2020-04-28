package com.devindow.myfitnessroutines.video;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.devindow.myfitnessroutines.R;

public class VideoStreamActivity extends AppCompatActivity {

    Video video = null;
    ProgressDialog progressDialog;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video_stream);

        // get Video passed in by Intent
        Intent intent = getIntent();
        video = (Video)intent.getSerializableExtra("video");

        // Routine Name in Title
        setTitle(video.name);

        videoView = (VideoView)findViewById(R.id.videoView1);
        progressDialog = new ProgressDialog(VideoStreamActivity.this);
        progressDialog.setTitle(video.name);
        progressDialog.setMessage("Buffering...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        try {
            final MediaController mediaController = new MediaController(VideoStreamActivity.this) {
                @Override public void hide() {} // don't let MediaController hide after 3 seconds
            };
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            Uri parsedUri = Uri.parse(video.url);
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
