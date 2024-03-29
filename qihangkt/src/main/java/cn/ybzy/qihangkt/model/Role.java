package cn.ybzy.qihangkt.model;

public class Role {

	private Integer id;
	private String name;
	private String code;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", code=" + code + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// 判断两个roel对象是不是相等的规则，需要重写id值一样，就认为role对象是相等的
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Role))
			return false;
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		return true;

	}

}
