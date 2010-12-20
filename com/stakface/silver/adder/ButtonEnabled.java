package com.stakface.silver.adder;

import com.stakface.silver.lib.*;
import java.util.*;
import javax.swing.*;

public class ButtonEnabled extends Variable<Boolean> {
    private JButton _button;
    private List<Variable<String>> _parents;

    public ButtonEnabled( VariableManager manager, String name, JButton button, String... parents ) {
        super( manager, name, false, parents );
        _button = button;
        _parents = new ArrayList<Variable<String>>();
        for (String parent : parents) {
            _parents.add( manager.getVariable( parent ) );
        }
    }

    protected Boolean update() {
        boolean valid = true;
        for (Variable<String> parent : _parents) {
            try {
                Double.parseDouble( parent.getValue() );
            } catch (Exception nfe) {
                valid = false;
            }
        }
        _button.setEnabled( valid );    // this wouldn't actually be here in a full implementation
        return valid;
    }
}
