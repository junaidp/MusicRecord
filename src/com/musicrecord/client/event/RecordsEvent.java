package com.musicrecord.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.musicrecord.shared.User;

public class RecordsEvent extends GwtEvent<RecordsEventHandler> {

    User loggedInUser;

    public RecordsEvent(User loggedInUser) {
	this.loggedInUser = loggedInUser;
    }

    public static Type<RecordsEventHandler> TYPE = new Type<RecordsEventHandler>();

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<RecordsEventHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(RecordsEventHandler handler) {
	handler.onRecords(this);

    }

    public User getLoggedInUser() {
	return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
	this.loggedInUser = loggedInUser;
    }

}
