package com.portfolio2.coinProgram.common.util;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings({"rawtypes"})
public class UISMapAdapter extends LinkedHashMap {
	
	
	private static final long serialVersionUID = -7363727856222191345L;
	
	protected String name = null;
	protected boolean isDefaultInit = false;
	
	
	public UISMapAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UISMapAdapter(int initialCapacity, float loadFactor, boolean accessOrder) {
		super(initialCapacity, loadFactor, accessOrder);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Constructs an empty insertion-ordered <tt>LinkedHashMap</tt> instance
	 * with the specified initial capacity and load factor.
	 * 
	 * @param initialCapacity the initial capacity
	 * @param loadFactor the load factor
	 * @throws IllegalArgumentException if the initial capacity is negative 
	 *         or the load factor is nonpositive
	 * 
	 * */
	public UISMapAdapter(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
		// TODO Auto-generated constructor stub
	}
	public UISMapAdapter(int initialCapacity) {
		super(initialCapacity);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings({"unchecked"})
	public UISMapAdapter(Map map) {
		super(map);
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIsDefaultInit(boolean isDefaultInit) {
		this.isDefaultInit = isDefaultInit;
	}
	public boolean getIsDefaultInit() {
		return isDefaultInit;
	}
	
}
