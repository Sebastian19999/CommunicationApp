package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cdr_internet_traffic")
public class InternetTraffic {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_traffic;
	
	@Column(name="phone_number")
	private String phone_number;
	

	
	@Column(name="start_date_traffic")
	private String start_date_traffic;
	@Column(name="end_date_traffic")
	private String end_date_traffic;
	
	
	@Column(name="traffic_amount")
	private double traffic_amount;
	
	


	public InternetTraffic() {
		
	}
	
	public InternetTraffic(String phone_number) {
		super();
		this.phone_number = phone_number;
	}

	public Integer getId_traffic() {
		return id_traffic;
	}

	public void setId_traffic(Integer id_traffic) {
		this.id_traffic = id_traffic;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	
	

	public double getTraffic_amount() {
		return traffic_amount;
	}

	public void setTraffic_amount(double traffic_amount) {
		this.traffic_amount = traffic_amount;
	}

	public String getStart_date_traffic() {
		return start_date_traffic;
	}

	public void setStart_date_traffic(String start_date_traffic) {
		this.start_date_traffic = start_date_traffic;
	}

	public String getEnd_date_traffic() {
		return end_date_traffic;
	}

	public void setEnd_date_traffic(String end_date_traffic) {
		this.end_date_traffic = end_date_traffic;
	}

	@Override
	public String toString() {
		return "InternetTraffic [id_traffic=" + id_traffic + ", phone_number=" + phone_number + ", start_date_traffic="
				+ start_date_traffic + ", end_date_traffic=" + end_date_traffic + ", traffic_amount=" + traffic_amount
				+ "]";
	}

	 
	
	
	
	
	
	

}
