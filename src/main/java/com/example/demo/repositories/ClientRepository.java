package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer>{
	
	@Query(value="select * from client e where e.name like %:keyword%", nativeQuery=true)
	List<Client> findByKeyword(@Param("keyword") String keyword);
	
	@Query(value="select * from client e where e.phone like %:keynumber%", nativeQuery=true)
	List<Client> findClientByPhoneNumber(@Param("keynumber") String keynumber);

}
