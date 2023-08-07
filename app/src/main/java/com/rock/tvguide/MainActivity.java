package com.rock.tvguide;

import android.Manifest;
import android.app.*;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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
import com.yausername.youtubedl_android.YoutubeDL;
import com.yausername.youtubedl_android.YoutubeDLRequest;
import java.io.File;
import java.util.UUID;

public class MainActivity extends Activity {

  private EditText urlEditText;
  private Button downloadButton;
  private TextView resultTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    urlEditText = findViewById(R.id.ed1);
    downloadButton = findViewById(R.id.btn);
    resultTextView = findViewById(R.id.tv);
    try {
      YoutubeDL.getInstance().init(this);
      tv.setText(("Done").toString());
    } catch (YoutubeDLException e) {
      tv.setText(e.toString());
    }
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
