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

    /**
     * Test trying to purchase a Cola when the inventory for Cola is 0,
     * and there is no more money in the machine, the display should read
     * "SOLD OUT" on the first display check.
     */
    @Test
    public void selectColaSoldOutWithoutMoneyTest() {
        // Insert four quarters
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        // Select product A1 (cola)
        this.vendingMachine.selectProduct("A1");

        // Select product A1 (cola) after it's been sold
        this.vendingMachine.selectProduct("A1");

        assertEquals("SOLD OUT", this.vendingMachine.checkDisplay());
    }

    /**
     * Test trying to purchase a Cola when the inventory for Cola is 0,
     * and there is no more money in the machine, the display should read
     * "INSERT COIN" on the second display check.
     */
    @Test
    public void selectColaSoldOutWithoutMoneyInsertCoinTest() {
        // Insert four quarters
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        // Select product A1 (cola)
        this.vendingMachine.selectProduct("A1");

        // Select product A1 (cola) after it's been sold
        this.vendingMachine.selectProduct("A1");

        // Check the display the first time
        this.vendingMachine.checkDisplay();

        assertEquals("INSERT COIN", this.vendingMachine.checkDisplay());
    }

    /**
     * Test trying to purchase a Cola when the inventory for Cola is 0,
     * and there is enough money added in the machine, the display should read
     * "SOLD OUT" on the first display check.
     */
    @Test
    public void selectColaSoldOutWithMoneyTest() {
        // Insert four quarters
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        // Select product A1 (cola)
        this.vendingMachine.selectProduct("A1");

        // Insert four quarters
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        // Select product A1 (cola) after it's been sold
        this.vendingMachine.selectProduct("A1");

        assertEquals("SOLD OUT", this.vendingMachine.checkDisplay());
    }

    /**
     * Test trying to purchase a Cola when the inventory for Cola is 0,
     * and there is enough money added in the machine, the display should read
     * "SOLD OUT" on the first display check.
     */
    @Test
    public void selectColaSoldOutWithMoneyDisplayTest() {
        // Insert four quarters
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        // Select product A1 (cola)
        this.vendingMachine.selectProduct("A1");

        // Insert four quarters
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        // Select product A1 (cola) after it's been sold
        this.vendingMachine.selectProduct("A1");

        // Check the display the first time
        this.vendingMachine.checkDisplay();

        assertEquals("$1.00", this.vendingMachine.checkDisplay());
    }

    /** Test returnCoins size test after inserting one nickel
     */
    @Test
    public void returnCoinsSizeOneNickelTest() {
        // Insert one quarter
        this.vendingMachine.acceptCoin(IdentifiedCoins.nickel);

        // Return Coins
        assertEquals(1, this.vendingMachine.returnCoins().size());
    }

    /** Test returnCoins size test after inserting one dime
     */
    @Test
    public void returnCoinsSizeOneDimeTest() {
        // Insert one quarter
        this.vendingMachine.acceptCoin(IdentifiedCoins.dime);

        // Return Coins
        assertEquals(1, this.vendingMachine.returnCoins().size());
    }

    /** Test returnCoins size test after inserting one quarter
     */
    @Test
    public void returnCoinsSizeOneQuarterTest() {
        // Insert one quarter
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        // Return Coins
        assertEquals(1, this.vendingMachine.returnCoins().size());
    }

    /** Test returnCoins size test after inserting one nickel, dime, quarter
     */
    @Test
    public void returnCoinsSizeOneNickelDimeQuarterTest() {
        // Insert one nickel, dime, quarter
        this.vendingMachine.acceptCoin(IdentifiedCoins.nickel);
        this.vendingMachine.acceptCoin(IdentifiedCoins.dime);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        // Return Coins
        assertEquals(3, this.vendingMachine.returnCoins().size());
    }

    /** Test returnCoins value test after inserting one nickel
     */
    @Test
    public void returnCoinsValueOneNickelTest() {
        // Insert one quarter
        this.vendingMachine.acceptCoin(IdentifiedCoins.nickel);

        // Return Coins
        assertEquals(5, this.vendingMachine.returnCoins().get(0).value);
    }

    /** Test returnCoins value test after inserting one nickel
     */
    @Test
    public void returnCoinsValueOneDimeTest() {
        // Insert one quarter
        this.vendingMachine.acceptCoin(IdentifiedCoins.dime);

        // Return Coins
        assertEquals(10, this.vendingMachine.returnCoins().get(0).value);
    }

    /** Test returnCoins value test after inserting one quarter
     */
    @Test
    public void returnCoinsValueOneQuarterTest() {
        // Insert one quarter
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        // Return Coins
        assertEquals(25, this.vendingMachine.returnCoins().get(0).value);
    }

    /** Test returnCoins value test after inserting one nickel, dime, quarter
     */
    @Test
    public void returnCoinsValueOneNickelDimeQuarterTest() {
        // Insert one nickel, dime, quarter
        this.vendingMachine.acceptCoin(IdentifiedCoins.nickel);
        this.vendingMachine.acceptCoin(IdentifiedCoins.dime);
        this.vendingMachine.acceptCoin(IdentifiedCoins.quarter);

        // Add together total coins value
        int coinValue = 0;
        for (Coin coin : this.vendingMachine.returnCoins()) {
            coinValue += coin.value;
        }

        assertEquals(40, coinValue);
    }
}
