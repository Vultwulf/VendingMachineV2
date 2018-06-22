package com.wolfhaus;

/**
 * The Coin class will handle all relevant methods
 * relating to identifying and passing information relating
 * to coins inserted into the Vending Machine.
 *
 * @author  Scott Wolfenbarger
 * @version 1.0
 * @since   2018-06-22
 */
public class Coin {
    /**
     * The name of a coin.
     */
    protected AcceptedCoins.Name name;

    /**
     * The value of a coin in cents.
     */
    protected int value;

    /**
     * The size of a coin in millimeters.
     */
    protected int size;

    /**
     * The weight of a coin in grams.
     */
    protected int weight;

    /**
     * Constructor for VendingMachine.
     * @param coinSize size of the coin in millimeters
     * @param coinWeight weight of the coin in grams
     */
    public Coin(int coinSize, int coinWeight) {
        this.size = coinSize;
        this.weight = coinWeight;
    }

    /**
     * Constructor for VendingMachine.
     * @param coinName name of the coin
     * @param coinValue value of the coin in cents
     * @param coinSize size of the coin in millimeters
     * @param coinWeight weight of the coin in grams
     */
    public Coin(AcceptedCoins.Name coinName, int coinValue, int coinSize, int coinWeight) {
        this.name = coinName;
        this.value = coinValue;
        this.size = coinSize;
        this.weight = coinWeight;
    }

    /**
     * The method to identify a coin, and apply the name and value to the object.
     */
    public Coin identify() {
        if (this.size == AcceptedCoins.nickel.size && this.weight == AcceptedCoins.nickel.weight) {
            // A nickel has been identified, apply the nickel name and value.
            this.name = AcceptedCoins.nickel.name;
            this.value = AcceptedCoins.nickel.value;
        } else if (this.size == AcceptedCoins.dime.size && this.weight == AcceptedCoins.dime.weight) {
            // A dime has been identified, apply the nickel name and value.
            this.name = AcceptedCoins.dime.name;
            this.value = AcceptedCoins.dime.value;
        } else if (this.size == AcceptedCoins.quarter.size && this.weight == AcceptedCoins.quarter.weight) {
            // A quarter has been identified, apply the quarter name and value.
            this.name = AcceptedCoins.quarter.name;
            this.value = AcceptedCoins.quarter.value;
        }

        return this;
    }
}
