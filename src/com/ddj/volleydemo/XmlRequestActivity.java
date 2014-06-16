/**
 * @author dingdj
 * Date:2014-6-16上午9:44:36
 *
 */
package com.ddj.volleydemo;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

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

/**
 * @author dingdj Date:2014-6-16上午9:44:36
 * 
 */
public class XmlRequestActivity extends Activity {
	
	private RequestQueue mQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mQueue = Volley.newRequestQueue(this);
		XMLRequest xmlRequest = new XMLRequest(
				"http://flash.weather.com.cn/wmaps/xml/china.xml",
				new Response.Listener<XmlPullParser>() {
					@Override
					public void onResponse(XmlPullParser response) {
						try {
							int eventType = response.getEventType();
							while (eventType != XmlPullParser.END_DOCUMENT) {
								switch (eventType) {
								case XmlPullParser.START_TAG:
									String nodeName = response.getName();
									if ("city".equals(nodeName)) {
										String pName = response.getAttributeValue(0);
										Log.d("TAG", "pName is " + pName);
									}
									break;
								}
								eventType = response.next();
							}
						} catch (XmlPullParserException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);
					}
				});
		mQueue.add(xmlRequest);
	}

	public class XMLRequest extends Request<XmlPullParser> {

		private final Listener<XmlPullParser> mListener;

		/**
		 * @param method
		 * @param url
		 * @param listener
		 */
		public XMLRequest(int method, String url, Listener<XmlPullParser> listener, ErrorListener errorListener) {
			super(method, url, errorListener);
			mListener = listener;
		}

		public XMLRequest(String url, Listener<XmlPullParser> listener, ErrorListener errorListener) {
			this(Method.GET, url, listener, errorListener);
		}

		@Override
		protected void deliverResponse(XmlPullParser response) {
			mListener.onResponse(response);
		}

		@Override
		protected Response<XmlPullParser> parseNetworkResponse(NetworkResponse response) {
			try {
				String xmlString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
				XmlPullParser xmlPullParser = factory.newPullParser();
				xmlPullParser.setInput(new StringReader(xmlString));
				return Response.success(xmlPullParser, HttpHeaderParser.parseCacheHeaders(response));
			} catch (UnsupportedEncodingException e) {
				return Response.error(new ParseError(e));
			} catch (XmlPullParserException e) {
				return Response.error(new ParseError(e));
			}
		}

	}

}
