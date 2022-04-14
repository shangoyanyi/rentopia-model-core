package com.rentopia.model.core.entity;

import com.google.gson.GsonBuilder;

public abstract class BaseEntity {
	private String id;	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	/**
	 * turn pogo to json
	 * @return
	 */
	public String toJson() {
        return new GsonBuilder().serializeNulls().create().toJson(this);
    }
}
