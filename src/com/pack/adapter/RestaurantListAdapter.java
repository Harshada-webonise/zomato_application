package com.pack.adapter;

import java.util.ArrayList;
import com.pack.Zomato.R;
import com.pack.model.RestaurantClass;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RestaurantListAdapter extends ArrayAdapter 
{

	Activity context;
	ArrayList<RestaurantClass> restoArray;
	public RestaurantListAdapter(Activity context, 	ArrayList<RestaurantClass> restoArray)
	{
		super(context, R.layout.textlistview,R.id.textView,restoArray);
		this.context=context;
		this.restoArray=restoArray;
		
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return restoArray.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		RestaurantClass restocls=restoArray.get(position);
		if(convertView==null)
		{
			LayoutInflater inflater=context.getLayoutInflater();
			convertView=inflater.inflate(R.layout.cuisin_textview_custom,null);
			
			holder = new ViewHolder();
			
			holder.nameTextView = (TextView) convertView
					.findViewById(R.id.text);
	
			
			convertView.setTag(holder);
		}
		else 
		{
			holder=(ViewHolder)convertView.getTag();
		}

	
		holder.nameTextView.setText(restocls.getRestoName()+"\t"+restocls.getRestoAddress());
	
		
		
		return convertView;
	}
	

	
static class ViewHolder 
{
	TextView nameTextView;		
}
	

}
	
	
	

