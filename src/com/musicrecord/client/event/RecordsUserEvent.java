package com.musicrecord.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.musicrecord.shared.User;

public class RecordsUserEvent extends GwtEvent<RecordsUserEventHandler> {

    User loggedInUser;

    public RecordsUserEvent(User loggedInUser) {
	this.loggedInUser = loggedInUser;
    }

    public static Type<RecordsUserEventHandler> TYPE = new Type<RecordsUserEventHandler>();

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<RecordsUserEventHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(RecordsUserEventHandler handler) {
	handler.onRecords(this);

    }

    public User getLoggedInUser() {
	return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
	this.loggedInUser = loggedInUser;
    }

}
