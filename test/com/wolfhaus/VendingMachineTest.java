package com.wolfhaus;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The VendingMachineTest implements all test-cases for use
 * of Test Driven Development for the VendingMachine program.
 *
 * https://github.com/PillarTechnology/kata-vending-machine
 *
 * @author  Scott Wolfenbarger
 * @version 1.0
 * @since   2018-06-22
 */
public class VendingMachineTest {

    /**
     * Test to identify a coin as a nickel with the value of 5
     */
    @Test
    public void identifyCoinNickelValueTest() {
        Coin insertedCoin = new Coin(21, 5);
        assertEquals(5, insertedCoin.identify().value);
    }
}
