package com.wsd.lianjia.customview;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wsd.lianjia.R;
import com.wsd.lianjia.application.MyApplication;
/**
 * 自定义checkedTextView控件：可以认为设置选择图片的大小
 * 实现功能：给定一个集合，可以自动将数据封装到集合中，适合封装list<Map<String,String>>类型
 * 对应数据库中一个字段值包含多个不同的数据，这种封装适合向数据库中筛选数据
 * 使用方法：需要调用setDataList()方法，只有设置了这个方法，才会有不同显示效果
 * @author wsd_leiguoqiang
 * QQ274764936
 */
public class CustomSelectedLinearLayout extends LinearLayout{
	/**
	 * textview文本内容
	 */
	private String TextView_text;
	/**
	 * textview文字大小
	 */
	private int TextView_size;
	/**
	 * 文字大小
	 */
	private int TextView_color;
	/**
	 * imageview的图片资源
	 */
	private int img_src;
	/**
	 * iamgveiw的padding属性
	 */
	private int img_padding;
	/**
	 * iamgveiw的paddingleft属性
	 */
	private int ima_paddingLeft;
	/**
	 * iamgveiw的paddingTop属性
	 */
	private int ima_paddingTop;
	/**
	 * iamgveiw的paddingRight属性
	 */
	private int ima_paddingRight;
	/**
	 * iamgveiw的paddingBottom属性
	 */
	private int ima_paddingBottom;
	/**
	 * 状态值
	 */
	private boolean isChecked;
	/**
	 * 需要认为指定的数据集合
	 */
	private List<Map<String, String>> list = new ArrayList<Map<String,String>>();
	/**
	 * map集合中的key类型
	 */
	private String key;
	private TextView view_one;
	private ImageView view_two;
	private String text;

	public CustomSelectedLinearLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public CustomSelectedLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomSelectedLinearLayout(Context context) {
		super(context);
		init();
	}

	private void init() {
		setOnClickListener(new OnClickListener() {
			

			@Override
			public void onClick(View v) {
				if((list!=null)&&(!TextUtils.isEmpty(key))){
					//改变状态值
					toggle();
					if(isChecked){
						text = view_one.getText().toString();
						Map<String, String> map = new HashMap<String, String>();
						map.put(key, text);
						list.add(map);
						MyApplication.log("---------------", "数据封装完成="+text);
					}else{
						for(Map<String, String> map:list){
							if(map.get(key).equals(text)){
								list.remove(map);
								MyApplication.log("---------------", "数据删除完成list长度="+list.size());
							}
						}
					}
				}
			}
		});
	}
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
			view_one = (TextView) getChildAt(0);
			view_two = (ImageView) getChildAt(1);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
	/**
	 * 开关选择
	 */
	public void toggle() {
		isChecked = !isChecked;
		if(!isChecked){
			view_two.setImageDrawable(getResources().getDrawable(R.drawable.icon_unselected));
			view_one.setTextColor(getResources().getColor(R.color.black_light));
			MyApplication.log("---------------", "没有选择绘制");
		}else{
			view_one.setTextColor(getResources().getColor(R.color.wathet_yellow));
			view_two.setImageDrawable(getResources().getDrawable(R.drawable.icon_selected));
			MyApplication.log("---------------", "选择绘制");
		}
	}
	public List<Map<String, String>> getList() {
		return list;
	}


	public String getKey() {
		return key;
	}
	/**
	 * 为该控件设置用于封装数据的数据集合
	 * @param list
	 * @param key
	 */
	public void setDataList(List<Map<String, String>> list,String key){
		this.list = list;
		this.key = key;
	}
}
