package com.example.demo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	@Column(name="address")
	private String address;
	@Column(name="phone")
	private String phone;
	@Column(name="balance")
	private double balance;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn( name = "sub_fid", referencedColumnName = "id_subscription")
	private Subscription subscription;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn( name = "pc_fid", referencedColumnName = "id_extra")
	private ExtraCharge extraCharge;
	
	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public ExtraCharge getExtraCharge() {
		return extraCharge;
	}

	public void setExtraCharge(ExtraCharge extraCharge) {
		this.extraCharge = extraCharge;
	}

	public Client() {
		
	}

	public Client(String name, String address, String phone, double balance) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", balance="
				+ balance + "]";
	}
	
	

}
