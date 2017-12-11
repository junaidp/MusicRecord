package com.musicrecord.client.view;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.musicrecord.client.presenter.RecordsPresenter;
import com.musicrecord.shared.Records;

public class RecordsView extends VerticalPanel implements RecordsPresenter.Display {

    protected CellTable<Records> cellTable;
    protected Column<Records, String> title;
    protected Column<Records, String> artist;
    protected Column<Records, String> category;
    protected Column<Records, String> edit;
    protected Column<Records, String> delete;
    private SimplePager pager;
    private TextBox textSearch = new TextBox();
    private ListBox listSearchBy = new ListBox();
    protected Button btnAddRecord = new Button("Add");

    public RecordsView() {

	cellTable = new CellTable<Records>();
	layout();
	setPagerToCellTable();
	btnAddRecord.ensureDebugId("buttonAdd");
	textSearch.ensureDebugId("textSearch");
	listSearchBy.ensureDebugId("listBoxSearchBy");

    }

    private void layout() {
	HorizontalPanel hpnlSearch = new HorizontalPanel();
	Label lblSearchBy = new Label("Search by :");
	lblSearchBy.addStyleName("w3-text-blue");
	Label lblCatalogue = new Label("Catalogue");
	lblCatalogue.addStyleName("w3-text-blue");
	hpnlSearch.setSpacing(5);
	hpnlSearch.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	hpnlSearch.add(lblSearchBy);
	hpnlSearch.add(listSearchBy);
	hpnlSearch.add(textSearch);
	hpnlSearch.add(btnAddRecord);

	btnAddRecord.getElement().getStyle().setMarginLeft(100, Unit.PCT);
	textSearch.getElement().setPropertyString("placeholder", "Enter keyword..");
	textSearch.setTitle("Enter Title or Artist Name..");
	listSearchBy.addItem("All");
	listSearchBy.addItem("Title");
	listSearchBy.addItem("Artist");
	btnAddRecord.setStyleName("w3-button w3-blue");
	btnAddRecord.setWidth("90px");
	listSearchBy.setHeight("32px");
	createCellTableFields();

	add(hpnlSearch);
	add(cellTable);
    }

    private void createCellTableFields() {

	cellTable.setWidth("100%");
	cellTable.setTableLayoutFixed(true);
	cellTable.setEmptyTableWidget(new HTML("No data found"));
	TextCell titleCell = new TextCell();
	title = new Column<Records, String>(titleCell) {

	    @Override
	    public String getValue(Records object) {
		return object.getTitle();
	    }
	};

	artist = new Column<Records, String>(new TextCell()) {

	    @Override
	    public String getValue(Records object) {
		return object.getArtist();
	    }
	};

	category = new Column<Records, String>(new TextCell()) {

	    @Override
	    public String getValue(Records object) {
		return object.getCategory().getCategoryname();
	    }
	};

	edit = new Column<Records, String>(new ButtonCell()

	{
	    @Override
	    public void render(final Context context, final SafeHtml data, final SafeHtmlBuilder sb) {
		sb.appendHtmlConstant("<button type=\"button\" class=\"w3-button w3-blue\" tabindex=\"-1\">");
		if (data != null) {
		    sb.append(data);
		}
		sb.appendHtmlConstant("</button>");
	    }
	}

	) {

	    @Override
	    public String getCellStyleNames(Context context, Records object) {
		return "w3-button w3-blue";
	    }

	    @Override
	    public String getValue(Records object) {
		return "Edit";
	    }
	};

	delete = new Column<Records, String>(new ButtonCell() {
	    @Override
	    public void render(final Context context, final SafeHtml data, final SafeHtmlBuilder sb) {
		sb.appendHtmlConstant("<button type=\"button\" class=\"w3-button w3-red\" tabindex=\"-1\">");
		if (data != null) {
		    sb.append(data);
		}
		sb.appendHtmlConstant("</button>");
	    }
	}

	) {

	    @Override
	    public String getCellStyleNames(Context context, Records object) {
		return "w3-button w3-red";
	    }

	    @Override
	    public String getValue(Records object) {
		return "Delete";
	    }
	};

	title.setSortable(true);
	artist.setSortable(true);

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

    public TextBox getTextSearch() {
	return textSearch;
    }

    public ListBox getListSearchBy() {
	return listSearchBy;
    }

    public Button getBtnAddRecord() {
	return btnAddRecord;
    }

}
