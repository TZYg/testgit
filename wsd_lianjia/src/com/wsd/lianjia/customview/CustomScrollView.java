package com.wsd.lianjia.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
/**
 * 自定义scrollview：可以进行滑动监听
 * 实现原理：scrollview继承view，自然有onScrollChanged()方法
 * 滑动过程中会时刻被调用，设置一个接口，共外界对象使用，从而达到扩展滑动监听功能
 * @author wsd_leiguoqiang
 * QQ:274764936
 */
public class CustomScrollView extends ScrollView {
	
	private OnScrollChangedListener scrollChangedListener;

	public CustomScrollView(Context context) {
		super(context);
	}

	public CustomScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if(scrollChangedListener!=null){
			scrollChangedListener.onScrollChanged(l, t, oldl, oldt);
		}
	}
	/**
	 * 自定义接口，扩展该控件的滑动监听功能
	 * @author wsd_leiguoqiang
	 */
	public interface OnScrollChangedListener{
		public void onScrollChanged(int l, int t, int oldl, int oldt);
	}
	public void setOnScrollChangedListener(OnScrollChangedListener scrollChangedListener) {
		this.scrollChangedListener = scrollChangedListener;
	}
}
