package com.controller;


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

import com.entity.view.JifengoodsOrderView;

import com.utils.PageUtils;
import com.utils.R;

/**
 * 积分商品
 * 后端接口
 * @author
 * @email
 * @date 2021-04-21
*/
@RestController
@Controller
@RequestMapping("/jifengoodsOrder")
public class JifengoodsOrderController {
    private static final Logger logger = LoggerFactory.getLogger(JifengoodsOrderController.class);

    @Autowired
    private JifengoodsOrderService jifengoodsOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service
    @Autowired
    private HuiyuanService huiyuanService;
    @Autowired
    private JifengoodsService jifengoodsService;
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private ShangjiaguanliService shangjiaguanliService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
     
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isNotEmpty(role) && "用户".equals(role)){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        if("用户".equals(role)){
            YonghuEntity yonghuEntity = yonghuService.selectById(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
            params.put("shangjiaTypes",yonghuEntity.getShangjiaTypes());
        }else if("商家管理员".equals(role) ){
            ShangjiaguanliEntity shangjiaguanli = shangjiaguanliService.selectById(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
            params.put("shangjiaTypes",shangjiaguanli.getShangjiaTypes());
        }else if("会员".equals(role) ){
            HuiyuanEntity huiyuan = huiyuanService.selectById(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
            params.put("shangjiaTypes",huiyuan.getShangjiaTypes());
        }
        params.put("orderBy","id");
        PageUtils page = jifengoodsOrderService.queryPage(params);

        //字典表数据转换
        List<JifengoodsOrderView> list =(List<JifengoodsOrderView>)page.getList();
        for(JifengoodsOrderView c:list){
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
        JifengoodsOrderEntity jifengoodsOrder = jifengoodsOrderService.selectById(id);
        if(jifengoodsOrder !=null){
            //entity转view
            JifengoodsOrderView view = new JifengoodsOrderView();
            BeanUtils.copyProperties( jifengoodsOrder , view );//把实体数据重构到view中

            //级联表
            HuiyuanEntity huiyuan = huiyuanService.selectById(jifengoodsOrder.getHuiyuanId());
            if(huiyuan != null){
                BeanUtils.copyProperties( huiyuan , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setHuiyuanId(huiyuan.getId());
            }
            //级联表
            JifengoodsEntity jifengoods = jifengoodsService.selectById(jifengoodsOrder.getJifengoodsId());
            if(jifengoods != null){
                BeanUtils.copyProperties( jifengoods , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setJifengoodsId(jifengoods.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

//    /**
//    * 后端保存
//    */
//    @RequestMapping("/save")
//    public R save(@RequestBody JifengoodsOrderEntity jifengoodsOrder, HttpServletRequest request){
//        logger.debug("save方法:,,Controller:{},,jifengoodsOrder:{}",this.getClass().getName(),jifengoodsOrder.toString());
//        jifengoodsOrder.setInsertTime(new Date());
//        jifengoodsOrder.setCreateTime(new Date());
//        jifengoodsOrderService.insert(jifengoodsOrder);
//        return R.ok();
//    }
//
//    /**
//    * 后端修改
//    */
//    @RequestMapping("/update")
//    public R update(@RequestBody JifengoodsOrderEntity jifengoodsOrder, HttpServletRequest request){
//        logger.debug("update方法:,,Controller:{},,jifengoodsOrder:{}",this.getClass().getName(),jifengoodsOrder.toString());
//        jifengoodsOrderService.updateById(jifengoodsOrder);//根据id更新
//        return R.ok();
//    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        jifengoodsOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }






}

