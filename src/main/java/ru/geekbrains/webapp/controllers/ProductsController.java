package ru.geekbrains.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.webapp.model.Product;
import ru.geekbrains.webapp.repository.ProductRepository;
import ru.geekbrains.webapp.service.ProductService;

@Controller
public class ProductsController {

    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(value = "/show/{id}")
    public String showProductByID(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.findById(id));
        return "product";
    }

    @GetMapping(value = "/create")
    public String showAddedForm() {
        return "create_product";
    }

    @PostMapping(value = "/create")
    public String saveProduct(@RequestParam Long id, @RequestParam String title, @RequestParam int cost) {
        productService.save(new Product(id,title,cost));
        return "redirect:/";
    }
}
