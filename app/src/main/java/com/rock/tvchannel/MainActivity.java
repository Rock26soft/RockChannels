package com.rock.tvchannel;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.*;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.*;
import androidx.tvprovider.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import meorg.jsoup.*;
import org.json.*;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.tvprovider.media.tv.Channel;
import androidx.tvprovider.media.tv.*;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> a = new HashMap<>();
	WindowManager windowManager;
	View window;
	private double i = 0;
	long movid;
	long chid;
	private HashMap<String, Object> progm = new HashMap<>();
	private double index = 0;
	private HashMap<String, Object> p = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> channels = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> allchnls = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> firechannel = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> programs = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> Tatachannels = new ArrayList<>();
	
	private LinearLayout linear10;
	private LinearLayout linear11;
	private FrameLayout fragment_container;
	private CardView cardview1;
	private CardView cardview2;
	private CardView cardview3;
	private LinearLayout linear19;
	private Switch switch2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private TextView textview2;
	private LinearLayout linear13;
	private LinearLayout linear14;
	private TextView textview3;
	private LinearLayout linear15;
	private LinearLayout linear16;
	private TextView textview4;
	private NonSwipeableViewPager viewpager1;
	
	private Intent in = new Intent();
	private SpeechRecognizer asd;
	private AlertDialog.Builder aaaa;
	private TimerTask at;
	private SharedPreferences data;
	private DatabaseReference channeldb = _firebase.getReference("tv");
	private ChildEventListener _channeldb_child_listener;
	private AuFragmentAdapter au;
	private RequestNetwork aaa;
	private RequestNetwork.RequestListener _aaa_request_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
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
		linear10 = findViewById(R.id.linear10);
		linear11 = findViewById(R.id.linear11);
		fragment_container = findViewById(R.id.fragment_container);
		cardview1 = findViewById(R.id.cardview1);
		cardview2 = findViewById(R.id.cardview2);
		cardview3 = findViewById(R.id.cardview3);
		linear19 = findViewById(R.id.linear19);
		switch2 = findViewById(R.id.switch2);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		textview2 = findViewById(R.id.textview2);
		linear13 = findViewById(R.id.linear13);
		linear14 = findViewById(R.id.linear14);
		textview3 = findViewById(R.id.textview3);
		linear15 = findViewById(R.id.linear15);
		linear16 = findViewById(R.id.linear16);
		textview4 = findViewById(R.id.textview4);
		viewpager1 = findViewById(R.id.viewpager1);
		asd = SpeechRecognizer.createSpeechRecognizer(this);
		aaaa = new AlertDialog.Builder(this);
		data = getSharedPreferences("ids", Activity.MODE_PRIVATE);
		au = new AuFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
		aaa = new RequestNetwork(this);
		
		cardview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!(index == 1)) {
					index = 1;
					viewpager1.setCurrentItem((int)0);
					data.edit().putString("page", "0").commit();
				}
				else {
					
				}
			}
		});
		
		cardview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!(index == 2)) {
					viewpager1.setCurrentItem((int)1);
					index = 2;
					data.edit().putString("page", "1").commit();
				}
				else {
					
				}
			}
		});
		
		cardview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!(index == 3)) {
					index = 3;
					viewpager1.setCurrentItem((int)3);
					data.edit().putString("page", "2").commit();
				}
				else {
					
				}
			}
		});
		
		switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("server", "2").commit();
				}
				else {
					data.edit().putString("server", "1").commit();
				}
			}
		});
		
		asd.setRecognitionListener(new RecognitionListener() {
			@Override
			public void onReadyForSpeech(Bundle _param1) {
			}
			
			@Override
			public void onBeginningOfSpeech() {
			}
			
			@Override
			public void onRmsChanged(float _param1) {
			}
			
			@Override
			public void onBufferReceived(byte[] _param1) {
			}
			
			@Override
			public void onEndOfSpeech() {
			}
			
			@Override
			public void onPartialResults(Bundle _param1) {
			}
			
			@Override
			public void onEvent(int _param1, Bundle _param2) {
			}
			
			@Override
			public void onResults(Bundle _param1) {
				final ArrayList<String> _results = _param1.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
				final String _result = _results.get(0);
				String packageName = "com.teamsmart.videomanager.tk";
				String url = "https://m.youtube.com/results?search_query="+ _result;
				
				Intent shareIntent = new Intent();
				shareIntent.setAction(Intent.ACTION_SEND);
				shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				shareIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				
				shareIntent.setPackage(packageName);
				shareIntent.setType("text/plain");
				shareIntent.putExtra(Intent.EXTRA_TEXT, url);
				startActivity(shareIntent);
			}
			
			@Override
			public void onError(int _param1) {
				final String _errorMessage;
				switch (_param1) {
					case SpeechRecognizer.ERROR_AUDIO:
					_errorMessage = "audio error";
					break;
					
					case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
					_errorMessage = "speech timeout";
					break;
					
					case SpeechRecognizer.ERROR_NO_MATCH:
					_errorMessage = "speech no match";
					break;
					
					case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
					_errorMessage = "recognizer busy";
					break;
					
					case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
					_errorMessage = "recognizer insufficient permissions";
					break;
					
					default:
					_errorMessage = "recognizer other error";
					break;
				}
				
			}
		});
		
		_channeldb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		channeldb.addChildEventListener(_channeldb_child_listener);
		
		_aaa_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				p.clear();
				FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tata.ls"), _response);
				List<com.rock.tvchannel.Channel> list = M3UParser.getParsedList(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tata.ls"));
				
				for (com.rock.tvchannel.Channel elem : list) {
						
						
						
						
					p = new HashMap<>();
					p.put("name", elem.getName());
					p.put("link", elem.getUrl());
					p.put("img", elem.getLogo());
					p.put("license", elem.getLicenseKey());
					p.put("id", elem.id());
					Tatachannels.add(p);
					 } 
				if (!(Tatachannels.size() == 0)) {
					FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/tatachannels.json"), new Gson().toJson(Tatachannels));
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		_focus(cardview1);
		_focus(cardview2);
		_focus(cardview3);
		_firebaselist();
		if (data.contains("server")) {
			if (data.getString("server", "").equals("2")) {
				switch2.setChecked(true);
			}
			else {
				switch2.setChecked(false);
			}
		}
		else {
			switch2.setChecked(false);
		}
		aaa.startRequestNetwork(RequestNetworkController.GET, "https://madstream.live/playlist/tata.json", "tata", _aaa_request_listener);
	}
	
	public class AuFragmentAdapter extends FragmentStatePagerAdapter {
		// This class is deprecated, you should migrate to ViewPager2:
		// https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2
		Context context;
		int tabCount;
		
		public AuFragmentAdapter(Context context, FragmentManager manager) {
			super(manager);
			this.context = context;
		}
		
		public void setTabCount(int tabCount) {
			this.tabCount = tabCount;
		}
		
		@Override
		public int getCount() {
			return tabCount;
		}
		
		@Override
		public CharSequence getPageTitle(int _position) {
			
			return null;
		}
		
		@Override
		public Fragment getItem(int _position) {
			if (_position == 0) {
				return new HomeFragmentActivity();
			}
			else {
				
			}
			if (_position == 1) {
				return new LiveFragmentActivity();
			}
			else {
				
			}
			if (_position == 2) {
				return new TataplayFragmentActivity();
			}
			else {
				
			}
			if (_position == 3) {
				return new TataplayFragmentActivity();
			}
			else {
				
			}
			return null;
		}
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		if (data.contains("page")) {
			viewpager1.setCurrentItem((int)Double.parseDouble(data.getString("page", "")));
		}
		else {
			
		}
	}
	public void _focus(final View _view) {
		_view.setScaleX((float)(0.9d));
		_view.setScaleY((float)(0.9d));
		_view.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)0, Color.TRANSPARENT, Color.TRANSPARENT));
		_view.setOnFocusChangeListener(new OnFocusChangeListener() {
			    @Override
			    public void onFocusChange(View view, boolean hasFocus) {
				        if (hasFocus) {
					        
					_view.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)00, Color.TRANSPARENT, 0xFF455A64));
					view.setScaleY((float)(1));
					view.setScaleX((float)(1));
					_view.setAlpha((float)(1));
					_view.performClick();
				} else {
					  
					_view.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)0, Color.TRANSPARENT, Color.TRANSPARENT));
					view.setScaleX((float)(0.9d));
					view.setScaleY((float)(0.9d));
					_view.setAlpha((float)(1));
					    }
				    }
		});
		  
	}
	
	
	public void _firebaselist() {
		try{
			
			ContentResolver contentResolver = getContentResolver();
			
			
			if (!data.contains("chid")) {
				String appLinkUriString = "rostv://open";
				Uri appLinkUri = Uri.parse(appLinkUriString);
				
				Channel.Builder builder = new Channel.Builder();
				builder.setType(TvContractCompat.Channels.TYPE_PREVIEW)
				        .setDisplayName("Live TV")
				        .setAppLinkIntentUri(appLinkUri);
				
				
				
				Channel channel = builder.build();
				
				// Insert the channel using the ContentResolver
				
				
				Uri channelUri = contentResolver.insert(TvContractCompat.Channels.CONTENT_URI, channel.toContentValues());
				
				chid = ContentUris.parseId(channelUri);
				data.edit().putString("chid", String.valueOf((long)(chid))).commit();
				Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.app_icon);
				ByteArrayOutputStream logoStream = new ByteArrayOutputStream();
				logoBitmap.compress(Bitmap.CompressFormat.PNG, 100, logoStream);
				byte[] logoByteArray = logoStream.toByteArray();
				
				Uri logoUri = TvContractCompat.buildChannelLogoUri(channelUri);
				contentResolver.openOutputStream(logoUri).write(logoByteArray);
				
			}
			if (!data.contains("movid")) {
				String appLinkUriString = "rostv://open";
				Uri appLinkUri = Uri.parse(appLinkUriString);
				
				Channel.Builder builder = new Channel.Builder();
				builder.setType(TvContractCompat.Channels.TYPE_PREVIEW)
				        .setDisplayName("Movies")
				        .setAppLinkIntentUri(appLinkUri);
				
				
				
				Channel channel = builder.build();
				
				// Insert the channel using the ContentResolver
				
				
				Uri channelUri = contentResolver.insert(TvContractCompat.Channels.CONTENT_URI, channel.toContentValues());
				
				movid = ContentUris.parseId(channelUri);
				data.edit().putString("movid", String.valueOf((long)(movid))).commit();
				Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.app_icon);
				ByteArrayOutputStream logoStream = new ByteArrayOutputStream();
				logoBitmap.compress(Bitmap.CompressFormat.PNG, 100, logoStream);
				byte[] logoByteArray = logoStream.toByteArray();
				
				Uri logoUri = TvContractCompat.buildChannelLogoUri(channelUri);
				contentResolver.openOutputStream(logoUri).write(logoByteArray);
				
			}
			
			chid = (long)Double.parseDouble(data.getString("chid", ""));
			movid = (long)Double.parseDouble(data.getString("movid", ""));
			
			clearProgramsInChannel(movid);
			clearProgramsInChannel(chid);
			
			
		}catch(Exception e){
			 
		}
		try{
			channeldb.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot _dataSnapshot) {
					firechannel = new ArrayList<>();
					try {
						GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
						for (DataSnapshot _data : _dataSnapshot.getChildren()) {
							HashMap<String, Object> _map = _data.getValue(_ind);
							firechannel.add(_map);
						}
					}
					catch (Exception _e) {
						_e.printStackTrace();
					}
					for (int i = 0; i < (int)(firechannel.size()); i++) {
						try{
							_addChannel(chid, firechannel.get((int)(i)).get("name").toString(), firechannel.get((int)(i)).get("img").toString(), firechannel.get((int)(i)).get("link").toString());
						}catch(Exception e){
							 
						}
					}
					FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/home.ls"), new Gson().toJson(firechannel));
					au.setTabCount(3);
					viewpager1.setAdapter(au);
					cardview1.performClick();
				}
				@Override
				public void onCancelled(DatabaseError _databaseError) {
				}
			});
		}catch(Exception e){
			channeldb.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot _dataSnapshot) {
					firechannel = new ArrayList<>();
					try {
						GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
						for (DataSnapshot _data : _dataSnapshot.getChildren()) {
							HashMap<String, Object> _map = _data.getValue(_ind);
							firechannel.add(_map);
						}
					}
					catch (Exception _e) {
						_e.printStackTrace();
					}
					FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/home.ls"), new Gson().toJson(firechannel));
					au.setTabCount(3);
					viewpager1.setAdapter(au);
					cardview1.performClick();
				}
				@Override
				public void onCancelled(DatabaseError _databaseError) {
				}
			});
		}
	}
	
	
	public void _addChannel(final long _id, final String _name, final String _img, final String _link) {
		if (FileUtil.isExistFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat("programsivetv")))) {
			progm = _deserialised_map(FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat("programsivetv")));
		}
		if (progm.containsKey(_name)) {
			PreviewProgram.Builder pbuilder = new PreviewProgram.Builder();
			pbuilder.setChannelId(_id)
			        .setType(TvContractCompat.PreviewPrograms.TYPE_CHANNEL)
			        .setTitle(_name)
			        .setDescription("Live Channel")
			        .setPosterArtUri(Uri.parse(_img))
			        .setPreviewVideoUri(Uri.parse(_link))
			        .setLive(true)
			        .setPosterArtAspectRatio(TvContractCompat.PreviewPrograms.ASPECT_RATIO_1_1)
			        .setIntentUri(Uri.parse("rostv://run/data?url="+_link));
			
			
			
			getContentResolver().update(TvContractCompat.buildPreviewProgramUri((long)progm.get(_name)), pbuilder.build().toContentValues(), null, null);
			
			
		}
		else {
			PreviewProgram.Builder pbuilder = new PreviewProgram.Builder();
			pbuilder.setChannelId(_id)
			        .setType(TvContractCompat.PreviewPrograms.TYPE_CHANNEL)
			        .setTitle(_name)
			        .setDescription("Live Channel")
			        .setPosterArtUri(Uri.parse(_img))
			        .setPreviewVideoUri(Uri.parse(_link))
			        .setLive(true)
			        .setPosterArtAspectRatio(TvContractCompat.PreviewPrograms.ASPECT_RATIO_1_1)
			        .setIntentUri(Uri.parse("rostv://run/data?url="+_link));
			
			Uri programUri = this.getContentResolver().insert(TvContractCompat.PreviewPrograms.CONTENT_URI, pbuilder.build().toContentValues());
			
			long programId = ContentUris.parseId(programUri);
			progm.put(_name, programId);
		}
		_serialize_map(progm, FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat("programsivetv")));
	}
	private void clearProgramsInChannel(long channelId) {
			ContentResolver contentResolver = getContentResolver();
		
		    Uri programsUri = TvContractCompat.buildProgramsUriForChannel(channelId);
		
		    String[] projection = {TvContractCompat.Programs._ID};
		    String selection = null;
		    String[] selectionArgs = null;
		    String sortOrder = null;
		
		    Cursor cursor = contentResolver.query(programsUri, projection, selection, selectionArgs, sortOrder);
		
		    if (cursor != null) {
			        try {
				            while (cursor.moveToNext()) {
					                long programId = cursor.getLong(cursor.getColumnIndex(TvContractCompat.Programs._ID));
					                getContentResolver().delete(TvContractCompat.buildPreviewProgramUri(programId), null, null);
					            }
				        } finally {
				            cursor.close();
				        }
			    }
			
	}
	
	{
	}
	
	
	public void _serialize_map(final HashMap<String, Object> _map, final String _path) {
		try {
			         FileOutputStream fileOut =
			         new FileOutputStream(_path);
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.writeObject(_map);
			         out.close();
			         fileOut.close();
			         
			      } catch (IOException i) {
			         i.printStackTrace();
			      }
	}
	
	
	public HashMap<String, Object> _deserialised_map(final String _path) {
		
		HashMap<String, Object> tempom = null;
		try {
				FileInputStream fileIn = new FileInputStream(_path);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				tempom = (HashMap<String, Object>) in.readObject();
				in.close();
				fileIn.close();
			    return tempom;
		} catch (IOException i) {
				i.printStackTrace();
				return tempom;
		} catch (ClassNotFoundException c) {
				System.out.println("Employee class not found");
				c.printStackTrace();
				return tempom;
		}
		
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