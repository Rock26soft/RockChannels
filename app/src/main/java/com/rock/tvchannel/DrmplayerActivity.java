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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import android.widget.TextView;
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


public class DrmplayerActivity extends AppCompatActivity {
	
	private String MPD_URL = "";
	private String KEY_ID = "";
	private String KEY_VALUE = "";
	private String html = "";
	
	private TextView textview1;
	private WebView webview1;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.drmplayer);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		textview1 = findViewById(R.id.textview1);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
	}
	
	private void initializeLogic() {
		try{
			MPD_URL = "https://bpprod7linear.akamaized.net/bpk-tv/irdeto_com_Channel_257/output/manifest.mpd";
			KEY_ID = "1cbe293fdc385dac8c0d502b04f338e4";
			KEY_VALUE = "0624cd2e8023aec5dab4a82b2020aaf7";
			java.io.InputStream inputstream1 = getAssets().open("player.html");
			
			
			
			WebView webview =(WebView)findViewById(R.id.webview1);
			
			
			
			webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
			CookieManager cookieManager = CookieManager.getInstance();
			cookieManager.setAcceptThirdPartyCookies(webview,true);
			
			
			webview.setWebViewClient(new WebViewClient());
			
			WebSettings webSettings = webview.getSettings();
			webSettings.setJavaScriptEnabled(true);
			webSettings.setDomStorageEnabled(true);
			webview.getSettings().setJavaScriptEnabled(true);
			webview.getSettings().setDomStorageEnabled(true);
			webview.getSettings().setPluginState(WebSettings.PluginState.ON);
			
			webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
			webview.getSettings().setUseWideViewPort(true);
			webview.getSettings().setLoadWithOverviewMode(true);
			webview.getSettings().setBuiltInZoomControls(false);
			webview.getSettings().setSupportZoom(false);
			webview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
			webview.getSettings().setDomStorageEnabled(true);
			webview.getSettings().setDatabaseEnabled(true);
			webview.getSettings().setDatabasePath("/data/data/" + webview.getContext().getPackageName() + "/databases/");
			
			webview.setWebChromeClient(new WebChromeClient());
			html = SketchwareUtil.copyFromInputStream(inputstream1).replace("==k==", KEY_VALUE).replace("==kid==", KEY_ID).replace("==link==", MPD_URL);
			webview1.loadData(html, "text/html", "UTF-8");
		}catch(Exception e){
			 
		}
		textview1.setText(html);
		textview1.setTextIsSelectable(true);
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