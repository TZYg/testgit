package com.wsd.lianjia.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.wsd.lianjia.R;
import com.wsd.lianjia.constant.Constant;
import com.wsd.lianjia.customview.CustomAutoSaveDataCheckBox;
import com.wsd.lianjia.customview.CustomSelectedLinearLayout;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 *  搜索页面详细结果页面
 * @author wsd_leiguoqiang
 * QQ274764936
 */
public class SearchDetailsActivity extends BaseActivity implements OnCheckedChangeListener,OnClickListener{
	/**
	 * radiogroup对象
	 */
	@ViewInject(R.id.activity_search_details_rg)
	private RadioGroup rg_menu;
	/**
	 * 区域
	 */
	@ViewInject(R.id.activity_search_details_rb_area)
	private RadioButton rb_area;
	/**
	 * 类型
	 */
	@ViewInject(R.id.activity_search_details_rb_type)
	private RadioButton rb_type;
	/**
	 * 价格
	 */
	@ViewInject(R.id.activity_search_details_rb_price)
	private RadioButton rb_price;
	/**
	 * 更多
	 */
	@ViewInject(R.id.activity_search_details_rb_more)
	private RadioButton rb_more;
	/**
	 * 当前显示的popupwindow,初始化为无意义值-1
	 */
	private int current_condition_layout = -1;
	/**
	 * 新显示的popupwindow
	 */
	private int new_condition_layout;
	/**
	 * 条件布局整体容器
	 */
	@ViewInject(R.id.activity_search_details_condition)
	private LinearLayout ll_condition_contains;
	/**
	 * 条件布局数组，总共4个，和radiogroup下标数量一致，并且对应关系
	 */
	private View[] array_condition_layout = new View[4];
	@ViewInject(R.id.activity_search_details_condition_more)
	private ViewGroup vg_contains_more;
	@ViewInject(R.id.activity_search_details_condition_price)
	private ViewGroup vg_contains_price;
	@ViewInject(R.id.activity_search_details_condition_type)
	private ViewGroup vg_contains_type;
	@ViewInject(R.id.activity_search_details_condition_area)
	private ViewGroup vg_contains_area;
	/**
	 * 整体条件布局尾部透明部分
	 */
	@ViewInject(R.id.activity_search_details_not_content)
	private View view_not_content;
	/**
	 * 朝东选择按钮，测试使用
	 */
	@ViewInject(R.id.activity_search_details_condition_more_chaodong)
	private CustomAutoSaveDataCheckBox checkbox_chaodong;
	/**
	 * 封装更多条件布局中的选择按钮数据
	 */
	private List<Map<String, String>> list_checkbox = new ArrayList<Map<String,String>>();
	/**
	 * map集合中封装checkbox中数据的key,朝向字段
	 */
	private String key_orientation = "orientation";
	/**
	 * 户型选择
	 */
	@ViewInject(R.id.activity_search_details_condition_type_buxian)
	private CustomSelectedLinearLayout checkedText_buxian;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_details);
		init();
		initListeners();
	}

	private void init() {
		x.view().inject(this);
		array_condition_layout[0] = vg_contains_area;
		array_condition_layout[1] = vg_contains_price;
		array_condition_layout[2] = vg_contains_type;
		array_condition_layout[3] = vg_contains_more;
		//测试自定义checkbox自动封装数据功能
		checkbox_chaodong.setDataList(list_checkbox, key_orientation);
		checkedText_buxian.setDataList(list_checkbox, key_orientation);
	}

	private void initListeners() {
		rg_menu.setOnCheckedChangeListener(this);
	}
	/**
	 * 自定义方法：进行条件布局的显示和隐藏
	 * @param new_condition_layout：radiogroup选择时候的下标值
	 */
	private void showConditionLayout(int new_condition_layout){
		/**
		 * 大前提：如果当前显示布局标记变量为-1，则开启条件布局正文动画，并且显示新条件布局
		 * 1）当前显示和新显示的布局一样，则隐藏当前布局，并更新布局下标标记变量为-1
		 * 2）当前显示和新显示的布局不一样，则隐藏当前布局，显示新布局，并且更新布局下标标记变量为当前值
		 * 注意：当新显示条件布局为更多时候，将半透明的底部去掉
		 */
		if(current_condition_layout==-1){
			if(new_condition_layout==3){
				view_not_content.setVisibility(View.GONE);
			}
			//显示条件布局外层容器
			ll_condition_contains.setVisibility(View.VISIBLE);
			//显示文本条件布局
			array_condition_layout[new_condition_layout].setVisibility(View.VISIBLE);
			//开启布局动画
			startAnimation(new_condition_layout);
			//编辑新布局下标
			current_condition_layout = new_condition_layout;
		}else{
			//显示布局或关闭最外层布局
			array_condition_layout[current_condition_layout].setVisibility(View.INVISIBLE);
			//不是最后一个
			if((current_condition_layout!=new_condition_layout)&&(new_condition_layout!=3)){
				//显示空白布局
				view_not_content.setVisibility(View.VISIBLE);
				array_condition_layout[new_condition_layout].setVisibility(View.VISIBLE);
				current_condition_layout = new_condition_layout;
				//是最后一个
			}else if((current_condition_layout!=new_condition_layout)&&(new_condition_layout==3)){
				//将空白布局隐藏掉
				view_not_content.setVisibility(View.GONE);
				//显示新布局
				array_condition_layout[new_condition_layout].setVisibility(View.VISIBLE);
				current_condition_layout = new_condition_layout;
			}else{
				//隐藏整体条件布局
				ll_condition_contains.setVisibility(View.INVISIBLE);
				view_not_content.setVisibility(View.VISIBLE);
				current_condition_layout = -1;
			}
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		//根据需要进行显示信的
		switch (checkedId) {
		case R.id.activity_search_details_rb_area:
			new_condition_layout = 0;
			showConditionLayout(new_condition_layout);
			break;
		case R.id.activity_search_details_rb_price:
			new_condition_layout = 1;
			showConditionLayout(new_condition_layout);
			break;
		case R.id.activity_search_details_rb_type:
			new_condition_layout = 2;
			showConditionLayout(new_condition_layout);
			break;
		case R.id.activity_search_details_rb_more:
			new_condition_layout = 3;
			showConditionLayout(new_condition_layout);
			break;
			//条件radiobutton关闭按钮
		}
	}
	/**
	 * 自定义方法：实现条件文本布局的动画效果
	 * @param index：布局参数
	 */
	private void startAnimation(int index){
		View view = array_condition_layout[index];
		TranslateAnimation animation = new TranslateAnimation(0, 0, -view.getHeight()+ll_condition_contains.getTop(), ll_condition_contains.getTop());
		animation.setDuration(Constant.ANIMATION_TIME_NORMAL);
		view.startAnimation(animation);
	}

	@Override
	public void onClick(View v) {
	}
}
