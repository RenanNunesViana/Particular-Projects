package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "CPF")
    private String cpf;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ROLE")
    private String role;
    
    @Column(name = "CEL")
    private String cel;
    
    @Column(name = "EMAIL")
	private String email;
    
    @Column(name = "CARSPLATE")
    private List<String> carsPlate;

    public User() {
     this.carsPlate = new ArrayList<>();	
    }
    
    public User(String cpf, String password, String firstName, String lastName) {
        this.cpf = cpf;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.carsPlate = new ArrayList<>();
    }

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCel() {
		return cel;
	}

	public void setCel(String cel) {
		this.cel = cel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getCarsPlate() {
		return carsPlate;
	}

	public void setCarsPlate(List<String> carsPlate) {
		this.carsPlate = carsPlate;
	}
    
}
