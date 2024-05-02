package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.ShangjiaguanliEntity;

import com.service.ShangjiaguanliService;
import com.entity.view.ShangjiaguanliView;

import com.utils.PageUtils;
import com.utils.R;

/**
 * 商家管理员
 * 后端接口
 * @author
 * @email
 * @date 2021-04-20
*/
@RestController
@Controller
@RequestMapping("/shangjiaguanli")
public class ShangjiaguanliController {
    private static final Logger logger = LoggerFactory.getLogger(ShangjiaguanliController.class);

    @Autowired
    private ShangjiaguanliService shangjiaguanliService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service


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
        params.put("orderBy","id");
        PageUtils page = shangjiaguanliService.queryPage(params);

        //字典表数据转换
        List<ShangjiaguanliView> list =(List<ShangjiaguanliView>)page.getList();
        for(ShangjiaguanliView c:list){
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
        ShangjiaguanliEntity shangjiaguanli = shangjiaguanliService.selectById(id);
        if(shangjiaguanli !=null){
            //entity转view
            ShangjiaguanliView view = new ShangjiaguanliView();
            BeanUtils.copyProperties( shangjiaguanli , view );//把实体数据重构到view中

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
    public R save(@RequestBody ShangjiaguanliEntity shangjiaguanli, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shangjiaguanli:{}",this.getClass().getName(),shangjiaguanli.toString());
        Wrapper<ShangjiaguanliEntity> queryWrapper = new EntityWrapper<ShangjiaguanliEntity>()
            .eq("username", shangjiaguanli.getUsername())
            .or()
            .eq("shangjiaguanli_phone", shangjiaguanli.getShangjiaguanliPhone())
            .or()
            .eq("shangjiaguanli_id_number", shangjiaguanli.getShangjiaguanliIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShangjiaguanliEntity shangjiaguanliEntity = shangjiaguanliService.selectOne(queryWrapper);
        if(shangjiaguanliEntity==null){
            shangjiaguanli.setCreateTime(new Date());
            shangjiaguanli.setPassword("123456");
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      shangjiaguanli.set
        //  }
            shangjiaguanliService.insert(shangjiaguanli);
            return R.ok();
        }else {
            return R.error(511,"账户或者身份证号或者手机号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShangjiaguanliEntity shangjiaguanli, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,shangjiaguanli:{}",this.getClass().getName(),shangjiaguanli.toString());
        //根据字段查询是否有相同数据
        Wrapper<ShangjiaguanliEntity> queryWrapper = new EntityWrapper<ShangjiaguanliEntity>()
            .notIn("id",shangjiaguanli.getId())
            .andNew()
            .eq("username", shangjiaguanli.getUsername())
            .or()
            .eq("shangjiaguanli_phone", shangjiaguanli.getShangjiaguanliPhone())
            .or()
            .eq("shangjiaguanli_id_number", shangjiaguanli.getShangjiaguanliIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShangjiaguanliEntity shangjiaguanliEntity = shangjiaguanliService.selectOne(queryWrapper);
        if("".equals(shangjiaguanli.getShangjiaguanliPhoto()) || "null".equals(shangjiaguanli.getShangjiaguanliPhoto())){
                shangjiaguanli.setShangjiaguanliPhoto(null);
        }
        if(shangjiaguanliEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      shangjiaguanli.set
            //  }
            shangjiaguanliService.updateById(shangjiaguanli);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者身份证号或者手机号已经被使用");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        shangjiaguanliService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        ShangjiaguanliEntity shangjiaguanli = shangjiaguanliService.selectOne(new EntityWrapper<ShangjiaguanliEntity>().eq("username", username));
        if(shangjiaguanli==null || !shangjiaguanli.getPassword().equals(password)) {
            return R.error("账号或密码不正确");
        }
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(yonghu.getRoleTypes());
        String token = tokenService.generateToken(shangjiaguanli.getId(),username, "shangjiaguanli", "商家管理员");
        R r = R.ok();
        r.put("token", token);
        r.put("role","商家管理员");
        r.put("username",shangjiaguanli.getShangjiaguanliName());
        r.put("tableName","shangjiaguanli");
        r.put("userId",shangjiaguanli.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody ShangjiaguanliEntity shangjiaguanli){
    //    	ValidatorUtils.validateEntity(user);
        if(shangjiaguanliService.selectOne(new EntityWrapper<ShangjiaguanliEntity>().eq("username", shangjiaguanli.getUsername()).orNew().eq("shangjiaguanli_phone",shangjiaguanli.getShangjiaguanliPhone()).orNew().eq("shangjiaguanli_id_number",shangjiaguanli.getShangjiaguanliIdNumber())) !=null) {
            return R.error("账户已存在或手机号或身份证号已经被使用");
        }
        shangjiaguanliService.insert(shangjiaguanli);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        ShangjiaguanliEntity shangjiaguanli = new ShangjiaguanliEntity();
        shangjiaguanli.setPassword("123456");
        shangjiaguanli.setId(id);
        shangjiaguanliService.updateById(shangjiaguanli);
        return R.ok();
    }

    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrShangjiaguanli(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        ShangjiaguanliEntity shangjiaguanli = shangjiaguanliService.selectById(id);
        return R.ok().put("data", shangjiaguanli);
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

