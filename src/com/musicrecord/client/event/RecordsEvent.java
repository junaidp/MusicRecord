package com.musicrecord.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.musicrecord.shared.User;

public class RecordsEvent extends GwtEvent<RecordsEventHandler> {

    public RecordsEvent(User loggedInUser) {

    }

    public RecordsEvent() {

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

}
