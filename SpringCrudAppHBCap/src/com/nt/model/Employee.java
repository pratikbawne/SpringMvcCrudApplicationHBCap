package com.nt.model;

public class Employee {
	
	private Integer id;
	private String name;
	private String desg;
	
	public Employee() {
		super();
	}

	public Employee(Integer id, String name, String desg) {
		super();
		this.id = id;
		this.name = name;
		this.desg = desg;
	}

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

	public String getDesg() {
		return desg;
	}

	public void setDesg(String desg) {
		this.desg = desg;
	}
	

}
