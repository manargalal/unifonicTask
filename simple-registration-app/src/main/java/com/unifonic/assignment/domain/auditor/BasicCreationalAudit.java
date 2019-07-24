package com.unifonic.assignment.domain.auditor;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BasicCreationalAudit implements Serializable {

	private static final long serialVersionUID = 1L;

	@CreatedBy
	@JsonIgnore
	@Column(name = "CREATED_BY",nullable = false ,updatable = false)
	private String createdBy;

	@JsonIgnore
	@CreatedDate
	@Column(name = "CREATED_AT",nullable = false,updatable = false)
	private Instant createdAt;
}
