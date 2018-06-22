package com.wolfhaus;

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
     * The total value of currency inserted into in the vending machine.
     */
    protected int insertedCoinsValue = 0;

    /**
     * The current display text on the vending machine.
     */
    protected String display;

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
     */
    public void selectProduct(String productButton){
        Product selectedProduct = this.products.stream().filter(p -> p.button.equals(productButton)).findFirst().orElse(null);

        if(this.insertedCoinsValue >= selectedProduct.price) {
            // There is at least enough money in the machine to purchase the selected product
            this.display = "THANK YOU";
        }
    }

}
