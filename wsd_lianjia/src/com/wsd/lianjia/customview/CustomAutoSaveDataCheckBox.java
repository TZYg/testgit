package com.wsd.lianjia.customview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wsd.lianjia.application.MyApplication;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * checkbox自定义控件：
 * 实现功能：给定一个集合，可以自动将数据封装到集合中，适合封装list<Map<String,String>>类型
 * 对应数据库中一个字段值包含多个不同的数据，这种封装适合向数据库中筛选数据
 * 使用方法：需要调用setDataList()方法
 * @author wsd_leiguoqiang
 * QQ274764936
 */
public class CustomAutoSaveDataCheckBox extends CheckBox{
	/**
	 * 需要认为指定的数据集合
	 */
	private List<Map<String, String>> list;
	/**
	 * map集合中的key类型
	 */
	private String key;
	/**
	 * 文本内容
	 */
	private String text;

	public CustomAutoSaveDataCheckBox(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public CustomAutoSaveDataCheckBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomAutoSaveDataCheckBox(Context context) {
		super(context);
		init();
	}

	private void init() {
		setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if((list!=null)&&(!TextUtils.isEmpty(key))){
					if(isChecked){
						text = getText().toString();
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
				}else{
					MyApplication.toast("该view没有设置数据集合");
				}
			}
		});
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
		//如果默认为ture,则添加数据到集合
		//对数据进行封装
		if(isChecked()){
			text = getText().toString();
			Map<String, String> map = new HashMap<String, String>();
			map.put(key, text);
			list.add(map);
			MyApplication.log("设置集合出", "数据为="+text);
		}
	}
}
