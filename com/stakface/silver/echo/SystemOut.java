package com.stakface.silver.echo;

import com.stakface.silver.lib.*;

public class SystemOut extends Variable<String> {
    private Terminator _terminator;
    private Variable<String> _systemIn;

    public SystemOut( VariableManager manager, Terminator terminator ) {
        super( manager, "system.out", false, "system.in" );
        _terminator = terminator;
        _systemIn = manager.getVariable( "system.in" );
    }

    protected String update() {
        String value = _systemIn.getValue();
        if (value != null) {
            if (value.equals( "exit" )) {
                _terminator.terminate();
            } else {
                System.out.println( "Input changed to: [" + value + "]" );
            }
        }
        return value;
    }
}
