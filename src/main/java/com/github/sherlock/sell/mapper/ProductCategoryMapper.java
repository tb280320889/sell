//package com.github.sherlock.sell.mapper;
//
//import java.util.List;
//import com.github.sherlock.sell.model.ProductCategory;
//import com.github.sherlock.sell.model.ProductCategoryExample;
//import org.apache.ibatis.annotations.Param;
//
//public interface ProductCategoryMapper {
//    long countByExample(ProductCategoryExample example);
//
//    int deleteByExample(ProductCategoryExample example);
//
//    int deleteByPrimaryKey(Integer categoryId);
//
//    int insert(ProductCategory record);
//
//    int insertSelective(ProductCategory record);
//
//    List<ProductCategory> selectByExample(ProductCategoryExample example);
//
//    ProductCategory selectByPrimaryKey(Integer categoryId);
//
//    int updateByExampleSelective(@Param("record") ProductCategory record, @Param("example") ProductCategoryExample example);
//
//    int updateByExample(@Param("record") ProductCategory record, @Param("example") ProductCategoryExample example);
//
//    int updateByPrimaryKeySelective(ProductCategory record);
//
//    int updateByPrimaryKey(ProductCategory record);
//}
