package com.rentopia.model.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import com.rentopia.model.core.entity.Asset;
import com.rentopia.model.core.service.AssetService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FirestoreController {
	
	@Autowired
	Firestore firestore;

	@Autowired
	AssetService assetService;
	
	@RequestMapping("/asset/add")
	public String save(@RequestParam("name") String name) throws Exception {
		Asset asset = new Asset();
		asset.setName(name);
		
		return assetService.save(asset);
	}
	
	@RequestMapping("/asset/update/{id}")
	public String save(@PathVariable("id") String id, @RequestParam("name") String name) throws Exception {
		Asset asset = assetService.findById(id);
		asset.setName(name);
		
		return assetService.update(asset);
	}
	
	
	@RequestMapping("/asset/delete/{id}")
	public String deleteById(@PathVariable("id") String id) throws Exception {
		return assetService.deleteById(id);
	}
	
	
	@RequestMapping("/asset/{id}")
	public String findById(@PathVariable ("id") String id) throws Exception {
		Asset asset = assetService.findById(id);
		return (asset==null)? "asset doesn't exist" : asset.toJson();
	}
	
	@RequestMapping("/asset/findAll")
	public List<Asset> findAll() throws Exception {		
		return assetService.findAll();
	}

}
