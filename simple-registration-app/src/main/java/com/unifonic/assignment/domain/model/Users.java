package com.unifonic.assignment.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.unifonic.assignment.domain.auditor.BasicUpdatableAudit;



@Data
@Entity
@Table(name="USERS")
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NamedQuery(name="Users.findAll", query="SELECT u FROM Users u")
public class Users extends BasicUpdatableAudit implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, updatable=false,nullable=false)
	private Long id;

	@Column(name="FIRST_NAME",length=20,nullable=false)
	private String firstName;
	
	@Column(name="LAST_NAME",length=20,nullable=false)
	private String lastName;

	@Column(name="EMAIL",unique=true,length=20,nullable=false)
	private String email;
	
	@Column(name="phone_NUMBER",unique=true,length=20,nullable=false)
	private String phoneNumber;
	
	
	


}
