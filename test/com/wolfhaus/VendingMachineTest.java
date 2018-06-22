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
     * Test insertedCoinsValue after selecting product with
     * enough money, the display should be "THANK YOU".
     */
    @Test
    public void selectColaWithEnoughMoneyThankYouTest() {
        // Insert four quarters
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        // Select product A1
        this.vendingMachine.selectProduct("A1");

        // Check the display the first time for "THANK YOU"
        assertEquals("THANK YOU", this.vendingMachine.checkDisplay());
    }

    /**
     * Test insertedCoinsValue after selecting product with
     * enough money, the display should be "INSERT COIN".
     */
    @Test
    public void afterSelectColaWithEnoughMoneyDisplayTest() {
        // Insert four quarters
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        // Select product A1
        this.vendingMachine.selectProduct("A1");

        // Check the display the first time for "THANK YOU"
        this.vendingMachine.checkDisplay();

        // Check the display the second time for "INSERT COIN"
        assertEquals("INSERT COIN", this.vendingMachine.checkDisplay());
    }

    /**
     * Test insertedCoinsValue after selecting product with
     * exact money, the value should be 0.
     */
    @Test
    public void afterSelectColaWithEnoughMoneyValueTest() {
        // Insert four quarters
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        // Select product A1
        this.vendingMachine.selectProduct("A1");

        // Check the insertedCoinsValue which should be 0
        assertEquals(0, this.vendingMachine.insertedCoinsValue);
    }

    /**
     * Test insertedCoinsValue after selecting product without
     * enough money, the display should be selected product price.
     */
    @Test
    public void selectColaWithoutEnoughMoneyPriceTest() {
        // Insert three quarters
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        // Select product A1
        this.vendingMachine.selectProduct("A1");

        // Check the display the first time for "PRICE $1.00"
        assertEquals("PRICE $1.00", this.vendingMachine.checkDisplay());
    }

    /**
     * Test insertedCoinsValue after selecting product with
     * exact money, the value should be 0.
     */
    @Test
    public void afterSelectColaWithoutEnoughMoneyPriceTest() {
        // Insert four quarters
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        // Select product A1
        this.vendingMachine.selectProduct("A1");

        // Check the display the first time for "PRICE $1.00"
        this.vendingMachine.checkDisplay();

        // Check the display the second time for "$0.75"
        assertEquals("$0.75", this.vendingMachine.checkDisplay());
    }

    /**
     * Test insertedCoinsValue after selecting product with
     * exact money, the value should be 0.
     */
    @Test
    public void selectColaWithExtraMoneyReturnCoinTest() {
        // Insert two nickels, two dimes, three quarters
        this.vendingMachine.acceptCoin(IdentifiedCoins.nickel);
        this.vendingMachine.acceptCoin(IdentifiedCoins.nickel);
        this.vendingMachine.acceptCoin(IdentifiedCoins.dime);
        this.vendingMachine.acceptCoin(IdentifiedCoins.dime);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        // Select product A1
        assertEquals(IdentifiedCoins.Name.NICKEL, this.vendingMachine.selectProduct("A1").get(0).name);
    }
}
