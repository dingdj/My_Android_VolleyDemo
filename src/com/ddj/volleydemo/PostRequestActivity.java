/**
 * @author dingdj
 * Date:2014-6-13下午5:39:53
 *
 */
package com.ddj.volleydemo;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * @author dingdj Date:2014-6-13下午5:39:53
 * 
 */
public class PostRequestActivity extends Activity {

	private RequestQueue requestQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		requestQueue = Volley.newRequestQueue(this);

		StringRequest stringRequest = new StringRequest(Method.POST, "http://www.baidu.com", new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.e("TAG", response);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("TAG", error.getMessage(), error);
			}
		}) {
			//post 请求参数
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("params1", "value1");
				map.put("params2", "value2");
				return map;
			}

		};
		requestQueue.add(stringRequest);
	}

}
