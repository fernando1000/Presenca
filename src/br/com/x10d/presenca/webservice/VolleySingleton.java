package br.com.x10d.presenca.webservice;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

	private static VolleySingleton volleySingleton = null;
	private RequestQueue requestQueue;	
	
	private VolleySingleton(Context context){  
		
		requestQueue  = Volley.newRequestQueue(context);	    
	}  
	
	public static VolleySingleton getInstanciaDoVolleySingleton(Context context){  
		   
		if(volleySingleton == null){  
		     
			volleySingleton = new VolleySingleton(context);
		}  
		
		return volleySingleton;   
	}  
	
	public RequestQueue getRequestQueue(){  
	    return this.requestQueue;
	}  
	   	
}
