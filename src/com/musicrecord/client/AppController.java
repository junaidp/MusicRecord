package com.musicrecord.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.musicrecord.client.event.AdminEvent;
import com.musicrecord.client.event.AdminEventHandler;
import com.musicrecord.client.event.RecordsEvent;
import com.musicrecord.client.event.RecordsEventHandler;
import com.musicrecord.client.presenter.LoginPresenter;
import com.musicrecord.client.presenter.Presenter;
import com.musicrecord.client.presenter.RecordsPresenter;
import com.musicrecord.client.view.LoginView;
import com.musicrecord.client.view.RecordsView;
import com.musicrecord.shared.User;

public class AppController implements Presenter, ValueChangeHandler<String> {
    private final HandlerManager eventBus;

    private final GreetingServiceAsync rpcService;
    private HasWidgets container;
    private User loggedInUser;
    private VerticalPanel centerPanel;
    private HasWidgets mainContainer;
    Presenter presenter = null;

    public AppController(GreetingServiceAsync rpcService, HandlerManager eventBus) {
	this.eventBus = eventBus;
	this.rpcService = rpcService;

	bind();
    }

    private void bind() {
	History.addValueChangeHandler(this);

	eventBus.addHandler(AdminEvent.TYPE, new AdminEventHandler() {
	    public void onAdmin(AdminEvent event) {
		loggedInUser = event.getLoggedInUser();

		History.newItem("admin");

	    }
	});

	eventBus.addHandler(RecordsEvent.TYPE, new RecordsEventHandler() {
	    public void onRecords(RecordsEvent event) {
		History.newItem("catalogue");

	    }
	});

    }

    public void go(final HasWidgets container) {
	this.container = container;
	this.mainContainer = container;

	if (centerPanel == null) {
	    centerPanel = new VerticalPanel();
	}

	if ("".equals(History.getToken())) {
	    History.newItem("login");
	} else {
	    History.fireCurrentHistoryState();
	}
    }

    public void onValueChange(ValueChangeEvent<String> event) {
	String token = event.getValue();

	if (token != null) {
	    presenter = null;

	    if (token.equals("login")) {
		presenter = new LoginPresenter(rpcService, eventBus, new LoginView());
		if (presenter != null) {
		    this.container = mainContainer;
		    presenter.go(container);
		}
	    }

	    if (token.equals("catalogue")) {
		presenter = new RecordsPresenter(rpcService, eventBus, new RecordsView());
		if (presenter != null) {
		    this.container = mainContainer;
		    presenter.go(container);
		}
	    }

	}
    }

    private void setContainer(HasWidgets container) {
	this.container = container;
	this.container.clear();
    }
}
