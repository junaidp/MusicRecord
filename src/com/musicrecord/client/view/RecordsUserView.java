package com.musicrecord.client.view;

import com.musicrecord.client.presenter.RecordsPresenter;

public class RecordsUserView extends RecordsView implements RecordsPresenter.Display {


	public RecordsUserView() {

		btnAddRecord.setVisible(false);
		
		cellTable.addColumn(title, MyConstants.COLUMN_TITLE);
		
		cellTable.addColumn(artist, MyConstants.COLUMN_ARTIST);
		
		cellTable.addColumn(category, MyConstants.COLUMN_CATEGORY);

	}

}
