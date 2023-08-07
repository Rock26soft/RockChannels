package com.rock.tvguide;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.exoplayer2.*;
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
  private ExoPlayer player;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.player);

    playerView = findViewById(R.id.playerView);

    player = new ExoPlayer.Builder(this).build();

    playerView.setPlayer(player);

    Uri uri = Uri.parse(getIntent().getStringExtra("url"));
    MediaItem mediaItem = MediaItem.fromUri(videoUri);
    // Set the media item to be played.
    player.setMediaItem(mediaItem);
    // Prepare the player.
    player.prepare();
    // Start the playback.
    player.play();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    player.release();
  }
}
