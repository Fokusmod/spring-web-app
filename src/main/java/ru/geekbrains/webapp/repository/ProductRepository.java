package ru.geekbrains.webapp.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.geekbrains.webapp.model.Product;
import ru.geekbrains.webapp.repository.preparation.PrepareDataApp;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ProductRepository {
    private List<Product> catalog;
    private ProductDAO dao;


    @PostConstruct
    public void init() {
        catalog = dao.findProduct();
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(catalog);
    }


    public Product findProductById(Long id) {
        return catalog.stream().filter(product -> product.getId() == id).findFirst().get();
    }

    public void addProduct(Product product) {
        dao.addProduct(product);
        catalog = dao.findProduct();
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

    public ProductDAO getDao() {
        return dao;
    }

    @Autowired
    public void setDao(ProductDAO dao) {
        this.dao = dao;
    }
}
