package com.musicrecord.client.widgets;

import java.util.ArrayList;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.musicrecord.shared.Category;
import com.musicrecord.shared.Records;

public class SaveEditRecordWidget extends VerticalPanel {

    private TextBox title, artist;
    private ListBox listCategory;
    private Button save, cancel;
    Label lblWindowHeader;

    public SaveEditRecordWidget(ArrayList<Category> categories, String poopWindowHeader) {

	VerticalPanel mainPanel = new VerticalPanel();

	HorizontalPanel popHeader = new HorizontalPanel();
	lblWindowHeader = new Label(poopWindowHeader);

	lblWindowHeader.addStyleName("w3-container w3-blue");
	lblWindowHeader.getElement().getStyle().setPaddingLeft(70, Unit.PX);
	popHeader.add(lblWindowHeader);
	popHeader.setWidth("100%");

	save = new Button("Save");
	save.setStyleName("w3-button w3-blue");
	save.setWidth("70px");

	cancel = new Button("Cancel");
	cancel.setWidth("70px");
	cancel.setStyleName("w3-button w3-blue");
	cancel.getElement().getStyle().setMarginLeft(42, Unit.PX);

	Label label_Title = new Label("Title : ");
	label_Title.addStyleName("w3-text-blue");
	title = new TextBox();

	Label label_Artist = new Label("Artist : ");
	label_Artist.addStyleName("w3-text-blue");
	artist = new TextBox();

	Label label_Category = new Label("Category : ");
	label_Category.addStyleName("w3-text-blue");

	listCategory = new ListBox();

	listCategory.setVisibleItemCount(1);
	listCategory.setWidth("75%");

	mainPanel.setSpacing(10);
	FlexTable flex = new FlexTable();

	flex.setWidget(0, 0, label_Title);
	flex.setWidget(0, 1, title);
	flex.setWidget(1, 0, label_Artist);
	flex.setWidget(1, 1, artist);
	flex.setWidget(2, 0, label_Category);
	flex.setWidget(2, 1, listCategory);

	HorizontalPanel hpnlButton = new HorizontalPanel();
	hpnlButton.setSpacing(2);
	// hpnlButton.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);

	hpnlButton.add(cancel);
	hpnlButton.add(save);

	flex.setWidget(3, 1, hpnlButton);
	mainPanel.add(popHeader);
	mainPanel.add(flex);

	for (Category c : categories) {
	    listCategory.addItem(c.getCategoryname(), c.getCategoryid() + "");
	}

	add(mainPanel);

	title.ensureDebugId("textTitle");
	artist.ensureDebugId("textArtist");
	listCategory.ensureDebugId("listCategory");
	save.ensureDebugId("buttonSave");

    }

    public void populateFields(Records record) {

	title.setText(record.getTitle());
	artist.setText(record.getArtist());

	for (int i = 0; i < listCategory.getItemCount(); i++) {
	    if (Integer.parseInt(listCategory.getValue(i)) == record.getCategory().getCategoryid()) {
		listCategory.setSelectedIndex(i);
		break;
	    }
	}
    }

    public Records getUpdatedRecord(Records record) {

	record.setTitle(title.getText());
	record.setArtist(artist.getText());
	Category cat = new Category();
	cat.setCategoryid(Integer.parseInt(listCategory.getValue(listCategory.getSelectedIndex())));
	record.setCategory(cat);
	return record;

    }

    public Button getSaveButton() {
	return save;
    }

    public Button getCancelButton() {
	return cancel;
    }
}
