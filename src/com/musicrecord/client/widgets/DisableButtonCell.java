package com.musicrecord.client.widgets;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public final class DisableButtonCell extends ButtonCell {
    private String btnColor = "w3-btn w3-light-grey";

    private String INPUT_ENABLED = "<button class=\"w3-btn w3-red\" type=\"button\" tabindex=\"-1\">";
    private String INPUT_DISABLED = "<button type=\"button\" tabindex=\"-1\" disabled=\"disabled\">";

    private static final String INPUT_CLOSE = "</button>";

    private boolean enabled = true;

    public boolean isEnabled() {
	return enabled;
    }

    public void setEnabled(final boolean enabled) {
	this.enabled = enabled;
    }

    public DisableButtonCell() {

    }

    @Override
    public void render(final Context context, final SafeHtml value, final SafeHtmlBuilder sb) {
	if (enabled) {
	    INPUT_ENABLED = "<button class=\"   " + btnColor + "   \" type=\"button\" tabindex=\"-1\">";
	    sb.appendHtmlConstant(INPUT_ENABLED + value.asString() + INPUT_CLOSE);
	} else {
	    INPUT_DISABLED = "<button class=\"   " + btnColor
		    + "   \" type=\"button\" tabindex=\"-1\" disabled=\"disabled\">";
	    sb.appendHtmlConstant(INPUT_DISABLED + value.asString() + INPUT_CLOSE);
	}
    }

    public String getBtnColor() {
	return btnColor;
    }

    public void setBtnColor(String btnColor) {
	this.btnColor = btnColor;
    }
}