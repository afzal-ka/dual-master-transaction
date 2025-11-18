package com.example.two_master_one_blank.service;

import com.example.two_master_one_blank.entity.Category;
import com.example.two_master_one_blank.entity.ProductRequest;
import com.example.two_master_one_blank.entity.Products;
import com.example.two_master_one_blank.entity.Supplier;
import com.example.two_master_one_blank.repo.CategoryRepo;
import com.example.two_master_one_blank.repo.ProductRepo;
import com.example.two_master_one_blank.repo.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SupplierRepo supplierRepo;
    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public List<Products> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Products getProductById(long id) {
        return productRepo.findById(id).orElseThrow(()-> new RuntimeException("Id not found"));
    }

    @Override
    public Products createProducts(ProductRequest productRequest) {
        Products products = new Products();
        products.setName(productRequest.getName());
        products.setPrice(productRequest.getPrice());

        Supplier supplier = supplierRepo.findById(productRequest.getSupplierId())
                .orElseThrow(()->new RuntimeException("Id not found"));
        Category category = categoryRepo.findById(productRequest.getCategoryId())
                .orElseThrow(()->new RuntimeException("Id not found"));
        products.setSupplier(supplier);
        products.setCategory(category);

        return productRepo.save(products);
    }

    @Override
    public Products updateProducts(long id, ProductRequest productRequest) {
        
        Products existingProduct = productRepo.findById(id).orElseThrow(()->new RuntimeException("Id not found"));
        existingProduct.setName(productRequest.getName());
        existingProduct.setPrice(productRequest.getPrice());

        Supplier supplier = supplierRepo.findById(productRequest.getSupplierId()).orElseThrow(()->new RuntimeException("Id not found"));
        Category category = categoryRepo.findById(productRequest.getCategoryId()).orElseThrow(()->new RuntimeException("Id not found"));
        existingProduct.setSupplier(supplier);
        existingProduct.setCategory(category);
        return productRepo.save(existingProduct);
    }

    @Override
    public void deleteProduct(long id) {
        productRepo.deleteById(id);
    }
}
