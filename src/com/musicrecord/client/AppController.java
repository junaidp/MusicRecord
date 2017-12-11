package com.musicrecord.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.musicrecord.client.event.AdminEvent;
import com.musicrecord.client.event.AdminEventHandler;
import com.musicrecord.client.event.RecordsAdminEvent;
import com.musicrecord.client.event.RecordsAdminEventHandler;
import com.musicrecord.client.event.RecordsUserEvent;
import com.musicrecord.client.event.RecordsUserEventHandler;
import com.musicrecord.client.presenter.HeaderPresenter;
import com.musicrecord.client.presenter.LoginPresenter;
import com.musicrecord.client.presenter.Presenter;
import com.musicrecord.client.presenter.RecordsPresenter;
import com.musicrecord.client.view.HeaderView;
import com.musicrecord.client.view.LoginView;
import com.musicrecord.client.view.RecordsAdminView;
import com.musicrecord.client.view.RecordsUserView;
import com.musicrecord.client.view.RecordsView;
import com.musicrecord.shared.User;

public class AppController implements Presenter, ValueChangeHandler<String> {
    private final HandlerManager eventBus;

    private final GreetingServiceAsync rpcService;
    private HasWidgets container, headerContainer ;
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

	eventBus.addHandler(RecordsAdminEvent.TYPE, new RecordsAdminEventHandler() {
	    public void onRecords(RecordsAdminEvent event) {
		loggedInUser = event.getLoggedInUser();

		History.newItem("catalogueAdmin");

	    }
	});

	eventBus.addHandler(RecordsUserEvent.TYPE, new RecordsUserEventHandler() {
	    public void onRecords(RecordsUserEvent event) {
		loggedInUser = event.getLoggedInUser();

		History.newItem("catalogueUser");

	    }
	    });

    }

    public void go(final HasWidgets container, HasWidgets headerContainer) {
	this.container = container;
	this.headerContainer = headerContainer;

	if (centerPanel == null) {
	    centerPanel = new VerticalPanel();
	}
	
	presenter = new HeaderPresenter(rpcService, eventBus, new HeaderView());
	presenter.go(headerContainer);

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

	    if (loggedInUser == null && !token.equals("login")) {
		History.newItem("login");
	    }

	    else if (token.equals("login")) {
		presenter = new LoginPresenter(rpcService, eventBus, new LoginView());
		presenter.go(container);
	    }

	    else if (token.equals("catalogueAdmin")) {
		presenter = new RecordsPresenter(rpcService, eventBus, new RecordsAdminView());
		presenter.go(container);

	    }
	    
	    else if (token.equals("catalogueUser")) {
			presenter = new RecordsPresenter(rpcService, eventBus, new RecordsUserView());
			presenter.go(container);

		    }

	}
    }

    private void setContainer(HasWidgets container) {
	this.container = container;
	this.container.clear();
    }

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
	}
}
