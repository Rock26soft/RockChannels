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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.*;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import meorg.jsoup.*;
import org.json.*;

public class MoviesFragmentActivity extends Fragment {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private ArrayList<HashMap<String, Object>> movies = new ArrayList<>();
	
	private LinearLayout linear1;
	private RecyclerView recyclerview1;
	private ImageView imageview1;
	private LinearLayout linear2;
	private TextView Name;
	private TextView series;
	
	private DatabaseReference mov = _firebase.getReference("mov");
	private ChildEventListener _mov_child_listener;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.movies_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		recyclerview1 = _view.findViewById(R.id.recyclerview1);
		imageview1 = _view.findViewById(R.id.imageview1);
		linear2 = _view.findViewById(R.id.linear2);
		Name = _view.findViewById(R.id.Name);
		series = _view.findViewById(R.id.series);
		
		_mov_child_listener = new ChildEventListener() {
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
		mov.addChildEventListener(_mov_child_listener);
	}
	
	private void initializeLogic() {
		recyclerview1.setLayoutManager(new GridLayoutManager(getContext().getApplicationContext(), 4));
		linear1.setVisibility(View.GONE);
		mov.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				movies = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						movies.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				recyclerview1.setAdapter(new Recyclerview1Adapter(movies));
				_focuss(0);
				
				recyclerview1.smoothScrollToPosition((int)0);
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}
	
	public void _focuss(final double _into) {
		Glide.with(getContext().getApplicationContext()).load(Uri.parse(movies.get((int)_into).get("img").toString())).into(imageview1);
		Name.setText(movies.get((int)_into).get("name").toString());
		series.setText(movies.get((int)_into).get("ep").toString());
		linear1.setVisibility(View.VISIBLE);
	}
	
	
	public void _focus(final View _view) {
		_view.setScaleX((float)(1));
		_view.setScaleY((float)(1));
		_view.setOnFocusChangeListener(new OnFocusChangeListener() {
				@Override
				public void onFocusChange(View view, boolean hasFocus) {
						if (hasFocus) {
								Animator animator = AnimatorInflater.loadAnimator(getContext().getApplicationContext(), R.anim.scale);
								animator.setTarget(view);
								animator.start();
								
					_focuss(recyclerview1.getChildAdapterPosition(recyclerview1.getFocusedChild()));
				} else {
					  
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
			View _v = _inflater.inflate(R.layout.mov, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_lp.setMargins(10,10,10,10);
			_view.setLayoutParams(_lp);
			
			Glide.with(getContext().getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("img").toString())).into(imageview1);
			textview1.setText(_data.get((int)_position).get("name").toString());
			_view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					Uri uri = Uri.parse(_data.get((int)_position).get("link").toString());
					Intent vlcIntent = new Intent(Intent.ACTION_VIEW);
					vlcIntent.setPackage("org.videolan.vlc");
					vlcIntent.setComponent(new ComponentName("org.videolan.vlc", "org.videolan.vlc.gui.video.VideoPlayerActivity"));
					vlcIntent.setDataAndTypeAndNormalize(uri, "video/*");
					startActivity(vlcIntent);
					
				}
			});
			_focus(_view);
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