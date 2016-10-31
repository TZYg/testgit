package com.wsd.lianjia.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wsd.lianjia.R;
import com.wsd.lianjia.entity.HistoryInfo;
import com.wsd.lianjia.entity.HistoryInfo.HistorySearch;

/**
 * 历史搜索列表adapter
 * @author wsd_leiguoqiang
 * QQ 274764936
 */
public class HistorySearchAdapter extends BaseAdapter{
	private List<HistorySearch> list_history;
	private Context context;

	public HistorySearchAdapter(List<HistorySearch> list_history, Context context) {
		super();
		this.list_history = list_history;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list_history.size();
	}

	@Override
	public Object getItem(int position) {
		return list_history.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView==null){
			convertView = View.inflate(context, R.layout.modle_listview_item_activity_search_history, null);
			holder = new ViewHolder();
			holder.tv_history_title = (TextView) convertView.findViewById(R.id.modle_listview_item_activity_search_title);
			holder.tv_history_discription = (TextView) convertView.findViewById(R.id.modle_listview_item_activity_search_discription);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		HistorySearch historySearch = list_history.get(position);
		holder.tv_history_title.setText(historySearch.getHistory_title());
		holder.tv_history_discription.setText(historySearch.getHistory_discription());
		return convertView;
	}

	/**
	 * viewholder工具类
	 */
	class ViewHolder{
		TextView tv_history_title;
		TextView tv_history_discription;
	}
}
