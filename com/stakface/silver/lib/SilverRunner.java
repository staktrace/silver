package com.stakface.silver.lib;

import java.io.IOException;

public class SilverRunner implements Terminator {
    protected VariableManager _manager;

    public SilverRunner() {
        _manager = new VariableManager();
    }

    public final void start() {
        _manager.lock();
        for (Variable variable : _manager.getVariables()) {
            variable.start();
        }
    }

    public final void terminate() {
        for (Variable input : _manager.getInputs()) {
            input.interrupt();
        }
    }
}
