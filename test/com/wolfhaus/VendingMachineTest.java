package com.wolfhaus;

import org.junit.Before;
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
        assertEquals(AcceptedCoins.nickel.value, insertedCoin.identify().value);
    }

    /**
     * Test to identify a coin as a dime with the value of 10
     */
    @Test
    public void identifyCoinDimeValueTest() {
        Coin insertedCoin = new Coin(18, 2);
        assertEquals(AcceptedCoins.dime.value, insertedCoin.identify().value);
    }
}
