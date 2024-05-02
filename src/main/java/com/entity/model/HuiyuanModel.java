package com.entity.model;

import com.entity.HuiyuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 会员
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-04-21
 */
public class HuiyuanModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 
     */
    private String username;


    /**
     * 
     */
    private String password;


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
     * 头像
     */
    private String huiyuanPhoto;


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
	 * 获取：
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：姓名
	 */
    public String getHuiyuanName() {
        return huiyuanName;
    }


    /**
	 * 设置：姓名
	 */
    public void setHuiyuanName(String huiyuanName) {
        this.huiyuanName = huiyuanName;
    }
    /**
	 * 获取：手机号
	 */
    public String getHuiyuanPhone() {
        return huiyuanPhone;
    }


    /**
	 * 设置：手机号
	 */
    public void setHuiyuanPhone(String huiyuanPhone) {
        this.huiyuanPhone = huiyuanPhone;
    }
    /**
	 * 获取：身份证号
	 */
    public String getHuiyuanIdNumber() {
        return huiyuanIdNumber;
    }


    /**
	 * 设置：身份证号
	 */
    public void setHuiyuanIdNumber(String huiyuanIdNumber) {
        this.huiyuanIdNumber = huiyuanIdNumber;
    }
    /**
	 * 获取：余额
	 */
    public Double getNewMoney() {
        return newMoney;
    }


    /**
	 * 设置：余额
	 */
    public void setNewMoney(Double newMoney) {
        this.newMoney = newMoney;
    }
    /**
	 * 获取：积分
	 */
    public Double getJifen() {
        return jifen;
    }


    /**
	 * 设置：积分
	 */
    public void setJifen(Double jifen) {
        this.jifen = jifen;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：头像
	 */
    public String getHuiyuanPhoto() {
        return huiyuanPhoto;
    }


    /**
	 * 设置：头像
	 */
    public void setHuiyuanPhoto(String huiyuanPhoto) {
        this.huiyuanPhoto = huiyuanPhoto;
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
