package com.musicrecord.client.view;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.musicrecord.client.presenter.RecordsPresenter;
import com.musicrecord.shared.Records;

public class RecordsView extends VerticalPanel implements RecordsPresenter.Display {

    private CellTable<Records> cellTable;
    private Column<Records, String> title;
    private Column<Records, String> artist;
    private Column<Records, String> edit;
    private Column<Records, String> delete;
    private SimplePager pager;

    public RecordsView() {

	createCellTableFields();
	setPagerToCellTable();

    }

    private void createCellTableFields() {
	cellTable = new CellTable<Records>();
	cellTable.setEmptyTableWidget(new HTML("No data found"));
	TextCell titleCell = new TextCell();
	title = new Column<Records, String>(titleCell) {

	    @Override
	    public String getValue(Records object) {
		return object.getTitle();
	    }
	};

	cellTable.addColumn(title, MyConstants.COLUMN_TITLE);

	artist = new Column<Records, String>(new TextCell()) {

	    @Override
	    public String getValue(Records object) {
		return object.getTitle();
	    }
	};

	cellTable.addColumn(artist, MyConstants.COLUMN_ARTIST);

	edit = new Column<Records, String>(new ButtonCell()) {

	    @Override
	    public String getValue(Records object) {
		return "Edit";
	    }
	};

	cellTable.addColumn(edit, "");

	delete = new Column<Records, String>(new ButtonCell()) {

	    @Override
	    public String getValue(Records object) {
		return "Delete";
	    }
	};

	cellTable.addColumn(delete, "");

	title.setSortable(true);
	artist.setSortable(true);

	add(cellTable);
    }

    public void createNewPager() {
	SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
	pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
    }

    public void setPagerToCellTable() {
	createNewPager();
	HorizontalPanel hpnlPager = new HorizontalPanel();

	hpnlPager.clear();
	hpnlPager.add(pager);
	hpnlPager.setWidth("800px");
	HorizontalPanel hpnlSpacing = new HorizontalPanel();
	hpnlPager.add(hpnlSpacing);
	hpnlSpacing.setWidth("400px");
	hpnlPager.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
	add(hpnlPager);
	setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	pager.setPageSize(MyConstants.PAGERSIZE);
	pager.setDisplay(cellTable);

    }

    public CellTable<Records> getCellTable() {
	return cellTable;
    }

    public void setCellTable(CellTable<Records> cellTable) {
	this.cellTable = cellTable;
    }

    public SimplePager getPager() {
	return pager;
    }

    public void setPager(SimplePager pager) {
	this.pager = pager;
    }

    public Column<Records, String> getEdit() {
	return edit;
    }

    public Column<Records, String> getDelete() {
	return delete;
    }

}
