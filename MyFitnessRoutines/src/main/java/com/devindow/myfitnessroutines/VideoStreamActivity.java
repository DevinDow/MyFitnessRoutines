package com.devindow.myfitnessroutines;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoStreamActivity extends AppCompatActivity {

    ProgressDialog pd;
    VideoView view;
    String URL = "http://techslides.com/demos/sample-videos/small.mp4"; // "http://techslides.com/demos/sample-videos/small.3gp"; // "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video_stream);

        view = (VideoView)findViewById(R.id.videoView1);
        pd = new ProgressDialog(VideoStreamActivity.this);
        pd.setTitle("Video Streaming Demo");
        pd.setMessage("Buffering...");
        pd.setIndeterminate(false);
        pd.setCancelable(false);
        pd.show();

        try {
            MediaController controller = new MediaController(VideoStreamActivity.this);
            controller.setAnchorView(view);
            Uri vid = Uri.parse(URL);
            view.setMediaController(controller);
            view.setVideoURI(vid);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        view.requestFocus();

        view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
// TODO Auto-generated method stub
                pd.dismiss();
                view.start();
            }
        });
    }
}
