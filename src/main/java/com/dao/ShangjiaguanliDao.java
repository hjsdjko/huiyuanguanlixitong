package com.dao;

import com.entity.ShangjiaguanliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ShangjiaguanliView;

/**
 * 商家管理员 Dao 接口
 *
 * @author 
 * @since 2021-04-20
 */
public interface ShangjiaguanliDao extends BaseMapper<ShangjiaguanliEntity> {

   List<ShangjiaguanliView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
