package com.stakface.silver.adder;

import com.stakface.silver.lib.*;
import javax.swing.*;
import java.awt.event.*;

public class ButtonClicked extends Variable<Boolean> implements ActionListener {
    private JButton _button;

    public ButtonClicked( VariableManager manager, String name, JButton button ) {
        super( manager, name, true );
        _button = button;
        _button.addActionListener( this );
    }
    
    public void actionPerformed( ActionEvent ae ) {
        synchronized (_button) {
            _button.notify();
        }
    }

    protected Boolean update() throws InterruptedException {
        Boolean value = getValue();
        if (value != null && value == true) {
            return false;
        }
        synchronized (_button) {
            _button.wait();
        }
        return true;
    }
}
