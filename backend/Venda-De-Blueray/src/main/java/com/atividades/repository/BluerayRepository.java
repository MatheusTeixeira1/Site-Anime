package com.atividades.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atividades.models.Blueray;

public interface BluerayRepository extends JpaRepository<Blueray, Long>{
	List<Blueray> findByNomeContainingIgnoreCase(String name);
}
