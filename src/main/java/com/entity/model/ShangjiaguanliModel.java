package com.entity.model;

import com.entity.ShangjiaguanliEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 商家管理员
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-04-21
 */
public class ShangjiaguanliModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 商家管理员账户
     */
    private String username;


    /**
     * 商家管理员密码
     */
    private String password;


    /**
     * 姓名
     */
    private String shangjiaguanliName;


    /**
     * 手机号
     */
    private String shangjiaguanliPhone;


    /**
     * 身份证号
     */
    private String shangjiaguanliIdNumber;


    /**
     * 头像
     */
    private String shangjiaguanliPhoto;


    /**
     * 性别
     */
    private Integer sexTypes;


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
	 * 获取：商家管理员账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：商家管理员账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：商家管理员密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：商家管理员密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：姓名
	 */
    public String getShangjiaguanliName() {
        return shangjiaguanliName;
    }


    /**
	 * 设置：姓名
	 */
    public void setShangjiaguanliName(String shangjiaguanliName) {
        this.shangjiaguanliName = shangjiaguanliName;
    }
    /**
	 * 获取：手机号
	 */
    public String getShangjiaguanliPhone() {
        return shangjiaguanliPhone;
    }


    /**
	 * 设置：手机号
	 */
    public void setShangjiaguanliPhone(String shangjiaguanliPhone) {
        this.shangjiaguanliPhone = shangjiaguanliPhone;
    }
    /**
	 * 获取：身份证号
	 */
    public String getShangjiaguanliIdNumber() {
        return shangjiaguanliIdNumber;
    }


    /**
	 * 设置：身份证号
	 */
    public void setShangjiaguanliIdNumber(String shangjiaguanliIdNumber) {
        this.shangjiaguanliIdNumber = shangjiaguanliIdNumber;
    }
    /**
	 * 获取：头像
	 */
    public String getShangjiaguanliPhoto() {
        return shangjiaguanliPhoto;
    }


    /**
	 * 设置：头像
	 */
    public void setShangjiaguanliPhoto(String shangjiaguanliPhoto) {
        this.shangjiaguanliPhoto = shangjiaguanliPhoto;
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
