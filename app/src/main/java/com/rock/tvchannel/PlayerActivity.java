package com.rock.tvchannel;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.*;
import androidx.tvprovider.*;
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import meorg.jsoup.*;
import org.json.*;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.exoplayer2.*;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.PlayerControlView;

public class PlayerActivity extends AppCompatActivity {
	
	private String url = "";
	SimpleExoPlayer player;
	
	private LinearLayout linear1;
	private PlayerView playerView;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.player);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		playerView = findViewById(R.id.playerView);
	}
	
	private void initializeLogic() {
		if (getIntent().hasExtra("link")) {
			url = getIntent().getStringExtra("link");
		}
		else {
			Intent inteent = getIntent();
			Uri dataUri = inteent.getData();
			if (dataUri != null) {
					url = dataUri.getQueryParameter("url");
					
			}
		}
		DefaultTrackSelector trackSelector = new DefaultTrackSelector();
		
		// Create the player
		
		
		// Create the player
		player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
		// Bind the player to the view
		playerView.setPlayer(player);
		// Create a media source representing the HLS stream
		String hlsStreamUrl = url;
		MediaSource mediaSource = buildHlsMediaSource(Uri.parse(hlsStreamUrl));
		
		// Prepare the player with the media source
		player.prepare(mediaSource);
		player.setPlayWhenReady(true);
		
	}
	
	
	private MediaSource buildHlsMediaSource(Uri uri) {
			// Create a data source factory
			String userAgent = Util.getUserAgent(this, getString(R.string.app_name));
			DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this, userAgent);
			
			// Create an HLS media source pointing to the URL
			return new HlsMediaSource.Factory(dataSourceFactory)
			.createMediaSource(uri);
	}
	{
			
			
			
			
			
			
			
	}
	
	
	@Override
	public void onStop() {
		super.onStop();
		player.release();
		finish();
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}