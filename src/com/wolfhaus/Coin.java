package com.wolfhaus;

/**
 * The Coin class will handle all relevant methods
 * relating to identifying and passing information relating
 * to coins inserted into the Vending Machine.
 *
 *
 * @author  Scott Wolfenbarger
 * @version 1.0
 * @since   2018-06-22
 */
public class Coin {
    /**
     *  Contains Names of coins, and UNKNOWN placeholder
     */
    public enum Name {
        NICKEL, DIME, QUARTER, UNKNOWN
    }

    /**
     * The name of a coin
     */
    protected Name name;

    /**
     * The value of a coin in cents
     */
    protected int value;

    /**
     * The size of a coin in millimeters
     */
    protected int size;

    /**
     * The weight of a coin in grams
     */
    protected int weight;

    /**
     * Constructor for VendingMachine
     * @param coinSize size of the coin in millimeters
     * @param coinWeight weight of the coin in grams
     */
    public Coin(int coinSize, int coinWeight)
    {
        this.size = coinSize;
        this.weight = coinWeight;
    }

    /**
     * The method to identify a coin, and apply the name and value to the object
     */
    public Coin identify() {
        if (this.size == 21 && this.weight == 5)
        {
            // A nickel has size of 21, weight of 5, with the value of 5
            this.name = Name.NICKEL;
            this.value = 5;
        }

        return this;
    }
}
