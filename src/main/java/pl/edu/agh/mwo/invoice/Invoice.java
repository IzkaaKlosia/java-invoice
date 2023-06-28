package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new LinkedHashMap<Product, Integer>();

    private Integer invoiceNumber;

    public Invoice() {
        invoiceNumber = getRandomNumber();
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        boolean foundedProduct = false;
        for ( Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product existingProduct = entry.getKey();
            Integer existingQuantity = entry.getValue();

            if (existingProduct.getName() == product.getName()){
                int newQuantity = quantity + existingQuantity;
                products.put(product, newQuantity);
                foundedProduct = true;
            }
        }
        if(!foundedProduct) {
            products.put(product, quantity);
        }
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getRandomNumber() {
        return new Random().nextInt(10000) + 1;
    }

    public int getNumber(){
        return invoiceNumber;
    }

    public String print() {
        StringBuilder sB = new StringBuilder();
        sB.append("Numer Faktury: " + getNumber() +"\n");

        for ( Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            sB.append(product.getName() + " "+ quantity + " " + product.getPrice() + "\n");
        }
        sB.append("Liczba pozycji: " + products.size());

        return sB.toString();
    }
}
