package com.wolfhaus;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * The VendingMachine program implements an application that
 * sastifies the requirements of the kata-vending-machine
 * project.
 *
 * https://github.com/PillarTechnology/kata-vending-machine
 *
 * @author  Scott Wolfenbarger
 * @version 1.0
 * @since   2018-06-22
 */
public class VendingMachine {
    /**
     * The list of products in the vending machine.
     */
    protected ArrayList<Product> products = new ArrayList<>();

    /**
     * The NumberFormat object
     */
    NumberFormat currency = NumberFormat.getCurrencyInstance();

    /**
     * The total value of currency inserted into in the vending machine.
     */
    protected int insertedCoinsValue = 0;

    /**
     * The current display text on the vending machine.
     */
    protected String display;

    /**
     * The current display text on the vending machine.
     */
    protected boolean exactChangeOnly = false;

    /**
     * Constructor for VendingMachine.
     */
    public VendingMachine(){
        // Add the initial products
        this.products.add(new Product("cola", 100, "A1", 1));
        this.products.add(new Product("chips", 50, "A2", 1));
        this.products.add(new Product("candy", 65, "A3", 1));
    }

    /**
     * Method to accept a coin.
     * @param coin to be accepted into the vending machine.
     */
    public void acceptCoin(Coin coin){
        Coin identifiedCoin = coin.identify();
        insertedCoinsValue += identifiedCoin.value;
    }

    /**
     * Method to select a product.
     * @param productButton string representing the product's button.
     * @return ArrayList of Coin to be returned to the user.
     */
    public ArrayList<Coin> selectProduct(String productButton){
        // Coins to return
        ArrayList<Coin> returnedCoins = new ArrayList<>();

        // Find the product
        Product selectedProduct = this.products.stream().filter(p -> p.button.equals(productButton)).findFirst().orElse(null);

        if(selectedProduct != null && selectedProduct.count > 0) {
            // Determine if this product can be purchased
            if (this.insertedCoinsValue >= selectedProduct.price) {
                // There is at least enough money in the machine to purchase the selected product
                this.display = "THANK YOU";

                // Dispense the product
                selectedProduct.dispense();

                // Subtract used coins from the insertedCoinsValue
                this.insertedCoinsValue -= selectedProduct.price;

                // If there is any extra money, return it to the user
                if (this.insertedCoinsValue > 0) {
                    returnedCoins = this.returnCoins();
                }
            } else {
                // There is not enough money in the machine for the selected product
                this.display = "PRICE " + currency.format((double) selectedProduct.price / 100);
            }
        } else {
            // The select product count is 0, it is sold out
            this.display = "SOLD OUT";
        }

        return returnedCoins;
    }

    /**
     * Method to check the current display text.
     * @return String of the display text.
     */
    public String checkDisplay(){
        // Return the current display buffer when reading the display
        String display = this.display;

        // Update the display buffer for the next display check.
        if(this.insertedCoinsValue == 0) {
            if(this.exactChangeOnly == true) {
                // If there are no coins in the machine and exact change mode is on, display "EXACT CHANGE ONLY"
                this.display = "EXACT CHANGE ONLY";
            } else {
                // If there are no coins in the machine, display "INSERT COINS"
                this.display = "INSERT COIN";
            }
        } else {
            // Display should read current value of inserted coins
            this.display = currency.format((double)this.insertedCoinsValue/100);
        }

        // If the returning display is in a null state, apply the most current display
        if(display == null)
        {
            display = this.display;
        }

        return display;
    }

    /**
     * Method to return coins to the user.
     * @return ArrayList of Coin to be returned to the user.
     */
    public ArrayList<Coin> returnCoins(){
        // Coins to return
        ArrayList<Coin> returnedCoins = new ArrayList<>();

        // Determine what coins will be returned
        while(this.insertedCoinsValue > 0)
        {
            if(this.insertedCoinsValue >= IdentifiedCoins.quarter.value) {
                // Inserted coin value is above or equal 25, return a quarter
                returnedCoins.add(IdentifiedCoins.quarter);
                this.insertedCoinsValue -= IdentifiedCoins.quarter.value;
            } else if(this.insertedCoinsValue >= IdentifiedCoins.dime.value) {
                // Inserted coin value is above or equal 10, return a dime
                returnedCoins.add(IdentifiedCoins.dime);
                this.insertedCoinsValue -= IdentifiedCoins.dime.value;
            } else if(this.insertedCoinsValue >= IdentifiedCoins.nickel.value) {
                // Inserted coin value is above or equal 5, return a dime
                returnedCoins.add(IdentifiedCoins.nickel);
                this.insertedCoinsValue -= IdentifiedCoins.nickel.value;
            }
        }

        return returnedCoins;
    }
}
