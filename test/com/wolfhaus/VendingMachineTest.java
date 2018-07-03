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

        // Set the initial state of the machine's coins.
        this.vendingMachine.bank.setBankCoins(10,10,10);
    }

    /**
     * Test to identify a coin as a nickel with the associated value (5).
     */
    @Test
    public void insertCoinNickelValueTest() {
        Coin insertedCoin = new Coin(21, 5);
        assertEquals(5, insertedCoin.value);
    }

    /**
     * Test to identify a coin as a dime with the associated value (10).
     */
    @Test
    public void insertCoinDimeValueTest() {
        Coin insertedCoin = new Coin(18, 2);
        assertEquals(10, insertedCoin.value);
    }

    /**
     * Test to identify a coin as a quarter with the associated value (25).
     */
    @Test
    public void insertCoinQuarterValueTest() {
        Coin insertedCoin = new Coin(25, 6);
        assertEquals(25, insertedCoin.value);
    }

    /**
     * Test to identify a coin as a unknown with the associated value (0).
     */
    @Test
    public void insertCoinUnknownValueTest() {
        Coin insertedCoin = new Coin(66, 12);
        assertEquals(0, insertedCoin.value);
    }

    /**
     * Test to check if bank nickel inventory increments by 1.
     */
    @Test
    public void insertCoinNickelBankInventoryTest() {
        Coin insertedCoin = new Coin(21, 5);

        this.vendingMachine.bank.insertCoin(insertedCoin);
        assertEquals(11, this.vendingMachine.bank.nickels);
    }

    /**
     * Test to check if bank dime inventory increments by 1.
     */
    @Test
    public void insertCoinDimeBankInventoryTest() {
        Coin insertedCoin = new Coin(18, 2);

        this.vendingMachine.bank.insertCoin(insertedCoin);
        assertEquals(11, this.vendingMachine.bank.dimes);
    }

    /**
     * Test to check if bank quarter inventory increments by 1.
     */
    @Test
    public void insertCoinQuarterBankInventoryTest() {
        Coin insertedCoin = new Coin(25, 6);

        this.vendingMachine.bank.insertCoin(insertedCoin);
        assertEquals(11, this.vendingMachine.bank.quarters);
    }

    /**
     * Test insertedCoinsValue after selecting product with
     * enough money, the display should be "THANK YOU".
     */
    @Test
    public void selectColaWithEnoughMoneyThankYouTest() {
        // Insert four quarters
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);

        // Select product A1 (cola)
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
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);

        // Select product A1 (cola)
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
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);

        // Select product A1 (cola)
        this.vendingMachine.selectProduct("A1");

        // Check the insertedCoinsValue which should be 0
        assertEquals(0, this.vendingMachine.bank.activeValue);
    }

    /**
     * Test insertedCoinsValue after selecting product without
     * enough money, the display should be selected product price.
     */
    @Test
    public void selectColaWithoutEnoughMoneyPriceTest() {
        // Insert three quarters
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);

        // Select product A1 (cola)
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
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);

        // Select product A1 (cola)
        this.vendingMachine.selectProduct("A1");

        // Check the display the first time for "PRICE $1.00"
        this.vendingMachine.checkDisplay();

        // Check the display the second time for "$0.75"
        assertEquals("$0.75", this.vendingMachine.checkDisplay());
    }

    /**
     * Test selectProduct A1 with extra money, a nickel should be returned.
     */
    @Test
    public void selectColaWithExtraMoneyReturnCoinTest() {
        // Insert two nickels, two dimes, three quarters
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.nickel);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.nickel);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.dime);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.dime);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);

        // Select product A1 (cola), expect a nickel returned
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
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);

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
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);

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
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);

        // Select product A1 (cola)
        this.vendingMachine.selectProduct("A1");

        // Insert four quarters
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);

        // Select product A1 (cola) after it's been sold
        this.vendingMachine.selectProduct("A1");

        assertEquals("SOLD OUT", this.vendingMachine.checkDisplay());
    }

    /**
     * Test trying to purchase a Cola when the inventory for Cola is 0,
     * and there is enough money added in the machine, the display should read
     * "$1.00" on the second display check.
     */
    @Test
    public void selectColaSoldOutWithMoneyDisplayTest() {
        // Insert four quarters
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);

        // Select product A1 (cola)
        this.vendingMachine.selectProduct("A1");

        // Insert four quarters
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);

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
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.nickel);

        // Return Coins
        assertEquals(1, this.vendingMachine.bank.returnCoins().size());
    }

    /** Test returnCoins size test after inserting one dime
     */
    @Test
    public void returnCoinsSizeOneDimeTest() {
        // Insert one dime
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.dime);

        // Return Coins
        assertEquals(1, this.vendingMachine.bank.returnCoins().size());
    }

    /** Test returnCoins size test after inserting one quarter
     */
    @Test
    public void returnCoinsSizeOneQuarterTest() {
        // Insert one quarter
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);

        // Return Coins
        assertEquals(1, this.vendingMachine.bank.returnCoins().size());
    }

    /** Test returnCoins size test after inserting one nickel, dime, quarter
     */
    @Test
    public void returnCoinsSizeOneNickelDimeQuarterTest() {
        // Insert one nickel, dime, quarter
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.nickel);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.dime);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);

        // Return Coins
        assertEquals(3, this.vendingMachine.bank.returnCoins().size());
    }

    /** Test returnCoins value test after inserting one nickel
     */
    @Test
    public void returnCoinsValueOneNickelTest() {
        // Insert one quarter
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.nickel);

        // Return Coins
        assertEquals(5, this.vendingMachine.bank.returnCoins().get(0).value);
    }

    /** Test returnCoins value test after inserting one nickel
     */
    @Test
    public void returnCoinsValueOneDimeTest() {
        // Insert one quarter
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.dime);

        // Return Coins
        assertEquals(10, this.vendingMachine.bank.returnCoins().get(0).value);
    }

    /** Test returnCoins value test after inserting one quarter
     */
    @Test
    public void returnCoinsValueOneQuarterTest() {
        // Insert one quarter
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);

        // Return Coins
        assertEquals(25, this.vendingMachine.bank.returnCoins().get(0).value);
    }

    /** Test returnCoins value test after inserting one nickel, dime, quarter
     */
    @Test
    public void returnCoinsValueOneNickelDimeQuarterTest() {
        // Insert one nickel, dime, quarter
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.nickel);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.dime);
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);

        int coinValue = 0;

        // Add together total coins value
        for (Coin coin : this.vendingMachine.bank.returnCoins()) {
            coinValue += coin.value;
        }

        assertEquals(40, coinValue);
    }

    /**
     * Test to check if bank nickel inventory reduces by 1.
     */
    @Test
    public void returnCoinNickelBankInventoryTest() {
        // Insert one nickel
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.nickel);

        this.vendingMachine.bank.returnCoins();
        assertEquals(10, this.vendingMachine.bank.nickels);
    }

    /**
     * Test to check if bank dime inventory reduces by 1.
     */
    @Test
    public void returnCoinDimeBankInventoryTest() {
        // Insert one nickel
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.dime);

        this.vendingMachine.bank.returnCoins();
        assertEquals(10, this.vendingMachine.bank.dimes);
    }

    /**
     * Test to check if bank quarter inventory reduces by 1.
     */
    @Test
    public void returnCoinQuarterBankInventoryTest() {
        // Insert one nickel
        this.vendingMachine.bank.insertCoin(IdentifiedCoins.quarter);

        this.vendingMachine.bank.returnCoins();
        assertEquals(10, this.vendingMachine.bank.quarters);
    }

    /** Test the display reads "EXACT CHANGE ONLY" when in Exact Change Only mode
     */
    @Test
    public void exactChangeTest() {
        this.vendingMachine.bank.setBankCoins(0,0,0);
        assertEquals("EXACT CHANGE ONLY", this.vendingMachine.checkDisplay());
    }
}
