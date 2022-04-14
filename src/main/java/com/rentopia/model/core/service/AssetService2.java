package com.rentopia.model.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import lombok.extern.slf4j.Slf4j;

import com.rentopia.model.core.entity.Asset;

@Slf4j
@Service
public class AssetService2 extends BaseService<Asset> {
    @Override
	protected String COLLECTION_NAME() {
		return "asset";
	}
    
    @Override
	protected Class<Asset> entityClass() {
		return Asset.class;
	}

}