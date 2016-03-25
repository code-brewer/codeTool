package foo;

import java.util.Date;

public class StRole{

	// Fields
	/** 角色编号 */
	private String pkid;
	/** 角色名称 */
	private String name;
	/** 状态 */
	private String status;
	/** 描述 */
	private String description;
	/** 创建人 */
	private String createUser;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人 */
	private String updateUser;

	// Constructors
	
	/** default constructor */
	public	StRole() {
	}

	// Property accessors
	
	/**
	 * 设置pkid 属性值
	 */
	public void setPkid(String pkid) {
		this.pkid = pkid;
	}
	
	/**
	 * 获取 pkid 属性值
	 */
	public String getPkid() {
		return this.pkid;
	}
	
	/**
	 * 设置name 属性值
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取 name 属性值
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置status 属性值
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 获取 status 属性值
	 */
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * 设置description 属性值
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 获取 description 属性值
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * 设置createUser 属性值
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	/**
	 * 获取 createUser 属性值
	 */
	public String getCreateUser() {
		return this.createUser;
	}
	
	/**
	 * 设置createTime 属性值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获取 createTime 属性值
	 */
	public Date getCreateTime() {
		return this.createTime;
	}
	
	/**
	 * 设置updateTime 属性值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * 获取 updateTime 属性值
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}
	
	/**
	 * 设置updateUser 属性值
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	/**
	 * 获取 updateUser 属性值
	 */
	public String getUpdateUser() {
		return this.updateUser;
	}
	

}
