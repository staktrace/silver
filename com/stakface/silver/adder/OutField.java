package com.stakface.silver.adder;

import com.stakface.silver.lib.*;
import java.util.*;
import javax.swing.*;

public class OutField extends Variable<Double> {
    private JTextField _field;
    private Variable<Boolean> _clicked;
    private List<Variable<String>> _parents;

    public OutField( VariableManager manager, String name, JTextField field, String... parents ) {
        super( manager, name, true, parents );
        _field = field;

        _parents = new ArrayList<Variable<String>>();
        for (int i = 1; i < parents.length; i++) {
            _parents.add( manager.getVariable( parents[i] ) );
        }
        _clicked = manager.getVariable( parents[0] );
    }

    protected Double update() throws InterruptedException {
        Boolean clicked = _clicked.getValue();
        if (clicked != null && clicked == true) {
            Double result = 0.0;
            for (Variable<String> parent : _parents) {
                result += Double.parseDouble( parent.getValue() );
            }
            _field.setText( result.toString() );
            return result;
        }
        return getValue();
    }
}
