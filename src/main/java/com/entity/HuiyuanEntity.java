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
 * 会员
 *
 * @author 
 * @email
 * @date 2021-04-21
 */
@TableName("huiyuan")
public class HuiyuanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public HuiyuanEntity() {

	}

	public HuiyuanEntity(T t) {
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
     * 
     */
    @TableField(value = "username")

    private String username;


    /**
     * 
     */
    @TableField(value = "password")

    private String password;


    /**
     * 姓名
     */
    @TableField(value = "huiyuan_name")

    private String huiyuanName;


    /**
     * 手机号
     */
    @TableField(value = "huiyuan_phone")

    private String huiyuanPhone;


    /**
     * 身份证号
     */
    @TableField(value = "huiyuan_id_number")

    private String huiyuanIdNumber;


    /**
     * 余额
     */
    @TableField(value = "new_money")

    private Double newMoney;


    /**
     * 积分
     */
    @TableField(value = "jifen")

    private Double jifen;


    /**
     * 性别
     */
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 头像
     */
    @TableField(value = "huiyuan_photo")

    private String huiyuanPhoto;


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
	 * 设置：
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：姓名
	 */
    public String getHuiyuanName() {
        return huiyuanName;
    }


    /**
	 * 获取：姓名
	 */

    public void setHuiyuanName(String huiyuanName) {
        this.huiyuanName = huiyuanName;
    }
    /**
	 * 设置：手机号
	 */
    public String getHuiyuanPhone() {
        return huiyuanPhone;
    }


    /**
	 * 获取：手机号
	 */

    public void setHuiyuanPhone(String huiyuanPhone) {
        this.huiyuanPhone = huiyuanPhone;
    }
    /**
	 * 设置：身份证号
	 */
    public String getHuiyuanIdNumber() {
        return huiyuanIdNumber;
    }


    /**
	 * 获取：身份证号
	 */

    public void setHuiyuanIdNumber(String huiyuanIdNumber) {
        this.huiyuanIdNumber = huiyuanIdNumber;
    }
    /**
	 * 设置：余额
	 */
    public Double getNewMoney() {
        return newMoney;
    }


    /**
	 * 获取：余额
	 */

    public void setNewMoney(Double newMoney) {
        this.newMoney = newMoney;
    }
    /**
	 * 设置：积分
	 */
    public Double getJifen() {
        return jifen;
    }


    /**
	 * 获取：积分
	 */

    public void setJifen(Double jifen) {
        this.jifen = jifen;
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
	 * 设置：头像
	 */
    public String getHuiyuanPhoto() {
        return huiyuanPhoto;
    }


    /**
	 * 获取：头像
	 */

    public void setHuiyuanPhoto(String huiyuanPhoto) {
        this.huiyuanPhoto = huiyuanPhoto;
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
        return "Huiyuan{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", huiyuanName=" + huiyuanName +
            ", huiyuanPhone=" + huiyuanPhone +
            ", huiyuanIdNumber=" + huiyuanIdNumber +
            ", newMoney=" + newMoney +
            ", jifen=" + jifen +
            ", sexTypes=" + sexTypes +
            ", huiyuanPhoto=" + huiyuanPhoto +
            ", shangjiaTypes=" + shangjiaTypes +
            ", createTime=" + createTime +
        "}";
    }
}
