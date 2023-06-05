package com.rock.tvchannel;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.pm.PackageManager;
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
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.*;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.*;
import androidx.tvprovider.*;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import meorg.jsoup.*;
import org.json.*;
import android.util.Base64;
import android.view.inputmethod.*;

public class WebviActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private String js = "";
	private String pjs = "";
	WebView webview;
	private String index = "";
	private String list = "";
	private String res = "";
	private boolean cont = false;
	private String fjs = "";
	
	private ArrayList<HashMap<String, Object>> channels = new ArrayList<>();
	
	private LinearLayout linear1;
	private WebView webview1;
	private LinearLayout linear5;
	private Button button2;
	private Button pbutton;
	private Button button1;
	
	private TimerTask a;
	private TimerTask conttimer;
	private TimerTask fjtime;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.webvi);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		linear5 = findViewById(R.id.linear5);
		button2 = findViewById(R.id.button2);
		pbutton = findViewById(R.id.pbutton);
		button1 = findViewById(R.id.button1);
		
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
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				webview1.loadUrl("javascript:"+ js);
				_showCont();
			}
		});
		
		pbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.loadUrl("javascript:"+ pjs);
				_showCont();
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				fjs = "document.querySelectorAll(\"[data-plyr='fullscreen']\")[document.querySelectorAll(\"[data-plyr='fullscreen']\").length - 1].click()\n";
				
				webview1.loadUrl("javascript:"+ fjs);
				_showCont();
			}
		});
	}
	
	private void initializeLogic() {
		Intent inteent = getIntent();
		Uri dataUri = inteent.getData();
		if (dataUri != null) {
				String urlp = dataUri.getQueryParameter("url");
				Uri uri = Uri.parse(urlp);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				
				
		}
		
		if (getIntent().getStringExtra("list").equals("open")) {
			Uri uri = Uri.parse(getIntent().getStringExtra("link"));
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		}
		else {
			_showCont();
			_focus(pbutton);
			_focus(button1);
			_focus(button2);
		}
		list = getIntent().getStringExtra("list");
		index = getIntent().getStringExtra("index");
		webview1.setBackgroundColor(0xFF000000);
		webview1.setEnabled(false);
		linear5.requestFocus();
		js = "document.querySelectorAll(\"[data-plyr='quality']\")[document.querySelectorAll(\"[data-plyr='quality']\").length - 1].click()\n\n";
		fjs = "document.querySelectorAll(\"[data-plyr='fullscreen']\")[document.querySelectorAll(\"[data-plyr='fullscreen']\").length - 1].click()";
		pjs = "document.querySelectorAll(\"[aria-label='Play']\")[0].click();\ndocument.querySelectorAll(\".jw-icon-volume\")[0].click();\n    \n ";
		webview1.loadUrl(getIntent().getStringExtra("link"));
		
		
		webview =(WebView)findViewById(R.id.webview1);
		
		
		
		webview1.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		
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
		
		
		webview1.setWebViewClient(new WebViewClient() {
				
				@Override
				public void onPageFinished(WebView view, String url) {
						
						// Inject CSS when page is done loading
						
						injectCSS();
						
						super.onPageFinished(view, url);
				}
				
				/*
	
	@Override 
	public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
	if(url.contains(".m3u8")){
	res = url;
	runOnUiThread(new Runnable() {
	@Override
	public void run() {
	
	textview1.append((CharSequence)res + "/n");
	}
	});
	}
	return super.shouldInterceptRequest(view, url);
	}*/
				
		});
		
		
		a = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						webview1.loadUrl("javascript:"+ js);
					}
				});
			}
		};
		_timer.schedule(a, (int)(15000));
		fjtime = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						webview1.loadUrl("javascript:"+ fjs);
					}
				});
			}
		};
		_timer.schedule(fjtime, (int)(10000));
	}
	
	private void injectCSS() {
		    try {
			        InputStream inputStream = getAssets().open("style.css");
			        byte[] buffer = new byte[inputStream.available()];
			        inputStream.read(buffer);
			        inputStream.close();
			        String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
			        webview1.loadUrl("javascript:(function() {" +
			                "var parent = document.getElementsByTagName('head').item(0);" +
			                "var style = document.createElement('style');" +
			                "style.type = 'text/css';" +
			                // Tell the browser to BASE64-decode the string into your script !!!
			                "style.innerHTML = window.atob('" + encoded + "');" +
			                "parent.appendChild(style)" +
			                "})()");
			
			    } catch (Exception e) {
			        
			    }
	}
	
	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		    
		    
		    if(! cont){
			    
			      switch (keyCode) {
				        
						
				        case 22:
				        {
					            _playnext();
							
					            return true;
					        }
				        case 21:
				        {
					                        _playback(); 
							
					            return true;
					            
					        }
				        case 20:
				        {
					            _showCont();
					            return true;
					            
					        }
				        case 23:
				        {
					            webview1.loadUrl("javascript:"+ pjs);
					            return true;
					        }
				      }
			    
			    
			    }  else  {
			        switch (keyCode) {
				        
						
				        
				        case 20:
				        {
					            _showCont();
					            return true;
					            
					        }
				      
				        }
			    }
		    return super.onKeyDown(keyCode, event);
		
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	
	
	@Override
	public void onStop() {
		super.onStop();
		
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		webview1.clearCache(true);
		finish();
	}
	
	    public void onLowMemory() {
		        super.onLowMemory();
		        SketchwareUtil.showMessage(getApplicationContext(), "low Ram");
		        }
	public void _playnext() {
		if (Double.parseDouble(list) == 1) {
			channels = new Gson().fromJson(FileUtil.readFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/apps.ls")), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
		else {
			channels = new Gson().fromJson(FileUtil.readFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/apps.ls2")), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
		try{
			if (Double.parseDouble(index) == (channels.size() - 1)) {
				SketchwareUtil.CustomToast(getApplicationContext(), channels.get((int)Double.parseDouble(index)).get("name").toString(), 0xFFFFFFFF, 26, 0xFF263238, 10, SketchwareUtil.TOP);
			}
			else {
				webview1.stopLoading();
				webview1.clearCache(true);
				webview1.clearHistory();
				webview1.loadUrl(channels.get((int)Double.parseDouble(index) + 1).get("link").toString());
				SketchwareUtil.CustomToast(getApplicationContext(), channels.get((int)Double.parseDouble(index) + 1).get("name").toString(), 0xFFFFFFFF, 26, 0xFF263238, 10, SketchwareUtil.TOP);
				index = String.valueOf((long)(Double.parseDouble(index) + 1));
			}
		}catch(Exception e){
			 
		}
	}
	
	
	public void _playback() {
		if (Double.parseDouble(list) == 1) {
			channels = new Gson().fromJson(FileUtil.readFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/apps.ls")), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
		else {
			channels = new Gson().fromJson(FileUtil.readFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/apps.ls2")), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
		try{
			if (Double.parseDouble(index) == 0) {
				SketchwareUtil.CustomToast(getApplicationContext(), channels.get((int)Double.parseDouble(index)).get("name").toString(), 0xFFFFFFFF, 26, 0xFF263238, 10, SketchwareUtil.TOP);
			}
			else {
				webview1.stopLoading();
				webview1.clearHistory();
				webview1.clearCache(true);
				webview1.loadUrl(channels.get((int)Double.parseDouble(index) - 1).get("link").toString());
				SketchwareUtil.CustomToast(getApplicationContext(), channels.get((int)Double.parseDouble(index) - 1).get("name").toString(), 0xFFFFFFFF, 26, 0xFF263238, 10, SketchwareUtil.TOP);
				index = String.valueOf((long)(Double.parseDouble(index) - 1));
			}
		}catch(Exception e){
			 
		}
	}
	
	
	public void _showCont() {
		linear5.setVisibility(View.VISIBLE);
		cont = true;
		try{
			conttimer.cancel();
		}catch(Exception e){
			 
		}
		conttimer = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						linear5.setVisibility(View.GONE);
						cont = false;
					}
				});
			}
		};
		_timer.schedule(conttimer, (int)(3000));
	}
	
	
	public void _focus(final View _view) {
		_view.setScaleX((float)(0.7d));
		_view.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)8, Color.TRANSPARENT, 0xFF607D8B));
		_view.setScaleY((float)(0.7d));
		_view.setOnFocusChangeListener(new OnFocusChangeListener() {
			    @Override
			    public void onFocusChange(View view, boolean hasFocus) {
				        if (hasFocus) {
					        
					_view.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)8, 0xFFFFEB3B, 0xFF607D8B));
					view.setScaleY((float)(1));
					view.setScaleX((float)(1));
					_view.setAlpha((float)(1));
				} else {
					  
					_view.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)8, Color.TRANSPARENT, 0xFF607D8B));
					view.setScaleX((float)(0.7d));
					view.setScaleY((float)(0.7d));
					_view.setAlpha((float)(0.8d));
					    }
				    }
		});
		  
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