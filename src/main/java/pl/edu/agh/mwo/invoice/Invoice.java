package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    //private Collection<Product> products = new ArrayList<>(); - jedna z mozliwosci ale srednio sie tu sprawdzi

    private Map<Product,Integer> products = new HashMap<>();


    public void addProduct(Product product) {
        // TODO: implement
    }

    public void addProduct(Product product, Integer quantity) {
        // TODO: implement
        this.products.put(product, quantity);
    }

    public BigDecimal getNetPrice() {
        return BigDecimal.ZERO;
    }

    public BigDecimal getTax() {
        return BigDecimal.ZERO;
    }

    public BigDecimal getTotal() {
        return null;
    }
}
