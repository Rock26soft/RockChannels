package com.rock.tvchannel;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class LiveFragmentActivity extends Fragment {
	
	private String urso = "";
	private double indet = 0;
	
	private ArrayList<HashMap<String, Object>> allchnls = new ArrayList<>();
	
	private RecyclerView recyclerview2;
	
	private Intent in = new Intent();
	private SharedPreferences data;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.live_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		recyclerview2 = _view.findViewById(R.id.recyclerview2);
		data = getContext().getSharedPreferences("ids", Activity.MODE_PRIVATE);
	}
	
	private void initializeLogic() {
		try{
			allchnls = new Gson().fromJson("[{\"title\":\"Jio Cricket English HD\",\"id\":\"1918\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Jio_Cricket_English.png\"},{\"title\":\"Jio Cricket English HD\",\"id\":\"1918\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Jio_Cricket_English.png\"},{\"title\":\"Colors HD\",\"id\":\"144\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Colors_HD.png\"},{\"title\":\"SET HD\",\"id\":\"291\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sony_HD.png\"},{\"title\":\"Zee TV HD\",\"id\":\"167\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zee_TV_HD.png\"},{\"title\":\"Sony Max HD\",\"id\":\"476\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sony_Max_HD.png\"},{\"title\":\"Zee Cinema HD\",\"id\":\"165\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zee_Cinema_HD.png\"},{\"title\":\"Sony Ten 5 HD\",\"id\":\"155\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Six_HD.png\"},{\"title\":\"Times NOW\",\"id\":\"383\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Times_NOW.png\"},{\"title\":\"Times Now Navbharat\",\"id\":\"1906\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Times_Now_Navbharat.png\"},{\"title\":\"Sony Ten 1 HD\",\"id\":\"162\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Ten_HD.png\"},{\"title\":\"Aaj Tak\",\"id\":\"173\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Aaj_Tak.png\"},{\"title\":\"BBC Marathi\",\"id\":\"1155\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/BBC_Marathi.png\"},{\"title\":\"ABP News India\",\"id\":\"177\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/ABP_News_India.png\"},{\"title\":\"Sony Ten 2 HD\",\"id\":\"891\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Ten2_HD.png\"},{\"title\":\"CNN NEWS 18\",\"id\":\"492\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/CNN_NEWS_18.png\"},{\"title\":\"MTV HD Plus\",\"id\":\"1145\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/MTV_HD_Plus.png\"},{\"title\":\"MTV Beats HD\",\"id\":\"753\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/MTV_Beats_HD.png\"},{\"title\":\"Republic TV\",\"id\":\"858\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Republic_TV.png\"},{\"title\":\"Mastiii\",\"id\":\"584\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Mastiii.png\"},{\"title\":\"ABP Majha\",\"id\":\"612\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/ABP_Majha.png\"},{\"title\":\"Nick Hindi\",\"id\":\"545\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Nick_Hindi.png\"},{\"title\":\"TV9 Karnataka\",\"id\":\"619\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/TV9_Karnataka.png\"},{\"title\":\"Pogo Hindi\",\"id\":\"559\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Pogo_Hindi.png\"},{\"title\":\"Polimer News\",\"id\":\"636\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Polimer_News.png\"},{\"title\":\"TV9 Telugu News\",\"id\":\"618\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/TV9_Telugu_News.png\"},{\"title\":\"Cartoon Network Hindi\",\"id\":\"816\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Cartoon_Network_Hindi.png\"},{\"title\":\"Discovery HD World\",\"id\":\"463\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Discovery_HD_World.png\"},{\"title\":\"Sun TV HD\",\"id\":\"896\",\"logo\":\"https://i.postimg.cc/65jLjybq/Sun-TV-HD.png\"},{\"title\":\"Sun News\",\"id\":\"676\",\"logo\":\"https://i.postimg.cc/5yGyvdp4/Sun-News.png\"},{\"title\":\"KTV HD\",\"id\":\"894\",\"logo\":\"https://i.postimg.cc/N0gsKjY8/KTV-HD.png\"},{\"title\":\"Sun Music HD\",\"id\":\"895\",\"logo\":\"https://i.postimg.cc/kGt90VFC/Sun-Music.png\"},{\"title\":\"Sun Life\",\"id\":\"682\",\"logo\":\"https://i.postimg.cc/B6Pn0w4R/Sun-Life.png\"},{\"title\":\"Gemini HD\",\"id\":\"897\",\"logo\":\"https://i.postimg.cc/sXM4RyBy/Gemini-HD.png\"},{\"title\":\"Gemini Movies HD\",\"id\":\"899\",\"logo\":\"https://i.postimg.cc/1tVwjHT3/Gemini-Movies-HD.png\"},{\"title\":\"Gemini Music HD\",\"id\":\"898\",\"logo\":\"https://i.postimg.cc/Hszc3Rxt/Gemini-Music-HD.png\"},{\"title\":\"Gemini Comedy\",\"id\":\"729\",\"logo\":\"https://i.postimg.cc/XYDZ659G/Gemini-Comedy.png\"},{\"title\":\"Gemini Life\",\"id\":\"684\",\"logo\":\"https://i.postimg.cc/4dYq2DsH/Gemini-Life.png\"},{\"title\":\"Gemini Music HD\",\"id\":\"898\",\"logo\":\"https://jiotv.catchup.cdn.jio.com/dare_images/images/Gemini_Music_HD.png\"},{\"title\":\"Surya HD\",\"id\":\"733\",\"logo\":\"https://i.postimg.cc/dtRs9yWP/Surya-HD.png\"},{\"title\":\"Surya Movies\",\"id\":\"1754\",\"logo\":\"https://i.postimg.cc/GmpWs8Vj/Surya-Movies.png\"},{\"title\":\"Surya Music\",\"id\":\"747\",\"logo\":\"https://i.postimg.cc/CKhz8NYQ/Surya-Music.png\"},{\"title\":\"Surya Comedy\",\"id\":\"1662\",\"logo\":\"https://i.postimg.cc/BQFN6xqF/Surya-Comedy.png\"},{\"title\":\"Udaya HD\",\"id\":\"901\",\"logo\":\"https://i.postimg.cc/XvqCZbwP/Udaya-HD.png\"},{\"title\":\"Udaya Movies\",\"id\":\"678\",\"logo\":\"https://i.postimg.cc/zGcfcRgd/Udaya-Movies.png\"},{\"title\":\"Udaya Music\",\"id\":\"744\",\"logo\":\"https://i.postimg.cc/Z52TctG2/Udaya-Music.png\"},{\"title\":\"Udaya Comedy\",\"id\":\"733\",\"logo\":\"https://i.postimg.cc/m2rBWgRK/Udaya-Comedy.png\"},{\"title\":\"Sun Bangla\",\"id\":\"1669\",\"logo\":\"https://sund-images.sunnxt.com/75117/200x200_4b19b530-f0bb-4b26-a9de-f3cdd5f8be20.jpg\"},{\"title\":\"ABP Ananda\",\"id\":\"672\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/ABP_Ananda.png\"},{\"title\":\"Sony BBC Earth HD\",\"id\":\"821\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sony_BBC_Earth_HD.png\"},{\"title\":\"History TV18 HD\",\"id\":\"146\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/History_HD.png\"},{\"title\":\"TLC HD\",\"id\":\"479\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/TLC_HD_World.png\"},{\"title\":\"Food Food\",\"id\":\"561\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Food_Food.png\"},{\"title\":\"GOOD TiMES\",\"id\":\"560\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/NDTV_Good_Times.png\"},{\"title\":\"Zee Business\",\"id\":\"657\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zee_Business.png\"},{\"title\":\"CNBC Tv18 Prime HD\",\"id\":\"143\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/CNBC_Tv18_Prime_HD.png\"},{\"title\":\"NDTV Profit\",\"id\":\"259\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/NDTV_Profit.png\"},{\"title\":\"CNBC Awaaz\",\"id\":\"190\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/CNBC_Awaaz.png\"},{\"title\":\"Aastha\",\"id\":\"175\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Aastha.png\"},{\"title\":\"Aastha Bhajan\",\"id\":\"594\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Aastha_Bhajan.png\"},{\"title\":\"Sanskar\",\"id\":\"288\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sanskar.png\"},{\"title\":\"India Science\",\"id\":\"1433\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/India_Science.png\"},{\"title\":\"Swayam Prabha 01\",\"id\":\"980\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_01.png\"},{\"title\":\"Swayam Prabha 02\",\"id\":\"981\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_02.png\"},{\"title\":\"Swayam Prabha 03\",\"id\":\"982\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_03.png\"},{\"title\":\"Sony SAB\",\"id\":\"154\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sony_SAB.png\"},{\"title\":\"And TV HD\",\"id\":\"472\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/And_TV_HD.png\"},{\"title\":\"Rishtey\",\"id\":\"279\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Rishtey.png\"},{\"title\":\"Zee Anmol\",\"id\":\"473\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zee_Anmol.png\"},{\"title\":\"Sony Pal\",\"id\":\"474\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sony_Pal.png\"},{\"title\":\"Sony SAB HD\",\"id\":\"471\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sony_SAB_HD.png\"},{\"title\":\"Zee Marathi\",\"id\":\"445\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zee_Marathi.png\"},{\"title\":\"Colors Marathi HD\",\"id\":\"755\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Colors_Marathi_HD.png\"},{\"title\":\"Colors Kannada HD\",\"id\":\"757\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Colors_Kannada_HD.png\"},{\"title\":\"Zee Kannada\",\"id\":\"689\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zee_Kannada.png\"},{\"title\":\"Comedy Central HD\",\"id\":\"1157\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Comedy_Central_HD.png\"},{\"title\":\"Zee Telugu\",\"id\":\"638\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zee_Telugu.png\"},{\"title\":\"Zee Tamil\",\"id\":\"628\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zee_Tamil.png\"},{\"title\":\"Colors Gujarati\",\"id\":\"196\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Colors_Gujarati.png\"},{\"title\":\"Zee Bangla\",\"id\":\"625\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zee_Bangla.png\"},{\"title\":\"Colors Bengali HD\",\"id\":\"756\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Colors_Bengali_HD.png\"},{\"title\":\"Epic\",\"id\":\"481\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Epic_HD.png\"},{\"title\":\"Colors Infinity HD\",\"id\":\"1158\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Colors_Infinity_HD.png\"},{\"title\":\"Zee Yuva\",\"id\":\"414\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zee_Yuva.png\"},{\"title\":\"PTC Punjabi\",\"id\":\"1171\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/PTC_Punjabi.png\"},{\"title\":\"Investigation Discovery\",\"id\":\"527\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/ID.png\"},{\"title\":\"DD India\",\"id\":\"528\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/DD_India.png\"},{\"title\":\"DD National\",\"id\":\"202\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/DD_National.png\"},{\"title\":\"Dabangg\",\"id\":\"532\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Dabangg.png\"},{\"title\":\"Colors Tamil HD\",\"id\":\"429\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Colors_Tamil_HD.png\"},{\"title\":\"DD Madhya Pradesh\",\"id\":\"536\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/DD_Madhya_Pradesh.png\"},{\"title\":\"DD Rajasthan (Jaipur)\",\"id\":\"538\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/DD_Rajasthan_Jaipur.png\"},{\"title\":\"Colors Cineplex\",\"id\":\"482\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Colors_Cineplex.png\"},{\"title\":\"Sony Pix HD\",\"id\":\"762\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sony_Pix_HD.png\"},{\"title\":\"Sony Max SD\",\"id\":\"289\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sony_Max_SD.png\"},{\"title\":\"Sony MAX2\",\"id\":\"483\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sony_MAX2.png\"},{\"title\":\"And Pictures HD\",\"id\":\"185\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/And_Pictures_HD.png\"},{\"title\":\"Zee Cinema\",\"id\":\"484\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zee_Cinema.png\"},{\"title\":\"B4U Movies\",\"id\":\"182\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/B4U_Movies.png\"},{\"title\":\"Jio Cinema\",\"id\":\"1074\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Jio_Cinema.png\"},{\"title\":\"Zee Bollywood\",\"id\":\"487\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zee_Classic.png\"},{\"title\":\"Bhojpuri Cinema\",\"id\":\"486\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Bhojpuri_Cinema.png\"},{\"title\":\"Enterr 10\",\"id\":\"485\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Enterr_10.png\"},{\"title\":\"Zee Action\",\"id\":\"488\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zee_Action.png\"},{\"title\":\"Zee Cinemalu\",\"id\":\"413\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zee_Cinemalu.png\"},{\"title\":\"Zee Anmol Cinema\",\"id\":\"415\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zee_Anmol_Cinema.png\"},{\"title\":\"News 18 India\",\"id\":\"231\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/IBN_7.png\"},{\"title\":\"India TV\",\"id\":\"235\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/India_TV.png\"},{\"title\":\"NDTV India\",\"id\":\"258\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/NDTV_India.png\"},{\"title\":\"Zee News\",\"id\":\"504\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zee_News.png\"},{\"title\":\"Times Now World\",\"id\":\"876\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Times_Now_HD.png\"},{\"title\":\"NDTV 24x7\",\"id\":\"255\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/NDTV_24x7.png\"},{\"title\":\"ETV Telugu\",\"id\":\"629\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/ETV_Telugu.png\"},{\"title\":\"Public TV\",\"id\":\"778\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Public_TV.png\"},{\"title\":\"24 Ghanta TV\",\"id\":\"464\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/24_Ghanta_TV.png\"},{\"title\":\"Sakshi tv\",\"id\":\"632\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sakshi_tv.png\"},{\"title\":\"Suvarna News\",\"id\":\"626\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Suvarna_News.png\"},{\"title\":\"NTV\",\"id\":\"646\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/NTV.png\"},{\"title\":\"Thanthi TV\",\"id\":\"830\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Thanthi_TV.png\"},{\"title\":\"News 24\",\"id\":\"501\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/News_24.png\"},{\"title\":\"TV9 Maharashtra\",\"id\":\"617\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/TV9_Maharashtra.png\"},{\"title\":\"BTV\",\"id\":\"938\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/BTV.png\"},{\"title\":\"Zee 24 Taas\",\"id\":\"442\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zee_24_Taas.png\"},{\"title\":\"Newslive\",\"id\":\"613\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Newslive.png\"},{\"title\":\"Sony Ten 3 HD\",\"id\":\"892\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Ten3_HD.png\"},{\"title\":\"Jio Sports HD\",\"id\":\"889\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Jio_Sports.png\"},{\"title\":\"Eurosport HD\",\"id\":\"875\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Dsports_HD.png\"},{\"title\":\"Jio Football HD\",\"id\":\"1156\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Jio_Football.png\"},{\"title\":\"Jio Cricket 1 HD\",\"id\":\"1160\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Jio_Cricket_1_HD.png\"},{\"title\":\"Jio Football 1 HD\",\"id\":\"1165\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Jio_Football_1.png\"},{\"title\":\"Jio Football 2 HD\",\"id\":\"1167\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Jio_Football_2.png\"},{\"title\":\"Jio Football 3 HD\",\"id\":\"1168\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Jio_Football_3.png\"},{\"title\":\"Jio Football 4 HD\",\"id\":\"1169\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Jio_Football_4.png\"},{\"title\":\"Sony Ten 1\",\"id\":\"514\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Ten_1.png\"},{\"title\":\"Ten 2\",\"id\":\"523\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Ten_2.png\"},{\"title\":\"Sony Ten 3\",\"id\":\"524\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Ten_3.png\"},{\"title\":\"Sony Ten 5\",\"id\":\"525\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sony_Six_SD.png\"},{\"title\":\"Zing\",\"id\":\"585\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zing.png\"},{\"title\":\"9XM\",\"id\":\"587\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/9XM.png\"},{\"title\":\"E 24\",\"id\":\"591\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/E_24.png\"},{\"title\":\"B4U Music\",\"id\":\"183\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/B4U_Music.png\"},{\"title\":\"ZOOM\",\"id\":\"592\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Zoom.png\"},{\"title\":\"MTV\",\"id\":\"248\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/MTV.png\"},{\"title\":\"9X Jalwa\",\"id\":\"440\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/9X_Jalwa.png\"},{\"title\":\"Public Music\",\"id\":\"773\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Public_Music.png\"},{\"title\":\"Jaya Max\",\"id\":\"420\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Jaya_Max.png\"},{\"title\":\"Sangeet Bhojpuri\",\"id\":\"741\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sangeet_Bhojpuri.png\"},{\"title\":\"PTC Music\",\"id\":\"1189\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/PTC_Music.png\"},{\"title\":\"Sangeet Bangla\",\"id\":\"740\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sangeet_Bangla.png\"},{\"title\":\"Sonic Hindi\",\"id\":\"815\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/sonic_Hindi.png\"},{\"title\":\"Sony Yay Hindi\",\"id\":\"872\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sony_Yay_Hindi.png\"},{\"title\":\"Nickelodeon\",\"id\":\"547\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Nickelodeon.png\"},{\"title\":\"Nick Tamil\",\"id\":\"546\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Nick_Tamil.png\"},{\"title\":\"Pogo Tamil\",\"id\":\"542\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Pogo_Tamil.png\"},{\"title\":\"CN HD+ English\",\"id\":\"1079\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/CN_HD_English.png\"},{\"title\":\"Cartoon Network Tamil\",\"id\":\"817\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Cartoon_Network_Tamil.png\"},{\"title\":\"Nick Telugu\",\"id\":\"543\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Nick_Telugu.png\"},{\"title\":\"Discovery Kids 2\",\"id\":\"554\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Discovery_Kids_2.png\"},{\"title\":\"Nick Junior\",\"id\":\"544\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Nick_Junior.png\"},{\"title\":\"Animal Planet HD World\",\"id\":\"286\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Animal_Planet_HD.png\"},{\"title\":\"Travelxp HD\",\"id\":\"164\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Travel_XP_HD.png\"},{\"title\":\"Animal Planet Hindi\",\"id\":\"566\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Animal_Planet_Hindi.png\"},{\"title\":\"Discovery Channel Hindi\",\"id\":\"575\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Discovery_Channel_Hindi.png\"},{\"title\":\"History TV18 HD Hindi\",\"id\":\"578\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/History_18_Hindi.png\"},{\"title\":\"Discovery Science\",\"id\":\"568\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Discovery_Science.png\"},{\"title\":\"Jio Events HD\",\"id\":\"871\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Jio_Events.png\"},{\"title\":\"Discovery Channel Telugu\",\"id\":\"576\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Discovery_Channel_Telugu.png\"},{\"title\":\"D Tamil\",\"id\":\"569\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Discovery_Channel_Tamil.png\"},{\"title\":\"History TV18 HD Tamil\",\"id\":\"579\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/History_18_Tamil.png\"},{\"title\":\"History TV18 HD Telugu\",\"id\":\"577\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/History_18_Telugu.png\"},{\"title\":\"Animal Planet English\",\"id\":\"567\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Animal_Planet_English.png\"},{\"title\":\"Samara News\",\"id\":\"1073\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Samara_News.png\"},{\"title\":\"Discovery Channel Bengali\",\"id\":\"573\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Discovery_Channel_Bengali.png\"},{\"title\":\"Travelxp Tamil\",\"id\":\"814\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Travel_XP_Tamil.png\"},{\"title\":\"Sony BBC Earth HD Telugu\",\"id\":\"854\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sony_BBC_Earth_HD_Telugu.png\"},{\"title\":\"Discovery\",\"id\":\"242\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Discovery.png\"},{\"title\":\"Sony BBC Earth HD Tamil\",\"id\":\"853\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sony_BBC_Earth_HD_Tamil.png\"},{\"title\":\"Ayush TV\",\"id\":\"1075\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Ayush_TV.png\"},{\"title\":\"Desi Channel\",\"id\":\"906\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Desi_Channel.png\"},{\"title\":\"Travelxp HD Hindi\",\"id\":\"562\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Travel_XP.png\"},{\"title\":\"TLC English\",\"id\":\"574\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/TLC_English.png\"},{\"title\":\"ETV Abhiruchi\",\"id\":\"565\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/ETV_Abhiruchi.png\"},{\"title\":\"TLC Hindi\",\"id\":\"571\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/TLC_Hindi.png\"},{\"title\":\"Sadhguru Television HD\",\"id\":\"1201\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sadhguru_Television.png\"},{\"title\":\"Kaumudy TV\",\"id\":\"563\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Kaumudy_TV.png\"},{\"title\":\"CNBC Tv 18\",\"id\":\"489\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/CNBC_Tv_18.png\"},{\"title\":\"ET Now\",\"id\":\"212\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/ET_Now.png\"},{\"title\":\"ET Now Swadesh\",\"id\":\"1907\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/ET_Now_Swadesh.png\"},{\"title\":\"Bhakti TV\",\"id\":\"776\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Bhakti_TV.png\"},{\"title\":\"Sri Sankara\",\"id\":\"829\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sri_Sankara.png\"},{\"title\":\"Sai TV\",\"id\":\"937\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/SaiTV.png\"},{\"title\":\"Peace of Mind\",\"id\":\"794\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Peace_of_Mind.png\"},{\"title\":\"mh1 Shraddha\",\"id\":\"609\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/mh1_Shraddha.png\"},{\"title\":\"Lakshya TV\",\"id\":\"886\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Lakshya_TV.png\"},{\"title\":\"Sri Venkateshwar Bhakti\",\"id\":\"598\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sri_Venkateshwar_Bhakti.png\"},{\"title\":\"Divya TV\",\"id\":\"801\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Divya_TV.png\"},{\"title\":\"Paras tv\",\"id\":\"602\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Paras_tv.png\"},{\"title\":\"Sadhna\",\"id\":\"593\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sadhna.png\"},{\"title\":\"Satsang TV\",\"id\":\"597\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Satsang_TV.png\"},{\"title\":\"Angel TV HD\",\"id\":\"835\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Angel_TV_HD.png\"},{\"title\":\"CVR OM Spiritual\",\"id\":\"772\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/CVR_OM_Spiritual.png\"},{\"title\":\"Aradhana TV\",\"id\":\"777\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Aradhana_TV.png\"},{\"title\":\"Jinvani TV\",\"id\":\"596\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Jinvani_TV.png\"},{\"title\":\"PTC Simran\",\"id\":\"1191\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/PTC_Simran.png\"},{\"title\":\"Shubh TV\",\"id\":\"828\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Shubh_TV.png\"},{\"title\":\"Sai Leela\",\"id\":\"1407\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sai_Leela.png\"},{\"title\":\"Hindu Dharmam\",\"id\":\"955\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Hindu_Dharmam.png\"},{\"title\":\"Subhavartha TV\",\"id\":\"607\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Subhavartha_TV.png\"},{\"title\":\"Divya Vani\",\"id\":\"934\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Divya_Vani.png\"},{\"title\":\"Arihant TV\",\"id\":\"466\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Arihant_TV.png\"},{\"title\":\"Mercy TV\",\"id\":\"924\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Rujumargam_TV.png\"},{\"title\":\"Calvary\",\"id\":\"977\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Calvary.png\"},{\"title\":\"Swayam Prabha 04\",\"id\":\"984\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_04.png\"},{\"title\":\"Swayam Prabha 05\",\"id\":\"986\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_05.png\"},{\"title\":\"Swayam Prabha 06\",\"id\":\"987\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_06.png\"},{\"title\":\"Swayam Prabha 07\",\"id\":\"985\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_07.png\"},{\"title\":\"Swayam Prabha 08\",\"id\":\"983\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_08.png\"},{\"title\":\"Swayam Prabha 09\",\"id\":\"988\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_09.png\"},{\"title\":\"Swayam Prabha 10\",\"id\":\"989\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_10.png\"},{\"title\":\"Swayam Prabha 11\",\"id\":\"990\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_11.png\"},{\"title\":\"Swayam Prabha 12\",\"id\":\"991\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_12.png\"},{\"title\":\"Jio Cricket 4 HD\",\"id\":\"1272\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Jio_Cricket_4_HD.png\"},{\"title\":\"Swayam Prabha 13\",\"id\":\"992\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_13.png\"},{\"title\":\"Swayam Prabha 14\",\"id\":\"993\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_14.png\"},{\"title\":\"Swayam Prabha 15\",\"id\":\"995\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_15.png\"},{\"title\":\"Swayam Prabha 16\",\"id\":\"994\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_16.png\"},{\"title\":\"Swayam Prabha 17\",\"id\":\"996\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_17.png\"},{\"title\":\"Swayam Prabha 18\",\"id\":\"999\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_18.png\"},{\"title\":\"Swayam Prabha 19\",\"id\":\"401\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_19.png\"},{\"title\":\"Swayam Prabha 20\",\"id\":\"403\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_20.png\"},{\"title\":\"Swayam Prabha 21\",\"id\":\"997\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_21.png\"},{\"title\":\"Swayam Prabha 22\",\"id\":\"998\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Swayam_Prabha_22.png\"},{\"title\":\"PM e Vidya 01\",\"id\":\"400\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/evidya_1.png\"},{\"title\":\"PM e Vidya 02\",\"id\":\"402\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/evidya_2.png\"},{\"title\":\"PM e Vidya 03\",\"id\":\"405\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/evidya_3.png\"},{\"title\":\"PM e Vidya 04\",\"id\":\"406\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/evidya_4.png\"},{\"title\":\"PM e Vidya 05\",\"id\":\"407\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/evidya_5.png\"},{\"title\":\"PM e Vidya 06\",\"id\":\"408\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/evidya_6.png\"},{\"title\":\"PM e Vidya 07\",\"id\":\"404\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/evidya_7.png\"},{\"title\":\"Raj Pariwar\",\"id\":\"533\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Raj_Pariwar.png\"},{\"title\":\"DD Bihar\",\"id\":\"539\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/DD_Bihar.png\"},{\"title\":\"DD Uttar Pradesh\",\"id\":\"540\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/DD_Uttar_Pradesh.png\"},{\"title\":\"Jaya TV HD\",\"id\":\"419\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Jaya_TV_HD.png\"},{\"title\":\"Mazavali Manorama HD\",\"id\":\"844\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Mazavali_Manorama_HD.png\"},{\"title\":\"MK TV\",\"id\":\"847\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/MK_TV.png\"},{\"title\":\"Colors Super\",\"id\":\"785\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Colors_Super.png\"},{\"title\":\"Vendhar TV\",\"id\":\"857\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Vendhar_TV.png\"},{\"title\":\"Sanjha TV\",\"id\":\"904\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Sanjha_TV.png\"},{\"title\":\"Live Punjabi TV\",\"id\":\"951\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Punjab_Today.png\"},{\"title\":\"JUSPunjabi\",\"id\":\"959\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/JUSPunjabi.png\"},{\"title\":\"Arre HD\",\"id\":\"963\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Arre.png\"},{\"title\":\"Peppers TV\",\"id\":\"796\",\"logo\":\"http://jiotv.catchup.cdn.jio.com/dare_images/images/Peppers_TV.png\"}]", new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			recyclerview2.setLayoutManager(new GridLayoutManager(getContext().getApplicationContext(), 4));
			
			
			recyclerview2.setAdapter(new Recyclerview2Adapter(allchnls));
		}catch(Exception e){
			 
		}
	}
	
	@Override
	public void onStop() {
		super.onStop();
		indet = recyclerview2.getChildAdapterPosition(recyclerview2.getFocusedChild());
	}
	
	@Override
	public void onResume() {
		super.onResume();
		try{
			recyclerview2.smoothScrollToPosition((int)indet);
		}catch(Exception e){
			 
		}
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
	
	public class Recyclerview2Adapter extends RecyclerView.Adapter<Recyclerview2Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("logo").toString())).into(imageview1);
				textview1.setText(_data.get((int)_position).get("title").toString());
				RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				_lp.setMargins(10,10,10,10);
				_view.setLayoutParams(_lp);
				
				_view.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						in.putExtra("link", "https://www.livesportsclub.tk/jioplay/app/live/".concat(_data.get((int)_position).get("id").toString().concat("/null/index.m3u8")));
						if (data.getString("server", "").equals("1")) {
							in.putExtra("link", "https://www.livesportsclub.tk/jioplay/app/live/".concat(_data.get((int)_position).get("id").toString().concat("/null/index.m3u8")));
						}
						else {
							in.putExtra("link", "https://crictv.tv/getm3u8/".concat(_data.get((int)_position).get("id").toString().concat("/master.m3u8?__cf_chl_tk=yVJ2uU3ZBhNonjX9rL6zm7yzfW1jUDiLSJRX1kHxbP8-1685520436-0-gaNycGzNCqU")));
							SketchwareUtil.showMessage(getContext().getApplicationContext(), "server2");
						}
						in.putExtra("index", String.valueOf((long)(_position)));
						in.putExtra("list", "1");
						in.setClass(getContext().getApplicationContext(), PlayerActivity.class);
						startActivity(in);
					}
				});
				_view.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View _view) {
						urso = "https://www.livesportsclub.tk/jioplay/app/live/".concat(_data.get((int)_position).get("id").toString().concat("/null/index.m3u8"));
						if (data.getString("server", "").equals("1")) {
							urso = "https://www.livesportsclub.tk/jioplay/app/live/".concat(_data.get((int)_position).get("id").toString().concat("/null/index.m3u8"));
						}
						else {
							urso = "https://crictv.tv/getm3u8/".concat(_data.get((int)_position).get("id").toString().concat("/master.m3u8"));
						}
						Uri uri = Uri.parse(urso);
						Intent vlcIntent = new Intent(Intent.ACTION_VIEW);
						vlcIntent.setPackage("org.videolan.vlc");
						vlcIntent.setComponent(new ComponentName("org.videolan.vlc", "org.videolan.vlc.gui.video.VideoPlayerActivity"));
						vlcIntent.setDataAndTypeAndNormalize(uri, "video/*");
						startActivity(vlcIntent);
						
						return true;
					}
				});
			}catch(Exception e){
				 
			}
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