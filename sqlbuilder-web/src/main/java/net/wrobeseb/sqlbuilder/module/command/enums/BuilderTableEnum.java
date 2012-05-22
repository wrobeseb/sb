package net.wrobeseb.sqlbuilder.module.command.enums;

public enum BuilderTableEnum {
	ORDERS("o"),
	ORDER_INTERACTIONS("oi"),
	ORDER_CONTRACTS("oc"),
	ORDER_HISTORY_CHANGES("ohc"),
	ORDER_HISTORY_SYS_CHANGES("ohsc");
	
	private String prefix;

	private BuilderTableEnum(String prefix) {
		this.prefix = prefix;
	}

	public String getPrefix() {
		return prefix;
	}
}
