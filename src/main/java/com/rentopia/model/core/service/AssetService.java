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
public class AssetService {
    private static final String COLLECTION_NAME = "asset";

    @Autowired
	Firestore firestore;

    /**
     * Save
     */
     public String save(Asset asset) throws Exception {        
         ApiFuture<DocumentReference> assetRef = firestore.collection(COLLECTION_NAME).add(asset);
         return assetRef.get().getId();
     }
     
     
     /**
      * update
      */
      public String update(Asset asset) throws Exception {        
    	  ApiFuture<WriteResult> writeResult = firestore.collection(COLLECTION_NAME).document(asset.getId()).set(asset);
          return writeResult.get().getUpdateTime().toString();
      }
     
     
     
     /**
      * delete
      */
      public String deleteById(String id) throws Exception {        
    	  ApiFuture<WriteResult> writeResult = firestore.collection(COLLECTION_NAME).document(id).delete();
    	  return writeResult.get().getUpdateTime().toString();
      }
     
     

    /**
     * findById.
     */
    public Asset findById(String id) throws Exception {
        if(id == null){
            throw new Exception("invalid id: null");
        }

        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Asset asset = null;

        if(document.exists()) {
            asset = document.toObject(Asset.class);
            asset.setId(document.getId());
            return asset;
        }else {
            return null;
        }
    }

    /**
     * findAll.
     */
    public List<Asset> findAll() throws Exception {
    	Iterable<DocumentReference> assetsRef = firestore.collection("asset").listDocuments();
		
		List<Asset> result = new ArrayList<Asset>();
		for (DocumentReference assetRef : assetsRef) {
			  log.info("assetRef id: " + assetRef.getId());
			  
			  Asset asset = assetRef.get().get().toObject(Asset.class);
			  asset.setId(assetRef.getId());
			  log.info(asset.toJson());
			  
			  result.add(asset);
		}

		return result;
    }

    
}