package com.rentopia.model.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;


import com.rentopia.model.core.entity.Asset;

@Slf4j
@Service
public class AssetService {
    private static final String COLLECTION_NAME = "asset";

    @Autowired
	Firestore firestore;

    /**
     * CREATE
     */
    public String save(Asset asset) throws InterruptedException, ExecutionException {        
        ApiFuture<WriteResult> collectionsApiFuture = firestore.collection(COLLECTION_NAME).document(asset.getId()).set(asset);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    /**
     * READ
     */
    public Asset findById(String id) throws InterruptedException, ExecutionException {
        
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Asset asset = null;

        if(document.exists()) {
            asset = document.toObject(Asset.class);
            return asset;
        }else {
            return null;
        }
    }


    
}