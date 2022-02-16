package com.rentopia.model.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name="USER")
public class User {
	private static final Logger logger = LoggerFactory.getLogger(User.class);
	
	@Override
	public String toString() {
		return "User -> {id = " + id  + ", name = " +  name + ",gender = " + gender  + "}";	
	}
	
	public User() {
		super();
	}
	
	public User(String name, String gender) {
		super();
		this.name = name;
		this.gender = gender;
		
		logger.info("create entity:" + this.toString());
	}
	
	
	/*
	@GeneratedValue 指定主鍵的生成策略。
	TABLE：使用表儲存id值
	IDENTITY：identitycolumn
	SEQUENCR ：sequence
	AUTO：根據資料庫的不同使用上面三個 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	private String name;
	private String gender;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}



	


}
