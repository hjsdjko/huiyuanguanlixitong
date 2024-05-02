package com.entity.view;

import com.entity.JifengoodsOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 积分商品
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-04-21
 */
@TableName("jifengoods_order")
public class JifengoodsOrderView extends JifengoodsOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;



		//级联表 huiyuan
			/**
			* 姓名
			*/
			private String huiyuanName;
			/**
			* 手机号
			*/
			private String huiyuanPhone;
			/**
			* 身份证号
			*/
			private String huiyuanIdNumber;
			/**
			* 余额
			*/
			private Double newMoney;
			/**
			* 积分
			*/
			private Double jifen;
			/**
			* 性别
			*/
			private Integer sexTypes;
				/**
				* 性别的值
				*/
				private String sexValue;
			/**
			* 头像
			*/
			private String huiyuanPhoto;
			/**
			* 商家
			*/
			private Integer shangjiaTypes;
				/**
				* 商家的值
				*/
				private String shangjiaValue;

		//级联表 jifengoods
			/**
			* 商品名字
			*/
			private String jifengoodsName;
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

	public JifengoodsOrderView() {

	}

	public JifengoodsOrderView(JifengoodsOrderEntity jifengoodsOrderEntity) {
		try {
			BeanUtils.copyProperties(this, jifengoodsOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



















				//级联表的get和set huiyuan
					/**
					* 获取： 姓名
					*/
					public String getHuiyuanName() {
						return huiyuanName;
					}
					/**
					* 设置： 姓名
					*/
					public void setHuiyuanName(String huiyuanName) {
						this.huiyuanName = huiyuanName;
					}
					/**
					* 获取： 手机号
					*/
					public String getHuiyuanPhone() {
						return huiyuanPhone;
					}
					/**
					* 设置： 手机号
					*/
					public void setHuiyuanPhone(String huiyuanPhone) {
						this.huiyuanPhone = huiyuanPhone;
					}
					/**
					* 获取： 身份证号
					*/
					public String getHuiyuanIdNumber() {
						return huiyuanIdNumber;
					}
					/**
					* 设置： 身份证号
					*/
					public void setHuiyuanIdNumber(String huiyuanIdNumber) {
						this.huiyuanIdNumber = huiyuanIdNumber;
					}
					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}
					/**
					* 获取： 积分
					*/
					public Double getJifen() {
						return jifen;
					}
					/**
					* 设置： 积分
					*/
					public void setJifen(Double jifen) {
						this.jifen = jifen;
					}
					/**
					* 获取： 性别
					*/
					public Integer getSexTypes() {
						return sexTypes;
					}
					/**
					* 设置： 性别
					*/
					public void setSexTypes(Integer sexTypes) {
						this.sexTypes = sexTypes;
					}


						/**
						* 获取： 性别的值
						*/
						public String getSexValue() {
							return sexValue;
						}
						/**
						* 设置： 性别的值
						*/
						public void setSexValue(String sexValue) {
							this.sexValue = sexValue;
						}
					/**
					* 获取： 头像
					*/
					public String getHuiyuanPhoto() {
						return huiyuanPhoto;
					}
					/**
					* 设置： 头像
					*/
					public void setHuiyuanPhoto(String huiyuanPhoto) {
						this.huiyuanPhoto = huiyuanPhoto;
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




				//级联表的get和set jifengoods
					/**
					* 获取： 商品名字
					*/
					public String getJifengoodsName() {
						return jifengoodsName;
					}
					/**
					* 设置： 商品名字
					*/
					public void setJifengoodsName(String jifengoodsName) {
						this.jifengoodsName = jifengoodsName;
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
					public Integer getJifengoodsNumber() {
						return jifengoodsNumber;
					}
					/**
					* 设置： 商品数量
					*/
					public void setJifengoodsNumber(Integer jifengoodsNumber) {
						this.jifengoodsNumber = jifengoodsNumber;
					}
					/**
					* 获取： 商品图片
					*/
					public String getJifengoodsPhoto() {
						return jifengoodsPhoto;
					}
					/**
					* 设置： 商品图片
					*/
					public void setJifengoodsPhoto(String jifengoodsPhoto) {
						this.jifengoodsPhoto = jifengoodsPhoto;
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
					* 获取： 价值
					*/
					public Double getDanjia() {
						return danjia;
					}
					/**
					* 设置： 价值
					*/
					public void setDanjia(Double danjia) {
						this.danjia = danjia;
					}
					/**
					* 获取： 所需积分
					*/
					public Double getSuoxujifen() {
						return suoxujifen;
					}
					/**
					* 设置： 所需积分
					*/
					public void setSuoxujifen(Double suoxujifen) {
						this.suoxujifen = suoxujifen;
					}
					/**
					* 获取： 商品详情
					*/
					public String getJifengoodsContent() {
						return jifengoodsContent;
					}
					/**
					* 设置： 商品详情
					*/
					public void setJifengoodsContent(String jifengoodsContent) {
						this.jifengoodsContent = jifengoodsContent;
					}

}
