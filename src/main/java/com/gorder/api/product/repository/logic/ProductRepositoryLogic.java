package com.gorder.api.product.repository.logic;

import com.gorder.api.product.model.Category;
import com.gorder.api.product.model.Product;
import com.gorder.api.product.repository.ProductRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryLogic implements ProductRepository {
    private final SqlSession session;

    public ProductRepositoryLogic(SqlSession session) {
        this.session = session;
    }

    @Override
    public int createProduct(Product product) {
        return session.insert("ProductMapper.insertProduct",product);
    }

    @Override
    public int addCategory(Category category) {
        return session.insert("ProductMapper.insertCategory",category);
    }

    @Override
    public int modifyProduct(Product product) {
        return session.update("ProductMapper.updateProduct",product);
    }

    @Override
    public int soldOutProductByNo(Product product) {
        return session.update("ProductMapper.updateSoldOutProduct",product);
    }

    @Override
    public int deleteProductNo(int productNo) {
        return session.delete("ProductMapper.deleteProductByNo",productNo);
    }

    @Override
    public List<Product> getMenuByAccountSerial(String accountSerial) {
        return session.selectList("ProductMapper.selectMenuByAccountSerial",accountSerial);
    }

    @Override
    public int modifyCategory(Category category) {
        return session.update("ProductMapper.updateCategory",category);
    }

    @Override
    public int deleteCategoryByNo(int categoryNo) {
        return session.delete("ProductMapper.deleteCategoryByNo",categoryNo);
    }

    @Override
    public List<Category> getCategoryByAccountSerial(String accountSerial) {
        return session.selectList("ProductMapper.selectCategoryByAccountSerial",accountSerial);
    }
}
