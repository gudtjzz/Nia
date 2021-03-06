package com.oraclejava.werim_lending_app.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryJSON {
	private String name;
	private List<MenuJSON> items = new ArrayList<>();
}
