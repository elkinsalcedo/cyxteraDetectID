package com.cyxtera.detectid.persistence.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "client")
@XmlRootElement

public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;

	@Column(name= "shared_key")
	@Basic(optional = false)
	private String sharedKey;
	
	@Column(name= "nombre")
	@Basic(optional = false)
	private String nombre;
	
	@Column(name= "email")
	@Basic(optional = false)
	private String email;
	
	@Column(name= "phone")
	@Basic(optional = false)
	private BigInteger phone;
	
	@Column(name= "registered_at")
	@Basic(optional = false)
	private LocalDateTime registeredAt;
	
	@Column(name= "start_date")
	@Basic(optional = false)
	private LocalDate startDate;
	
	@Column(name= "end_date")
	@Basic(optional = false)
	private LocalDate endDate;

	public Client() {
    }

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the sharedKey
	 */
	public String getSharedKey() {
		return sharedKey;
	}

	/**
	 * @param sharedKey the sharedKey to set
	 */
	public void setSharedKey(String sharedKey) {
		this.sharedKey = sharedKey;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public BigInteger getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(BigInteger phone) {
		this.phone = phone;
	}

	/**
	 * @return the registeredAt
	 */
	public LocalDateTime getRegisteredAt() {
		return registeredAt;
	}

	/**
	 * @param registeredAt the registeredAt to set
	 */
	public void setRegisteredAt(LocalDateTime registeredAt) {
		this.registeredAt = registeredAt;
	}

	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
}

