package com.horry.listviewreferess.utils;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.BaseAdapter;

import com.horry.listviewreferess.widget.PullToRefreshLayout;
import com.horry.listviewreferess.widget.PullToRefreshLayout.OnRefreshListener;

public abstract class MyListener<T> implements OnRefreshListener
{
	private List<T> data;
	BaseAdapter adapter;
	Handler handler;
	public MyListener(){
		
	}
	public MyListener(List<T> data,BaseAdapter adapter){
		this.data=data;
		this.adapter=adapter;
	}
	public MyListener(List<T> data,BaseAdapter adapter,Handler handler){
		this.data=data;
		this.adapter=adapter;
		this.handler=handler;
	}
	@Override
	public void onRefresh(PullToRefreshLayout pullToRefreshLayout)
	{
		// 下拉刷新操作
		Message msg = new Message();
		msg.obj=pullToRefreshLayout;
		msg.what=100;
		handler.sendMessageDelayed(msg, 2000);
	}

	@Override
	public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout)
	{
		// 加载操作
		new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				// 千万别忘了告诉控件加载完毕了哦！
				updata(adapter, data);
				pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
			}
		}.sendEmptyMessageDelayed(0, 2000);
	}
	public abstract void updata(BaseAdapter adapter,List<T> data);
}
