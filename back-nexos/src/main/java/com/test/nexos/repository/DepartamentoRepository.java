package com.test.nexos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.nexos.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

	@Query(value = "SELECT * FROM departamento d WHERE d.departamento_codigo = ?1", nativeQuery = true)
	public Departamento findByCodigo(String departamentoCodigo);

}
