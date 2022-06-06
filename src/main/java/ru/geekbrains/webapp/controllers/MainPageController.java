package ru.geekbrains.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.webapp.service.ProductService;

@Controller
public class MainPageController {

    private ProductService productService;

    @Autowired
    public MainPageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showMainPage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";
    }

    @GetMapping(value = ("/add/{id}"))
    public String addCost (@PathVariable Long id){
        productService.addPrise(id-1);
        return "redirect:/";
    }

    @GetMapping(value = ("/sub/{id}"))
    public String subCost (@PathVariable Long id){
        productService.subPrice(id-1);
        return "redirect:/";
    }


}
