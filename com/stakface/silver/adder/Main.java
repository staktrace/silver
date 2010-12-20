package com.stakface.silver.adder;

import com.stakface.silver.lib.*;
import java.awt.*;
import javax.swing.*;

public class Main extends SilverRunner {
    private JFrame _frame;

    public Main() {
        _frame = new JFrame( "Adder" );
        JTextField summand1, summand2, summand3, output;
        JButton add;

        new TextField( _manager, "summand1", summand1 = new JTextField() );
        new TextField( _manager, "summand2", summand2 = new JTextField() );
        new TextField( _manager, "summand3", summand3 = new JTextField() );

        new ButtonEnabled( _manager, "add", add = new JButton( "Add" ), "summand1", "summand2", "summand3" );
        new ButtonClicked( _manager, "clicked", add );
        new OutField( _manager, "sum", output = new JTextField(), "clicked", "summand1", "summand2", "summand3" );

        _frame.getContentPane().setLayout( new BoxLayout( _frame.getContentPane(), BoxLayout.Y_AXIS ) );
        _frame.getContentPane().add( summand1 );
        _frame.getContentPane().add( summand2 );
        _frame.getContentPane().add( summand3 );
        _frame.getContentPane().add( add );
        _frame.getContentPane().add( output );
        _frame.pack();
    }

    public static void main( String[] args ) {
        Main main = new Main();
        main.start();
        main._frame.setVisible( true );
    }
}
