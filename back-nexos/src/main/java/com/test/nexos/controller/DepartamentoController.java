package com.test.nexos.controller;

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

import com.test.nexos.model.Departamento;
import com.test.nexos.service.DepartamentoService;

@RestController
@RequestMapping("api-departamentos")
@CrossOrigin("*")
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;

	@PostMapping("/create")
	public ResponseEntity<?> createDepartamento(@RequestBody Departamento departamento) {
		System.out.println("el departamento trae:" + departamento.toString());
		return departamentoService.createDepartamento(departamento);
	}

	@GetMapping("/{departamentoCodigo}")
	public ResponseEntity<?> getDepartamentoByCodigo(@PathVariable String departamentoCodigo) {
		System.out.println("el codigo del departamento es: " + departamentoCodigo);
		return departamentoService.getDepartamentoByCodigo(departamentoCodigo);
	}

	@DeleteMapping("/{departamentoCodigo}")
	public ResponseEntity<?> deleteDepartamento(@PathVariable String departamentoCodigo) {
		System.out.println("el codigo del departamento es: " + departamentoCodigo);
		return departamentoService.deleteDepartamento(departamentoCodigo);
	}

	@GetMapping
	public ResponseEntity<?> listDepartamentos() {
		String aux = """
				el poder
				ahora es
				MIO
				""";
		System.out.println("entro a listar departamentos %s %s".formatted(aux, "ño"));
		return departamentoService.listDepartamentos();
	}

}
