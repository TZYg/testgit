package com.wsd.lianjia.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

public class CustomCanselRadioButton extends RadioButton{

	public CustomCanselRadioButton(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public CustomCanselRadioButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CustomCanselRadioButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void toggle() {
		setChecked(!isChecked());
	}
}
