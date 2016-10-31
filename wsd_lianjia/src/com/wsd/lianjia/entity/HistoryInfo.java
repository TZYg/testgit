package com.wsd.lianjia.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.wsd.lianjia.adapter.HistorySearchAdapter;
import com.wsd.lianjia.application.MyApplication;
import com.wsd.lianjia.constant.Constant;

/**
 * 历史信息实体类，封装小区名字和搜索关键字
 * @author wsd_leiguoqiang
 * QQ 274764936
 */
public class HistoryInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 历史信息集合
	 */
	private List<HistorySearch> list_historySearch = new ArrayList<HistorySearch>();
	
	public List<HistorySearch> getList_historySearch() {
		return list_historySearch;
	}

	public void setList_historySearch(List<HistorySearch> list_historySearch) {
		this.list_historySearch = list_historySearch;
	}
	/**
	 * 添加单条历史搜索数据到本地
	 * @param historySearch
	 */
	public void addHistoryItem(HistorySearch historySearch){
		//添加到集合
		list_historySearch.add(historySearch);
		//持久化保存在本地
		saveHistoryInfo();
	}
	/**
	 * 将本地历史数据读取出来
	 */
	public HistoryInfo readHistoryInfo(){
		try {
			File cacheDir = MyApplication.getContext().getCacheDir();
			File history_file = new File(cacheDir, Constant.HISTORY_SEARCH_INFO);
			@SuppressWarnings("resource")
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(history_file)); 
			HistoryInfo history_info = (HistoryInfo) ois.readObject();
			if(history_info!=null){
				return history_info;
			}else{
				return new HistoryInfo();
			}
		} catch (Exception e) {
		}
		return new HistoryInfo();
		
	}
	/**
	 * 将历史搜索数据保存到本地
	 */
	public void saveHistoryInfo(){
		try {
			File cacheDir = MyApplication.getContext().getCacheDir();
			File history_file = new File(cacheDir, Constant.HISTORY_SEARCH_INFO);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(history_file));
			oos.writeObject(this);
		} catch (Exception e) {
		}
	}
	/**
	 * 删除所有历史搜索信息
	 */
	public void deleteAllHistoryInfo(HistorySearchAdapter adapter){
		list_historySearch.clear();
		//持久化保存数据
		saveHistoryInfo();
		//进行历史列别listview刷新操作
		adapter.notifyDataSetChanged();
	}
	
	/**
	 * 具体历史信息实体类
	 */
	public class HistorySearch implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * 历史信息标题
		 */
		private String history_title;
		/**
		 * 历史信息描述
		 */
		private String history_discription;
		
		
		
		public String getHistory_title() {
			return history_title;
		}
		public void setHistory_title(String history_title) {
			this.history_title = history_title;
		}
		public String getHistory_discription() {
			return history_discription;
		}
		public void setHistory_discription(String history_discription) {
			this.history_discription = history_discription;
		}
		@Override
		public String toString() {
			return "HistorySearch [history_title=" + history_title
					+ ", history_discription=" + history_discription + "]";
		}
	}
}
