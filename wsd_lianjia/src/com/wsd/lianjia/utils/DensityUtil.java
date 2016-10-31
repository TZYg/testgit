package com.wsd.lianjia.utils;

import android.content.Context;

/**
 * ����ʱ��ʹ�ã���java�����ж�̬��ȥ����
 * @author wsd_leiguoqiang
 */
public class DensityUtil {
	/**
	 * dpת����px
	 * @return
	 */
	public static int dp2px(Context context,int dpValues){
		float scale = context.getResources().getDisplayMetrics().density;
		return (int)(dpValues*scale+0.5f);
	}
	/**
	 * pxת����dp
	 * @param context
	 * @param pxValues
	 * @return
	 */
	public static int px2dp(Context context,float pxValues){
		float scale = context.getResources().getDisplayMetrics().density;
		return (int)(pxValues/scale+0.5f);
	}
}
