/**
 * @author dingdj
 * Date:2014-6-13下午5:01:55
 *
 */
package com.ddj.volleydemo;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * @author dingdj
 * Date:2014-6-13下午5:01:55
 *
 */
public class MainActivity extends Activity {
	
	private TextView textView;
	
	private RequestQueue requestQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView)findViewById(R.id.content);
		requestQueue = Volley.newRequestQueue(this); 
		
		StringRequest stringRequest = new StringRequest("http://www.baidu.com",
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.e("TAG", response);
						textView.setText(response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);
					}
				});
		requestQueue.add(stringRequest);
	}
	
	
	
	

}
