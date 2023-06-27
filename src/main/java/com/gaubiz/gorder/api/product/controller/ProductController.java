package com.gaubiz.gorder.api.product.controller;

import com.gaubiz.gorder.api.product.model.Category;
import com.gaubiz.gorder.api.product.model.Product;
import com.gaubiz.gorder.api.product.service.ProductService;
import com.gaubiz.gorder.security.jwt.JwtProperties;
import com.gaubiz.gorder.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final JwtProvider jwtProvider;

    public ProductController(ProductService productService, JwtProvider jwtProvider) {
        this.productService = productService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/add/product")
    public ResponseEntity<?> createProduct(
            @Validated @RequestBody Product product
    ){
        return productService.createProduct(product);
    }

    @PatchMapping("/modify")
    public ResponseEntity<?> modifyProduct(
            @RequestBody Product product
    ){
        return productService.modifyProductByNo(product);
    }

    @PatchMapping("/sold-out")
    public ResponseEntity<?> soldOutProduct(
            @RequestBody Product product
    ){
        return productService.soldOutProduct(product);
    }

    @DeleteMapping("/delete/{productNo}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable int productNo
    ){
        return productService.deleteProductByNo(productNo);
    }

    @GetMapping
    public ResponseEntity<?> getMenu(
          @Min(value = 0) @RequestParam int categoryNo
    ){
        return productService.getMenuByCategoryNo(categoryNo);
    }



    @PostMapping("/add/category")
    public ResponseEntity<?> addCategory(
            @Validated @RequestBody Category category
    ){
        return productService.addCategory(category);
    }

    @PatchMapping("/modify/category")
    public ResponseEntity<?> modifyCategory(
            @RequestBody Category category
    ){
        return productService.modifyCategory(category);
    }

    @DeleteMapping("/delete/category/{categoryNo}")
    public ResponseEntity<?> deleteCategoryByNo(
            @PathVariable int categoryNo
    ){
        return productService.deleteCategoryByNo(categoryNo);
    }

}
