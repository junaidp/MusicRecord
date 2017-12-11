package com.musicrecord.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.musicrecord.shared.User;

public class RecordsAdminEvent extends GwtEvent<RecordsAdminEventHandler> {

    User loggedInUser;

    public RecordsAdminEvent(User loggedInUser) {
	this.loggedInUser = loggedInUser;
    }

    public static Type<RecordsAdminEventHandler> TYPE = new Type<RecordsAdminEventHandler>();

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<RecordsAdminEventHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(RecordsAdminEventHandler handler) {
	handler.onRecords(this);

    }

    public User getLoggedInUser() {
	return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
	this.loggedInUser = loggedInUser;
    }

}
