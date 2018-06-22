package com.wolfhaus;
/**
 * The AcceptedCoin class will handle will handle all accepted coins
 * in the Vending Machine program.
 */
public class AcceptedCoins {
    /**
     *  Contains Names of coins, and UNKNOWN placeholder.
     */
    public enum Name {
        /**
         * Name of coin nickel.
         */
        NICKEL,

        /**
         * Name of coin dime.
         */
        DIME,

        /**
         * Name of coin quarter.
         */
        QUARTER,

        /**
         * Name of coin unknown.
         */
        UNKNOWN
    }

    /**
     *  Contains the attributes of a known nickel object.
     */
    protected static Coin nickel = new Coin(Name.NICKEL, 5, 21, 5);

    /**
     *  Contains the attributes of a known dime object.
     */
    protected static Coin dime = new Coin(Name.DIME, 10, 18, 2);

    /**
     *  Contains the attributes of a known quarter object
     */
    protected static Coin quarter = new Coin(Name.QUARTER, 25, 25, 6);
}
