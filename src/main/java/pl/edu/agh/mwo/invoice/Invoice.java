package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    //private Collection<Product> products = new ArrayList<>(); - jedna z mozliwosci ale srednio sie tu sprawdzi

    private Map<Product,Integer> products = new HashMap<>();


    public void addProduct(Product product) {
        if(product == null) throw new IllegalArgumentException();
        this.products.put(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if(product == null || quantity <=0) throw new IllegalArgumentException();
        this.products.put(product, quantity);
    }

    public BigDecimal getNetPrice() {
        BigDecimal netPrices = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> e : this.products.entrySet()) {
            Product p = e.getKey();
            BigDecimal price = p.getPrice().multiply(BigDecimal.valueOf(e.getValue()));
            netPrices= netPrices.add(price);
        }
        return netPrices;
    }

    public BigDecimal getTax() {
        BigDecimal taxes = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> e : this.products.entrySet()) {
            Product p = e.getKey();
            BigDecimal tax = p.getPriceWithTax().subtract(p.getPrice());
            taxes= taxes.add(tax);
        }
        return taxes;
    }

    public BigDecimal getTotal() {
        BigDecimal prices = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> e : this.products.entrySet()) {
            Product p = e.getKey();
            BigDecimal price = p.getPriceWithTax().multiply(BigDecimal.valueOf(e.getValue()));
            prices= prices.add(price);
        }
        return prices;
    }
}
