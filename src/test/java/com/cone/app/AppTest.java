package com.cone.app;

// import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void shouldAnswerWithTrue()
    {
        String[] args = new String[1];

        args[0] = "-ls";

        App.main(args);
    }
}
