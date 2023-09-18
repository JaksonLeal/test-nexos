package com.test.nexos.service;

import org.springframework.http.ResponseEntity;

import com.test.nexos.model.Departamento;

public interface DepartamentoService {

	public ResponseEntity<?> createDepartamento(Departamento departamento);

	public ResponseEntity<?> getDepartamentoByCodigo(String departamentoCodigo);

	public ResponseEntity<?> deleteDepartamento(String departamentoCodigo);

	public ResponseEntity<?> listDepartamentos();

}
