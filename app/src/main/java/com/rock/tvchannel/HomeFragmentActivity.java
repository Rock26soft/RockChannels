package com.rock.tvchannel;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.*;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.*;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.tvprovider.*;
import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import meorg.jsoup.*;
import org.json.*;

public class HomeFragmentActivity extends Fragment {
	
	private ArrayList<HashMap<String, Object>> channels = new ArrayList<>();
	
	private LinearLayout linear2;
	private TextView textview2;
	private RecyclerView recyclerview1;
	
	private Intent in = new Intent();
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.home_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear2 = _view.findViewById(R.id.linear2);
		textview2 = _view.findViewById(R.id.textview2);
		recyclerview1 = _view.findViewById(R.id.recyclerview1);
	}
	
	private void initializeLogic() {
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (FileUtil.isExistFile(FileUtil.getPackageDataDir(getContext().getApplicationContext()).concat("/home.ls"))) {
			recyclerview1.setLayoutManager(new GridLayoutManager(getContext().getApplicationContext(), 4));
			channels = new Gson().fromJson(FileUtil.readFile(FileUtil.getPackageDataDir(getContext().getApplicationContext()).concat("/home.ls")), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			recyclerview1.setAdapter(new Recyclerview1Adapter(channels));
		}
		else {
			
		}
	}
	
	@Override
	public void onStop() {
		super.onStop();
		
	}
	public void _focus(final View _view) {
		_view.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)8, Color.TRANSPARENT, 0xFFFFFFFF));
		_view.setScaleX((float)(1));
		_view.setScaleY((float)(1));
		_view.setOnFocusChangeListener(new OnFocusChangeListener() {
				@Override
				public void onFocusChange(View view, boolean hasFocus) {
						if (hasFocus) {
								Animator animator = AnimatorInflater.loadAnimator(getContext().getApplicationContext(), R.anim.scale);
								animator.setTarget(view);
								animator.start();
								
					_view.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)2, 0xFF263238, 0xFFFFFFFF));
				} else {
					  
					_view.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)8, Color.TRANSPARENT, 0xFFFFFFFF));
					if (_view.getScaleX() == 1.2d) {
						Animator animator = AnimatorInflater.loadAnimator(getContext().getApplicationContext(), R.anim.unscale);
						animator.setTarget(_view);
						animator.start();
						
						
					}
					else {
						_view.setScaleX((float)(1));
						_view.setScaleY((float)(1));
					}
					    }
				    }
		});
		  
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.channel, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			
			try{
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("img").toString())).into(imageview1);
				textview1.setText(_data.get((int)_position).get("name").toString());
				RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				_lp.setMargins(15,15,15,15);
				_view.setLayoutParams(_lp);
				
				_view.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						in.putExtra("link", _data.get((int)_position).get("link").toString());
						in.putExtra("index", String.valueOf((long)(_position)));
						in.putExtra("list", "open");
						in.setClass(getContext().getApplicationContext(), PlayerActivity.class);
						startActivity(in);
					}
				});
				_view.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View _view) {
						if (_data.get((int)_position).get("type").toString().equals("link")) {
							Uri uri = Uri.parse(_data.get((int)_position).get("link").toString());
							Intent intent = new Intent(Intent.ACTION_VIEW, uri);
							startActivity(intent);
							
						}
						else {
							in.putExtra("link", _data.get((int)_position).get("link").toString());
							in.putExtra("index", String.valueOf((long)(_position)));
							in.putExtra("list", "1");
							in.setClass(getContext().getApplicationContext(), WebviActivity.class);
							startActivity(in);
						}
						return true;
					}
				});
				_view.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFFFFFFF));
				_view.setAlpha((float)(1));
				_focus(_view);
				if (_position == 0) {
					_view.requestFocus();
				}
				else {
					
				}
			}catch(Exception e){
				 
			}
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
	}
}