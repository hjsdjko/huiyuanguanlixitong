package com.entity.vo;

import com.entity.ShangjiaguanliEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 商家管理员
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email
 * @date 2021-04-21
 */
@TableName("shangjiaguanli")
public class ShangjiaguanliVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 商家管理员账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 商家管理员密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 姓名
     */

    @TableField(value = "shangjiaguanli_name")
    private String shangjiaguanliName;


    /**
     * 手机号
     */

    @TableField(value = "shangjiaguanli_phone")
    private String shangjiaguanliPhone;


    /**
     * 身份证号
     */

    @TableField(value = "shangjiaguanli_id_number")
    private String shangjiaguanliIdNumber;


    /**
     * 头像
     */

    @TableField(value = "shangjiaguanli_photo")
    private String shangjiaguanliPhoto;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


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

    @TableField(value = "create_time")
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
	 * 设置：商家管理员账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：商家管理员账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：商家管理员密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：商家管理员密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：姓名
	 */
    public String getShangjiaguanliName() {
        return shangjiaguanliName;
    }


    /**
	 * 获取：姓名
	 */

    public void setShangjiaguanliName(String shangjiaguanliName) {
        this.shangjiaguanliName = shangjiaguanliName;
    }
    /**
	 * 设置：手机号
	 */
    public String getShangjiaguanliPhone() {
        return shangjiaguanliPhone;
    }


    /**
	 * 获取：手机号
	 */

    public void setShangjiaguanliPhone(String shangjiaguanliPhone) {
        this.shangjiaguanliPhone = shangjiaguanliPhone;
    }
    /**
	 * 设置：身份证号
	 */
    public String getShangjiaguanliIdNumber() {
        return shangjiaguanliIdNumber;
    }


    /**
	 * 获取：身份证号
	 */

    public void setShangjiaguanliIdNumber(String shangjiaguanliIdNumber) {
        this.shangjiaguanliIdNumber = shangjiaguanliIdNumber;
    }
    /**
	 * 设置：头像
	 */
    public String getShangjiaguanliPhoto() {
        return shangjiaguanliPhoto;
    }


    /**
	 * 获取：头像
	 */

    public void setShangjiaguanliPhoto(String shangjiaguanliPhoto) {
        this.shangjiaguanliPhoto = shangjiaguanliPhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
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

}
