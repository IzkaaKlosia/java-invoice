package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) {

        if(name == null){
            throw new IllegalArgumentException("wrong name");
        }

        if(name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }

        //if(name == null || name.equals ("")){
        //            throw new IllegalArgumentException("wrong name");
        //        }
        if(price == null){
            throw new IllegalArgumentException("price cannot be null");
        }
        if(price.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalArgumentException("price cannot be less than 0");
        }

        this.name = name;
        this.price = price;
        this.taxPercent = tax;

    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getTaxPercent() {
        return this.taxPercent;
    }

    public BigDecimal getPriceWithTax() {
        BigDecimal priceWithTax = this.price.multiply(this.taxPercent).add(this.price);
        return priceWithTax;
    }
}
