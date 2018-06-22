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
     * Test to identify a coin as a nickel with the associated value (5)
     */
    @Test
    public void identifyCoinNickelValueTest() {
        Coin insertedCoin = new Coin(21, 5);
        assertEquals(IdentifiedCoins.nickel.value, insertedCoin.identify().value);
    }

    /**
     * Test to identify a coin as a dime with the associated value (10)
     */
    @Test
    public void identifyCoinDimeValueTest() {
        Coin insertedCoin = new Coin(18, 2);
        assertEquals(IdentifiedCoins.dime.value, insertedCoin.identify().value);
    }

    /**
     * Test to identify a coin as a quarter with the associated value (25)
     */
    @Test
    public void identifyCoinQuarterValueTest() {
        Coin insertedCoin = new Coin(25, 6);
        assertEquals(IdentifiedCoins.quarter.value, insertedCoin.identify().value);
    }

    /**
     * Test to identify a coin as a unknown with the associated value (0)
     */
    @Test
    public void identifyCoinUnknownValueTest() {
        Coin insertedCoin = new Coin(66, 12);
        assertEquals(IdentifiedCoins.unknown.value, insertedCoin.identify().value);
    }
}
