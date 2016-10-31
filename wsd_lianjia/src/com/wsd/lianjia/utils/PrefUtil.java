package com.wsd.lianjia.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * sharedPreferences工具类：用于本地存储app偏好设置数据
 * @author wsd_leiguoqiang
 * QQ 274764936
 */
public class PrefUtil {
	/**
	 * 设置int类型的值
	 * @param context
	 * @param sharedPreferencesName
	 * @param inValue
	 */
	public static void setPrefInt(Context context,String sharedPreferencesName,int inValue){
		SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		sharedPreferences.edit().putInt(sharedPreferencesName, inValue).commit();
	}

	/**
	 * 获取int类型的值,默认返回值为-1
	 * @param context
	 * @param sharedPreferencesName
	 */
	public static int getPrefInt(Context context,String sharedPreferencesName){
		SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sharedPreferences.getInt(sharedPreferencesName, -1);
	}

	/**
	 * 设置string类型的值
	 * @param context
	 * @param sharedPreferencesName
	 * @param StrValue
	 */
	public static void setPrefString(Context context,String sharedPreferencesName,String StrValue){
		SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		sharedPreferences.edit().putString(sharedPreferencesName, StrValue).commit();
	}

	/**
	 * 获取String类型的值,默认返回值为-1
	 * @param context
	 * @param sharedPreferencesName
	 */
	public static String getPrefString(Context context,String sharedPreferencesName){
		SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sharedPreferences.getString(sharedPreferencesName,null);
	}

	/**
	 * 设置boolean类型的值
	 * @param context
	 * @param sharedPreferencesName
	 * @param booleanValue
	 */
	public static void setPrefBoolean(Context context,String sharedPreferencesName,boolean booleanValue){
		SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		sharedPreferences.edit().putBoolean(sharedPreferencesName, booleanValue).commit();
	}

	/**
	 * 获取boolean类型的值
	 * @param context
	 * @param sharedPreferencesName
	 * @param defultBoolean
	 */
	public static boolean getPrefBoolean(Context context,String sharedPreferencesName,boolean defultBoolean){
		SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(sharedPreferencesName, defultBoolean);
	}

}
