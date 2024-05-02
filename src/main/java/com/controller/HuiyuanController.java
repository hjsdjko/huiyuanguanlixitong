package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.entity.ShangjiaguanliEntity;
import com.entity.YonghuEntity;
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

import com.entity.HuiyuanEntity;

import com.entity.view.HuiyuanView;

import com.utils.PageUtils;
import com.utils.R;

/**
 * 会员
 * 后端接口
 * @author
 * @email
 * @date 2021-04-20
*/
@RestController
@Controller
@RequestMapping("/huiyuan")
public class HuiyuanController {
    private static final Logger logger = LoggerFactory.getLogger(HuiyuanController.class);

    @Autowired
    private HuiyuanService huiyuanService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service

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
        if("用户".equals(role)){
            YonghuEntity yonghuEntity = yonghuService.selectById(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
            params.put("shangjiaTypes",yonghuEntity.getShangjiaTypes());
        }else if("商家管理员".equals(role) ){
            ShangjiaguanliEntity shangjiaguanli = shangjiaguanliService.selectById(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
            params.put("shangjiaTypes",shangjiaguanli.getShangjiaTypes());
        }
        params.put("orderBy","id");
        PageUtils page = huiyuanService.queryPage(params);

        //字典表数据转换
        List<HuiyuanView> list =(List<HuiyuanView>)page.getList();
        for(HuiyuanView c:list){
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
        HuiyuanEntity huiyuan = huiyuanService.selectById(id);
        if(huiyuan !=null){
            //entity转view
            HuiyuanView view = new HuiyuanView();
            BeanUtils.copyProperties( huiyuan , view );//把实体数据重构到view中

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
    public R save(@RequestBody HuiyuanEntity huiyuan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,huiyuan:{}",this.getClass().getName(),huiyuan.toString());
        Wrapper<HuiyuanEntity> queryWrapper = new EntityWrapper<HuiyuanEntity>()
            .eq("username", huiyuan.getUsername())
            .or()
            .eq("huiyuan_phone", huiyuan.getHuiyuanPhone())
            .or()
            .eq("huiyuan_id_number", huiyuan.getHuiyuanIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HuiyuanEntity huiyuanEntity = huiyuanService.selectOne(queryWrapper);
        if(huiyuanEntity==null){
            huiyuan.setCreateTime(new Date());
          String role = String.valueOf(request.getSession().getAttribute("role"));
            if("用户".equals(role)){
                YonghuEntity yonghuEntity = yonghuService.selectById(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
                huiyuan.setShangjiaTypes(yonghuEntity.getShangjiaTypes());
            }else if("商家管理员".equals(role) ){
                ShangjiaguanliEntity shangjiaguanli = shangjiaguanliService.selectById(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
                huiyuan.setShangjiaTypes(shangjiaguanli.getShangjiaTypes());
            }
            huiyuan.setPassword("123456");
            huiyuanService.insert(huiyuan);
            return R.ok();
        }else {
            return R.error(511,"账户或手机号活身份证号被使用了");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody HuiyuanEntity huiyuan, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,huiyuan:{}",this.getClass().getName(),huiyuan.toString());
        //根据字段查询是否有相同数据
        Wrapper<HuiyuanEntity> queryWrapper = new EntityWrapper<HuiyuanEntity>()
            .notIn("id",huiyuan.getId())
            .andNew()
            .eq("username", huiyuan.getUsername())
            .or()
            .eq("huiyuan_phone", huiyuan.getHuiyuanPhone())
            .or()
            .eq("huiyuan_id_number", huiyuan.getHuiyuanIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HuiyuanEntity huiyuanEntity = huiyuanService.selectOne(queryWrapper);
        if("".equals(huiyuan.getHuiyuanPhoto()) || "null".equals(huiyuan.getHuiyuanPhoto())){
                huiyuan.setHuiyuanPhoto(null);
        }
        if(huiyuanEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      huiyuan.set
            //  }
            huiyuanService.updateById(huiyuan);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或手机号活身份证号被使用了");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        huiyuanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 登录
     */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        HuiyuanEntity huiyuan = huiyuanService.selectOne(new EntityWrapper<HuiyuanEntity>().eq("username", username));
        if(huiyuan==null || !huiyuan.getPassword().equals(password)) {
            return R.error("账号或密码不正确");
        }
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(yonghu.getRoleTypes());
        String token = tokenService.generateToken(huiyuan.getId(),username, "huiyuan", "会员");
        R r = R.ok();
        r.put("token", token);
        r.put("role","会员");
        r.put("username",huiyuan.getHuiyuanName());
        r.put("tableName","huiyuan");
        r.put("userId",huiyuan.getId());
        return r;
    }

    /**
     * 注册
     */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody HuiyuanEntity huiyuan){
        //    	ValidatorUtils.validateEntity(user);
        if(huiyuanService.selectOne(new EntityWrapper<HuiyuanEntity>().eq("username", huiyuan.getUsername()).orNew().eq("huiyuan_phone",huiyuan.getHuiyuanPhone()).orNew().eq("huiyuan_id_number",huiyuan.getHuiyuanIdNumber())) !=null) {
            return R.error("账户已存在或手机号或身份证号已经被使用");
        }
        huiyuan.setNewMoney(0.0);
        huiyuanService.insert(huiyuan);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        HuiyuanEntity huiyuan = new HuiyuanEntity();
        huiyuan.setPassword("123456");
        huiyuan.setId(id);
        huiyuanService.updateById(huiyuan);
        return R.ok();
    }

    /**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrHuiyuan(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        HuiyuanEntity huiyuan = huiyuanService.selectById(id);
        return R.ok().put("data", huiyuan);
    }


    /**
     * 退出
     */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }






}

