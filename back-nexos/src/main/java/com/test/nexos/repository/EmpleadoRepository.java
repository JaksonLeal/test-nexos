package com.test.nexos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.test.nexos.model.Empleado;

import jakarta.transaction.Transactional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

	@Query(value = "SELECT * FROM empleado e WHERE e.documento_numero = ?1", nativeQuery = true)
	public Optional<Empleado> findByDocumentoNumero(String documentoNumero);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM empleado e WHERE e.documento_numero = ?1", nativeQuery = true)
	public void deleteByDocumentoNumero(String documentoNumero);

}
