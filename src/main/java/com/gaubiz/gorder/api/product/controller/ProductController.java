package com.gaubiz.gorder.api.product.controller;

import com.gaubiz.gorder.api.product.model.Category;
import com.gaubiz.gorder.api.product.model.Product;
import com.gaubiz.gorder.api.product.service.ProductService;
import com.gaubiz.gorder.api.validation.Groups;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@Api(tags = {"상품 API"})
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
    @ApiOperation(value = "상품 추가")
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
    @ApiOperation(value = "상품 수정")
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
    @ApiOperation(value = "상품 품절 처리")
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
    @ApiOperation(value = "상품 삭제")
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
//    @ApiOperation(value = "메뉴 조회")
//    @GetMapping
//    public ResponseEntity<?> getMenu(
//          @Min(value = 0, message = "{message.009}")
//          @RequestParam int categoryNo
//    ){
//        return productService.getMenuByCategoryNo(categoryNo);
//    }

    @GetMapping
    public ResponseEntity<?> getMenu(
            @RequestParam String accountSerial
    ){
        return productService.getMenuByAccountSerial(accountSerial);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getCategory(
            @RequestParam String accountSerial
    ){
        return productService.getCategoryByAccountSerial(accountSerial);
    }

    /*
        param :
        String categoryName,
        String accountSerial
     */
    @ApiOperation(value = "카테고리 추가")
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
    @ApiOperation(value = "카테고리 수정")
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
    @ApiOperation(value = "카테고리 삭제")
    @DeleteMapping("/delete/category/{categoryNo}")
    public ResponseEntity<?> deleteCategoryByNo(
            @PathVariable int categoryNo
    ){
        return productService.deleteCategoryByNo(categoryNo);
    }




}
