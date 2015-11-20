package com.polus.binil.androidlistviewwithsearch;

public class ItemListPogo {

	private String itemName;
    private String itemNumber;


	public ItemListPogo( String name,String number) {

		this.itemName = name;
        this.itemNumber = number;
	}




	public String getItemName() {
		return this.itemName;
	}

    public String getItemNumber() {
        return this.itemNumber;
    }
}
