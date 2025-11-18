package com.example.two_master_one_blank.service;

import com.example.two_master_one_blank.entity.ProductRequest;
import com.example.two_master_one_blank.entity.Products;

import java.util.List;

public interface ProductService {

    List<Products> getAllProducts();
    Products getProductById(long id);
    Products createProducts(ProductRequest productRequest);
    Products updateProducts(long id,ProductRequest productRequest);
    void deleteProduct(long id);
}
