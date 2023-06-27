package com.gaubiz.gorder.api.product.service.logic;

import com.gaubiz.gorder.api.product.model.Category;
import com.gaubiz.gorder.api.product.model.Product;
import com.gaubiz.gorder.api.product.repository.ProductRepository;
import com.gaubiz.gorder.api.product.service.ProductService;
import com.gaubiz.gorder.msg.HttpStatusMsg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceLogic implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceLogic(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<?> createProduct(Product product) {
        int result = productRepository.createProduct(product);
        return null;
    }

    @Override
    public ResponseEntity<?> addCategory(Category category) {
        int result = productRepository.addCategory(category);
        if (result > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(HttpStatusMsg.HTTP_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> modifyProductByNo(Product product) {
        int result = productRepository.modifyProduct(product);
        if (result > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(HttpStatusMsg.HTTP_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> soldOutProduct(Product product) {
        int result = productRepository.soldOutProductByNo(product);
        if (result > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(HttpStatusMsg.HTTP_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteProductByNo(int productNo) {
        int result = productRepository.deleteProductNo(productNo);
        if (result > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
    }

    @Override
    public ResponseEntity<?> getMenuByCategoryNo(int categoryNo) {
        List<Product> productList = productRepository.getMenuByCategoryNo(categoryNo);
        if (productList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatusMsg.HTTP_NOT_FOUND);
        } else {
            return ResponseEntity.ok(productList);
        }
    }

    @Override
    public ResponseEntity<?> modifyCategory(Category category) {
        int result = productRepository.modifyCategory(category);
        if (result > 0) {
            return ResponseEntity.ok().body(HttpStatusMsg.HTTP_OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(HttpStatusMsg.HTTP_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteCategoryByNo(int categoryNo) {
        int result = productRepository.deleteCategoryByNo(categoryNo);
        if(result > 0){
            return ResponseEntity.ok().body(HttpStatusMsg.HTTP_OK);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(HttpStatusMsg.HTTP_SERVER_ERROR);
        }
    }
}
