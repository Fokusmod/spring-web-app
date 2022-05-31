package ru.geekbrains.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.webapp.model.Product;
import ru.geekbrains.webapp.service.ProductService;

@Controller
public class MainPageController {

    ProductService productService;

    @Autowired
    public MainPageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showMainPage(Model model) {
        model.addAttribute("products",productService.findAll());
        return "index";
    }
}
