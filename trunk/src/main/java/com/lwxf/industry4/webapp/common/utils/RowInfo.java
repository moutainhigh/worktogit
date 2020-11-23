package com.lwxf.industry4.webapp.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 数据库中表的行信息
 * 作者: 强乐
 * 时间：2015年10月23日11:19:52
 */
public class RowInfo {
	
	private List<String> columnNames=new ArrayList<String>();
	
	private Map<String,Object> columnValues=new TreeMap<String,Object>();
	
	/**
	 * 添加列信息
	 */
	public boolean addColumInfo(String columName){
		boolean isSuccess=false;
		columnNames.add(columName);
		return isSuccess;
	}
	
	/**
	 * 添加列信息
	 */
	public boolean addColumInfo(List<String> columnNames){
		boolean isSuccess=false;
		this.columnNames=columnNames;
		return isSuccess;
	}
	
	/**
	 * 获取列信息
	 */
	public List<String> getColumInfo(){
		return columnNames;
	}
	
	/**
	 * 添加列的值
	 */
	public boolean addColumValue(String columName,Object columValue){
		boolean isSuccess=false;
		columnValues.put(columName, columValue);
		return isSuccess;
	}
	
	/**
	 * 获取列信息
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> T getColumValue(String columName){
		T t=null;
		try{
			t=(T)columnValues.get(columName);
		}catch(Exception e){}
		return t;
	}
	
	/**
	 * 获取整行信息
	 */
	public List<Object> getRowValue(){
		List<Object> returnList=new ArrayList<Object>();
		if(columnValues!=null && columnValues.size()>0){
			for(Object key : columnValues.keySet().toArray()){
				returnList.add(columnValues.get(key));
			}
		}
		return returnList;
	}
	
	/**
	 * 获取整行Map
	 * @return
	 */
	public Map<String,Object> getColumnValues(){
		return columnValues;
	}
}