package com.gorder.api.product.service;

import com.gorder.api.product.model.Category;
import com.gorder.api.product.model.Product;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<?> createProduct(Product product);

    ResponseEntity<?> addCategory(Category category);

    ResponseEntity<?> modifyProductByNo(Product product);

    ResponseEntity<?> soldOutProduct(Product product);

    ResponseEntity<?> deleteProductByNo(int productNo);

    ResponseEntity<?> getMenuByAccountSerial(String accountSerial);

    ResponseEntity<?> modifyCategory(Category category);

    ResponseEntity<?> deleteCategoryByNo(int categoryNo);

    ResponseEntity<?> getCategoryByAccountSerial(String accountSerial);
}
