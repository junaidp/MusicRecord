package com.musicrecord.client.view;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.musicrecord.client.presenter.HeaderPresenter;

public class HeaderView extends VerticalPanel implements HeaderPresenter.Display {

    
    public HeaderView() {
    	
    	HorizontalPanel header = new HorizontalPanel();
    	Label lblMusicRecord = new Label("Music Record");
  
    	lblMusicRecord.addStyleName("headerText");
    	header.add(lblMusicRecord);
    	
    	header.setWidth("100%");
    	header.addStyleName("w3-container w3-blue");
    	setWidth("100%");
    	add(header);
	    }
	
	private void layout() {
		
	    }
	
	}
