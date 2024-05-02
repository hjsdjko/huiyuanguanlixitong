package com.dao;

import com.entity.JifengoodsEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JifengoodsView;

/**
 * 积分商品 Dao 接口
 *
 * @author 
 * @since 2021-04-21
 */
public interface JifengoodsDao extends BaseMapper<JifengoodsEntity> {

   List<JifengoodsView> selectListView(Pagination page, @Param("params") Map<String, Object> params);

}
