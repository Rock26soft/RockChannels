package com.rock.tvguide;

import android.Manifest;
import android.app.*;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.*;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.yausername.youtubedl_android.*;
import com.yausername.youtubedl_android.mapper.*;
import java.io.File;
import java.util.UUID;

public class MainActivity extends Activity {

  private EditText urlEditText;
  private Button downloadButton;
  private TextView resultTextView;
  private Handler mainHandler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    urlEditText = findViewById(R.id.ed1);
    downloadButton = findViewById(R.id.btn);
    resultTextView = findViewById(R.id.tv);
    try {
      YoutubeDL.getInstance().init(this);
      resultTextView.setText(("Done").toString());
    } catch (YoutubeDLException e) {
      resultTextView.setText(e.toString());
    }

    mainHandler = new Handler(Looper.getMainLooper());

    downloadButton.setOnClickListener(
      new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          final String url = urlEditText.getText().toString();
          if (!url.isEmpty()) {
            // Run the task in a background thread
            new Thread(
              new Runnable() {
                @Override
                public void run() {
                  // Perform your background tasks here
                  final String videoUrl = getVideoUrlFromUrl(url);

                  // Update UI on the main thread
                  mainHandler.post(
                    new Runnable() {
                      @Override
                      public void run() {
                        resultTextView.setText(videoUrl);
                      }
                    }
                  );
                }
              }
            )
              .start();
          }
        }
      }
    );
  }

  private String getVideoUrlFromUrl(String url) {
    YoutubeDLRequest request = new YoutubeDLRequest(url);
    request.addOption("-f", "best");
    VideoInfo streamInfo = YoutubeDL.getInstance().getInfo(request);
    return streamInfo.getUrl().toString();
  }

  @Override
  protected void onStart() {
    super.onStart();
  }

  @Override
  protected void onStop() {
    super.onStop();
  }
}
