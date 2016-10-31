package com.wsd.lianjia.fragment.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 消息fragment
 * @author wsd_leiguoqiang
 * QQ 274764936
 */
public class MessageFragment extends Fragment{
	private View view;
	private LinearLayout layout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView textview = new TextView(getActivity());
		textview.setText("消息页面");
		layout = new LinearLayout(getActivity());
		layout.addView(textview);
		return layout;
	}

}
