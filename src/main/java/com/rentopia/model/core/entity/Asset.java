package com.rentopia.model.core.entity;

import com.google.gson.GsonBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//lombok annotations
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Asset extends BaseEntity {
//	private String id;
	private String name;
	private int rent;
	private String asset_owner_id;
	private String asset_renter_id;	
}
