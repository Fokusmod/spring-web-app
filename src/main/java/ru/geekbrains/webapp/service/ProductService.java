package ru.geekbrains.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.webapp.model.Product;
import ru.geekbrains.webapp.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findProductById(id);
    }

    public void save(Product product) {
        repository.addProduct(product);
    }

    public void addPrise (Long id) {
        repository.addCost(Math.toIntExact(id));
    }

    public void subPrice (Long id) {
        repository.subCost(Math.toIntExact(id));
    }

}
