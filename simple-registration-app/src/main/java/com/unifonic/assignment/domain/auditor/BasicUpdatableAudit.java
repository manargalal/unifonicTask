package com.unifonic.assignment.domain.auditor;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public abstract class BasicUpdatableAudit extends BasicCreationalAudit {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@LastModifiedBy
	@Column(name = "UPDATED_BY", nullable= false)
	private String updatedBy;

	@JsonIgnore
	@LastModifiedDate
	@Column(name = "UPDATED_AT",nullable= false)
	private Instant updatedAt;
}
