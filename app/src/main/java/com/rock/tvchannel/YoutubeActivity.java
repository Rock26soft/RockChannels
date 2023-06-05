package com.rock.tvchannel;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import android.widget.Button;
import android.widget.LinearLayout;
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
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import meorg.jsoup.*;
import org.json.*;


public class YoutubeActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private String commands = "";
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private Button button1;
	private WebView webview1;
	private TextView textview1;
	
	private Intent a = new Intent();
	private TimerTask t;
	private TimerTask tt;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.youtube);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		button1 = findViewById(R.id.button1);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		textview1 = findViewById(R.id.textview1);
		
		button1.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				
				return true;
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				t = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								commands = "$.ajax({\n        \"url\": \"admin/apiService.php\",\n        \"type\": \"POST\",\n        \"data\": \"action=get_channel_details&id=".concat(getIntent().getStringExtra("tvid").concat("\",\n\"beforeSend\": function(xhr) {\n  setAPIShield(xhr,\"".concat(getIntent().getStringExtra("tvid")).concat("\");\n        },\n        \"success\": function(data) {\n            try {\n                data = JSON.parse(data);\n            } catch (err) {}\n            if (data.status == \"success\") {\n                \n                TV.play(data.data.link)\n                \n            } else {\n                TV.play(\"fail\")\n            }\n        },\n        \"error\": function(data) {\n            \n        }\n    });")));
								webview1.addJavascriptInterface(new editor(), "TV");
								
								
								webview1.loadUrl("javascript:"+ commands);
							}
						});
					}
				};
				_timer.schedule(t, (int)(1500));
			}
		});
		
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
		textview1.setText("Loading");
		
		webview1.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		WebView webview =(WebView)findViewById(R.id.webview1);
		
		
		
		
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
		webview.getSettings().setDomStorageEnabled(true);
		webview.getSettings().setDatabaseEnabled(true);
		webview.getSettings().setDatabasePath("/data/data/" + webview.getContext().getPackageName() + "/databases/");
		
		webview.setWebChromeClient(new WebChromeClient() {
			    @Override
			    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
				      //  textview1.append( "/n"+consoleMessage.message());
				        return true;
				    }
		});
		
		
		webview1.setWebContentsDebuggingEnabled(true);
		
		webview1.loadUrl("https://madplay.live/jiotvusf/");
		tt = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						button1.performClick();
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(tt, (int)(2000), (int)(2000));
		
		webview1.addJavascriptInterface(new editor(), "TV");
		
	}
	class editor{
			@JavascriptInterface
			@SuppressWarnings("unused")
			public void play(String link){
					
				
				
			textview1.setText(link);
			tt.cancel();
			
			Uri uri = Uri.parse(link);
			Intent intent = new Intent(Intent.ACTION_VIEW);
			        intent.setPackage("org.videolan.vlc");
			        intent.setDataAndType(uri, "video/mp4");
			
			        // Verify that the VLC app is installed and available to handle the intent
			        if (intent.resolveActivity(getPackageManager()) != null) {
				            // Open the VLC app to play the video
				            startActivity(intent);
				        } else {
				            // VLC app is not installed, show an error messag
				            }
			        finish();
		}
	}
	
	
	@Override
	public void onStop() {
		super.onStop();
		tt.cancel();
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