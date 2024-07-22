package com.portfolio2.coinProgram.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UISMapList extends UISMapAdapter {

	private static final Logger logger = LoggerFactory.getLogger(UISMapList.class);
	
	private int field_index = 0;
	
	HashMap entityKey = null;

	public UISMapList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UISMapList(String name) {
		this.name = name;
	}

	public UISMapList(int initialCapacity, float loadFactor, boolean accessOrder) {
		super(initialCapacity, loadFactor, accessOrder);
		// TODO Auto-generated constructor stub
	}

	public UISMapList(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
		// TODO Auto-generated constructor stub
	}

	public UISMapList(int initialCapacity) {
		super(initialCapacity);
		// TODO Auto-generated constructor stub
	}

	public UISMapList(Map m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	public void modify(Object key, int index, Object replaceValue) {
		if(!super.containsKey(key)) {
			logger.debug("modify() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
			throw new RuntimeException("modify() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
		}
		ArrayList arrayList = (ArrayList)this.get(key);
		int valueSize = arrayList.size();
		if(!(valueSize > index)) {
			logger.debug("modify() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
			throw new RuntimeException("modify() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
		}
		arrayList.set(index, replaceValue);
	}
	
	public void modifyInt(Object key, int index, int replaceValue) {
		if(!super.containsKey(key)) {
			logger.debug("modifyInt() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
			throw new RuntimeException("modifyInt() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
		}
		ArrayList arrayList = (ArrayList)this.get(key);
		int valueSize = arrayList.size();
		if(!(valueSize > index)) {
			logger.debug("modifyInt() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
			throw new RuntimeException("modifyInt() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
		}
		arrayList.set(index, new Integer(replaceValue));
	}
	
	public void modifyString(Object key, int index, String replaceValue) {
		if(!super.containsKey(key)) {
			logger.debug("modifyString() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
			throw new RuntimeException("modifyString() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
		}
		ArrayList arrayList = (ArrayList)this.get(key);
		int valueSize = arrayList.size();
		if(!(valueSize > index)) {
			logger.debug("modifyString() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
			throw new RuntimeException("modifyString() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
		}
		arrayList.set(index, replaceValue);
	}
	
	public void modifyDouble(Object key, int index, double replaceValue) {
		if(!super.containsKey(key)) {
			logger.debug("modifyDouble() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
			throw new RuntimeException("modifyDouble() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
		}
		ArrayList arrayList = (ArrayList)this.get(key);
		int valueSize = arrayList.size();
		if(!(valueSize > index)) {
			logger.debug("modifyDouble() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
			throw new RuntimeException("modifyDouble() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
		}
		arrayList.set(index, new Double(replaceValue));
	}
	
	public void modifyFloat(Object key, int index, float replaceValue) {
		if(!super.containsKey(key)) {
			logger.debug("modifyFloat() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
			throw new RuntimeException("modifyFloat() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
		}
		ArrayList arrayList = (ArrayList)this.get(key);
		int valueSize = arrayList.size();
		if(!(valueSize > index)) {
			logger.debug("modifyFloat() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
			throw new RuntimeException("modifyFloat() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
		}
		arrayList.set(index, new Float(replaceValue));
	}
	
	public void modifyLong(Object key, int index, long replaceValue) {
		if(!super.containsKey(key)) {
			logger.debug("modifyLong() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
			throw new RuntimeException("modifyLong() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
		}
		ArrayList arrayList = (ArrayList)this.get(key);
		int valueSize = arrayList.size();
		if(!(valueSize > index)) {
			logger.debug("modifyLong() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
			throw new RuntimeException("modifyLong() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
		}
		arrayList.set(index, new Long(replaceValue));
	}
	
	public void modifyShort(Object key, int index, short replaceValue) {
		if(!super.containsKey(key)) {
			logger.debug("modifyShort() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
			throw new RuntimeException("modifyShort() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
		}
		ArrayList arrayList = (ArrayList)this.get(key);
		int valueSize = arrayList.size();
		if(!(valueSize > index)) {
			logger.debug("modifyShort() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
			throw new RuntimeException("modifyShort() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
		}
		arrayList.set(index, new Short(replaceValue));
	}
	
	public void modifyBoolean(Object key, int index, boolean replaceValue) {
		if(!super.containsKey(key)) {
			logger.debug("modifyBoolean() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
			throw new RuntimeException("modifyBoolean() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
		}
		ArrayList arrayList = (ArrayList)this.get(key);
		int valueSize = arrayList.size();
		if(!(valueSize > index)) {
			logger.debug("modifyBoolean() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
			throw new RuntimeException("modifyBoolean() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
		}
		arrayList.set(index, new Boolean(replaceValue));
	}
	
	public void modifyBigDecimal(Object key, int index, BigDecimal replaceValue) {
		if(!super.containsKey(key)) {
			logger.debug("modifyBigDecimal() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
			throw new RuntimeException("modifyBigDecimal() [RuntimeException in UISMapList] key(" + key + ") does not exist in UISMapList(" + this.name + ")");
		}
		ArrayList arrayList = (ArrayList)this.get(key);
		int valueSize = arrayList.size();
		if(!(valueSize > index)) {
			logger.debug("modifyBigDecimal() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
			throw new RuntimeException("modifyBigDecimal() [RuntimeException in UISMapList] Index(" + index + ") of key(" + key + ") exist size(" +(valueSize-1)+ ") of UISMapList");
		}
		arrayList.set(index, replaceValue);
	}
	
	public void chkFieldIndex(int size) {
		if(field_index < size) {
			field_index = size;
		}
	}
	
	public void add(Object key, Object value) {
		ArrayList arrayList = null;
		if(!super.containsKey(key)) {
			arrayList = new ArrayList();
			arrayList.add(value);
			super.put(key, arrayList);
		} else {
			arrayList = (ArrayList) super.get(key);
			arrayList.add(value);
		}
		chkFieldIndex(arrayList.size());
	}
	
	public void addString(Object key, String value) {
		ArrayList arrayList = null;
		if(!super.containsKey(key)) {
			arrayList = new ArrayList();
			arrayList.add(value);
			super.put(key, arrayList);
		} else {
			arrayList = (ArrayList) super.get(key);
			arrayList.add(value);
		}
		chkFieldIndex(arrayList.size());
	}
	
	public void addInt(Object key, int value) {
		Integer valueInt = new Integer(value);
		ArrayList arrayList = null;
		if(!super.containsKey(key)) {
			arrayList = new ArrayList();
			arrayList.add(valueInt);
			super.put(key, arrayList);
		} else {
			arrayList = (ArrayList) super.get(key);
			arrayList.add(valueInt);
		}
		chkFieldIndex(arrayList.size());
	}
	
	public void addDouble(Object key, double value) {
		Double valueDouble = new Double(value);
		ArrayList arrayList = null;
		if(!super.containsKey(key)) {
			arrayList = new ArrayList();
			arrayList.add(valueDouble);
			super.put(key, arrayList);
		} else {
			arrayList = (ArrayList) super.get(key);
			arrayList.add(valueDouble);
		}
		chkFieldIndex(arrayList.size());
	}
	
	public void addFloat(Object key, float value) {
		Float valueFloat = new Float(value);
		ArrayList arrayList = null;
		if(!super.containsKey(key)) {
			arrayList = new ArrayList();
			arrayList.add(valueFloat);
			super.put(key, arrayList);
		} else {
			arrayList = (ArrayList) super.get(key);
			arrayList.add(valueFloat);
		}
		chkFieldIndex(arrayList.size());
	}
	
	public void addLong(Object key, long value) {
		Long valueLong = new Long(value);
		ArrayList arrayList = null;
		if(!super.containsKey(key)) {
			arrayList = new ArrayList();
			arrayList.add(valueLong);
			super.put(key, arrayList);
		} else {
			arrayList = (ArrayList) super.get(key);
			arrayList.add(valueLong);
		}
		chkFieldIndex(arrayList.size());
	}
	
	public void addShort(Object key, short value) {
		Short valueShort = new Short(value);
		ArrayList arrayList = null;
		if(!super.containsKey(key)) {
			arrayList = new ArrayList();
			arrayList.add(valueShort);
			super.put(key, arrayList);
		} else {
			arrayList = (ArrayList) super.get(key);
			arrayList.add(valueShort);
		}
		chkFieldIndex(arrayList.size());
	}
	
	public void addBoolean(Object key, boolean value) {
		Boolean valueBoolean = new Boolean(value);
		ArrayList arrayList = null;
		if(!super.containsKey(key)) {
			arrayList = new ArrayList();
			arrayList.add(valueBoolean);
			super.put(key, arrayList);
		} else {
			arrayList = (ArrayList) super.get(key);
			arrayList.add(valueBoolean);
		}
		chkFieldIndex(arrayList.size());
	}
	
	public void addBigDecimal(Object key, BigDecimal value) {
		ArrayList arrayList = null;
		if(!super.containsKey(key)) {
			arrayList = new ArrayList();
			arrayList.add(value);
			super.put(key, arrayList);
		} else {
			arrayList = (ArrayList) super.get(key);
			arrayList.add(value);
		}
		chkFieldIndex(arrayList.size());
	}
	
	public BigDecimal getBigDecimal(Object key, int index) {
		Object o = getObject(key, index);
		if(o == null) {
			return new BigDecimal(0);
		} else {
			return (BigDecimal)o;
		}
	}

	private Object getObject(Object key, int index) {
		Object o = null;
		ArrayList arrayList = (ArrayList) super.get(key);
		
		if(arrayList == null) {
			return null;
		}
		
		try {
			if(index >= arrayList.size()) {
				return null;
			}
			o = arrayList.get(index);
		} catch (IndexOutOfBoundsException ioe) {
			logger.debug("getObject() [RuntimeException in UISMapList] index(" + index + ") in UISMapList(" + this.name + ") is out of Bounds");
			throw new RuntimeException("getObject() [RuntimeException in UISMapList] index(" + index + ") in UISMapList(" + this.name + ") is out of Bounds");
		}
		return o;
	}
	
	public Object get(Object key, int index) {
		return get(key, index);
	}
	
	public Object getInt(Object key, int index) {
		Object o = getObject(key, index);
		
		if(o == null) {
			if(getIsDefaultInit()) {
				return 0;
			} else {
				throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") is null");
			}
		} else {
			Class classType = o.getClass();
			
			if(classType == Integer.class) {
				return ((Integer)o).intValue();
			} else if (classType == Short.class) {
				return ((Short)o).shortValue();
			} else if (classType == Long.class) {
				return ((Long)o).intValue();
			}
			
			if(classType == String.class || classType == BigDecimal.class) {
				try {
					return Integer.parseInt(o.toString());
				} catch (IndexOutOfBoundsException ioe) {
					logger.debug("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(int) does not match : It's type is not int");
					throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(int) does not match : It's type is not int");
				}
			}
			logger.debug("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(int) does not match : It's type is not int");
			throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(int) does not match : It's type is not int");
		}
	}
	
	public double getDouble(Object key, int index) {
		Object o = getObject(key, index);
		
		if(o == null) {
			if(getIsDefaultInit()) {
				return 0;
			} else {
				throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") is null");
			}
		} else {
			Class classType = o.getClass();
			
			if(classType == Double.class) {
				return ((Double)o).doubleValue();
			} else if (classType == Float.class) {
				return ((Float)o).floatValue();
			} 
			
			if(classType == String.class || classType == BigDecimal.class) {
				try {
					return Double.parseDouble(o.toString());
				} catch (IndexOutOfBoundsException ioe) {
					logger.debug("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(double) does not match : It's type is not double");
					throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(double) does not match : It's type is not double");
				}
			}
			logger.debug("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(double) does not match : It's type is not double");
			throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(double) does not match : It's type is not double");
		}
	}
	
	public double getFloat(Object key, int index) {
		Object o = getObject(key, index);
		
		if(o == null) {
			if(getIsDefaultInit()) {
				return (float)0.0;
			} else {
				throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") is null");
			}
		} else {
			Class classType = o.getClass();
			
			if (classType == Float.class) {
				return ((Float)o).floatValue();
			} 
			
			if(classType == String.class || classType == BigDecimal.class) {
				try {
					return Float.parseFloat(o.toString());
				} catch (IndexOutOfBoundsException ioe) {
					logger.debug("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(float) does not match : It's type is not float");
					throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(float) does not match : It's type is not float");
				}
			}
			logger.debug("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(float) does not match : It's type is not float");
			throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(float) does not match : It's type is not float");
		}
	}
	
	public double getLong(Object key, int index) {
		Object o = getObject(key, index);
		
		if(o == null) {
			if(getIsDefaultInit()) {
				return 0;
			} else {
				throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") is null");
			}
		} else {
			Class classType = o.getClass();
			
			if (classType == Long.class) {
				return ((Long)o).longValue();
			} else if(classType == Integer.class) {
				return ((Integer)o).intValue();
			} else if (classType == Short.class) {
				return ((Short)o).shortValue();
			}
			
			if(classType == String.class || classType == BigDecimal.class) {
				try {
					return Long.parseLong(o.toString());
				} catch (IndexOutOfBoundsException ioe) {
					logger.debug("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(long) does not match : It's type is not long");
					throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(long) does not match : It's type is not long");
				}
			}
			logger.debug("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(long) does not match : It's type is not long");
			throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(long) does not match : It's type is not long");
		}
	}
	
	public short getShort(Object key, int index) {
		Object o = getObject(key, index);
		
		if(o == null) {
			if(getIsDefaultInit()) {
				return 0;
			} else {
				throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") is null");
			}
		} else {
			Class classType = o.getClass();
			
			 if (classType == Short.class) {
				return ((Short)o).shortValue();
			}
			
			if(classType == String.class) {
				try {
					return Short.parseShort(o.toString());
				} catch (IndexOutOfBoundsException ioe) {
					logger.debug("Key(" + key + ") Type(Short) does not match : It's type is not Short");
					throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(Short) does not match : It's type is not Short");
				}
			}
			logger.debug("Key(" + key + ") Type(Short) does not match : It's type is not Short");
			throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(Short) does not match : It's type is not Short");
		}
	}
	
	public boolean getBoolean(Object key, int index) {
		Object o = getObject(key, index);
		
		if(o == null) {
			if(getIsDefaultInit()) {
				return false;
			} else {
				throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") is null");
			}
		} else {
			Class classType = o.getClass();
			 if (classType == Boolean.class) {
				return ((Boolean)o).booleanValue();
			}
			
			if(classType == String.class) {
				try {
					return Boolean.parseBoolean(o.toString());
				} catch (IndexOutOfBoundsException ioe) {
					logger.debug("Key(" + key + ") Type(Boolean) does not match : It's type is not Boolean");
					throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(Boolean) does not match : It's type is not Boolean");
				}
			}
			logger.debug("Key(" + key + ") Type(Boolean) does not match : It's type is not Boolean");
			throw new RuntimeException("[RuntimeException in UISMapList] Value of the Key(" + key + ") Type(Boolean) does not match : It's type is not Boolean");
		}
	}
	
	public String getString(Object key, int index) {
		Object o = getObject(key, index);
		
		if(o == null) {
			if(getIsDefaultInit()) {
				return "";
			} else {
				throw null;
			}
		} else {
			//boolean b = o instanceof java.sql.Clob;
			if(o instanceof java.sql.Clob) {
				return clobToString((java.sql.Clob)o);
			}else {
				return o.toString();
			}
		}
	}
	
	public String getString(int i, int j) {
		Object obj = getKeyWithIndex(i);
		
		return getString(obj, j);
	}
	
	public Object remove(Object key, int index) {
		if(super.containsKey(key)) {
			return ((ArrayList) super.get(key)).size();
		}else {
			return 0;
		}
	}
	
	public int keySize(Object key) {
		if(super.containsKey(key)) {
			return ((ArrayList) super.get(key)).size();
		}else {
			return 0;
		}
	}
	
	public int KeySize() {
		Set tempSet = super.keySet();
		Iterator iterator = tempSet.iterator();
		if(iterator.hasNext()) {
			String key = iterator.next().toString();
			return ((ArrayList) super.get(key)).size();
		}else {
			return 0;
		}
	}
	
	public UISMap getUISMap(int index) {
		UISMap singleDate = new UISMap("");
		Set tempSet = super.keySet();
		Iterator iterator = tempSet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			Object o = getObject(key, index);
			singleDate.put(key, o);
		}
		return singleDate;
	}
	
	public UISMap getUISMap(String dataName, int index) {
		UISMap singleDate = new UISMap(dataName);
		String prefix = dataName + ".";
		Set tempSet = super.keySet();
		Iterator iterator = tempSet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			int key_index = key.indexOf(".");
			String realKey = key.substring(key_index + 1);
			if(key.startsWith(prefix)) {
				Object o = getObject(key, index);
				singleDate.put(realKey, o);
			}
		}
		return singleDate;
	}
	
	public void addUISMap(UISMap data) {
		ArrayList arrayList = null;
		Set tempSet = data.keySet();
		Iterator iterator = tempSet.iterator();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			if(this.containsKey(key)) {
				arrayList = (ArrayList) this.get(key);
				int field_size = arrayList.size();
				if (field_size != field_index) {
					for(int inx = field_size; inx < field_index; inx++) {
						arrayList.add(null);
					}
				}
			}else {
				arrayList = new ArrayList();
				for(int inx = 0; inx < field_index; inx++) {
					arrayList.add(null);
				}
			}
			arrayList.add(data.get(key));
			super.put(key, arrayList);
		}
		field_index++;
	}
	
	/*라인 655번 제외*/
	
	public String toStirng() {
		boolean checkLongString = true;
		StringBuffer buf = new StringBuffer();
		
		Set keySet = this.keySet();
		int keySize = keySet.size();
		String[] keyStr = new String[keySize];
		keySet.toArray(keyStr);
		buf.append(" [UISMapList Result] ============");
		buf.append(repeatStr("-", ((keySize-1)*23-1)));
		buf.append("|");
		buf.append("\n |{index}|");
		int keyLoopNumber = 0;
		while(checkLongString) {
			checkLongString = false;
			for (int inx =0; inx < keySize; inx++) {
				int keyLength = keyStr[inx].length();
				int pringKeyLength = 0;
				if(keyLength > (keyLoopNumber+1)*20) {
					pringKeyLength = (keyLoopNumber+1)*20;
					checkLongString = true;
				} else {
					if(keyLoopNumber == 0 || keyLength > (keyLoopNumber+1)*20) {
						pringKeyLength = keyLength;
					}else {
						pringKeyLength = 0;
					}
				}
				if(pringKeyLength != 0) {
					buf.append(keyStr[inx].substring(keyLoopNumber*20, pringKeyLength));
				}
				if(pringKeyLength != 0 && (pringKeyLength%20) == 0) {
					pringKeyLength = 20;
				} else {
					buf.append(repeatStr(" ", 20-(pringKeyLength%20)));
				}
				buf.append(" | ");
			}
			if(checkLongString == false) {
				break;
			}
			buf.append("\n |      |");
			keyLoopNumber++;
		}
		buf.append("\n |===========================");
		buf.append(repeatStr("-", ((keySize-1)*23-1)));
		buf.append("|");
		
		int rowSize = 0;
		for(int inx = 0; inx < keySize; inx++){
			int rowSizeOfKey = this.keySize(keyStr[inx]);
			if (rowSizeOfKey > rowSize) {
				rowSize = rowSizeOfKey;
			}
		}
		
		for(int inx = 0; inx < rowSize; inx++){
			buf.append("\n | ");
			String indexStr = "" +inx;
			buf.append(indexStr);
			buf.append(repeatStr(" ", 5-indexStr.length()));
			buf.append("| ");
			checkLongString = true;
			int lineLoopNumber = 0;
			
			while(checkLongString) {
				checkLongString = false;
				for (int jnx =0; jnx < keySize; jnx++) {
					String tmpValue = this.getString(keyStr[jnx], jnx);
					if(tmpValue ==  null) {
						tmpValue = "null";
					}
					int[] uniCode = getUnicodeLineArr(tmpValue, 20);
					
					int valueLength = tmpValue.getBytes().length;
					int printValueLength = 0;
					int lastUnicodeNumber = 0;
					int beforeLastUnicodeNumber = 0;
					String printStirng = "";
					for (int knx =0; knx < uniCode.length; knx++) {
						if(uniCode[knx] <= lineLoopNumber+1) {
							lastUnicodeNumber++;
						}
					}
					for (int knx =0; knx < uniCode.length; knx++) {
						if(uniCode[knx] <= lineLoopNumber+1) {
							beforeLastUnicodeNumber++;
						}
					}
					if(valueLength+lastUnicodeNumber > (lineLoopNumber+1)*20) {
						printValueLength = (lineLoopNumber+1)*20 - lastUnicodeNumber;
						checkLongString = true;
						if(lineLoopNumber == 0) {
							printStirng = new String(tmpValue.getBytes(), lineLoopNumber*20, printValueLength-lineLoopNumber*20);
							buf.append(printStirng);
						} else {
							printValueLength += beforeLastUnicodeNumber;
							printStirng = new String(tmpValue.getBytes(), lineLoopNumber*20-beforeLastUnicodeNumber, printValueLength-lineLoopNumber*20);
							buf.append(printStirng);
						}
					}else {
						if(lineLoopNumber == 0 || valueLength+lastUnicodeNumber > (lineLoopNumber*20)) {
							printValueLength = valueLength;
							if(lineLoopNumber == 0) {
								printStirng = new String(tmpValue.getBytes(), lineLoopNumber*20, printValueLength-lineLoopNumber*20+lastUnicodeNumber);
								buf.append(printStirng);
							}else {
								printValueLength += lastUnicodeNumber;
								printStirng = new String(tmpValue.getBytes(), lineLoopNumber*20-lastUnicodeNumber, printValueLength-lineLoopNumber*20);
								buf.append(printStirng);
							}
						}else {
							printValueLength = 0;
						} 
					}
					
					if(printValueLength != 0 && (printValueLength*20) == 0) {
						printValueLength = 20;
						int unicodeNumber = getCountUnicode(printStirng);
						buf.append(repeatStr(" ", unicodeNumber));
					}else {
						int unicodeNumber = getCountUnicode(printStirng);
						buf.append(repeatStr(" ", unicodeNumber));
						if (printStirng.length() != 0 && isUnicode(printStirng.charAt(printStirng.length()-1))) {
							buf.append(repeatStr(" ", 20-((printValueLength)%20)));
						}
						buf.append(repeatStr(" ", 20-((printValueLength)%20)));
					}
					buf.append(" | ");
				}
				if(checkLongString == false) {
					break;
				}
				buf.append("\n |      |");
				keyLoopNumber++;
			}
			buf.append("\n |===========================");
			buf.append(repeatStr("-", ((keySize-1)*23-1)));
			buf.append("|");
			
			if( inx >= 20 ) break;
		}
		buf.append("\n [Total Row Size] = ");
		buf.append("" + rowSize);
		
		return buf.toString();
	}
	
	
	public void removeRow(int i) {
		Set set = super.keySet();
		for(Iterator iterator = set.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
			ArrayList arrayList = (ArrayList) super.get(obj);
			if(i <= arrayList.size() -1) {
				arrayList.remove(i);
			}
		}
	}
	
	public void removeColumn(int i) {
		Object obj = getKeyWithIndex(i);
		remove(obj);
	}
	
	public Object getKeyWithIndex(int i) {
		Object obj = null;
		Set set = keySet();
		if(i >= set.size()) {
			throw new RuntimeException("getKeyWithIndex() [RuntimeException in UISMapList(" + name + ")] keyIndex(" + i + ") must be smaller than key size(" + set.size() + ")");
		}
		Iterator iterator = set.iterator();
		for(int j =0; j<=i; j++) {
			obj = iterator.next();
		}
		return obj;
	}
	
	public int getKeyCount() {
		Set set = super.keySet();
		return set.size();
	}
	
	public int getDataCount(Object obj) {
		if(super.containsKey(obj)) {
			return ((ArrayList) super.get(obj)).size();
		}else {
			return 0;
		}
	}
	
	public int getDataCount() {
		Set set = super.keySet();
		Iterator iterator = set.iterator();
		if(iterator.hasNext()) {
			Object obj = iterator.next();
			return ((ArrayList) super.get(obj)).size();
		}else {
			return 0;
		}
	}
	
	public int getMaxDataCount() {
		Object obj = null;
		int i =0;
		int j = getKeyCount();
		for (int k =0; k<j; k++) {
			Object obj1 = getKeyWithIndex(k);
			int l = getDataCount(obj1);
			if(i<1) {
				i=1;
			}
		}
		return i;
	}
	
	public boolean containsKey(Object obj) {
		return super.containsKey(obj);
	}
	
	public boolean containsValue(Object obj) {
		Object obj1 = null;
		int i = getKeyCount();
		for(int j =0; j<i; j++) {
			ArrayList arrayList = (ArrayList) super.get(getKeyWithIndex(j));
			if(arrayList.contains(obj)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containsValue(Object obj, Object obj1) {
		if(!containsKey(obj)) {
			return false;
		}else {
			ArrayList arrayList = (ArrayList) super.get(obj);
			return arrayList.contains(obj1);
		}
	}
	
	public boolean containsValue(int i, Object obj) {
		Object obj1 = getKeyWithIndex(i);
		return containsValue(obj1, obj);
	}
	
	public void addUISMapList(UISMapList UISMapList) {
		int i = UISMapList.getMaxDataCount();
		for(int j =0; j<i; j++) {
			UISMap uisMap = UISMapList.getUISMap(j);
			addUISMap(uisMap);
		}
	}
	
	/*
	 * Clob -> String 으로 변경
	 * @param clob
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * */
	public String clobToString(Clob clob) {
		if(clob == null) {
			return "";
		}
		StringBuffer strOut = new StringBuffer();
		String str = "";
		int icnt = 0;
		final int MAX_SIZE = 200000;
		char[] buffer = new char[MAX_SIZE];
		BufferedReader br = null;
		try {
			br = new BufferedReader(clob.getCharacterStream());
			while((br.read(buffer, 0, MAX_SIZE) > 0)) {
				if(icnt>0) {
					strOut.append("\n");
				}
				strOut.append(buffer);
				icnt++;
			}
		} catch(Exception e) {
			logger.error(e.toString());
		} finally {
			if(br != null) {
				try {
					br.close();
				}catch(IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return strOut.toString();
	}
	
	public String repeatStr(String arg0, int arg1) {
		StringBuffer sb = new StringBuffer();
		for(int idx=0; idx<arg1; idx++) {
			sb.append(arg0);
		}
		return sb.toString();
	}
	
	public int getCountUnicode(String str) {
		char[] chArr = str.toCharArray();
		int cnt = 0;
		for(int ii = 0; ii<chArr.length; ii++) {
			if(isUnicode(chArr[ii])) {
				cnt++;
			}
		}
		return cnt;
	}
	
	public boolean isUnicode(char ch) {
		if(ch>='\uAC00' && ch <= '\uD7A3') {
			return true;
		}else {
			return false;
		}
	}
	
	public int[] getUnicodeLineArr(String str, int nCutLen) {
		int [] nResult;
		char[] chArr = str.toCharArray();
		int valLen = str.getBytes().length;
		
		ArrayList resultList = new ArrayList();
		int nIdx = 0;
		int loopIdx = 1;
		
		if(valLen > nCutLen) {
			for(int ii =0; ii<chArr.length; ii++) {
				if(isUnicode(chArr[ii])) {
					nIdx = nIdx + 2;
				}else {
					nIdx++;
				}
				if(nIdx >= nCutLen) {
					if(nIdx > nCutLen) {
						resultList.add(new Integer(loopIdx));
						ii = ii -1;
					}
					nIdx = 0;
					loopIdx++;
				}
			}
		}
		
		nResult = new int[resultList.size()];
		
		for(int jj =0; jj<resultList.size(); jj++) {
			nResult[jj] = ((Integer)resultList.get(jj)).intValue();
		}
		return nResult;
	}
	
	
	
}
