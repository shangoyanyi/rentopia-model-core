package com.rentopia.model.core.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// JPA annotations
@Entity
@Table(name="USER")
// lombok annotations
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	/*
	@GeneratedValue 指定主鍵的生成策略。
	TABLE：使用表儲存id值
	IDENTITY：identitycolumn
	SEQUENCR ：sequence
	AUTO：根據資料庫的不同使用上面三個 
	 */
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid.hex")
	@Column(name="id")
	private String id;
	private String name;
	private String gender;
	
	
	



	


}
