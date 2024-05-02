package com.dao;

import com.entity.GoodsOrderListEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.GoodsOrderListView;

/**
 * 购买订单详情 Dao 接口
 *
 * @author 
 * @since 2021-04-20
 */
public interface GoodsOrderListDao extends BaseMapper<GoodsOrderListEntity> {

   List<GoodsOrderListView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
