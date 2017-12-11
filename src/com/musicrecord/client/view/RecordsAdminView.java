package com.musicrecord.client.view;

import com.musicrecord.client.presenter.RecordsPresenter;

public class RecordsAdminView extends RecordsView implements RecordsPresenter.Display {

	
	public RecordsAdminView() {
		
		cellTable.addColumn(title, MyConstants.COLUMN_TITLE);

		cellTable.addColumn(artist, MyConstants.COLUMN_ARTIST);

		cellTable.addColumn(category, MyConstants.COLUMN_CATEGORY);

		cellTable.addColumn(edit, "");

		cellTable.addColumn(delete, "");
	}
}
