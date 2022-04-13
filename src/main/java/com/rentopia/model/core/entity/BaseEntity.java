package com.rentopia.model.core.entity;

import com.google.gson.GsonBuilder;

public abstract class BaseEntity {
	public String toJson() {
        return new GsonBuilder().serializeNulls().create().toJson(this);
    }
}
