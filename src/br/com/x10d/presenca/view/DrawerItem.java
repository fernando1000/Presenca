package br.com.x10d.presenca.view;

public class DrawerItem {

	private String ItemName;
	private int imgResID;
	
	public DrawerItem(String itemName, int imgResID) {
		this.ItemName = itemName;
		this.imgResID = imgResID;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public int getImgResID() {
		return imgResID;
	}

	public void setImgResID(int imgResID) {
		this.imgResID = imgResID;
	}
	
}
