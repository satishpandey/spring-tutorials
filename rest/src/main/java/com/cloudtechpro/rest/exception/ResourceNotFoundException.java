package com.cloudtechpro.rest.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1791564636123821405L;
	private String resourceId;

	public ResourceNotFoundException(String resourceId, String message) {
		super(message);
		this.setResourceId(resourceId);
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
}
