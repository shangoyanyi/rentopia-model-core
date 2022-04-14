package com.rentopia.model.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.rentopia.model.core.entity.BaseEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseService <T extends BaseEntity> {
	// return collection name
	protected abstract String COLLECTION_NAME();
	
	// return T.class
	protected abstract Class<T> entityClass();
	
    @Autowired
	Firestore firestore;
    
    
    /**
     * Save
     */
     public String save(T object) throws Exception {        
         ApiFuture<DocumentReference> docRef = firestore.collection(COLLECTION_NAME()).add(object);
         return docRef.get().getId();
     }
     
     
     /**
      * update
      */
      public String update(T object) throws Exception {        
    	  ApiFuture<WriteResult> writeResult = firestore.collection(COLLECTION_NAME()).document(object.getId()).set(object);
          return writeResult.get().getUpdateTime().toString();
      }
     
     
     
     /**
      * delete
      */
      public String deleteById(String id) throws Exception {        
    	  ApiFuture<WriteResult> writeResult = firestore.collection(COLLECTION_NAME()).document(id).delete();
    	  return writeResult.get().getUpdateTime().toString();
      }
     
     

    /**
     * findById.
     */
    public T findById(String id) throws Exception {
        if(id == null){
            throw new Exception("invalid id: null");
        }

        DocumentReference docRef = firestore.collection(COLLECTION_NAME()).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        T object = null;

        if(document.exists()) {
        	object = document.toObject(entityClass());
        	object.setId(document.getId());
            return object;
        }else {
            return null;
        }
    }

    /**
     * findAll.
     */
    public List<T> findAll() throws Exception {
    	Iterable<DocumentReference> docRefs = firestore.collection(COLLECTION_NAME()).listDocuments();
		
		List<T> result = new ArrayList<T>();
		for (DocumentReference docRef : docRefs) {
			  log.info("docRef id: " + docRef.getId());
			  
			  T object = docRef.get().get().toObject(entityClass());
			  object.setId(docRef.getId());
			  log.info(object.toJson());
			  
			  result.add(object);
		}

		return result;
    }

}
