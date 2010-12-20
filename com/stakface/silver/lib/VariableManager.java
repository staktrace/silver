package com.stakface.silver.lib;

import java.util.*;

public class VariableManager {
    private boolean _locked;
    private Map<String, Variable> _variables;
    private List<Variable> _ordered;
    private Set<Variable> _inputs;

    public VariableManager() {
        _locked = false;
        _variables = new HashMap<String, Variable>();
        _ordered = new ArrayList<Variable>();
        _inputs = new HashSet<Variable>();
    }

    public Variable getVariable( String name ) {
        if (! _variables.containsKey( name )) {
            throw new VariableNotFoundException( name );
        }
        return _variables.get( name );
    }

    public void registerVariable( Variable v ) {
        if (_locked) {
            throw new IllegalStateException( "Variables cannot be added after the program has started" );
        }

        if (v == null || v.getIdentifier() == null) {
            throw new IllegalArgumentException( "A variable and it's name must both be non-null" );
        }

        String name = v.getIdentifier();
        if (_variables.containsKey( name )) {
            throw new IllegalArgumentException( "Attempted duplicate registration for variable " + name );
        }
        _variables.put( name, v );
        _ordered.add( v );
        if (v.isInput()) {
            _inputs.add( v );
        }
    }

    void lock() {
        if (_locked) {
            throw new IllegalStateException( "Variable manager has already been locked" );
        }
        _locked = true;
    }

    List<Variable> getVariables() {
        return _ordered;
    }

    Set<Variable> getInputs() {
        return _inputs;
    }
}
