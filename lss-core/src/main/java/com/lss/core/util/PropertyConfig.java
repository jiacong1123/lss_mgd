package com.lss.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.StringUtils;

public class PropertyConfig extends PropertyPlaceholderConfigurer
{
	
	
	  private static Map<String, String> map=new HashMap<String, String>();  //�����Ա�������  
	  
	  @Override  
	  protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,  
	      Properties props) throws BeansException {
		  logger.info("init propertiesLoad props values:" + props);
		  super.processProperties(beanFactoryToProcess, props);
		  for (Object key : props.keySet()) {  
	          String keyStr = key.toString();
	          String value=null;
	          if(keyStr!=null)
	          {
	        	  value=props.getProperty(keyStr);
	          }
	          map.put(keyStr, value);
	      }  
	  } 
	  
	  public static void initValueToStaticMap(String key, String value)
	  {
		  if(key != null && !"".equals(key) && value != null && !"".equals(value))
		  {
			  map.put(key, value);
		  }else
		  {
			  //logger.error("init local properties data failure, reason:key or value is null");
		  }
	  }
	  
	  public static String getValueBykey(String key)
	  {
		  String value=map.get(key);
		  if(StringUtils.isEmpty(value))
		  {
			  value = null;
		  }
		  return value;
	  }
}
  