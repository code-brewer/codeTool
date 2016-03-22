package foo;

import java.util.Date;


public class StRole{
	
	private long id;
	/**
	*角色编号
	*/
	private String pkid;
	/**
	*角色名称
	*/
	private String name;
	/**
	*状态
	*/
	private String status;
	/**
	*描述
	*/
	private String description;
	/**
	*创建人
	*/
	private String createUser;
	/**
	*创建时间
	*/
	private Date createTime;
	/**
	*更新时间
	*/
	private Date updateTime;
	/**
	*更新人
	*/
	private String updateUser;

	
	public void setPkid(String pkid){
		this.pkid = pkid;
	}
	
	public String getPkid(){
		return this.pkid;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setCreateUser(String createUser){
		this.createUser = createUser;
	}
	
	public String getCreateUser(){
		return this.createUser;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return this.createTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return this.updateTime;
	}
	
	public void setUpdateUser(String updateUser){
		this.updateUser = updateUser;
	}
	
	public String getUpdateUser(){
		return this.updateUser;
	}

}
