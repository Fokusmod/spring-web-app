package ru.geekbrains.webapp.repository;

import org.springframework.stereotype.Repository;
import ru.geekbrains.webapp.model.Product;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Predicate;

@Repository
public class ProductRepository {
    private List<Product> catalog;


    @PostConstruct
    public void init() {
        this.catalog = new ArrayList<>(Arrays.asList(
                new Product(1L, "Apple", 100),
                new Product(2L, "Banana", 70),
                new Product(3L, "Orange", 120),
                new Product(4L, "Papaya", 110)
        ));
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(catalog);
    }

    public Product findProductById(Long id) {
        return catalog.stream().filter(product -> product.getId() == id).findFirst().get();
    }

    public void addProduct(Product product) {
        catalog.add(product);
    }

    public void addCost(int id) {
        int count = 1;
        int beginValue = catalog.get(id).getCost();
        beginValue += count;
        catalog.get(id).setCost(beginValue);
    }

    public void subCost(int id) {
        int count = 1;
        int beginValue = catalog.get(id).getCost();
        beginValue -= count;
        catalog.get(id).setCost(beginValue);
    }


}
