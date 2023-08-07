package com.rock.tvguide;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class PlayerActivity extends AppCompatActivity {

  private PlayerView playerView;
  private SimpleExoPlayer player;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.player);

    playerView = findViewById(R.id.playerView);

    player = new SimpleExoPlayer.Builder(this).build();

    playerView.setPlayer(player);

    Uri uri = Uri.parse(getIntent().getStringExtra("url"));
    DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
      this,
      Util.getUserAgent(this, "ExoPlayerDemo")
    );
    MediaSource mediaSource = new ProgressiveMediaSource.Factory(
      dataSourceFactory
    )
      .createMediaSource(uri);

    player.prepare(mediaSource);

    player.setPlayWhenReady(true);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    player.release();
  }
}
