package com.unifonic.assignment.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.unifonic.assignment.domain.auditor.BasicUpdatableAudit;

@Data
@Entity
@Table(name="USERS_VERIFICATION_CODES")
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NamedQuery(name="UserVerificationCodes.findAll", query="SELECT vc FROM UserVerificationCodes vc")
public class UserVerificationCodes extends BasicUpdatableAudit implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, updatable=false,nullable=false)
	private Long id;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="PHONE_NUMBER",unique=true,nullable=false)
	private String phoneNumber;
}
