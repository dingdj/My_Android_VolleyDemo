/**
 * @author dingdj
 * Date:2014-6-13下午5:46:59
 *
 */
package com.ddj.volleydemo;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

/**
 * @author dingdj
 * Date:2014-6-13下午5:46:59
 *
 */
public class JsonRequestActivity extends Activity {
	
	private RequestQueue requestQueue;
	
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView)findViewById(R.id.content);
		requestQueue = Volley.newRequestQueue(this); 
		
		JsonObjectRequest jsonRequest = new JsonObjectRequest("http://m.weather.com.cn/data/101010100.html", 
				null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d("TAG", response.toString());
						textView.setText(response.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);
					}
				});
		requestQueue.add(jsonRequest);
	}

}
