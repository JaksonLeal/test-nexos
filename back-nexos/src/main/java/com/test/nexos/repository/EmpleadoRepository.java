package com.test.nexos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.nexos.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

	@Query(value = "SELECT * FROM empleado e WHERE e.documento_numero = ?1", nativeQuery = true)
	public Empleado findByDocumentoNumero(String documentoNumero);

	@Query(value = "DELETE FROM empleado e WHERE e.documento_numero = ?1", nativeQuery = true)
	public void deleteByDocumentoNumero(String documentoNumero);

}
