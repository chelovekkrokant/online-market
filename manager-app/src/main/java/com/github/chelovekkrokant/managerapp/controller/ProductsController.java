package com.github.chelovekkrokant.managerapp.controller;

import com.github.chelovekkrokant.managerapp.controller.payload.NewProductPayload;
import com.github.chelovekkrokant.managerapp.entity.Product;
import com.github.chelovekkrokant.managerapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalogue/products")
public class ProductsController {

    private final ProductService productService;

    @GetMapping( "list")
    public String getProductsList(Model model) {
        model.addAttribute("products", this.productService.findAllProducts());
        return "catalogue/products/list";
    }

    @GetMapping("create")
    public String getNewProductsPage(){
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProductPage(NewProductPayload newProductPayload) {
        Product newProduct = this.productService.createProduct(newProductPayload.title(), newProductPayload.details());
        return "redirect:/catalogue/products/%d".formatted(newProduct.getId());
    }

}
