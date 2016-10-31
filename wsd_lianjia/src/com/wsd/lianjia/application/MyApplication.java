package com.wsd.lianjia.application;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;
/**
 * application对象：处理一些app初始化操作
 * @author wsd_leiguoqiang
 */
public class MyApplication extends Application{
	/**
	 * activity集合对象，用于退出程序时候，销毁所有activity时使用
	 */
	private List<Activity> list_activity = new  ArrayList<Activity>();
	/**
	 * application类型的context对象
	 */
	private static Context context;
	/**
	 * app的log日记此时标记变量
	 */
	private static boolean flag = true;

	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
		init();
	}

	private void init() {
		context = this;
	}

	public static Context getContext() {
		return context;
	}

	public List<Activity> getList_activity() {
		return list_activity;
	}

	public void setList_activity(List<Activity> list_activity) {
		this.list_activity = list_activity;
	}
	
	/**
	 * app全局测试方法
	 * @param tag
	 * @param logcat
	 */
	public static void log(String tag , String logcat){
		if(flag==true){
			Log.i(tag, logcat);
		}
	}
	/**
	 * toast显示
	 * @param string
	 */
	public static void toast(String string){
		Toast.makeText(context, string , Toast.LENGTH_SHORT).show();
	}
	/**
	 * 结束当前app，用于退出按钮使用
	 * @param list
	 */
	public static void finishActivity(List<Activity> list){
		for(Activity activity:list){
			activity.finish();
		}
		Process.killProcess(Process.myPid());
	}
}
