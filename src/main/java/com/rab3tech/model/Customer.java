package com.rab3tech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="customer_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data // for getters and setters
public class Customer {
	@Id
	@Column(name="customer_id", nullable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;
	@Column(name="firstname", nullable=false)
	private String firstname;
	@Column(name="lastname", nullable=false)
	private String lastname;
	@Column(name="address", nullable=false)
	private String address;
	@Column(name="mobile", nullable=false)
	private String mobile;
	@Column(name="email", nullable=false, unique=true)
	private String email;
	@Column(name="password", nullable=false)
	private String password;

}
