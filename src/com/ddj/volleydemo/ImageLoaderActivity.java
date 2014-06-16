/**
 * @author dingdj
 * Date:2014-6-16上午9:10:39
 *
 */
package com.ddj.volleydemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;

/**
 * @author dingdj Date:2014-6-16上午9:10:39
 * 
 */
public class ImageLoaderActivity extends Activity {

	private RequestQueue requestQueue;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		requestQueue = Volley.newRequestQueue(this);
		
		imageView = (ImageView) findViewById(R.id.image);
		
		ImageListener listener = ImageLoader.getImageListener(imageView, R.drawable.default_image, R.drawable.failed_image);

		ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());

		imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", listener, 200, 200);
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
