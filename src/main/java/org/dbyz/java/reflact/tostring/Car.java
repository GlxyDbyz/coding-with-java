package org.dbyz.java.reflact.tostring;

public class Car {
	private int integer1;
	private Integer integer2;
	private long long3;
	private Long long4;

	public Car() {
		super();
	}

	@Override
	public String toString() {
		String result = null;
		try {
			result = ObjectToStringUtil.toString(this);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Car(int integer1, Integer integer2, long long3, Long long4) {
		super();
		this.integer1 = integer1;
		this.integer2 = integer2;
		this.long3 = long3;
		this.long4 = long4;
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