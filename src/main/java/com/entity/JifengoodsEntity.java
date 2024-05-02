package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 积分商品
 *
 * @author 
 * @email
 * @date 2021-04-21
 */
@TableName("jifengoods")
public class JifengoodsEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JifengoodsEntity() {

	}

	public JifengoodsEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 商品名字
     */
    @TableField(value = "jifengoods_name")

    private String jifengoodsName;


    /**
     * 商品种类
     */
    @TableField(value = "goods_types")

    private Integer goodsTypes;


    /**
     * 商品数量
     */
    @TableField(value = "jifengoods_number")

    private Integer jifengoodsNumber;


    /**
     * 商品图片
     */
    @TableField(value = "jifengoods_photo")

    private String jifengoodsPhoto;


    /**
     * 单位
     */
    @TableField(value = "danwei")

    private String danwei;


    /**
     * 价值
     */
    @TableField(value = "danjia")

    private Double danjia;


    /**
     * 所需积分
     */
    @TableField(value = "suoxujifen")

    private Double suoxujifen;


    /**
     * 商品详情
     */
    @TableField(value = "jifengoods_content")

    private String jifengoodsContent;


    /**
     * 商家
     */
    @TableField(value = "shangjia_types")

    private Integer shangjiaTypes;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：商品名字
	 */
    public String getJifengoodsName() {
        return jifengoodsName;
    }


    /**
	 * 获取：商品名字
	 */

    public void setJifengoodsName(String jifengoodsName) {
        this.jifengoodsName = jifengoodsName;
    }
    /**
	 * 设置：商品种类
	 */
    public Integer getGoodsTypes() {
        return goodsTypes;
    }


    /**
	 * 获取：商品种类
	 */

    public void setGoodsTypes(Integer goodsTypes) {
        this.goodsTypes = goodsTypes;
    }
    /**
	 * 设置：商品数量
	 */
    public Integer getJifengoodsNumber() {
        return jifengoodsNumber;
    }


    /**
	 * 获取：商品数量
	 */

    public void setJifengoodsNumber(Integer jifengoodsNumber) {
        this.jifengoodsNumber = jifengoodsNumber;
    }
    /**
	 * 设置：商品图片
	 */
    public String getJifengoodsPhoto() {
        return jifengoodsPhoto;
    }


    /**
	 * 获取：商品图片
	 */

    public void setJifengoodsPhoto(String jifengoodsPhoto) {
        this.jifengoodsPhoto = jifengoodsPhoto;
    }
    /**
	 * 设置：单位
	 */
    public String getDanwei() {
        return danwei;
    }


    /**
	 * 获取：单位
	 */

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }
    /**
	 * 设置：价值
	 */
    public Double getDanjia() {
        return danjia;
    }


    /**
	 * 获取：价值
	 */

    public void setDanjia(Double danjia) {
        this.danjia = danjia;
    }
    /**
	 * 设置：所需积分
	 */
    public Double getSuoxujifen() {
        return suoxujifen;
    }


    /**
	 * 获取：所需积分
	 */

    public void setSuoxujifen(Double suoxujifen) {
        this.suoxujifen = suoxujifen;
    }
    /**
	 * 设置：商品详情
	 */
    public String getJifengoodsContent() {
        return jifengoodsContent;
    }


    /**
	 * 获取：商品详情
	 */

    public void setJifengoodsContent(String jifengoodsContent) {
        this.jifengoodsContent = jifengoodsContent;
    }
    /**
	 * 设置：商家
	 */
    public Integer getShangjiaTypes() {
        return shangjiaTypes;
    }


    /**
	 * 获取：商家
	 */

    public void setShangjiaTypes(Integer shangjiaTypes) {
        this.shangjiaTypes = shangjiaTypes;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Jifengoods{" +
            "id=" + id +
            ", jifengoodsName=" + jifengoodsName +
            ", goodsTypes=" + goodsTypes +
            ", jifengoodsNumber=" + jifengoodsNumber +
            ", jifengoodsPhoto=" + jifengoodsPhoto +
            ", danwei=" + danwei +
            ", danjia=" + danjia +
            ", suoxujifen=" + suoxujifen +
            ", jifengoodsContent=" + jifengoodsContent +
            ", shangjiaTypes=" + shangjiaTypes +
            ", createTime=" + createTime +
        "}";
    }
}
