package cn.ybzy.qihangkt.service;

import java.util.List;

public interface BaseService<T> {

	// 增改删查
	//方法有一定的局限性，只能是entity类的属性个数和表格的列数在一对一的情况下才能使用
	public void add(T t);
	
	public void addForNotMatch(Object[] fieldNames, Object[] fieldVales);
	
	public void delete(int id);

	public void update(T t);

	public T select(int id);

	public List<T> selectAll();

}
