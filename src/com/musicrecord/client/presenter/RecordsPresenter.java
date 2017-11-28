package com.musicrecord.client.presenter;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.RangeChangeEvent;
import com.musicrecord.client.GreetingServiceAsync;
import com.musicrecord.shared.Records;

public class RecordsPresenter implements Presenter

{
    private final GreetingServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;
    private String sortOrder;
    private String sortField;

    public interface Display {
	Widget asWidget();

	CellTable<Records> getCellTable();

	Column<Records, String> getEdit();

	Column<Records, String> getDelete();
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

	display.getDelete().setFieldUpdater(new FieldUpdater<Records, String>() {

	    @Override
	    public void update(int index, Records record, String value) {
		deleteRecord(record);

	    }

	});

	display.getEdit().setFieldUpdater(new FieldUpdater<Records, String>() {

	    @Override
	    public void update(int index, Records record, String value) {
		editRecord(record);

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
		int columnIndex = display.getCellTable().getColumnIndex((Column<Records, String>) event.getColumn());
		if (columnIndex == 0) {
		    sortField = "title";
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

    private void editRecord(Records record) {
	rpcService.editRecord(record, new AsyncCallback<String>() {

	    @Override
	    public void onSuccess(String result) {
		fetchRecords();

	    }

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("Record deletion failed" + caught.getLocalizedMessage());

	    }
	});

    }

    private void fetchRecords() {
	final int start = display.getCellTable().getVisibleRange().getStart();
	int length = display.getCellTable().getVisibleRange().getLength();

	HashMap<String, String> requestInfo = new HashMap<String, String>();
	requestInfo.put("keyWord", "");
	requestInfo.put("start", start + "");
	requestInfo.put("length", length + "");
	requestInfo.put("sortOrder", sortOrder);
	requestInfo.put("sortField", sortField);
	requestInfo.put("gameTypeId", "");

	rpcService.fetchRecords(requestInfo, new AsyncCallback<ArrayList<Records>>() {

	    @Override
	    public void onSuccess(ArrayList<Records> result) {
		display.getCellTable().setRowData(0, result);

	    }

	    @Override
	    public void onFailure(Throwable caught) {
		Window.alert("fail");

	    }
	});
    }

}
