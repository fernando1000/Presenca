package br.com.x10d.presenca.webservice;

import com.android.volley.DefaultRetryPolicy;

import android.util.Log;

public class VolleyTimeout {
	
	private static int TIME_OUT_MS = 7000;
		
	public static DefaultRetryPolicy devolveTimeout(){
		
		Log.i("tag", "passei pelo devolveTimeout");
		
		return (new DefaultRetryPolicy(TIME_OUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)); 
	}
}
