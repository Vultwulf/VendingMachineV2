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
     * The VendingMachine object to be used with the tests.
     */
    private VendingMachine vendingMachine = new VendingMachine();

    @Before
    public void setUp() {
        // Instantiate the VendingMachine object
        this.vendingMachine = new VendingMachine();
    }

    /**
     * Test to identify a coin as a nickel with the associated value (5).
     */
    @Test
    public void identifyCoinNickelValueTest() {
        Coin insertedCoin = new Coin(21, 5);
        assertEquals(5, insertedCoin.identify().value);
    }

    /**
     * Test to identify a coin as a dime with the associated value (10).
     */
    @Test
    public void identifyCoinDimeValueTest() {
        Coin insertedCoin = new Coin(18, 2);
        assertEquals(10, insertedCoin.identify().value);
    }

    /**
     * Test to identify a coin as a quarter with the associated value (25).
     */
    @Test
    public void identifyCoinQuarterValueTest() {
        Coin insertedCoin = new Coin(25, 6);
        assertEquals(25, insertedCoin.identify().value);
    }

    /**
     * Test to identify a coin as a unknown with the associated value (0).
     */
    @Test
    public void identifyCoinUnknownValueTest() {
        Coin insertedCoin = new Coin(66, 12);
        assertEquals(0, insertedCoin.identify().value);
    }

    /**
     * Test to select a product with exact money test.
     */
    @Test
    public void selectColaWithExactMoneyThankYouTest() {
        // Insert four quarters
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        this.vendingMachine.selectProduct("A1");
        assertEquals("THANK YOU", this.vendingMachine.display);
    }

    /**
     * Test insertedCoinsValue after selecting product with
     * exact money, the value should be 0.
     */
    @Test
    public void afterSelectColaWithExactMoneyValueTest() {
        // Insert four quarters
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        this.vendingMachine.selectProduct("A1");
        assertEquals(0, this.vendingMachine.insertedCoinsValue);
    }

}
