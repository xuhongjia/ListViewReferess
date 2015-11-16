package com.horry.listviewreferess;

import java.util.ArrayList;
import java.util.List;

import com.horry.listviewreferess.utils.CommonBaseAdapter;
import com.horry.listviewreferess.utils.MyListener;
import com.horry.listviewreferess.utils.ViewHolder;
import com.horry.listviewreferess.widget.PullToRefreshLayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {
	private ListView listView;
	CommonBaseAdapter<String> adapter;
	List<String> items;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.content_view);
		initListView();
		
	}

	/**
	 * ListView初始化方法
	 */
	private void initListView()
	{
		items = new ArrayList<String>();
		items.add("可下拉刷新上拉加载的ListView");
		items.add("可下拉刷新上拉加载的GridView");
		items.add("可下拉刷新上拉加载的ExpandableListView");
		items.add("可下拉刷新上拉加载的SrcollView");
		items.add("可下拉刷新上拉加载的WebView");
		items.add("可下拉刷新上拉加载的ImageView");
		items.add("可下拉刷新上拉加载的TextView");
		adapter = new CommonBaseAdapter<String>(this, items,R.layout.list_item_layout){

			@Override
			public void convert(ViewHolder holder, String t) {
				// TODO 自动生成的方法存根
				holder.setText(R.id.tv, t);
			}

		};
		listView.setAdapter(adapter);
		listView.setOnItemLongClickListener(new OnItemLongClickListener()
		{
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				Toast.makeText(
						MainActivity.this,
						" LongClick on "
								+ parent.getAdapter().getItemId(position),
								Toast.LENGTH_SHORT).show();
				return true;
			}
		});
		Handler handler =new Handler(){
			@Override
			public void handleMessage(Message msg)
			{
				// 千万别忘了告诉控件刷新完毕了哦！
				if(msg.what==100)
				{
					items.add("可下拉刷新上拉加载的TextView");
					adapter.notifyDataSetChanged();
					PullToRefreshLayout pullToRefreshLayout = (PullToRefreshLayout) msg.obj;
					pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);	
				}
			}
		};
		((PullToRefreshLayout) findViewById(R.id.refresh_view))
		.setOnRefreshListener(new MyListener<String>(items,adapter,handler){

			@Override
			public void updata(BaseAdapter adapter, List<String> data) {
				// TODO 自动生成的方法存根
				data.add("可下拉刷新上拉加载的TextView");
				adapter.notifyDataSetChanged();
			}
			
		});
		
	}
}
