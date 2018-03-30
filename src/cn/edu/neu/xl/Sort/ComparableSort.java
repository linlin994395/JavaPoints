package cn.edu.neu.xl.Sort;

import java.io.Serializable;

/*
 * 自然排序的实体类要实现 java.lang.Comparable 接口，接口中提供了一个compareTo(Object obj)方法，我们通过重写这个方法来实现实体类对象的排序。
 */
public class ComparableSort implements Comparable<Object> {
	
	private int id;

	@Override
	public int compareTo(Object obj) {
        if(obj instanceof ComparableSort){
        	ComparableSort temp = (ComparableSort) obj;
        	return id - temp.id;
        }
		return 0;
	}

}
