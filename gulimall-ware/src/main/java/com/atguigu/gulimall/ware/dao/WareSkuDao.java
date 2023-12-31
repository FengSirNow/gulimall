package com.atguigu.gulimall.ware.dao;

import com.atguigu.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author feng
 * @email feng@gmail.com
 * @date 2022-08-05 12:19:19
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
