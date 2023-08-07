package com.gaubiz.gorder.api.product.repository;

import com.gaubiz.gorder.api.product.model.Category;
import com.gaubiz.gorder.api.product.model.Product;

import java.util.List;

public interface ProductRepository {
    int createProduct(Product product);

    int addCategory(Category category);

    int modifyProduct(Product product);

    int soldOutProductByNo(Product product);

    int deleteProductNo(int productNo);

    List<Product> getMenuByAccountSerial(String accountSerial);

    int modifyCategory(Category category);

    int deleteCategoryByNo(int categoryNo);

    List<Category> getCategoryByAccountSerial(String accountSerial);
}
