package com.elmerikinnunen.shoppinglist.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private Long id;
	private String name;
	private int qty;
	private Long userId;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "departmentID")
	private Department department;
	
	public Product() {
		
	}
	
	public Product(Long id, String name,Long userId, int qty, Department department) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.qty = qty;
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		if (this.department == null) {
		return "Product [id=" + id + ", name=" + name + ", qty=" + qty;
		
		}else {
			 return "Product [id=" + id + ", name=" + name + ", qty=" + qty + ", department=" + this.getDepartment();
		}
	}
	

}
