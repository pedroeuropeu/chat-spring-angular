package com.chat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

/**
 *
 * @author Pedro
 */
@Entity
@Table(name = "tb_user")
@Data
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_USER_SEQ")
	@SequenceGenerator(name = "TB_USER_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

	@Column(length = 255, nullable = false)
	private String name;
	
	@Column(length = 255, nullable = false)
	private String email;

	public User() {
		super();
	}

}
