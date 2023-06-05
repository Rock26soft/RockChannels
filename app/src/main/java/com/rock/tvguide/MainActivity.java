
package com.rock.tvguide;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm;
import com.google.android.exoplayer2.drm.UnsupportedDrmException;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private SimpleExoPlayer player;
    private PlayerView playerView;

    private static final String DRM_SCHEME = "clearkey";
    private static final UUID DRM_SCHEME_UUID = C.CLEARKEY_UUID;

    // Sample ClearKey KID and Key
    private static final String CLEARKEY_KID = "HL4pP9w4XayMDVArBPM45A";
    private static final String CLEARKEY_KEY = "BiTNLoAjrsXatKgrICCq9w";

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
        TrackSelector trackSelector = new DefaultTrackSelector();
        player = ExoPlayerFactory.newSimpleInstance(this, new DefaultRenderersFactory(this), trackSelector);

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "ExoPlayerDemo"));
        DrmSessionManager drmSessionManager;
        try {
            drmSessionManager = buildDrmSessionManager();
        } catch (UnsupportedDrmException e) {
            e.printStackTrace();
            return;
        }

        ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .setExtractorsFactory(new DefaultExtractorsFactory())
                .createMediaSource(Uri.parse("https://bpprod7linear.akamaized.net/bpk-tv/irdeto_com_Channel_257/output/manifest.mpd"));

        player.setPlayWhenReady(true);
        player.prepare(mediaSource);
        player.setDrmSessionManager(drmSessionManager);
        player.addListener(new Player.DefaultEventListener() {
            @Override
            public void onPlayerError(PlaybackException error) {
                super.onPlayerError(error);
                // Handle player error
            }
        });

        playerView.setPlayer(player);
    }

    private DrmSessionManager buildDrmSessionManager() throws UnsupportedDrmException {
        FrameworkMediaDrm mediaDrm = new FrameworkMediaDrm(DRM_SCHEME_UUID);
        
        byte[] key = Util.getUtf8Bytes(CLEARKEY_KEY);
        UUID keyId = UUID.fromString(CLEARKEY_KID);
        
        return new DefaultDrmSessionManager.Builder()
                .setUuidAndExoMediaDrmProvider(DRM_SCHEME_UUID, FrameworkMediaDrm.DEFAULT_PROVIDER)
                .build(new DrmMediaDrmCallback(keyId, key));
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
