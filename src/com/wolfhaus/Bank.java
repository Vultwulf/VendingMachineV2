package com.wolfhaus;

import java.util.ArrayList;

/**
 * The IdentifiedCoins class will handle will handle all accepted coins
 * in the Vending Machine program.
 */
public class Bank {

    /**
     *  Contains number of nickels in the vending machine
     */
    protected int nickels = 0;

    /**
     *  Contains number of dimes in the vending machine
     */
    protected int dimes = 0;

    /**
     *  Contains number of quarters in the vending machine
     */
    protected int quarters = 0;

    /**
     *  Current active currency in the vending machine
     */
    protected int activeValue = 0;

    /**
     *  Set the current state of the vending machine's coin inventory.
     * @param nickels The amount of nickels in the vending machine.
     * @param dimes The amount of dimes in the vending machine.
     * @param quarters The amount of quarters in the vending machine.
     */
    public void setBankCoins(int nickels, int dimes, int quarters) {
        this.nickels = nickels;
        this.dimes = dimes;
        this.quarters = quarters;
    }

    /**
     *  Insert a coin into the vending machine.
     * @param coin Coin to be inserted.
     */
    public void insertCoin(Coin coin) {
        // Identify the inserted coin
        coin = coin.identify();

        switch (coin.name) {
            case NICKEL: this.nickels++;
                activeValue += IdentifiedCoins.nickel.value;
                break;
            case DIME: this.dimes++;
                activeValue += IdentifiedCoins.dime.value;
                break;
            case QUARTER: this.quarters++;
                activeValue += IdentifiedCoins.quarter.value;
                break;
        }
    }

    /**
     *  Return coins to the user.
     * @return returnCoins Coin to be returned.
     */
    public ArrayList<Coin> returnCoins() {
        // Coins to return
        ArrayList<Coin> returnedCoins = new ArrayList<>();

        // Determine what coins will be returned
        while(this.activeValue > 0)
        {
            if(this.activeValue >= IdentifiedCoins.quarter.value && this.quarters > 1) {
                // Inserted coin value is above or equal 25, return a quarter
                this.quarters -= 1;
                activeValue -= IdentifiedCoins.quarter.value;
                returnedCoins.add(IdentifiedCoins.quarter);
            } else if(this.activeValue >= IdentifiedCoins.dime.value && this.dimes > 1) {
                // Inserted coin value is above or equal 10, return a dime
                activeValue -= IdentifiedCoins.dime.value;
                this.dimes -= 1;
                returnedCoins.add(IdentifiedCoins.dime);
            } else if(this.activeValue >= IdentifiedCoins.nickel.value && this.nickels > 1) {
                // Inserted coin value is above or equal 5, return a nickel
                activeValue -= IdentifiedCoins.nickel.value;
                this.nickels -= 1;
                returnedCoins.add(IdentifiedCoins.nickel);
            }
        }

        return returnedCoins;
    }

    /**
     *  Determine if it's possible to return coins for a given price.
     * @return testPassed boolean value if change can be made.
     */
    public boolean returnCoinTest(int testValue) {
        // Copy the actual values to test values.
        int testQuarters = this.quarters;
        int testDimes = this.dimes;
        int testNickels = this.nickels;
        boolean testPassed = true;

        // Determine what coins will be returned
        while(testValue > 0)
        {
            if(testValue >= IdentifiedCoins.quarter.value && testQuarters > 1) {
                // Inserted coin value is above or equal 25, return a quarter
                testValue -= IdentifiedCoins.quarter.value;
                testQuarters -= 1;
            } else if(testValue >= IdentifiedCoins.dime.value && testDimes > 1) {
                // Inserted coin value is above or equal 10, return a dime
                testValue -= IdentifiedCoins.dime.value;
                testDimes -= 1;
            } else if(testValue >= IdentifiedCoins.nickel.value && testNickels > 1) {
                // Inserted coin value is above or equal 5, return a dime
                testValue -= IdentifiedCoins.nickel.value;
                testNickels -= 1;
            } else {
                // If we get to a point where change can't be given, the test fails.
                testPassed = false;
                break;
            }
        }

        return testPassed;
    }
}
