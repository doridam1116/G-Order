package com.gaubiz.gorder.api.product.service.logic;

import com.gaubiz.gorder.api.product.model.Category;
import com.gaubiz.gorder.api.product.model.Product;
import com.gaubiz.gorder.api.product.repository.ProductRepository;
import com.gaubiz.gorder.api.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import static com.gaubiz.gorder.config.PropertyConfig.getMessageSource;

@Service
public class ProductServiceLogic implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceLogic(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<?> createProduct(Product product) {
        int result = productRepository.createProduct(product);
        return returnByResult(result);
    }

    @Override
    public ResponseEntity<?> addCategory(Category category) {
        int result = productRepository.addCategory(category);
        return returnByResult(result);
    }

    @Override
    public ResponseEntity<?> modifyProductByNo(Product product) {
        int result = productRepository.modifyProduct(product);
        return returnByResult(result);
    }

    @Override
    public ResponseEntity<?> soldOutProduct(Product product) {
        int result = productRepository.soldOutProductByNo(product);
        return returnByResult(result);
    }

    @Override
    public ResponseEntity<?> deleteProductByNo(int productNo) {
        int result = productRepository.deleteProductNo(productNo);
        return returnByResult(result);
    }

    @Override
    public ResponseEntity<?> getMenuByCategoryNo(int categoryNo) {
        List<Product> productList = productRepository.getMenuByCategoryNo(categoryNo);
        if (productList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessageSource().getMessage("HTTP_NOT_FOUND",null,Locale.getDefault()));
        } else {
            return ResponseEntity.ok(productList);
        }
    }

    @Override
    public ResponseEntity<?> modifyCategory(Category category) {
        int result = productRepository.modifyCategory(category);
        return returnByResult(result);
    }

    @Override
    public ResponseEntity<?> deleteCategoryByNo(int categoryNo) {
        int result = productRepository.deleteCategoryByNo(categoryNo);
        return returnByResult(result);
    }

    public ResponseEntity<?> returnByResult(int result){
        if(result > 0){
            return ResponseEntity.ok().body(getMessageSource().getMessage("HTTP_OK",null,Locale.getDefault()));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR",null,Locale.getDefault()));
        }
    }
}
