package org.dbyz.java.reflact.tostring;

public class Entity2 {
	private int integer1;
	private Integer integer2;
	private long long3;
	private Long long4;
	private Car2 car = new Car2(12, 22, 12L, 22L);
	
	public Entity2() {
		super();
	}

	
	public Entity2(int integer1, Integer integer2, long long3, Long long4) {
		super();
		this.integer1 = integer1;
		this.integer2 = integer2;
		this.long3 = long3;
		this.long4 = long4;
	}

	
	@Override
	public String toString() {
		return "Entity2 [car=" + car + ", integer1=" + integer1 + ", integer2="
				+ integer2 + ", long3=" + long3 + ", long4=" + long4 + "]";
	}

	public int getInteger1() {
		return integer1;
	}

	public void setInteger1(int integer1) {
		this.integer1 = integer1;
	}

	public Integer getInteger2() {
		return integer2;
	}

	public void setInteger2(Integer integer2) {
		this.integer2 = integer2;
	}

	public long getLong3() {
		return long3;
	}

	public void setLong3(long long3) {
		this.long3 = long3;
	}

	public Long getLong4() {
		return long4;
	}

	public void setLong4(Long long4) {
		this.long4 = long4;
	}

}
