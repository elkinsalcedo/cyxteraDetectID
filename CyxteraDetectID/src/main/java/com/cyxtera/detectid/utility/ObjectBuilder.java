/**
 * 
 */
package com.cyxtera.detectid.utility;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * @author esalcedo
 *
 */
public class ObjectBuilder {
	/**
	 * @param inClass
	 * @param outClass
	 * @param object
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object convertObject(Class inClass,Class outClass,Object object) {
		 MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		 mapperFactory.classMap(inClass,outClass);
		 MapperFacade mapper = mapperFactory.getMapperFacade();
		 return mapper.map(object, outClass);
	}
	
	/**
	 * @param inClass
	 * @param outClass
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List  converListObject(Class inClass,Class outClass,List list){
		List result=new ArrayList<Object>();
		for(Object obj:list) {
			result.add(convertObject(inClass,outClass,obj));
		}
		return result;
	}
	
	/**
	 * @param iterable
	 * @return
	 */
	public static <E> List<E> toList(Iterable<E> iterable) {
	    if(iterable instanceof List) {
	      return (List<E>) iterable;
	    }
	    ArrayList<E> list = new ArrayList<E>();
	    if(iterable != null) {
	      for(E e: iterable) {
	        list.add(e);
	      }
	    }
	    return list;
	  }	  

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object convertObject(Class inClass,Class outClass,Object object, String[] excludeParams) {
		 MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		 if(excludeParams != null && excludeParams.length > 0) {
			 for (String exclude : excludeParams) {
				 mapperFactory.classMap(inClass,outClass).exclude(exclude);
			}
		 }else
			 mapperFactory.classMap(inClass,outClass);
		 MapperFacade mapper = mapperFactory.getMapperFacade();
		 return mapper.map(object, outClass);
	}
	

	/**
	 * @param inClass
	 * @param outClass
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List  converListObject(Class inClass,Class outClass,List list, String[] excludeParams){
		List result=new ArrayList<Object>();
		for(Object obj:list) {
			result.add(convertObject(inClass,outClass,obj, excludeParams));
		}
		return result;
	}
}
