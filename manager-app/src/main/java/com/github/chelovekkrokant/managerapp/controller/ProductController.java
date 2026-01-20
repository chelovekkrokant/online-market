package com.github.chelovekkrokant.managerapp.controller;

import com.github.chelovekkrokant.managerapp.client.ProductRestClient;
import com.github.chelovekkrokant.managerapp.entity.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import com.github.chelovekkrokant.managerapp.controller.payload.UpdateProductPayload;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("catalogue/products/{productId:\\d+}")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRestClient productRestClient;

    private final MessageSource messageSource;

    @ModelAttribute("product")
    public Product product(@PathVariable Integer productId) {
        return this.productRestClient.findProduct(productId).orElseThrow(() -> new NoSuchElementException("catalogue.errors.product.not_found"));
    }

    @GetMapping
    public String getProductPage() {
        return "catalogue/products/product";
    }

    @GetMapping("edit")
    public String editProductPage() {
        return "catalogue/products/edit";
    }

    @PostMapping("edit")
    public String updateProduct(@ModelAttribute("product") Product product, @Valid UpdateProductPayload payload, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage).toList());
            return "catalogue/products/edit";
        }

        this.productRestClient.updateProduct(product.id(), payload.title(), payload.details());
        return "redirect:/catalogue/products/%d".formatted(product.id());
    }

    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        this.productRestClient.deleteProduct(product.id());
        return "redirect:/catalogue/products/list";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception, Model model, Locale locale) {
        model.addAttribute("error", this.messageSource.getMessage(exception.getMessage(), new Object[0], locale));
        return "errors/404";
    }

}