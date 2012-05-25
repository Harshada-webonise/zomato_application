package com.pack.adapter;


import java.util.ArrayList;
import com.pack.Zomato.R;
import com.pack.model.*;

import android.app.Activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;
public class CuisinesListAdapter extends ArrayAdapter {


		Activity context;
		ArrayList<CuisineClass> cuisineArray;
		
		public CuisinesListAdapter(Activity context,ArrayList<CuisineClass> cuisineArray)
		{
			super(context,R.layout.textlistview,R.id.textView,cuisineArray);
			Log.d("in","adapter");
			this.context=context;
			this.cuisineArray=cuisineArray;
			// TODO Auto-generated constructor stub
		}
	
	

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return cuisineArray.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}


		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			ViewHolder holder;
			CuisineClass cls=cuisineArray.get(position);
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

		
			String title=cls.getCuisinesTitle();
			Log.d("title-----------------",""+title);
			
			holder.nameTextView.setText(cls.getCuisinesTitle());
		
			
			
			return convertView;
		}
		
	
		
	static class ViewHolder 
	{
		TextView nameTextView;		
	}
		

	}
