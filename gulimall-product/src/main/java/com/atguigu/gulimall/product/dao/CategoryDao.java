package com.atguigu.gulimall.product.dao;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author feng
 * @email feng@gmail.com
 * @date 2022-08-04 18:16:59
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
