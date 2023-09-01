package com.example.demo.model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_customers")
public class Customer{
	
	@Id
	private String cpf;
	private String name;
	private List<String> cel;
	private List<String> email;
	private List<String> carsPlate;
	private Integer points;
	
	public Customer() {
		
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getCel() {
		return cel;
	}

	public void setCel(List<String> cel) {
		this.cel = cel;
	}

	public List<String> getEmail() {
		return email;
	}

	public void setEmail(List<String> email) {
		this.email = email;
	}

	public List<String> getCarsPlate() {
		return carsPlate;
	}

	public void setCarsPlate(List<String> carsPlate) {
		this.carsPlate = carsPlate;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
}
