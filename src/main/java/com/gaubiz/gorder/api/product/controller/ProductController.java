package com.gaubiz.gorder.api.product.controller;

import com.gaubiz.gorder.api.product.model.Category;
import com.gaubiz.gorder.api.product.model.Product;
import com.gaubiz.gorder.api.product.service.ProductService;
import com.gaubiz.gorder.api.validation.Groups;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    /*
        param :
        String productName,
        String productDetail,
        int productPrice,
        int categoryNo
     */
    @PostMapping("/add")
    public ResponseEntity<?> createProduct(
            @Validated(Groups.addProductGroup.class)
            @RequestBody Product product
    ){
        return productService.createProduct(product);
    }


    /*
        param :
        int productNo,
        String productName,
        String productDetail,
        int productPrice,
        int categoryNo
     */
    @PatchMapping("/modify")
    public ResponseEntity<?> modifyProduct(
           @Validated(Groups.productModifyGroup.class)
           @RequestBody Product product
    ){
        return productService.modifyProductByNo(product);
    }


    /*
        param :
        int productNo,
        int productActive
     */
    @PatchMapping("/sold-out")
    public ResponseEntity<?> soldOutProduct(
           @Validated(Groups.soldOutGroup.class)
           @RequestBody Product product
    ){
        return productService.soldOutProduct(product);
    }


    /*
        param :
        int productNo
     */
    @DeleteMapping("/delete/{productNo}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable int productNo
    ){
        return productService.deleteProductByNo(productNo);
    }


    /*
        param :
        int categoryNo
     */
    @GetMapping
    public ResponseEntity<?> getMenu(
          @Min(value = 0, message = "{message.009}")
          @RequestParam int categoryNo
    ){
        return productService.getMenuByCategoryNo(categoryNo);
    }


    /*
        param :
        String categoryName,
        String accountSerial
     */
    @PostMapping("/add/category")
    public ResponseEntity<?> addCategory(
            @Validated(Groups.addCategoryGroup.class)
            @RequestBody Category category
    ){
        return productService.addCategory(category);
    }


    /*
        param :
        int productNo,
        String productName,
        String productDetail,
        int productPrice,
        int categoryNo
     */
    @PatchMapping("/modify/category")
    public ResponseEntity<?> modifyCategory(
            @Validated(Groups.modifyCategoryGroup.class)
            @RequestBody Category category
    ){
        return productService.modifyCategory(category);
    }


    /*
        param :
        int categoryNo
     */
    @DeleteMapping("/delete/category/{categoryNo}")
    public ResponseEntity<?> deleteCategoryByNo(
            @PathVariable int categoryNo
    ){
        return productService.deleteCategoryByNo(categoryNo);
    }

}
