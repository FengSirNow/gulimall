package com.atguigu.gulimall.product.service.impl;

import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;
import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }


    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> entities = categoryDao.selectList(null);
        List<CategoryEntity> collect = entities.stream().filter(categoryEntity -> categoryEntity.getParentCid().longValue() == 0)
                .map(menu -> {
                    menu.setChildren(getchildrens(menu, entities));
                    return menu;
                })
                .sorted(Comparator.comparing(CategoryEntity::getSort, Comparator.nullsLast(Integer::compareTo))).collect(Collectors.toList());
        return collect;
    }

    private List<CategoryEntity> getchildrens(CategoryEntity menu, List<CategoryEntity> entities) {
        List<CategoryEntity> collect = entities.stream().filter(categoryEntity ->
                        categoryEntity.getParentCid().equals(menu.getCatId())
                ).map(a -> {
                    a.setChildren(getchildrens(a, entities));
                    return a;
                })
                .sorted(Comparator.comparing(CategoryEntity::getSort, Comparator.nullsLast(Integer::compareTo))).collect(Collectors.toList());
        return collect;
    }

}