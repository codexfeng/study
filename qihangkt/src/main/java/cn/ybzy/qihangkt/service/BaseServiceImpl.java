package cn.ybzy.qihangkt.service;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.ybzy.qihangkt.dao.BaseDao;
import cn.ybzy.qihangkt.tools.MapToEntityTool;

/**
 * BaseServiceImpl设计为抽象类
 * 
 * @author Administrator
 *
 * @param <T>
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	// 获取BaseDao对象的解决方法
	public abstract BaseDao getBaseDao();

	// 在增改删查方法中需要知道T是什么Class
	public Class<?> clazz;

	public String tableName;

	public BaseServiceImpl() {
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) type;
		Type[] types = pt.getActualTypeArguments();
		clazz = (Class<?>) types[0];
		// 获取数据库的表名
		tableName = "t_" + clazz.getSimpleName().toLowerCase();

	}

	@Override
	public void add(T t) {

		List<Object> list = new ArrayList<>();
		// 循环表的字段
		for (Field field : t.getClass().getDeclaredFields()) {
			// 打开获取private修饰的属性权限
			field.setAccessible(true);
			try {
				list.add(field.get(t));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		getBaseDao().add(tableName, list.toArray());
	}

	public void addForNotMatch(Object[] fieldNames, Object[] fieldVales) {
		getBaseDao().addForNotMatch(tableName, fieldNames, fieldVales);

	}

	@Override
	public void delete(int id) {
		getBaseDao().delete(tableName, id);

	}

	@Override
	public void update(T t) {
		List<Object> list = new ArrayList<>();
		int id = 0;
		for (Field field : t.getClass().getDeclaredFields()) {
			// 打开获取private修饰的属性权限
			field.setAccessible(true);
			try {
				if (field.get(t) == null) {
					continue;
				}
				if ("id".equals(field.getName())) {
					id = (int) field.get(t);
					continue;
				}
				list.add(field.getName() + "='" + field.get(t) + "'");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		getBaseDao().update(tableName, id, list.toArray());

	}

	@SuppressWarnings("unchecked")
	@Override
	public T select(int id) {
		// mybatis返回值是Map，需要转型T
		Map<Object, Object> rsMap = getBaseDao().select(tableName, id);

		return (T) MapToEntityTool.map2entity(rsMap, clazz);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> selectAll() {
		List<Map<Object, Object>> rsList = getBaseDao().selectAll(tableName);
		List<T> list = new ArrayList<>();
		T t = null;
		for (Map<Object, Object> map : rsList) {

			t = (T) MapToEntityTool.map2entity(map, clazz);
			list.add(t);
		}
		return list;
	}

}
