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
    protected ArrayList<Product> products = new ArrayList();

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
    protected String display = "INSERT COIN";

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
     */
    public void acceptCoin(Coin coin){
        Coin identifiedCoin = coin.identify();
        insertedCoinsValue += identifiedCoin.value;
    }

    /**
     * Method to select a product.
     * @param productButton string representing the product's button
     */
    public void selectProduct(String productButton){
        Product selectedProduct = this.products.stream().filter(p -> p.button.equals(productButton)).findFirst().orElse(null);
        String message = "";
        if(this.insertedCoinsValue >= selectedProduct.price){
            // There is at least enough money in the machine to purchase the selected product
            this.display = "THANK YOU";

            // Dispense the product.
            selectedProduct.dispense();

            // Reset the insertedCoinsValue to 0
            this.insertedCoinsValue = 0;
        } else {
            // There is not enough money in the machine for the selected product
            this.display = "PRICE " + currency.format((double)selectedProduct.price/100);
        }

    }

    /**
     * Method to check the display text.
     */
    public String checkDisplay(){
        String display = this.display;

        if (this.display.equals("THANK YOU")) {
            // The display should only read "THANK YOU" on first check
             this.display = "INSERT COIN";
        } else if (this.display.contains("PRICE")) {
            // The display should only read "PRICE $x.xx on first check
            this.display = currency.format((double)this.insertedCoinsValue/100);
        } else {
            if(this.insertedCoinsValue == 0) {
                // If there are no coins in the machine, display "INSERT COINS"
                this.display = "INSERT COIN";
            } else {
                // Display should read current value of inserted coins
                this.display = currency.format((double)this.insertedCoinsValue/100);
            }
        }

        return display;
    }
}
