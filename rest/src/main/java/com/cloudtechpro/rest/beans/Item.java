package com.cloudtechpro.rest.beans;

/**
 * Item bean
 * 
 * @author satish
 *
 */
public class Item {

	private final long code;
	private final String name;

	public Item(long code, String name) {
		this.code = code;
		this.name = name;
	}

	public long getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[ ");
		builder.append("Code: ").append(code);
		builder.append(", Name").append(name);
		builder.append(" ]");
		return builder.toString();
	}
}
