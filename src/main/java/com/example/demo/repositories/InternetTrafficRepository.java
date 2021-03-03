package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Contact;
import com.example.demo.entities.InternetTraffic;

@Repository
public interface InternetTrafficRepository extends JpaRepository<InternetTraffic,Integer>{

	@Query(value="select * from cdr_internet_traffic e where e.phone_number like %:keyword%", nativeQuery=true)
	List<InternetTraffic> findByNumber(String keyword);
	
	@Query(value="select * from cdr_internet_traffic e where e.phone_number like %:keyword% and e.start_date_traffic like %:month%", nativeQuery=true)
	List<InternetTraffic> findByNumberAndMonth(String keyword,String month);
	
	
}
