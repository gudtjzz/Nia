package com.oraclejava.werim_lending_app.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryOptionJSON {
	private List<CategoryJSON> categories = new ArrayList<>();
	private List<OptionsJSON> options = new ArrayList<>();
}
