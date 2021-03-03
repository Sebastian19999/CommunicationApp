package com.example.demo.quartz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "date")
public class QuartzEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_date;
	
	@Column(name="update_date")
	private String update_date;
	
	public QuartzEntity() {
		
	}
	
	

	public QuartzEntity(String update_date) {
		this.update_date = update_date;
	}



	public Integer getId_date() {
		return id_date;
	}

	public void setId_date(Integer id_date) {
		this.id_date = id_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}



	@Override
	public String toString() {
		return "QuartzEntity [id_date=" + id_date + ", update_date=" + update_date + "]";
	}
	
	
	
	
	
	
}
