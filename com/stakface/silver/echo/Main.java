package com.stakface.silver.echo;

import com.stakface.silver.lib.*;
import java.io.*;

public class Main extends SilverRunner {
    public Main() throws IOException {
        new SystemInVariable( _manager );
        new SystemOut( _manager, this );
    }

    public static void main( String[] args ) throws IOException {
        Main main = new Main();
        main.start();
    }
}
