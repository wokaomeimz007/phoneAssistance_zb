package cn.com.zb.phoneassistance.utils;

public class BatteryInfo {
	private String health;
	private String temperature;
	private String typeTech;

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getTypeTech() {
		return typeTech;
	}

	public void setTypeTech(String typeTech) {
		this.typeTech = typeTech;
	}

	@Override
	public String toString() {
		return "health" + "=" + health + ",temperature" + "=" + temperature
				+ ",typeTech" + "=" + typeTech;
	}

}
