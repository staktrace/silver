package com.stakface.silver.adder;

import com.stakface.silver.lib.*;
import com.stakface.lib.reflector.*;
import javax.swing.*;
import javax.swing.event.*;

public class TextField extends Variable<String> implements ReflectionListener<Object> {
    private JTextField _field;

    public TextField( VariableManager manager, String name, JTextField field ) {
        super( manager, name, true );
        _field = field;
        _field.getDocument().addDocumentListener( new DocumentReflector( this ) );
    }

    public void reflectionTriggered( Object x ) {
        synchronized (_field) {
            _field.notify();
        }
    }

    protected String update() throws InterruptedException {
        synchronized (_field) {
            _field.wait();
        }
        return _field.getText();
    }
}
