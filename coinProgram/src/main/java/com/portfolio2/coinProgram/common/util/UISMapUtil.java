package com.portfolio2.coinProgram.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class UISMapUtil {

//	private static final Logger logger = LoggerFactory.getLogger(UISMapUtil.class);
	
	public UISMapUtil() {
	}

	public static UISMap getData(HttpServletRequest req) {
		UISMap uisMap = new UISMap("REQUEST_DS");
		Enumeration e = req.getParameterNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			uisMap.put(key, req.getParameter(key));
		}
		return uisMap;
	}
	
	public static UISMap getDataFromCookie(HttpServletRequest req) {
		UISMap uisMap = new UISMap("COOKIE_DS");
		Cookie[] cookies = req.getCookies();
		if(cookies == null) {
			return uisMap;
		}
		
		for (int i =0; i< cookies.length; i++) {
			String key = cookies[i].getName();
			String value = cookies[i].getValue();
			if (value == null) {
				value = "";
			}
			String cookiesValue = value;
			uisMap.put(key, cookiesValue);
		}
		return uisMap;
	}
	
	public static UISMap getDataFromAttribute(HttpServletRequest req) {
		UISMap uisMap = new UISMap("REQUEST_DS");
		Enumeration e = req.getAttributeNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			uisMap.put(key, req.getAttribute(key));
		}
		return uisMap;
	}
	
	/* 리스트 찾아봐
	public static UISMapList getMultiData(HttpServletRequest req) {
		UISMapList uisMapList = new UISMapList("REQUEST_MDS");
		Enumeration e = req.getParameterNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String[] values = req.getParameterValues(key);
			ArrayList list = new ArrayList();
			for(int i =0; i<values.length; i++) {
				list.add(values[i]);
			}
			uisMapList.put(key, list);
		}
		return uisMapList;
	}
	*/
	
	/*
	public static UISMap getData(DefaultMultipartHttpServletRequest mReq) {
		UISMap uisMap = new UISMap("REQUEST_DS");
		List formData = new ArrayList((Collection) mReq.getParameterMap());
		Iterator iter = formData.iterator();
		while (iter.hasNext()) {
			EfFormInfo item = (EfFormInfo) iter.next();
			String key = item.getFieldName();
			if (!uisMap.containsKey(key)) {uisMap.put(key, item.getFieldValue());}
		}
		return uisMap;
	}
	
	public static UISMapList getMultiData(EfMultipartRequest mReq) {
		UISMapList uisMapList = new UISMapList("REQUEST_MDS");
		List formData = mReq.getParameters();
		Iterator iter = formData.iterator();
		while (iter.hasNext()) {
			EfFormInfo item = (EfFormInfo) iter.next();
			String key = item.getFieldName();
			if (!uisMap.containsKey(key)) {
				ArrayList list = new ArrayList();
				list.add(item.getFieldValue());
				uisMapList.put(key, list);
			} else {
				ArrayList list = (ArrayList) uisMapList.get(key);
				list.add(item.getFieldValue());
			}
		}
		return uisMapList;
	}
	*/
	//
	/*
	public static UISMap deepClone(UISMap data) {
		UISMap newData = new UISMap(data.getName());
		
		UISMap src = data;
		UISMap target = newData;
		
		Set set = src.keySet();
		Iterator e = set.iterator();
		
		while (e.hasNext()) {
			String key = (String) e.next();
			Object value = src.get(key);
			target.put(key, value);
		}
		return newData;
	}
	
	public static UISMap makeUISMapFromQueryForList(List list) {
		return makeUISMapFromQueryForList(list, "L");
	}
	
	public static UISMap makeUISMapFromQueryForList(List list, String type) {
		UISMap result = new UISMap();
		if(list.size() > 0) {
			ListorderedMap orderedMap = (ListorderedMap)list.get(0);
			MapIterator map = orderedMap.mapIterator();
			while(map.hasNext()) {
				Object key = map.next();
				Object val = map.getValue();
				if(StringUtil.NVL(type).equal("L")) {
					result.set(camelize(String.valueOf(key).toLowerCase()), val);
				} else {
					result.set(key, val);
				}
				//logger.debug(key + val);
			}
		}
		return result;
	}
	
	public static UISMapList makeUISMapListFromQueryForList(List list) {
		return makeUISMapListFromQueryForList(list, "L");
	}
	
	public static UISMapList makeUISMapListFromQueryForList(List list, String type) {
		UISMap result = new UISMap();
		UISMap temp = null;
		
		for(int idx=0; idx<list.size(); idx++) {
			temp = new UISMap();
			ListorderedMap orderedMap = (ListorderedMap)list.get(idx);
			MapIterator map = orderedMap.mapIterator();
			while(map.hasNext()) {
				Object key = map.next();
				Object val = map.getValue();
				if(StringUtil.NVL(type).equal("L")) {
					temp.set(camelize(String.valueOf(key).toLowerCase()), val);
				} else {
					temp.set(key, val);
				}
				//logger.debug(key + val);
			}
			result.addUISMap(temp);
		}
		return result;
	}
	
	public static UISMap makeUISMapFromMap(Map map) {
		return new UISMap(map);
	}
	
	public static UISMapList makeUISMapListFromMap(UISMap pUISMap) {
		return new UISMapList(pUISMap);
	}
	
	public static UISMap getSingleUISMap(UISMapList data) {
		UISMap result = null;
		if (data != null && data.keySize() > 0) {
			result = data.getUISMap(0);
		}
		
		if (result == null) {
			result = new UISMap();
		}
		return result;
	}
	
	public static UISMap[] toArrayUISMap(UISMapList list) {
		
		UISMap[] arrUISMap = new UISMap[list.getDataCount()];
		
		for (int idx=0; idx<list.getDataCount(); idx++) {
			arrUISMap[idx] = list.getUISMap(idx);
		}
		
		return arrUISMap;
	}
	
	*/
	
	/*
	 * 
	 * y j heon from ef.base.util.StringUtil
	 * */
	public static String camelize(String s, String splitPattern) {
		if((s==null)||("".equals(s))) {
			return s;
		}
		
		String[] parts = s.split(splitPattern);
		
		if(parts.length == 1) {
			return parts[0].toLowerCase();
		}
		
		StringBuffer camelized = new StringBuffer();
		
		for (int i =0; i< parts.length; ++i) {
			if((parts[i] == null) || (parts[i].equals(""))) {
				continue;
			}
			camelized.append(Character.toLowerCase(parts[i].charAt(0)));
			camelized.append(parts[i].substring(1).toLowerCase());
		}
		
		camelized.setCharAt(0, Character.toLowerCase(camelized.charAt(0)));
		
		return camelized.toString();
	}
	
	public static String camelize(String s) {
		return camelize(s, "_");
	}
	
	/*
	 * returnUISMapList
	 * @param list
	 * @return
	 * */
	
	/*
	public static UISMapList returnUISMapList(List<UISMap> list) {
		UISMapList uisMapList = new UISMapList();
		
		for(int idx=0; idx<list.size(); idx++) {
			UISMap result = list.get(idx);
			
			if(result != null) {
				uisMapList.addUISMap(result);
			}
		}
		return uisMapList;
	}
	*/
	
	
	
}
