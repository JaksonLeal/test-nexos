package com.test.nexos.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.nexos.model.Empleado;
import com.test.nexos.service.EmpleadoService;

@RestController
@RequestMapping("api-empleados")
@CrossOrigin("*")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	@PostMapping("/create")
	public ResponseEntity<?> createEmpleado(@RequestBody Empleado empleado) {
		System.out.println("el empleado trae: " + empleado.toString());
		return empleadoService.createEmpleado(empleado);
	}

	@GetMapping("/{documentoNumero}")
	public ResponseEntity<Optional<Empleado>> getEmpleadoByDocumentoNumero(@PathVariable String documentoNumero) {
		System.out.println("el codigo del empleado essa: " + documentoNumero);
		return empleadoService.testGetEmpleadoByDocumentoNumero(documentoNumero);
	}

	@DeleteMapping("/{documentoNumero}")
	public ResponseEntity<?> deleteEmpleado(@PathVariable String documentoNumero) {
		System.out.println("el numero de documento del empleado es: " + documentoNumero);
		return empleadoService.deleteEmpleado(documentoNumero);
	}

	@GetMapping
	public ResponseEntity<?> listEmpleados() {
		System.out.println("entro a listar empleados");
		return empleadoService.listEmpleados();
	}

}
