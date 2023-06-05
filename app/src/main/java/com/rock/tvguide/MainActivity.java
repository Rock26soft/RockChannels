package com.rock.tvguide;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.exoplayer2.*;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.drm.*;
import com.google.android.exoplayer2.source.*;
import com.google.android.exoplayer2.trackselection.*;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.*;
import com.google.android.exoplayer2.util.Util;
import java.util.UUID;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

  private SimpleExoPlayer player;
  private PlayerView playerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    playerView = findViewById(R.id.player_view);
  }

  @Override
  protected void onStart() {
    super.onStart();
    initializePlayer();
  }

  @Override
  protected void onStop() {
    super.onStop();
    releasePlayer();
  }

  private void initializePlayer() {
    
    player =
      new SimpleExoPlayer.Builder(this)
        .setMediaSourceFactory(new DefaultMediaSourceFactory(this))
        .build();
    playerView.setPlayer(player);
    DataSource.Factory dataSourceFactory = new DefaultHttpDataSource.Factory();
    String vid =
      "https://bpprod7linear.akamaized.net/bpk-tv/irdeto_com_Channel_257/output/manifest.mpd";

    Uri videoURI = Uri.parse(vid);

    String keyString =
      "{\"keys\":[{\"kty\":\"oct\",\"k\":\"BiTNLoAjrsXatKgrICCq9w\",\"kid\":\"HL4pP9w4XayMDVArBPM45A\"}],'type':\"temporary\"}";

    MediaDrmCallback drmCallback = new LocalMediaDrmCallback(
      keyString.getBytes()
    );

    MediaSource dashMediaSource = new DashMediaSource.Factory(dataSourceFactory)
      .setDrmSessionManagerProvider(mediaItem -> {
        return new DefaultDrmSessionManager.Builder()
          .setPlayClearSamplesWithoutKeys(true)
          .setMultiSession(false)
          .setKeyRequestParameters(new HashMap<String, String>())
          .setUuidAndExoMediaDrmProvider(
            C.CLEARKEY_UUID,
            FrameworkMediaDrm.DEFAULT_PROVIDER
          )
          .build(drmCallback);
      })
      .createMediaSource(MediaItem.fromUri(videoURI));

    player.setMediaSource(dashMediaSource);
    player.setPlayWhenReady(true);
    player.prepare();
    player.play();
  }

  private void releasePlayer() {
    if (player != null) {
      player.release();
      player = null;
    }
  }
}
