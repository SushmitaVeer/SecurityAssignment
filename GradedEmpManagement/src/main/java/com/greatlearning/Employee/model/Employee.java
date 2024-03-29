package com.greatlearning.Employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 	@Column(name = "id")
	    private int id;
	 	@Column(name = "firstName")
	 	private String firstName;
	 	@Column(name = "lastName")
	    private String lastName;
	 	@Column(name = "email")
	    private String email;
}
