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

import com.entity.view.JifengoodsView;

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
@RequestMapping("/jifengoods")
public class JifengoodsController {
    private static final Logger logger = LoggerFactory.getLogger(JifengoodsController.class);

    @Autowired
    private JifengoodsService jifengoodsService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private ShangjiaguanliService shangjiaguanliService;
    @Autowired
    private HuiyuanService huiyuanService;
    @Autowired
    private JifengoodsOrderService jifengoodsOrderService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
     
        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isNotEmpty(role) && "用户".equals(role)){
//            params.put("yonghuId",request.getSession().getAttribute("userId"));
//        }
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
        PageUtils page = jifengoodsService.queryPage(params);

        //字典表数据转换
        List<JifengoodsView> list =(List<JifengoodsView>)page.getList();
        for(JifengoodsView c:list){
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
        JifengoodsEntity jifengoods = jifengoodsService.selectById(id);
        if(jifengoods !=null){
            //entity转view
            JifengoodsView view = new JifengoodsView();
            BeanUtils.copyProperties( jifengoods , view );//把实体数据重构到view中

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
    public R save(@RequestBody JifengoodsEntity jifengoods, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jifengoods:{}",this.getClass().getName(),jifengoods.toString());
        Wrapper<JifengoodsEntity> queryWrapper = new EntityWrapper<JifengoodsEntity>()
            .eq("jifengoods_name", jifengoods.getJifengoodsName())
            .eq("goods_types", jifengoods.getGoodsTypes())
            .eq("jifengoods_number", jifengoods.getJifengoodsNumber())
            .eq("danwei", jifengoods.getDanwei())
            .eq("shangjia_types", jifengoods.getShangjiaTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JifengoodsEntity jifengoodsEntity = jifengoodsService.selectOne(queryWrapper);
        if(jifengoodsEntity==null){
            jifengoods.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      jifengoods.set
        //  }
            String role = String.valueOf(request.getSession().getAttribute("role"));
            if("用户".equals(role)){
                YonghuEntity yonghuEntity = yonghuService.selectById(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
                jifengoods.setShangjiaTypes(yonghuEntity.getShangjiaTypes());
            }else if("商家管理员".equals(role) ){
                ShangjiaguanliEntity shangjiaguanli = shangjiaguanliService.selectById(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
                jifengoods.setShangjiaTypes(shangjiaguanli.getShangjiaTypes());
            }
            jifengoodsService.insert(jifengoods);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JifengoodsEntity jifengoods, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jifengoods:{}",this.getClass().getName(),jifengoods.toString());
        //根据字段查询是否有相同数据
        Wrapper<JifengoodsEntity> queryWrapper = new EntityWrapper<JifengoodsEntity>()
            .notIn("id",jifengoods.getId())
            .andNew()
            .eq("jifengoods_name", jifengoods.getJifengoodsName())
            .eq("goods_types", jifengoods.getGoodsTypes())
            .eq("jifengoods_number", jifengoods.getJifengoodsNumber())
            .eq("danwei", jifengoods.getDanwei())
            .eq("shangjia_types", jifengoods.getShangjiaTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JifengoodsEntity jifengoodsEntity = jifengoodsService.selectOne(queryWrapper);
        if("".equals(jifengoods.getJifengoodsPhoto()) || "null".equals(jifengoods.getJifengoodsPhoto())){
                jifengoods.setJifengoodsPhoto(null);
        }
        if(jifengoodsEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      jifengoods.set
            //  }
            jifengoodsService.updateById(jifengoods);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        jifengoodsService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 兑换
     */
    @RequestMapping("/duihuan")
    public R duihuan(Integer id, HttpServletRequest request){
        logger.debug("duihuan:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("会员".equals(role)){
            JifengoodsEntity jifengoodsEntity = jifengoodsService.selectById(id);
            if(jifengoodsEntity == null){
                return R.error(511,"查不到兑换商品");
            }
            HuiyuanEntity huiyuan = huiyuanService.selectById(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
            if(huiyuan == null){
                return R.error(511,"查不到会员信息");
            }
            Double suoxujifen = jifengoodsEntity.getSuoxujifen();//商品所需积分
            Double jifen = huiyuan.getJifen();//会员积分
            Double balance = jifen - suoxujifen;
            if(balance<0){
                return R.error(511,"您的积分:"+jifen+",商品所需积分:"+suoxujifen+"积分不够兑换");
            }
            Integer jifengoodsNumber = jifengoodsEntity.getJifengoodsNumber();
            if(jifengoodsNumber <= 0){
                return R.error(511,"商品数量不够,请联系管理员");
            }
            jifengoodsNumber -=1;
            jifengoodsEntity.setJifengoodsNumber(jifengoodsNumber);
            huiyuan.setJifen(balance);

            JifengoodsOrderEntity jifengoodsOrderEntity = new JifengoodsOrderEntity();
            jifengoodsOrderEntity.setCreateTime(new Date());
            jifengoodsOrderEntity.setInsertTime(new Date());
            jifengoodsOrderEntity.setHuiyuanId(huiyuan.getId());
            jifengoodsOrderEntity.setJifengoodsId(id);

            jifengoodsOrderService.insert(jifengoodsOrderEntity); // 新增兑换订单
            jifengoodsService.updateById(jifengoodsEntity);// 更新库存
            huiyuanService.updateById(huiyuan);// 更新会员
            return R.ok();
        }else{
            return R.error(511,"您没有权限兑换商品");
        }
    }






}

