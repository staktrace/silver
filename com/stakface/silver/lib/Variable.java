package com.stakface.silver.lib;

import java.util.*;

public abstract class Variable<T> extends Thread {
    private T _value;
    private String _name;
    private boolean _isInput;

    private Set<Variable> _dependencies;

    public Variable( VariableManager manager, String name, boolean isInput, String... parents ) {
        _name = name;
        _isInput = isInput;

        for (String parent : parents) {
            manager.getVariable( parent ).registerDependency( this );
        }
        _dependencies = new HashSet<Variable>();

        manager.registerVariable( this );
    }

    public final String getIdentifier() {
        return _name;
    }

    public final boolean isInput() {
        return _isInput;
    }

    protected abstract T update() throws InterruptedException;

    public final T getValue() {
        synchronized (this) {
            return _value;
        }
    }

    public void registerDependency( Variable dependent ) {
        if (dependent == null) {
            throw new IllegalArgumentException( "Dependent variable may not be null (this=" + getIdentifier() + ")" );
        }
        _dependencies.add( dependent );
    }

    public void run() {
        while (! Thread.currentThread().interrupted()) {
            T oldValue = getValue();
            T newValue;
            try {
                newValue = update();
            } catch (InterruptedException ie) {
                break;
            }
            synchronized (this) {
                _value = newValue;
            }

            boolean changed = false;
            if (oldValue == null || newValue == null) {
                changed = oldValue != newValue;
            } else {
                changed = ! oldValue.equals( newValue );
            }
            if (changed) {
                for (Variable dependent : _dependencies) {
                    synchronized (dependent) {
                        dependent.notify();
                    }
                }
            }

            if (! isInput()) {
                try {
                    synchronized (this) {
                        wait();
                    }
                } catch (InterruptedException ie) {
                    break;
                }
            }
        }

        for (Variable dependent : _dependencies) {
            dependent.interrupt();
        }
    }
}
