/**
 * @author dingdj
 * Date:2014-6-13下午5:57:49
 *
 */
package com.ddj.volleydemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

/**
 * @author dingdj Date:2014-6-13下午5:57:49
 * 
 */
public class ImageRequestAcitvity extends Activity {

	private RequestQueue requestQueue;

	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = (ImageView) findViewById(R.id.image);
		requestQueue = Volley.newRequestQueue(this);

		ImageRequest imageRequest = new ImageRequest("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", new Response.Listener<Bitmap>() {
			@Override
			public void onResponse(Bitmap response) {
				imageView.setImageBitmap(response);
			}
		}, 0, 0, Config.RGB_565, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
			}
		});
		requestQueue.add(imageRequest);
	}

}
