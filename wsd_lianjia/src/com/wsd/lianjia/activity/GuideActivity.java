package com.wsd.lianjia.activity;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.wsd.lianjia.R;
import com.wsd.lianjia.utils.PrefUtil;
/**
 * GuideActivity:app的新手指导页面，只会显示一次
 * @author wsd_leiguoqiang
 * QQ 274764936
 */
public class GuideActivity extends Activity implements OnPageChangeListener,OnClickListener{
	/**
	 * viewpager对象
	 */
	@ViewInject(R.id.activity_guide_vp)
	private ViewPager vp;
	/**
	 * 本地图片资源id集合
	 */
	private int[] array_image_resouse = {R.drawable.guide_iamge_one,R.drawable.guide_image_two,
			R.drawable.guide_iamge_one,R.drawable.guide_image_two};
	/**
	 * imageview控件集合
	 */
	private List<ImageView> list_image = new ArrayList<ImageView>();
	/**
	 * 小圆点的容器
	 */
	@ViewInject(R.id.activity_guide_ll_points_contains)
	private LinearLayout ll_points_contains;
	
	@ViewInject(R.id.activity_guide_btn_start)
	private Button btn_start;
	/**
	 * 小圆点的间距
	 */
	private int pointDis;
	/**
	 * 移动小圆点
	 */
	@ViewInject(R.id.activity_guide_point_action)
	private ImageView image_point;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		init();
		initListeners();
	}

	private void init() {
		x.view().inject(this);
		int acount =0;
		for(int id:array_image_resouse){
			/**
			 * 初始化图片资源
			 */
			ImageView imageview = new ImageView(this);
			imageview.setBackgroundResource(id);
			list_image.add(imageview);
			LinearLayout ll_point = new LinearLayout(this);
			ll_point.setBackgroundResource(R.drawable.shape_point_white_bg);
			if(acount>0){
				LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				params.leftMargin = 10;
				ll_point.setLayoutParams(params);
			}
			ll_points_contains.addView(ll_point);
			acount++;
		}
		viewpagerAdapter adapter = new viewpagerAdapter();
		vp.setAdapter(adapter);
		/**
		 * 添加绘制过程监听，
		 */
		ll_points_contains.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			/**
			 * view的layout()方法已经执行完毕，可以拿到view的相关layoutparams参数
			 */
			@Override
			public void onGlobalLayout() {
				pointDis = ll_points_contains.getChildAt(1).getLeft()-ll_points_contains.getChildAt(0).getLeft();
			}
		});
	}

	private void initListeners() {
		vp.setOnPageChangeListener(this);
		btn_start.setOnClickListener(this);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		//获取偏移量
		int offset = (int) (pointDis*arg1) + arg0*pointDis; 
		//设置偏移量
		RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) image_point.getLayoutParams();
		params.leftMargin = offset;
		image_point.setLayoutParams(params);
		
	}

	@Override
	public void onPageSelected(int arg0) {
		if(arg0==list_image.size()-1){
			btn_start.setVisibility(View.VISIBLE);
		}else{
			btn_start.setVisibility(View.INVISIBLE);
		}
	}

	private class viewpagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return list_image.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView view = list_image.get(position);
			container.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.activity_guide_btn_start:
			//修改首次启动标记变量
			PrefUtil.setPrefBoolean(this, "is_first_entry", false);
			startActivity(new Intent(this, MainActivity.class));
			finish();
			break;
		}
	}
}
