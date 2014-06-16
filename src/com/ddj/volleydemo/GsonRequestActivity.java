/**
 * @author dingdj
 * Date:2014-6-16上午9:52:41
 *
 */
package com.ddj.volleydemo;

import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

/**
 * @author dingdj
 * Date:2014-6-16上午9:52:41
 *
 */
public class GsonRequestActivity extends Activity {
	
	private RequestQueue mQueue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mQueue = Volley.newRequestQueue(this);
		GsonRequest<Weather> gsonRequest = new GsonRequest<Weather>(
				"http://www.weather.com.cn/data/sk/101010100.html", Weather.class,
				new Response.Listener<Weather>() {
					@Override
					public void onResponse(Weather weather) {
						WeatherInfo weatherInfo = weather.getWeatherinfo();
						Log.d("TAG", "city is " + weatherInfo.getCity());
						Log.d("TAG", "temp is " + weatherInfo.getTemp());
						Log.d("TAG", "time is " + weatherInfo.getTime());
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);
					}
				});
		mQueue.add(gsonRequest);
	}
	
	
	public class GsonRequest<T> extends Request<T> {

		private final Listener<T> mListener;

		private Gson mGson;

		private Class<T> mClass;

		public GsonRequest(int method, String url, Class<T> clazz, Listener<T> listener,
				ErrorListener errorListener) {
			super(method, url, errorListener);
			mGson = new Gson();
			mClass = clazz;
			mListener = listener;
		}

		public GsonRequest(String url, Class<T> clazz, Listener<T> listener,
				ErrorListener errorListener) {
			this(Method.GET, url, clazz, listener, errorListener);
		}

		@Override
		protected Response<T> parseNetworkResponse(NetworkResponse response) {
			try {
				String jsonString = new String(response.data,
						HttpHeaderParser.parseCharset(response.headers));
				return Response.success(mGson.fromJson(jsonString, mClass),
						HttpHeaderParser.parseCacheHeaders(response));
			} catch (UnsupportedEncodingException e) {
				return Response.error(new ParseError(e));
			}
		}

		@Override
		protected void deliverResponse(T response) {
			mListener.onResponse(response);
		}

	}
	
	public class Weather {

		private WeatherInfo weatherinfo;

		public WeatherInfo getWeatherinfo() {
			return weatherinfo;
		}

		public void setWeatherinfo(WeatherInfo weatherinfo) {
			this.weatherinfo = weatherinfo;
		}

	}
	
	public class WeatherInfo {

		private String city;

		private String temp;

		private String time;

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getTemp() {
			return temp;
		}

		public void setTemp(String temp) {
			this.temp = temp;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

	}

}
