package com.test.nexos.service;

import org.springframework.http.ResponseEntity;

import com.test.nexos.model.Empleado;

public interface EmpleadoService {

	public ResponseEntity<?> createEmpleado(Empleado empleado);

	public ResponseEntity<?> getEmpleadoByDocumentoNumero(String documentoNumero);

	public ResponseEntity<?> deleteEmpleado(String documentoNumero);

	public ResponseEntity<?> listEmpleados();

}
