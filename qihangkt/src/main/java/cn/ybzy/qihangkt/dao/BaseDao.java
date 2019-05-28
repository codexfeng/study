package cn.ybzy.qihangkt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BaseDao {

	// 增删改查
	// 注解对应xml配置 映射，多个参数需要加注解
	public void add(@Param("tableName") String tableName, @Param("objects") Object[] objects);

	public void delete(@Param("tableName") String tableName, @Param("id") Integer id);

	// （参数）修改的表名，修改的数据id,修改的具体数据字段
	public void update(@Param("tableName") String tableName, @Param("id") Integer id,
			@Param("objects") Object... objects);

	// 查询需要返回数据，用集合Map接收. 通过ID查询一条数据
	public Map<Object, Object> select(@Param("tableName") String tableName, @Param("id") Integer id);

	// 查询所有的数据 查询那张表不确定，使用map封装
	public List<Map<Object, Object>> selectAll(@Param("tableName") String tableName);

	public void addForNotMatch(@Param("tableName") String tableName, @Param("fieldNames") Object[] fieldNames, @Param("fieldVales") Object[] fieldVales);
}
