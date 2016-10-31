package com.wsd.lianjia.activity;

import java.text.AttributedCharacterIterator.Attribute;
import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wsd.lianjia.R;
import com.wsd.lianjia.adapter.HistorySearchAdapter;
import com.wsd.lianjia.entity.HistoryInfo;
import com.wsd.lianjia.entity.HistoryInfo.HistorySearch;
/**
 * SearchActivity:搜索页面
 * @author wsd_leiguoqiang
 * QQ 274764936
 */
public class SearchActivity extends Activity implements OnClickListener{
	/**
	 * 选择菜单
	 */
	@ViewInject(R.id.activity_search_menu_textview)
	private TextView tv_menu;
	/**
	 * 搜索框
	 */
	@ViewInject(R.id.activity_search_bar)
	private EditText et_search_bar;
	/**
	 * 取消按钮
	 */
	@ViewInject(R.id.activity_search_cancel)
	private TextView tv_cancle;

	private PopupWindow popupwindow;
	/**
	 * 历史搜索信息listview
	 */
	@ViewInject(R.id.activity_search_listview_info)
	private ListView listview_history;
	/**
	 * 历史信息集合,后续需要通过访问服务器进行数据的采集,这里制作一个本地存储测试
	 */
	private List<HistorySearch> list_history = new ArrayList<HistorySearch>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		init();
		initListeners();
	}

	private void init() {
		x.view().inject(this);
		//给listveiw添加脚布局
		addListviewHistoryFooterView();
		list_history = new HistoryInfo().readHistoryInfo().getList_historySearch();
		HistorySearchAdapter adapter = new HistorySearchAdapter(list_history, this);
		listview_history.setAdapter(adapter);
		TextView emptyView = new TextView(this);
		emptyView.setText("没有任何历史搜索数据");
		emptyView.setHeight(100);
		emptyView.setWidth(50);
		listview_history.setEmptyView(emptyView);
	}
	/**
	 * listveiw添加脚布局
	 */
	private void addListviewHistoryFooterView() {
		TextView tv_bottom = new TextView(this);
		tv_bottom.setText("删除所有历史数据");
		tv_bottom.setWidth(100);
		tv_bottom.setHeight(50);
		//将文字大小设置为12dp
		tv_bottom.setTextSize(getResources().getDisplayMetrics().density*12+0.5f);
		tv_bottom.setTextColor(getResources().getColor(R.color.black_light));
		tv_bottom.setGravity(Gravity.CENTER);
		tv_bottom.setPadding(0, 5, 0, 5);
		listview_history.addFooterView(tv_bottom,null, true);
	}

	private void initListeners() {
		tv_cancle.setOnClickListener(this);
		tv_menu.setOnClickListener(this);
		et_search_bar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.activity_search_menu_textview:
			/**
			 * 弹出三个子菜单popuwindow
			 */
			showPopupwindow();

			break;
		case R.id.activity_search_bar:

			break;
		case R.id.activity_search_cancel:
			startActivity(new Intent(SearchActivity.this, SearchDetailsActivity.class));
//			finish();
			break;

		}
	}
	/**
	 * 自定义方法，显示弹窗
	 */
	private void showPopupwindow() {
		View view = View.inflate(this, R.layout.modle_popuwindow_search_activity, null);
		view.measure(0, 0);
		popupwindow = new PopupWindow(view, tv_menu.getWidth(), view.getMeasuredHeight());
		TextView tv_ershoufang = (TextView) view.findViewById(R.id.modle_popuwindow_ershoufang);
		TextView tv_xinfang = (TextView) view.findViewById(R.id.modle_popuwindow_xinfang);
		TextView tv_zufang = (TextView) view.findViewById(R.id.modle_popuwindow_zufang);
		popupwindow.setFocusable(true);
		popupwindow.setOutsideTouchable(true);
		popupwindow.update();
		//显示popupwindow
		popupwindow.showAsDropDown(tv_menu, 0, 10);
		popupwindow.getContentView().setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				dismissPopupwindow();
				return false;
			}
		});
		tv_ershoufang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tv_menu.setText("二手房");
				dismissPopupwindow();
			}
		});
		tv_xinfang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tv_menu.setText("新房");
				dismissPopupwindow();
			}
		});
		tv_zufang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tv_menu.setText("租房");
				dismissPopupwindow();
			}
		});
	}
	/**
	 * 自定一方法，关闭并且销毁popupwindow对象
	 */
	private void dismissPopupwindow(){
		popupwindow.setFocusable(true);
		popupwindow.dismiss();
		popupwindow = null;
		System.gc();
	}
}
