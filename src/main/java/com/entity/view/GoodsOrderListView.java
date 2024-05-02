package com.entity.view;

import com.entity.GoodsOrderListEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 购买订单详情
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-04-20
 */
@TableName("goods_order_list")
public class GoodsOrderListView extends GoodsOrderListEntity implements Serializable {
    private static final long serialVersionUID = 1L;



		//级联表 goods
			/**
			* 商品名字
			*/
			private String goodsName;
			/**
			* 商品种类
			*/
			private Integer goodsTypes;
				/**
				* 商品种类的值
				*/
				private String goodsValue;
			/**
			* 商品数量
			*/
			private Integer goodsNumber;
			/**
			* 商品图片
			*/
			private String goodsPhoto;
			/**
			* 单位
			*/
			private String danwei;
			/**
			* 单价
			*/
			private Double danjia;
			/**
			* 商品详情
			*/
			private String goodsContent;
			/**
			* 商家
			*/
			private Integer shangjiaTypes;
				/**
				* 商家的值
				*/
				private String shangjiaValue;
			/**
			* 是否删除
			*/
			private Integer flag;

		//级联表 goods_order
			/**
			* 订单名
			*/
			private String goodsOrderName;
			/**
			* 操作人姓名
			*/
			private String caozuoName;
			/**
			* 操作人所在表名
			*/
			private String caozuoTable;
			/**
			* 会员id
			*/
			private Integer huiyuanId;
			/**
			* 原总价
			*/
			private Double originalMoney;
			/**
			* 折后价
			*/
			private Double discountMoney;
			@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
			@DateTimeFormat
			/**
			* 订单添加时间
			*/
			private Date insertTime;

	public GoodsOrderListView() {

	}

	public GoodsOrderListView(GoodsOrderListEntity goodsOrderListEntity) {
		try {
			BeanUtils.copyProperties(this, goodsOrderListEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}











				//级联表的get和set goods
					/**
					* 获取： 商品名字
					*/
					public String getGoodsName() {
						return goodsName;
					}
					/**
					* 设置： 商品名字
					*/
					public void setGoodsName(String goodsName) {
						this.goodsName = goodsName;
					}
					/**
					* 获取： 商品种类
					*/
					public Integer getGoodsTypes() {
						return goodsTypes;
					}
					/**
					* 设置： 商品种类
					*/
					public void setGoodsTypes(Integer goodsTypes) {
						this.goodsTypes = goodsTypes;
					}


						/**
						* 获取： 商品种类的值
						*/
						public String getGoodsValue() {
							return goodsValue;
						}
						/**
						* 设置： 商品种类的值
						*/
						public void setGoodsValue(String goodsValue) {
							this.goodsValue = goodsValue;
						}
					/**
					* 获取： 商品数量
					*/
					public Integer getGoodsNumber() {
						return goodsNumber;
					}
					/**
					* 设置： 商品数量
					*/
					public void setGoodsNumber(Integer goodsNumber) {
						this.goodsNumber = goodsNumber;
					}
					/**
					* 获取： 商品图片
					*/
					public String getGoodsPhoto() {
						return goodsPhoto;
					}
					/**
					* 设置： 商品图片
					*/
					public void setGoodsPhoto(String goodsPhoto) {
						this.goodsPhoto = goodsPhoto;
					}
					/**
					* 获取： 单位
					*/
					public String getDanwei() {
						return danwei;
					}
					/**
					* 设置： 单位
					*/
					public void setDanwei(String danwei) {
						this.danwei = danwei;
					}
					/**
					* 获取： 单价
					*/
					public Double getDanjia() {
						return danjia;
					}
					/**
					* 设置： 单价
					*/
					public void setDanjia(Double danjia) {
						this.danjia = danjia;
					}
					/**
					* 获取： 商品详情
					*/
					public String getGoodsContent() {
						return goodsContent;
					}
					/**
					* 设置： 商品详情
					*/
					public void setGoodsContent(String goodsContent) {
						this.goodsContent = goodsContent;
					}
					/**
					* 获取： 商家
					*/
					public Integer getShangjiaTypes() {
						return shangjiaTypes;
					}
					/**
					* 设置： 商家
					*/
					public void setShangjiaTypes(Integer shangjiaTypes) {
						this.shangjiaTypes = shangjiaTypes;
					}


						/**
						* 获取： 商家的值
						*/
						public String getShangjiaValue() {
							return shangjiaValue;
						}
						/**
						* 设置： 商家的值
						*/
						public void setShangjiaValue(String shangjiaValue) {
							this.shangjiaValue = shangjiaValue;
						}
					/**
					* 获取： 是否删除
					*/
					public Integer getFlag() {
						return flag;
					}
					/**
					* 设置： 是否删除
					*/
					public void setFlag(Integer flag) {
						this.flag = flag;
					}


				//级联表的get和set goods_order
					/**
					* 获取： 订单名
					*/
					public String getGoodsOrderName() {
						return goodsOrderName;
					}
					/**
					* 设置： 订单名
					*/
					public void setGoodsOrderName(String goodsOrderName) {
						this.goodsOrderName = goodsOrderName;
					}
					/**
					* 获取： 操作人姓名
					*/
					public String getCaozuoName() {
						return caozuoName;
					}
					/**
					* 设置： 操作人姓名
					*/
					public void setCaozuoName(String caozuoName) {
						this.caozuoName = caozuoName;
					}
					/**
					* 获取： 操作人所在表名
					*/
					public String getCaozuoTable() {
						return caozuoTable;
					}
					/**
					* 设置： 操作人所在表名
					*/
					public void setCaozuoTable(String caozuoTable) {
						this.caozuoTable = caozuoTable;
					}
					/**
					* 获取： 会员id
					*/
					public Integer getHuiyuanId() {
						return huiyuanId;
					}
					/**
					* 设置： 会员id
					*/
					public void setHuiyuanId(Integer huiyuanId) {
						this.huiyuanId = huiyuanId;
					}
					/**
					* 获取： 原总价
					*/
					public Double getOriginalMoney() {
						return originalMoney;
					}
					/**
					* 设置： 原总价
					*/
					public void setOriginalMoney(Double originalMoney) {
						this.originalMoney = originalMoney;
					}
					/**
					* 获取： 折后价
					*/
					public Double getDiscountMoney() {
						return discountMoney;
					}
					/**
					* 设置： 折后价
					*/
					public void setDiscountMoney(Double discountMoney) {
						this.discountMoney = discountMoney;
					}
					/**
					* 获取： 订单添加时间
					*/
					public Date getInsertTime() {
						return insertTime;
					}
					/**
					* 设置： 订单添加时间
					*/
					public void setInsertTime(Date insertTime) {
						this.insertTime = insertTime;
					}
















}
