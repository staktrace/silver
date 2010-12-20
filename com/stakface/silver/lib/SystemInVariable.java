package com.stakface.silver.lib;

import java.io.*;

public class SystemInVariable extends Variable<String> {
    private BufferedReader _reader;

    public SystemInVariable( VariableManager manager ) throws IOException {
        super( manager, "system.in", true );
        _reader = new BufferedReader( new InputStreamReader( System.in ) );
    }

    protected String update() throws InterruptedException {
        try {
            return _reader.readLine();
        } catch (IOException ioe) {
            return null;
        }
    }
}
