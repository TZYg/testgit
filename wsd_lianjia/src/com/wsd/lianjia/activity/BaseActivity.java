package com.wsd.lianjia.activity;

import java.util.List;

import com.wsd.lianjia.application.MyApplication;

import android.app.Activity;
import android.os.Bundle;
/**
 * 基类Activity，用于拓展所有activity公共功能使用
 * @author wsd_leiguoqiang
 * QQ 274764936
 */
public class BaseActivity extends Activity{
	private List<Activity> list_activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication application = (MyApplication) getApplication();
		list_activity = application.getList_activity();
		list_activity.add(this);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		list_activity.remove(this);
	}
}
