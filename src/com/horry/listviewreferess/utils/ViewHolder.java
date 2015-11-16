package com.horry.listviewreferess.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewHolder {
	private SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;

	public ViewHolder(Context context,ViewGroup parant,int layoutId,int position) {
		this.mPosition=position;
		this.mViews=new SparseArray<View>();
		mConvertView =LayoutInflater.from(context).inflate(layoutId, parant,false);
		mConvertView.setTag(this);
	}
	public static ViewHolder get(Context context,View convertView,ViewGroup parant,int layoutId,int position) {
		if(convertView==null)
		{
			return new ViewHolder(context, parant, layoutId, position);
		}else {
			ViewHolder holder =(ViewHolder) convertView.getTag();
			holder.mPosition=position;
			return holder;
		}
	}
	/**
	 * 通过viewId获取控件
	 * @param viewId
	 * @return
	 */
	public <T extends View> T getView(int viewId){
		View view = mViews.get(viewId);
		if(view==null)
		{
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T)view;
	}
	public ViewHolder setText(int id,String text){
		((TextView)getView(id)).setText(text);
		return this;
	}
	public View getConvertView(){
		return mConvertView;
	}
}
