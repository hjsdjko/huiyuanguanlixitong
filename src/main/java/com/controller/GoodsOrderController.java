package com.controller;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.entity.*;
import com.service.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;

import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.view.GoodsOrderView;

import com.utils.PageUtils;
import com.utils.R;

/**
 * 购买订单
 * 后端接口
 * @author
 * @email
 * @date 2021-04-20
*/
@RestController
@Controller
@RequestMapping("/goodsOrder")
public class GoodsOrderController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsOrderController.class);

    @Autowired
    private GoodsOrderService goodsOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service
    @Autowired
    private HuiyuanService huiyuanService;
    // 列表详情的表级联service
    @Autowired
    private GoodsOrderListService goodsOrderListService;
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ShangjiaguanliService shangjiaguanliService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
     
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("用户".equals(role)){
            YonghuEntity yonghuEntity = yonghuService.selectById(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
            params.put("shangjiaTypes",yonghuEntity.getShangjiaTypes());
        }else if("商家管理员".equals(role) ){
            ShangjiaguanliEntity shangjiaguanli = shangjiaguanliService.selectById(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
            params.put("shangjiaTypes",shangjiaguanli.getShangjiaTypes());
        }
        params.put("orderBy","id");
        PageUtils page = goodsOrderService.queryPage(params);

        //字典表数据转换
        List<GoodsOrderView> list =(List<GoodsOrderView>)page.getList();
        for(GoodsOrderView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        GoodsOrderEntity goodsOrder = goodsOrderService.selectById(id);
        if(goodsOrder !=null){
            //entity转view
            GoodsOrderView view = new GoodsOrderView();
            BeanUtils.copyProperties( goodsOrder , view );//把实体数据重构到view中

            //级联表
            HuiyuanEntity huiyuan = huiyuanService.selectById(goodsOrder.getHuiyuanId());
            if(huiyuan != null){
                BeanUtils.copyProperties( huiyuan , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setHuiyuanId(huiyuan.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody GoodsOrderEntity goodsOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,goodsOrder:{}",this.getClass().getName(),goodsOrder.toString());
        goodsOrder.setInsertTime(new Date());
        goodsOrder.setCreateTime(new Date());
        goodsOrderService.insert(goodsOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody GoodsOrderEntity goodsOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,goodsOrder:{}",this.getClass().getName(),goodsOrder.toString());
        goodsOrderService.updateById(goodsOrder);//根据id更新
        return R.ok();
    }


    /**
    * 购买
    */
    @RequestMapping("/outGoodsOrderList")
    public R outGoodsOrderList(@RequestBody  Map<String, Object> params,HttpServletRequest request){
        logger.debug("outGoodsOrderList方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        Date date = new Date();
        //当前表的表级联查询
        String huiyuanId = (String) params.get("huiyuanId"); //会员id
        String goodsOrderName = (String) params.get("goodsOrderName"); // 订单名
        HashMap<String, Integer> map = (HashMap<String, Integer>) params.get("map");// id 和 数量
        if(map == null || map.size() ==0){
            return R.error("添加不能为空");
        }else{
            Set<String> ids = map.keySet(); // 获取添加数据的ids
            List<GoodsEntity> goodsList = goodsService.selectList(new EntityWrapper<GoodsEntity>().in("id", ids));
            if(goodsList == null  || goodsList.size() ==0 || map.size() != goodsList.size() ){
                return R.error("查询不到商品 或者 买的商品和数据库中不一致,请去列表中查找确认");
            }else{
                Double originalMoney = 0.0;
                for(GoodsEntity goods : goodsList){
                    Integer i = goods.getGoodsNumber() - map.get(String.valueOf(goods.getId()));
                    if(i<0){
                        return R.error("购买数量超过库存数量");
                    }
                    goods.setGoodsNumber(i);
                    Integer number = map.get(String.valueOf(goods.getId()));//购买商品数量
                    originalMoney += (number*goods.getDanjia());
                }
                //添加当前列表
                GoodsOrderEntity goodsOrderEntity = new GoodsOrderEntity();//新增订单
                goodsOrderEntity.setGoodsOrderName(goodsOrderName);
                goodsOrderEntity.setCaozuoTable(String.valueOf(request.getSession().getAttribute("tableName")));
                goodsOrderEntity.setCaozuoName(String.valueOf(request.getSession().getAttribute("username")));
                goodsOrderEntity.setHuiyuanId(Integer.valueOf(huiyuanId));
                goodsOrderEntity.setOriginalMoney(originalMoney);
                DecimalFormat df   = new DecimalFormat("######0.00");
                String discountMoney = df.format(originalMoney * 0.95);
                goodsOrderEntity.setDiscountMoney(Double.valueOf(discountMoney));
                goodsOrderEntity.setInsertTime(date);
                goodsOrderEntity.setCreateTime(date);
                goodsOrderService.insert(goodsOrderEntity);

                    //详情表的添加
                List<GoodsOrderListEntity> goodsOrderLists = new ArrayList<>();
                for(String i:ids){
                    GoodsOrderListEntity entity = new GoodsOrderListEntity();
                    entity.setGoodsOrderId(goodsOrderEntity.getId());
                    entity.setGoodsId(Integer.valueOf(i));
                    entity.setGoodsOrderListNumber(map.get(i));
                    entity.setCreateTime(date);
                    goodsOrderLists.add(entity);
                }
                goodsOrderListService.insertBatch(goodsOrderLists);
                goodsService.updateBatchById(goodsList);
                HuiyuanEntity huiyuanEntity = huiyuanService.selectById(huiyuanId);
                huiyuanEntity.setJifen(huiyuanEntity.getJifen()+Double.valueOf(discountMoney));
                huiyuanService.updateById(huiyuanEntity);
                return R.ok();
            }
        }
    }

//    /**
//    * 入入入入入入入入入入入入入入入
//    */
//    @RequestMapping("/inGoodsOrderList")
//    public R inGoodsOrderList(@RequestBody  Map<String, Object> params,HttpServletRequest request){
//        logger.debug("outGoodsOrderList方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
//
//
//        Date date = new Date();//当前时间
//        Integer userId = (Integer) request.getSession().getAttribute("userId");//当前登录人的id
//        String tableName = (String) request.getSession().getAttribute("tableName");//当前登录人的表名
//        YonghuEntity yonghuEntity = yonghuService.selectById(userId);//当前登录人的信息
//            //当前表的表级联查询
//        String huiyuanId = (String) params.get("huiyuanId"); //会员id
//        String goodsOrderName = (String) params.get("goodsOrderName"); // 订单名
//        HashMap<String, Integer> map = (HashMap<String, Integer>) params.get("map");// id 和 数量
//        if(map == null || map.size() ==0){
//            return R.error("添加不能为空");
//        }else{
//            Set<String> ids = map.keySet(); // 获取添加数据的ids
//            List<GoodsEntity> goodsList = goodsService.selectList(new EntityWrapper<GoodsEntity>().in("id", ids));
//            if(goodsList == null  || goodsList.size() ==0 || map.size() != goodsList.size() ){
//                return R.error("查询物资为空 或者 采购的物资和数据库中不一致,请去列表中查找确认");
//            }else{
//                for(GoodsEntity goods : goodsList){
//                    goods.setGoodsNumber(goods.getGoodsNumber()+map.get(String.valueOf(goods.getId())));
//                }
//            }
//
//                //添加当前列表
//            GoodsOrderEntity goodsOrderEntity = new GoodsOrderEntity();//新增订单
//            goodsOrderEntity.setGoodsOrderName(goodsOrderName);
//            String role = String.valueOf(request.getSession().getAttribute("role"));
//            if(StringUtil.isNotEmpty(role) && "管理员".equals(role)){
//                goodsOrderEntity.setCaozuoName(String.valueOf(request.getSession().getAttribute("username")));
//            }else{
//                goodsOrderEntity.setCaozuoName(yonghuEntity.getYonghuName());
//            }
//            goodsOrderEntity.setCaozuoTable(tableName);
//            goodsOrderEntity.setHuiyuanId(Integer.valueOf(huiyuanId));
//            goodsOrderEntity.setOriginalMoney();
//            goodsOrderEntity.setDiscountMoney();
//            goodsOrderEntity.setInsertTime(date);
//            goodsOrderEntity.setCreateTime(date);
//            goodsOrderService.insert(goodsOrderEntity);
//
//                //详情表的添加
//            List<GoodsOrderListEntity> goodsOrderLists = new ArrayList<>();
//            for(String i:ids){
//                GoodsOrderListEntity entity = new GoodsOrderListEntity();
//                entity.setGoodsOrderId(goodsOrderEntity.getId());
//                entity.setGoodsId(Integer.valueOf(i));
//                entity.setOrderNumber(map.get(i));
//                entity.setCreateTime(date);
//                goodsOrderLists.add(entity);
//            }
//            goodsOrderListService.insertBatch(goodsOrderLists);
//            goodsService.updateBatchById(goodsList);
//            return R.ok();
//        }
//    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        goodsOrderService.deleteBatchIds(Arrays.asList(ids));
        EntityWrapper<GoodsOrderListEntity> wrapper = new EntityWrapper<>();
        wrapper.in("goods_order_id",ids);
        goodsOrderListService.delete(wrapper);
        return R.ok();
    }






}

