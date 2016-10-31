package com.wsd.lianjia.activity;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.wsd.lianjia.R;
import com.wsd.lianjia.application.MyApplication;
import com.wsd.lianjia.fragment.impl.FirstPagerFragment;
import com.wsd.lianjia.fragment.impl.MessageFragment;
import com.wsd.lianjia.fragment.impl.MineFragment;
import com.wsd.lianjia.fragment.impl.SubscribeFragment;

/**
 * MainActivity:app主Activity
 * @author wsd_leiguoqiang
 * QQ 274764936
 */
public class MainActivity extends FragmentActivity implements OnCheckedChangeListener{
	/**
	 * RadioButton数组
	 */
	private RadioButton[] array_radiobutton = new RadioButton[4];
	/**
	 * fragment数组
	 */
	private Fragment[] array_fragment = new Fragment[4];
	/**
	 * fragment对象管理器
	 */
	private FragmentManager fragmentManager;
	/**
	 * fragment事物提交器，节省内存
	 */
	private FragmentTransaction transaction;
	/**
	 * 首页fragment
	 */
	private FirstPagerFragment first_pager_fragment;
	/**
	 * 消息fragment
	 */
	private MessageFragment message_fragment;
	/**
	 * 预约fragment
	 */
	private SubscribeFragment subscribe_fragment;
	/**
	 * 我的fragment
	 */
	private MineFragment mine_fragment;

	@ViewInject(R.id.mainActivity_radiobutton_first_pager)
	private RadioButton first_pager_button;

	@ViewInject(R.id.mainActivity_radiobutton_message)
	private RadioButton message_button;

	@ViewInject(R.id.mainActivity_radiobutton_yuyue)
	private RadioButton subscribe_button;

	@ViewInject(R.id.mainActivity_radiobutton_mine)
	private RadioButton mine_button;
	@ViewInject(R.id.mainActivity_bottom_menu_radiogroup)
	private RadioGroup radio_group;
	/**
	 * 存储fragment的标记
	 */
	private String[] array_tag_fragment = new String[4];
	/**
	 * 标记当前显示的fragment下标
	 */
	private int current_fragment_display = -1;
	private List<Activity> list_activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init(savedInstanceState);
		initListeners();
	}

	private void init(Bundle savedInstanceState) {
		x.view().inject(this);
		MyApplication application = (MyApplication) MyApplication.getContext();
		list_activity = application.getList_activity();
		list_activity.add(this);

		if(fragmentManager==null){
			fragmentManager = getSupportFragmentManager();
		}
		array_tag_fragment[0] = "first_pager_fragment";
		array_tag_fragment[1] = "message_fragment";
		array_tag_fragment[2] = "subscribe_fragment";
		array_tag_fragment[3] = "mine_fragment";
		/**
		 * 实例化fragment
		 * 处理的目的：当app因为系统内存不足意外销毁的时候，重启该activity的时候，fragment不会重叠
		 */
		if(savedInstanceState!=null){
			/**
			 * 当savedinstanceState不为空的时候，说明该activity已经创建过一次，
			 * 里面就保存了之前的fragment对象，直接赋值给fragment全局变量
			 */
			first_pager_fragment = (FirstPagerFragment) fragmentManager.findFragmentByTag(array_tag_fragment[0]);
			message_fragment = (MessageFragment) fragmentManager.findFragmentByTag(array_tag_fragment[1]);
			subscribe_fragment = (SubscribeFragment) fragmentManager.findFragmentByTag(array_tag_fragment[2]);
			mine_fragment = (MineFragment) fragmentManager.findFragmentByTag(array_tag_fragment[3]);

		}
		//		else{
		//			if(first_pager_fragment==null){
		//				first_pager_fragment = new FirstPagerFragment();
		//			}
		//			if(message_fragment==null){
		//				message_fragment = new MessageFragment();
		//			}
		//			if(subscribe_fragment==null){
		//				subscribe_fragment = new SubscribeFragment();
		//			}
		//			if(mine_fragment==null){
		//				mine_fragment = new MineFragment();
		//			}
		//		}
		/**
		 * 初始化fragment和radiobutton数组
		 */
		array_fragment[0] = first_pager_fragment; 
		array_fragment[1] = message_fragment; 
		array_fragment[2] = subscribe_fragment; 
		array_fragment[3] = mine_fragment;

		array_radiobutton[0] = first_pager_button;
		array_radiobutton[1] = message_button;
		array_radiobutton[2] = subscribe_button;
		array_radiobutton[3] = mine_button;

		//设置默认显示fragment
		displayFragment(0);

	}

	private void initListeners() {
		radio_group.setOnCheckedChangeListener(this);
	}
	/**
	 * 自定义方法：显示fragment
	 * @param index
	 */
	private void displayFragment(int index){
		transaction = fragmentManager.beginTransaction();
		if(current_fragment_display!=-1){
			//隐藏当前fragment
			transaction.hide(array_fragment[current_fragment_display]);
		}
		if(array_fragment[index]==null){
			switch (index) {
			case 0:
				array_fragment[index] = new FirstPagerFragment();
				break;
			case 1:
				array_fragment[index] = new MessageFragment();
				break;
			case 2:
				array_fragment[index] = new SubscribeFragment();
				break;
			case 3:
				array_fragment[index] = new MineFragment();
				break;
			}
			
		}
		//判断需要显示的fragment有没有添加
		if(!array_fragment[index].isAdded()){
			transaction.add(R.id.mainActivity_fragment_contains, array_fragment[index], array_tag_fragment[index]);
		}
		//进行显示和提交
		transaction.show(array_fragment[index]);
		transaction.commit();
		current_fragment_display = index;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.mainActivity_radiobutton_first_pager:
			displayFragment(0);
			break;
		case R.id.mainActivity_radiobutton_message:
			displayFragment(1);
			break;
		case R.id.mainActivity_radiobutton_yuyue:
			displayFragment(2);
			break;
		case R.id.mainActivity_radiobutton_mine:
			displayFragment(3);
			break;
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		list_activity.remove(this);
	}
}
