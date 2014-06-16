/**
 * @author dingdj
 * Date:2014-6-16上午9:20:33
 *
 */
package com.ddj.volleydemo;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.ddj.volleydemo.ImageLoaderActivity.BitmapCache;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;

/**
 * @author dingdj Date:2014-6-16上午9:20:33
 * 
 */
public class NetworkImageViewActivity extends Activity {

	private RequestQueue requestQueue;
	NetworkImageView networkImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.network_imageview);
		networkImageView = (NetworkImageView) findViewById(R.id.network_image_view);

		networkImageView.setDefaultImageResId(R.drawable.default_image);
		networkImageView.setErrorImageResId(R.drawable.failed_image);

		requestQueue =  Volley.newRequestQueue(this);
		ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());
		networkImageView.setImageUrl("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", imageLoader);
	}

	// 图片缓存
	public class BitmapCache implements ImageCache {

		private LruCache<String, Bitmap> mCache;

		public BitmapCache() {
			int maxSize = 10 * 1024 * 1024;
			mCache = new LruCache<String, Bitmap>(maxSize) {
				@Override
				protected int sizeOf(String key, Bitmap bitmap) {
					return bitmap.getRowBytes() * bitmap.getHeight();
				}
			};
		}

		@Override
		public Bitmap getBitmap(String url) {
			return mCache.get(url);
		}

		@Override
		public void putBitmap(String url, Bitmap bitmap) {
			mCache.put(url, bitmap);
		}

	}

}
