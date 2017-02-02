package com.tryout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by patrickackerman on 12/14/16.
 */
public class ButtonOKListener implements ActionListener {
    private StringListener textListener;

    public void setStringListener(StringListener stringListener) {
        this.textListener = stringListener;
    };

    public void actionPerformed(ActionEvent var1) {
        textListener.textEmitted("OK");
    }
}
