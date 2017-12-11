package com.musicrecord.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface RecordsUserEventHandler extends EventHandler {
    void onRecords(RecordsUserEvent event);
}
