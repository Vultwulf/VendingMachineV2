package com.wolfhaus;

/**
 * The Product class will handle will contain information related
 * to all products in the Vending Machine program.
 */
public class Product {
    /**
     * The name of the button.
     */
    protected String button;

    /**
     * The name of a product.
     */
    protected String name;

    /**
     * The price of the product.
     */
    protected int price;

    /**
     * The inventory count of the product.
     */
    protected int count;

    /**
     * Constructor for Product.
     * @param productName name of the coin
     * @param productPrice value of the coin in cents
     * @param productButton size of the coin in millimeters
     * @param productCount weight of the coin in grams
     */
    public Product(String productName, int productPrice, String productButton, int productCount)
    {
        this.name = productName;
        this.price = productPrice;
        this.button = productButton;
        this.count = productCount;
    }

    /**
     * The method to dispense a product from the vending machine.
     */
    public void dispense()
    {
        // If there is product available, reduce the count by one.
        if(this.count > 0) {
            this.count --;
        }
    }
}