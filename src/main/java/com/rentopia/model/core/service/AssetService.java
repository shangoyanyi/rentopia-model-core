package com.rentopia.model.core.service;

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
public class AssetService {
    private static final String COLLECTION_NAME = "asset";

    @Autowired
	Firestore firestore;

    /**
     * Save
     */
    // public String save(Asset asset) throws InterruptedException, ExecutionException {        
    //     ApiFuture<WriteResult> collectionsApiFuture = firestore.collection(COLLECTION_NAME).document(asset.getId()).set(asset);
    //     return collectionsApiFuture.get().getUpdateTime().toString();
    // }

    /**
     * FindById.
     */
    public Asset findById(String id) throws InterruptedException, ExecutionException {
        if(id == null){
            throw new ExecutionException("invalid id: null");
        }

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