package com.rentopia.model.core.entity;

import com.google.cloud.firestore.DocumentReference;

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
public class Asset {
	private String name;
	private int rent;
	private DocumentReference asset_owner;
	private DocumentReference asset_renter;

}
