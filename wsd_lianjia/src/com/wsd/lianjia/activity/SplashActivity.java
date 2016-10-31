package com.wsd.lianjia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.wsd.lianjia.R;
import com.wsd.lianjia.utils.PrefUtil;

/**
 * app启动activity，一般作为宣传公司和logo使用
 * @author wsd_leiguoqiang
 * QQ 274764936
 */
public class SplashActivity extends BaseActivity{
	private LinearLayout ll_contains;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		initAnimation();
	}

	private void initAnimation() {
		ll_contains = (LinearLayout) findViewById(R.id.activity_splash_contains);
		
//		//旋转动画
//		RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//		rotateAnimation.setDuration(1000);
//		rotateAnimation.setFillAfter(true);
		//渐变动画
		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(2000);
		alphaAnimation.setFillAfter(true);
		//缩放动画
		ScaleAnimation scaleAnimation = new ScaleAnimation(2, 1, 2, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(2000);
		scaleAnimation.setFillAfter(true);
		
		AnimationSet set = new AnimationSet(true);
		set.addAnimation(scaleAnimation);
		set.addAnimation(alphaAnimation);
//		set.addAnimation(rotateAnimation);
		ll_contains.startAnimation(set);
		//设置动画监听
		set.setAnimationListener(new AnimationListener() {
			private Handler handler;
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				handler = new Handler();
				//推迟1秒钟进入到另外一个activity
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						boolean isFirst = PrefUtil.getPrefBoolean(SplashActivity.this, "is_first_entry", true);
						if(isFirst){
							SplashActivity.this.startActivity(new Intent(SplashActivity.this, GuideActivity.class));
							finish();
						}else{
							SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
							finish();
						}
					}
				}, 1000);
			}
		});
	}
}
