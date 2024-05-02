package com.entity.model;

import com.entity.JifengoodsEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 积分商品
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-04-21
 */
public class JifengoodsModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 商品名字
     */
    private String jifengoodsName;


    /**
     * 商品种类
     */
    private Integer goodsTypes;


    /**
     * 商品数量
     */
    private Integer jifengoodsNumber;


    /**
     * 商品图片
     */
    private String jifengoodsPhoto;


    /**
     * 单位
     */
    private String danwei;


    /**
     * 价值
     */
    private Double danjia;


    /**
     * 所需积分
     */
    private Double suoxujifen;


    /**
     * 商品详情
     */
    private String jifengoodsContent;


    /**
     * 商家
     */
    private Integer shangjiaTypes;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：商品名字
	 */
    public String getJifengoodsName() {
        return jifengoodsName;
    }


    /**
	 * 设置：商品名字
	 */
    public void setJifengoodsName(String jifengoodsName) {
        this.jifengoodsName = jifengoodsName;
    }
    /**
	 * 获取：商品种类
	 */
    public Integer getGoodsTypes() {
        return goodsTypes;
    }


    /**
	 * 设置：商品种类
	 */
    public void setGoodsTypes(Integer goodsTypes) {
        this.goodsTypes = goodsTypes;
    }
    /**
	 * 获取：商品数量
	 */
    public Integer getJifengoodsNumber() {
        return jifengoodsNumber;
    }


    /**
	 * 设置：商品数量
	 */
    public void setJifengoodsNumber(Integer jifengoodsNumber) {
        this.jifengoodsNumber = jifengoodsNumber;
    }
    /**
	 * 获取：商品图片
	 */
    public String getJifengoodsPhoto() {
        return jifengoodsPhoto;
    }


    /**
	 * 设置：商品图片
	 */
    public void setJifengoodsPhoto(String jifengoodsPhoto) {
        this.jifengoodsPhoto = jifengoodsPhoto;
    }
    /**
	 * 获取：单位
	 */
    public String getDanwei() {
        return danwei;
    }


    /**
	 * 设置：单位
	 */
    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }
    /**
	 * 获取：价值
	 */
    public Double getDanjia() {
        return danjia;
    }


    /**
	 * 设置：价值
	 */
    public void setDanjia(Double danjia) {
        this.danjia = danjia;
    }
    /**
	 * 获取：所需积分
	 */
    public Double getSuoxujifen() {
        return suoxujifen;
    }


    /**
	 * 设置：所需积分
	 */
    public void setSuoxujifen(Double suoxujifen) {
        this.suoxujifen = suoxujifen;
    }
    /**
	 * 获取：商品详情
	 */
    public String getJifengoodsContent() {
        return jifengoodsContent;
    }


    /**
	 * 设置：商品详情
	 */
    public void setJifengoodsContent(String jifengoodsContent) {
        this.jifengoodsContent = jifengoodsContent;
    }
    /**
	 * 获取：商家
	 */
    public Integer getShangjiaTypes() {
        return shangjiaTypes;
    }


    /**
	 * 设置：商家
	 */
    public void setShangjiaTypes(Integer shangjiaTypes) {
        this.shangjiaTypes = shangjiaTypes;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
