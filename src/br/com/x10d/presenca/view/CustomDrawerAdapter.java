package br.com.x10d.presenca.view;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import br.com.x10d.presenca.R;

public class CustomDrawerAdapter extends ArrayAdapter<DrawerItem> {

	private Context context;
	private List<DrawerItem> drawerItemList;
	private int layoutResID;

	public CustomDrawerAdapter(Context context, int layoutResourceID, List<DrawerItem> listItems) {
		super(context, layoutResourceID, listItems);
		this.context = context;
		this.drawerItemList = listItems;
		this.layoutResID = layoutResourceID;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	
		DrawerItemHolder drawerHolder;
		View view = convertView;

		if (view == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			drawerHolder = new DrawerItemHolder();

			view = inflater.inflate(layoutResID, parent, false);
			
			drawerHolder.ItemName = (TextView) view.findViewById(R.id.drawer_itemName);
			drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);
			drawerHolder.title = (TextView) view.findViewById(R.id.drawerTitle);
			drawerHolder.headerLayout = (LinearLayout) view.findViewById(R.id.headerLayout);
			drawerHolder.itemLayout = (LinearLayout) view.findViewById(R.id.itemLayout);

			view.setTag(drawerHolder);
		} 
		else {
			drawerHolder = (DrawerItemHolder) view.getTag();
		}
		
		DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);
		
			drawerHolder.headerLayout.setVisibility(LinearLayout.GONE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.VISIBLE);
			drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(dItem.getImgResID()));
			LayoutParams lp = new LayoutParams(100, 100);			
			 				lp.setMargins(10, 0, 0, 0);		
			drawerHolder.icon.setLayoutParams(lp);

			drawerHolder.ItemName.setText(dItem.getItemName());
			
		
		return view;
	}

	private static class DrawerItemHolder {
		TextView ItemName, title;
		ImageView icon;
		LinearLayout headerLayout, itemLayout;
	}
}