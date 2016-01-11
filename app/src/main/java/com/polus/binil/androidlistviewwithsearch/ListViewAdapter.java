package com.polus.binil.androidlistviewwithsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ListViewAdapter extends BaseAdapter {

	Context mContext;
	LayoutInflater inflater;
	private List<ItemListPogo> itemListPogo = null;
	private ArrayList<ItemListPogo> arraylist;

	public ListViewAdapter(Context context, List<ItemListPogo> itemLists) 
	{
		mContext = context;
		this.itemListPogo = itemLists;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<ItemListPogo>();
		this.arraylist.addAll(itemLists);
	}

	public class ViewHolder {

		TextView product_name;
		TextView name;
        ImageView callIcon;
	}

	@Override
	public int getCount() {
		return itemListPogo.size();
	}

	@Override
	public ItemListPogo getItem(int position) {
		return itemListPogo.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent) 
	{
		final ViewHolder holder;
		if (view == null) 
		{
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.list_item, null);
            

			holder.name     = (TextView) view.findViewById(R.id.name);
            holder.callIcon        = (ImageView) view.findViewById(R.id.callIcon);
			
			view.setTag(holder);
		} 
		else 
		{
			holder = (ViewHolder) view.getTag();
		}

		// Set the results into TextViews
		holder.name.setText(itemListPogo.get(position).getItemName());
        holder.callIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.e("Tag ","clciked "+itemListPogo.get(position).getItemNumber());
                Toast.makeText(mContext,"call "+ itemListPogo.get(position).getItemNumber(),Toast.LENGTH_LONG).show();
            }
        });

		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) 
			{
				//On things on click
                Log.e("Tag ","Name "+itemListPogo.get(position).getItemName());

                Intent i = new Intent(mContext,EditContacts.class);
                i.putExtra("name", itemListPogo.get(position).getItemName());
                i.putExtra("number", itemListPogo.get(position).getItemNumber());
               ((Activity)mContext).finish();
               ((Activity)mContext).startActivity(i);
            }
		});

		return view;
	}

	// Filter Class

	public void filter(String charText)
	{
		charText = charText.toLowerCase(Locale.getDefault());
		Log.e("Tag ", "search " + charText);
		itemListPogo.clear();
		int spaceCount = 0;
		for (char c : charText.toCharArray()) {
			if (c == ' ') {
				spaceCount++;
			}
		}
		if (charText.length() == 0)
		{
			itemListPogo.addAll(arraylist);
		}
		else
		{
			for (ItemListPogo wp : arraylist)
			{
				try {
					if(spaceCount > 0)
					{
						if (wp.getItemName().toLowerCase(Locale.getDefault())
								.startsWith(charText))
						{


							itemListPogo.add(wp);
						}
					}
					else
					{
						String[] splited = wp.getItemName().split("\\s+");
						int n = splited.length;
						for (int i = 0; i < n; i++) {
							if (splited[i].toLowerCase(Locale.getDefault()).startsWith(charText)) {
								itemListPogo.add(wp);
							}
						}
					}

				/* if (wp.getItemName().toLowerCase(Locale.getDefault())
						.startsWith(charText))
				{


					itemListPogo.add(wp);
				}*/

				}
				catch (NullPointerException e)
				{

				}
				catch (Exception e)
				{

				}
			}
		}
		notifyDataSetChanged();
	}
}
