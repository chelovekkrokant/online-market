package com.github.chelovekkrokant.managerapp.controller;

import com.github.chelovekkrokant.managerapp.client.BadRequestException;
import com.github.chelovekkrokant.managerapp.client.ProductRestClient;
import com.github.chelovekkrokant.managerapp.controller.payload.NewProductPayload;
import com.github.chelovekkrokant.managerapp.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalogue/products")
public class ProductsController {

    private final ProductRestClient productRestClient;

    @GetMapping( "list")
    public String getProductsList(Model model, @RequestParam(name = "filter", required = false) String filter) {
        model.addAttribute("products", this.productRestClient.findAllProducts(filter));
        model.addAttribute("filter", filter);
        return "catalogue/products/list";
    }

    @GetMapping("create")
    public String getNewProductsPage(){
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProductPage(NewProductPayload payload,  Model model) {
        try {
            Product newProduct = this.productRestClient.createProduct(payload.title(), payload.details());
            return "redirect:/catalogue/products/%d".formatted(newProduct.id());
        } catch (BadRequestException e) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", e.getErrors());
            return "catalogue/products/edit ";
        }
    }

}
