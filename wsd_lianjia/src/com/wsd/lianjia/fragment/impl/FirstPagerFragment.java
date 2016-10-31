package com.wsd.lianjia.fragment.impl;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.wsd.lianjia.R;
import com.wsd.lianjia.activity.SearchActivity;
import com.wsd.lianjia.activity.SelectCityActivity;
import com.wsd.lianjia.customview.CustomScrollView.OnScrollChangedListener;
import com.wsd.lianjia.fragment.IFirstPagerFragment;
import com.wsd.lianjia.utils.DensityUtil;
/**
 * 首页fragment
 * @author wsd_leiguoqiang
 * QQ 274764936
 */
public class FirstPagerFragment extends Fragment implements IFirstPagerFragment,OnCheckedChangeListener
,OnClickListener,OnScrollChangedListener{
	/**
	 * firstpager到selectActivity页面请求码
	 */
	private static final int FIRST_PAGER_TO_SELECT_ACTIVITY = 1;
	/**
	 * firstpager到selectActivity页面请求码，弹出搜索框
	 */
	private static final int FIRST_PAGER_SEARCH_TO_SELECT_ACTIVITY = 2;
	/**
	 * fragment布局对象
	 */
	private View view;

	@ViewInject(R.id.home_fragment_radiobutton_fangzhu)
	private RadioButton button_fangzhu;

	@ViewInject(R.id.home_fragment_radiobutton_maifang)
	private RadioButton button_maifang;

	@ViewInject(R.id.home_fragment_radiobutton_zufang)
	private RadioButton button_zufang;

	@ViewInject(R.id.home_fragment_radiogroup)
	private RadioGroup radio_group;

	@ViewInject(R.id.home_fragment_three_menu_woyaomaifang)
	private LinearLayout contains_woyaomaifang;

	@ViewInject(R.id.home_fragment_three_contains_zufang)
	private LinearLayout contains_woyaozufang;

	@ViewInject(R.id.home_fragment_three_contains_fangzhu)
	private LinearLayout contains_woshifangzhu;
	/**
	 * 城市选择按钮
	 */
	@ViewInject(R.id.home_fragment_city)
	private TextView tv_city;
	/**
	 * 弹出搜索框
	 */
	@ViewInject(R.id.home_fragment_search_view)
	private LinearLayout contains_search;
	/**
	 *  弹出搜索框中城市显示textview
	 */
	@ViewInject(R.id.home_fragment_search_view_city)
	private TextView tv_search_city;
	/**
	 * 弹出搜索框的关键词textview
	 */
	@ViewInject(R.id.home_fragment_search_textview)
	private TextView tv_search;
	/**
	 * 最外层的自定义scrollview
	 */
	@ViewInject(R.id.home_fragment_scroll_view)
	private com.wsd.lianjia.customview.CustomScrollView contains_scroll;
	/**
	 * 城市选择textveiw顶部到父级容器scrollview上边的距离
	 */
	private int tv_city_y;
	/**
	 * 标记变量，标记弹出搜索框时候显示
	 */
	private boolean flag_display_search;
	/**
	 * 正文内容最外层容器
	 */
	@ViewInject(R.id.home_fragment_contains)
	private LinearLayout content_contains;
	/**
	 * 搜索按钮
	 */
	@ViewInject(R.id.home_fragment_search_button)
	private ImageButton imagebutton_search;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = View.inflate(getActivity(), R.layout.fragment_first_pager, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();
		initListeners();
	}

	private void init() {
		x.view().inject(this,view);
		tv_city_y = DensityUtil.dp2px(getActivity(), 150);
		contains_scroll.setOnScrollChangedListener(this);
	}

	private void initListeners() {
		radio_group.setOnCheckedChangeListener(this);
		tv_city.setOnClickListener(this);
		tv_search_city.setOnClickListener(this);
		tv_search.setOnClickListener(this);
		imagebutton_search.setOnClickListener(this);
	}

	@Override
	public void onResume() {
		super.onResume();
	}



	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==getActivity().RESULT_OK){
			switch (requestCode) {
			case FIRST_PAGER_TO_SELECT_ACTIVITY:
				tv_city.setText(data.getStringExtra("city_name"));
				tv_search_city.setText(data.getStringExtra("city_name"));
				break;
			case FIRST_PAGER_SEARCH_TO_SELECT_ACTIVITY:
				tv_search_city.setText(data.getStringExtra("city_name"));
				tv_city.setText(data.getStringExtra("city_name"));
				
				break;
			}
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.home_fragment_radiobutton_maifang:
			contains_woyaomaifang.setVisibility(View.VISIBLE);
			contains_woyaozufang.setVisibility(View.INVISIBLE);
			contains_woshifangzhu.setVisibility(View.INVISIBLE);
			break;
		case R.id.home_fragment_radiobutton_zufang:
			contains_woyaomaifang.setVisibility(View.INVISIBLE);
			contains_woyaozufang.setVisibility(View.VISIBLE);
			contains_woshifangzhu.setVisibility(View.INVISIBLE);
			break;
		case R.id.home_fragment_radiobutton_fangzhu:
			contains_woyaomaifang.setVisibility(View.INVISIBLE);
			contains_woyaozufang.setVisibility(View.INVISIBLE);
			contains_woshifangzhu.setVisibility(View.VISIBLE);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//城市选择按钮
		case R.id.home_fragment_city:
			Intent intent = new Intent(getActivity(),SelectCityActivity.class);
			startActivityForResult(intent, FIRST_PAGER_TO_SELECT_ACTIVITY);
			break;
			//弹出框城市按钮
		case R.id.home_fragment_search_view_city:
			Intent in = new Intent(getActivity(),SelectCityActivity.class);
			startActivityForResult(in, FIRST_PAGER_SEARCH_TO_SELECT_ACTIVITY);
			break;
			// 弹出搜索框关键字按钮
		case R.id.home_fragment_search_textview:
			startActivity(new Intent(getActivity(), SearchActivity.class));
			break;
		//搜索按钮
		case R.id.home_fragment_search_button:
			startActivity(new Intent(getActivity(), SearchActivity.class));
			break;
		}
	}
	/**
	 * 重写scrollveiw回调接口方法
	 * l,t代表scrollview本身的左上角坐标点
	 */
	@Override
	public void onScrollChanged(int l, int t, int oldl, int oldt) {
		//操作弹出搜索菜单
		t = Math.abs(t);
		if(t>=tv_city_y&&(!flag_display_search)){
			flag_display_search = true;
			//弹出搜索框
			TranslateAnimation animation = new TranslateAnimation(contains_search.getWidth(), 0, 0, 0);
			animation.setDuration(200);
			animation.setFillAfter(true);
			contains_search.startAnimation(animation);
			contains_search.setVisibility(View.VISIBLE);
		}else if(t<tv_city_y&&flag_display_search){
			flag_display_search = false;
			//弹出搜索框
			TranslateAnimation animation = new TranslateAnimation(0,contains_search.getWidth(), 0, 0);
			animation.setDuration(200);
			animation.setFillAfter(true);
			contains_search.startAnimation(animation);
			contains_search.setVisibility(View.INVISIBLE);
		}
	}
}
