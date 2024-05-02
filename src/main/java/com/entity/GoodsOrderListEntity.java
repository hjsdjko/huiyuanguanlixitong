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
 * 购买订单详情
 *
 * @author 
 * @email
 * @date 2021-04-20
 */
@TableName("goods_order_list")
public class GoodsOrderListEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public GoodsOrderListEntity() {

	}

	public GoodsOrderListEntity(T t) {
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
     * 订单
     */
    @TableField(value = "goods_order_id")

    private Integer goodsOrderId;


    /**
     * 商品
     */
    @TableField(value = "goods_id")

    private Integer goodsId;


    /**
     * 数量
     */
    @TableField(value = "goods_order_list_number")

    private Integer goodsOrderListNumber;


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
	 * 设置：订单
	 */
    public Integer getGoodsOrderId() {
        return goodsOrderId;
    }


    /**
	 * 获取：订单
	 */

    public void setGoodsOrderId(Integer goodsOrderId) {
        this.goodsOrderId = goodsOrderId;
    }
    /**
	 * 设置：商品
	 */
    public Integer getGoodsId() {
        return goodsId;
    }


    /**
	 * 获取：商品
	 */

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
    /**
	 * 设置：数量
	 */
    public Integer getGoodsOrderListNumber() {
        return goodsOrderListNumber;
    }


    /**
	 * 获取：数量
	 */

    public void setGoodsOrderListNumber(Integer goodsOrderListNumber) {
        this.goodsOrderListNumber = goodsOrderListNumber;
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
        return "GoodsOrderList{" +
            "id=" + id +
            ", goodsOrderId=" + goodsOrderId +
            ", goodsId=" + goodsId +
            ", goodsOrderListNumber=" + goodsOrderListNumber +
            ", createTime=" + createTime +
        "}";
    }
}
