package com.devProgram.javaCoreAssessment.enums;

public enum VariantEnum {
	T_SHIRT(1, "T-Shirt"), SNEAKERS(2, "Sneakers"), PANTS(3, "Pants");

	private final int index;
	private final String description;

	VariantEnum(int index, String description) {
		this.index = index;
		this.description = description;

	}

	public int getIndex() {
		return index;
	}

	public String getDescription() {
		return description;
	}

	public static VariantEnum fromIndex(int index) {
		for (VariantEnum variant : VariantEnum.values()) {
			if (variant.getIndex() == index) {
				return variant;
			}
		}
		return null;
	}

}
