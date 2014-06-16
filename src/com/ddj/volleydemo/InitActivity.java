/**
 * @author dingdj
 * Date:2014-6-13下午5:25:18
 *
 */
package com.ddj.volleydemo;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ddj.commonkit.android.ActivityUtil;

/**
 * @author dingdj Date:2014-6-13下午5:25:18
 * 
 */
public class InitActivity extends ListActivity {

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listView = getListView();
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, 
				new String[] { "StringRequest请求MainActivity",
							    "StringRequestPost请求PostRequestActivity",
				                "JsonObjectRequest请求JsonRequestActivity",
				                "ImageRequest请求ImageRequestAcitvity",
				                "ImageLoader请求ImageLoaderAcitvity",
				                "NetworkImageView使用NetworkImageViewAcitvity",
				                "XmlRequestActivity使用XmlRequestActivity",
				                "GsonRequestActivity使用GsonRequestActivity"}));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		if(position == 0){
			ActivityUtil.startActivityByClassNameSafe(this, MainActivity.class.getCanonicalName());
		}else if(position == 1){
			ActivityUtil.startActivityByClassNameSafe(this, PostRequestActivity.class.getCanonicalName());
		}else if(position == 2){
			ActivityUtil.startActivityByClassNameSafe(this, JsonRequestActivity.class.getCanonicalName());
		}else if(position == 3){
			ActivityUtil.startActivityByClassNameSafe(this, ImageRequestAcitvity.class.getCanonicalName());
		}else if(position == 4){
			ActivityUtil.startActivityByClassNameSafe(this, ImageLoaderActivity.class.getCanonicalName());
		}else if(position == 5){
			ActivityUtil.startActivityByClassNameSafe(this, NetworkImageViewActivity.class.getCanonicalName());
		}else if(position == 6){
			ActivityUtil.startActivityByClassNameSafe(this, XmlRequestActivity.class.getCanonicalName());
		}else if(position == 7){
			ActivityUtil.startActivityByClassNameSafe(this, GsonRequestActivity.class.getCanonicalName());
		}
		super.onListItemClick(l, v, position, id);
	}
	
	

}
