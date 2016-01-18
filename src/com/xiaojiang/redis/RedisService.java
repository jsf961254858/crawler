package com.xiaojiang.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xiaojiang.exception.ZYBException;

public interface RedisService {
	
	public long nextId() throws ZYBException;
	
	public boolean isContainsKey(String key) throws ZYBException;
	
	public void putStringReplace(String key, String value, Integer expiredDate)throws ZYBException;
	
	public void putStringList(String key, String value, Integer expiredDate)throws ZYBException;
	
	public void putStringAppend(String key, String value, Integer expiredDate)throws ZYBException;
	
	public void putMap(String key, Map<String, String> map, Integer expiredDate)throws ZYBException;
	
	public void putSet(String key, String value, Integer expiredDate)throws ZYBException;
	
	public String popSet(String key)throws ZYBException;
	
	public Set<String> getKeys(String notifyType)throws ZYBException;
	
	public String getStringAndDelete(String key)throws ZYBException;
	
	public String getString(String key)throws ZYBException;
	
	public Map<String, String> getMapAndDelete(String key)throws ZYBException;
	
	public Map<String, String> getMap(String key)throws ZYBException;
	
	public List<String> getList(String key, Long start, Long limit)throws ZYBException;
	
	public List<String> getAllListAndDelete(String key)throws ZYBException;
	
	public Set<String> getSet(String key)throws ZYBException;
	
	public String getPopList(String key)throws ZYBException;
	
	public String getIndexList(String key, int index)throws ZYBException;
	
	public Long getListSize(String key)throws ZYBException;
	
	public Long getMapSize(String key)throws ZYBException;
	
	public Long getSetSize(String key)throws ZYBException;
	
	public void delete(String key)throws ZYBException;
	
	public void setMapFieldValue(String key, String field, String value, Integer expiredDate)throws ZYBException;
	
	public String getMapFieldValue(String key, String field)throws ZYBException;
	
	public void doTest(String key, String field)throws ZYBException;

	
}
