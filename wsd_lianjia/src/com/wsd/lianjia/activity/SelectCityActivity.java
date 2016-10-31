package com.wsd.lianjia.activity;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.wsd.lianjia.R;
/**
 * Activity:城市选择
 * @author wsd_leiguoqiang
 * QQ：274764936
 * 
 * 实现原理：
 * 通过访问服务器，获取相关成熟数据（xml、json），将成熟数据封装在list集合中，呈现到listviw控件中
 */
public class SelectCityActivity extends Activity implements OnItemClickListener,OnClickListener{

	@ViewInject(R.id.activity_select_city_current_city)
	private TextView tv_current_city;

	@ViewInject(R.id.activity_select_city_listview)
	private ListView listview_city;
	
	@ViewInject(R.id.activity_select_city_back)
	private ImageButton image_button_back;

	/**
	 * 服务器暂且未搭建，现在手动模拟数据
	 */
	private List<String> list_cities = new ArrayList<String>();

	private String[] array_cities;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_city);
		init();
		initListerners();
	}

	private void init() {
		x.view().inject(this);
		array_cities = new String[] {"北京","上海","广州","深圳","杭州","重庆","珠海","武汉","天津"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.modle_listview_textview_item, array_cities);
		listview_city.setAdapter(adapter);
	}

	private void initListerners() {
		listview_city.setOnItemClickListener(this);
		image_button_back.setOnClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		intent.putExtra("city_name", array_cities[position]);
		setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.activity_select_city_back:
			finish();
			break;

		}
	}
}
