package com.musicrecord.client.presenter;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.RangeChangeEvent;
import com.musicrecord.client.GreetingServiceAsync;
import com.musicrecord.client.widgets.SaveEditRecordWidget;
import com.musicrecord.shared.Category;
import com.musicrecord.shared.Records;

public class RecordsPresenter implements Presenter

{
    private final GreetingServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;
    private String sortOrder = "asc";
    private String sortField = "title";
    private ArrayList<Category> categories;

    public interface Display {
	Widget asWidget();

	CellTable<Records> getCellTable();

	Column<Records, String> getEdit();

	Column<Records, String> getDelete();

	TextBox getTextSearch();

	ListBox getListSearchBy();

	Button getBtnAddRecord();
    }

    public RecordsPresenter(GreetingServiceAsync rpcService, HandlerManager eventBus, Display view) {
	this.rpcService = rpcService;
	this.eventBus = eventBus;
	this.display = view;

    }

    public void go(HasWidgets container) {
	container.clear();
	container.add(display.asWidget());
	bind();
    }

    private void bind() {

	fetchRecords();
	fetchCategories();
	setHandlers();

    }

    private void setHandlers() {
	display.getBtnAddRecord().addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		SaveEditRecordWidget saveEditRecordWidget = new SaveEditRecordWidget(categories);
		Records record = new Records();
		saveRecord(record, saveEditRecordWidget);

	    }
	});
	display.getListSearchBy().addChangeHandler(event -> fetchRecords());

	display.getTextSearch().addKeyUpHandler(event -> fetchRecords());

	display.getDelete().setFieldUpdater(new FieldUpdater<Records, String>() {

	    @Override
	    public void update(int index, Records record, String value) {
		deleteRecord(record);

	    }

	});

	display.getEdit().setFieldUpdater(new FieldUpdater<Records, String>() {

	    @Override
	    public void update(int index, Records record, String value) {
		SaveEditRecordWidget saveEditRecordWidget = new SaveEditRecordWidget(categories);
		saveEditRecordWidget.populateFields(record);
		saveRecord(record, saveEditRecordWidget);

	    }

	});

	display.getCellTable().addRangeChangeHandler(new RangeChangeEvent.Handler() {

	    @Override
	    public void onRangeChange(RangeChangeEvent event) {
		fetchRecords();

	    }
	});

	display.getCellTable().addColumnSortHandler(new ColumnSortEvent.Handler() {

	    @Override
	    public void onColumnSort(ColumnSortEvent event) {
		if (event.isSortAscending()) {
		    sortOrder = "asc";
		} else if (!event.isSortAscending()) {
		    sortOrder = "desc";

		}
		@SuppressWarnings("unchecked")
		int columnIndex = display.getCellTable().getColumnIndex((Column<Records, String>) event.getColumn());
		if (columnIndex == 0) {
		    sortField = "title";
		} else if (columnIndex == 1) {
		    sortField = "artist";
		}
		fetchRecords();
	    }
	});
    }

    private void deleteRecord(Records record) {
	rpcService.deleteRecord(record, new AsyncCallback<String>() {

	    @Override
	    public void onSuccess(String result) {
		fetchRecords();

	    }

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("Record updation failed" + caught.getLocalizedMessage());

	    }
	});

    }

    private void saveRecord(final Records record, SaveEditRecordWidget saveEditRecordWidget) {

	final PopupPanel popup = new PopupPanel();
	popup.setGlassEnabled(true);

	saveEditRecordWidget.getSaveButton().addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		saveEditRecordWidget.getUpdatedRecord(record);

		rpcService.saveRecord(record, new AsyncCallback<String>() {

		    @Override
		    public void onSuccess(String result) {
			fetchRecords();

			Window.alert(result);
			popup.removeFromParent();

		    }

		    @Override
		    public void onFailure(Throwable caught) {
			Window.alert("Record Saved failed" + caught.getLocalizedMessage());
			popup.removeFromParent();

		    }
		});
	    }
	});

	saveEditRecordWidget.getCancelButton().addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {

		popup.removeFromParent();

	    }
	});

	popup.setWidget(saveEditRecordWidget);
	popup.center();
    }

    private void fetchRecords() {
	final int start = display.getCellTable().getVisibleRange().getStart();
	int length = display.getCellTable().getVisibleRange().getLength();

	HashMap<String, String> requestInfo = new HashMap<String, String>();
	requestInfo.put("keyWord", display.getTextSearch().getText());
	requestInfo.put("start", start + "");
	requestInfo.put("length", length + "");
	requestInfo.put("sortOrder", sortOrder);
	requestInfo.put("sortField", sortField);
	requestInfo.put("searchBy",
		display.getListSearchBy().getItemText(display.getListSearchBy().getSelectedIndex()));

	rpcService.fetchRecords(requestInfo, new AsyncCallback<ArrayList<Records>>() {

	    @Override
	    public void onSuccess(ArrayList<Records> result) {
		display.getCellTable().setRowData(start, result);
		display.getCellTable().setRowCount(result.get(0).getCount());
	    }

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("fail");

	    }
	});
    }

    private void fetchCategories() {

	rpcService.fetchCategories(new AsyncCallback<ArrayList<Category>>() {

	    @Override
	    public void onSuccess(ArrayList<Category> category) {
		categories = category;
	    }

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("fail");

	    }

	});
    }

}
