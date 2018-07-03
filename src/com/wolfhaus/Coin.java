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
    protected IdentifiedCoins.Name name;

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

        this.identify();
    }

    /**
     * Constructor for VendingMachine.
     * @param coinName name of the coin
     * @param coinValue value of the coin in cents
     * @param coinSize size of the coin in millimeters
     * @param coinWeight weight of the coin in grams
     */
    public Coin(IdentifiedCoins.Name coinName, int coinValue, int coinSize, int coinWeight) {
        this.name = coinName;
        this.value = coinValue;
        this.size = coinSize;
        this.weight = coinWeight;
    }

    /**
     * The method to identify a coin, and apply the name and value to the object.
     * @return The identified coin object.
     */
    private Coin identify() {
        if (this.size == IdentifiedCoins.nickel.size && this.weight == IdentifiedCoins.nickel.weight) {
            // A nickel has been identified, apply the nickel name and value.
            this.name = IdentifiedCoins.nickel.name;
            this.value = IdentifiedCoins.nickel.value;
        } else if (this.size == IdentifiedCoins.dime.size && this.weight == IdentifiedCoins.dime.weight) {
            // A dime has been identified, apply the nickel name and value.
            this.name = IdentifiedCoins.dime.name;
            this.value = IdentifiedCoins.dime.value;
        } else if (this.size == IdentifiedCoins.quarter.size && this.weight == IdentifiedCoins.quarter.weight) {
            // A quarter has been identified, apply the quarter name and value.
            this.name = IdentifiedCoins.quarter.name;
            this.value = IdentifiedCoins.quarter.value;
        } else
        {
            // This is an unidentified coin for which this vending machine recognizes no value
            this.name = IdentifiedCoins.unknown.name;
            this.value = IdentifiedCoins.unknown.value;
        }

        return this;
    }
}
