package cn.ybzy.qihangkt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BaseDao {

	// ��ɾ�Ĳ�
	// ע���Ӧxml���� ӳ�䣬���������Ҫ��ע��
	public void add(@Param("tableName") String tableName, @Param("objects") Object[] objects);

	public void delete(@Param("tableName") String tableName, @Param("id") Integer id);

	// ���������޸ĵı������޸ĵ�����id,�޸ĵľ��������ֶ�
	public void update(@Param("tableName") String tableName, @Param("id") Integer id,
			@Param("objects") Object... objects);

	// ��ѯ��Ҫ�������ݣ��ü���Map����. ͨ��ID��ѯһ������
	public Map<Object, Object> select(@Param("tableName") String tableName, @Param("id") Integer id);

	// ��ѯ���е����� ��ѯ���ű�ȷ����ʹ��map��װ
	public List<Map<Object, Object>> selectAll(@Param("tableName") String tableName);

	public void addForNotMatch(@Param("tableName") String tableName, @Param("fieldNames") Object[] fieldNames, @Param("fieldVales") Object[] fieldVales);
}
