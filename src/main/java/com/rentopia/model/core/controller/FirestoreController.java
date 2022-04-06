package com.rentopia.model.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import com.rentopia.model.core.entity.Asset;
import com.rentopia.model.core.entity.User;
import com.rentopia.model.core.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FirestoreController {
	
	@Autowired
	Firestore firestore;

	@Autowired
	AssetService assetService;
	
	@RequestMapping("/asset/{id}")
	public String findById(@RequestParam("id") String id) throws InterruptedException, ExecutionException {
		Asset asset = assetService.findById(id);
		return (asset==null)? "asset doesn't exist" : asset.toString();
	}
	
	@RequestMapping("/asset/findAll")
	public List<String> findAllAssets() throws InterruptedException, ExecutionException {
		// Firestore firestore = FirestoreClient.getFirestore();
		
		Iterable<DocumentReference> assets = firestore.collection("asset").listDocuments();
		
		List<String> resp = new ArrayList<String>();
		for (DocumentReference asset : assets) {
			  log.info("asset id: " + asset.getId());
			  //resp.add(asset.toString());
			  
			  Asset assetObject = asset.get().get().toObject(Asset.class);
			  log.info(assetObject.toString());
			  
			  resp.add(assetObject.toString());

		}
		
		return resp;
	}

}
