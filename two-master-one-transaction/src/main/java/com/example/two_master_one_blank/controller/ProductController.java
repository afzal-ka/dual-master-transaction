package com.example.two_master_one_blank.controller;

import com.example.two_master_one_blank.entity.ProductRequest;
import com.example.two_master_one_blank.entity.Products;
import com.example.two_master_one_blank.repo.CategoryRepo;
import com.example.two_master_one_blank.repo.SupplierRepo;
import com.example.two_master_one_blank.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private SupplierRepo supplierRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping("/products")
    public String getAllProducts(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }
    
    @GetMapping("/products/new")
    public String addForm(Model model){
        model.addAttribute("productRequest", new ProductRequest());
        model.addAttribute("suppliers", supplierRepo.findAll());
        model.addAttribute("categories",categoryRepo.findAll());
        model.addAttribute("isUpdate",false);
        return "product-form";
    }

    @PostMapping("/products/new")
    public String addProduct(@ModelAttribute ProductRequest productRequest){
        productService.createProducts(productRequest);
        return "redirect:/products";
    }

    @GetMapping("/products/update/{id}")
    public String updateForm(@PathVariable long id, Model model){

        Products existingProduct = productService.getProductById(id);
        ProductRequest productRequest = new ProductRequest();
        productRequest.setId(existingProduct.getId());
        productRequest.setName(existingProduct.getName());
        productRequest.setPrice(existingProduct.getPrice());

        // ✅ Safe null handling
        if (existingProduct.getSupplier() != null) {
            productRequest.setSupplierId(existingProduct.getSupplier().getId());
        }
        if (existingProduct.getCategory() != null) {
            productRequest.setCategoryId(existingProduct.getCategory().getId());
        }

        model.addAttribute("productRequest", productRequest);
        model.addAttribute("suppliers", supplierRepo.findAll());
        model.addAttribute("categories",categoryRepo.findAll());
        model.addAttribute("isUpdate",true);
        return "product-form";
    }

    @PostMapping("/products/update/{id}")
    public String updateProduct(@ModelAttribute ProductRequest productRequest, @PathVariable long id){
        productService.updateProducts(id,productRequest);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }

}
