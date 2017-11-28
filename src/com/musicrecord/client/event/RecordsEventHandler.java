package com.musicrecord.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface RecordsEventHandler extends EventHandler {
    void onRecords(RecordsEvent event);
}
