package com.test.nexos.controller;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import com.test.nexos.dto.EmpleadoDTO;
import com.test.nexos.model.Empleado;

//usar restTemplate con base de datos bloqueante como mysql
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // indica el orden de los metodos
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // indica que usareos un puerto random
public class EmpleadoControllerTestRestTemplateTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Order(1)
	void testSaveEmployee() {

		Empleado employee = Empleado.builder().apellido("leal")
				.correoElectronico("Yopal").nombre("jakson")
				.documentoNumero("211").build();
		ResponseEntity<?> response = restTemplate
				.postForEntity("http://localhost:8080/api-empleados/create",
						employee, null);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(22, response.getHeaders().getContentLength());

	}

	@Test
	@Order(2)
	void testGetEmployee() {

		String numDoc = "211";
		EmpleadoDTO empleado;
		ResponseEntity<?> response = restTemplate
				.getForEntity("http://localhost:8080/api-empleados/{documentoNumero}",
						EmpleadoDTO.class, numDoc);

		empleado = (EmpleadoDTO) response.getBody();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(empleado);
		assertEquals("jakson", empleado.getNombre());

	}

	@Test
	@Order(3)
	void testDeleteEmployee() {

		String numDoc = "211";
		ResponseEntity<Void> response = restTemplate
				.exchange("http://localhost:8080/api-empleados/{documentoNumero}",
						HttpMethod.DELETE, null, Void.class, numDoc);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(18, response.getHeaders().getContentLength());
		assertFalse(response.hasBody());

	}

	@Test
	@Order(4)
	void testGetListEmployee() {

		saveEmployeeToTestList();

		ResponseEntity<?> response = restTemplate.getForEntity("http://localhost:8080/api-empleados",
				EmpleadoDTO[].class);
		List <EmpleadoDTO> list = (List<EmpleadoDTO>) Arrays.asList(response.getBody());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		assertEquals(1, list.size());
		assertEquals(211, list.get(0).getDocumentoNumero());
	}

	private void saveEmployeeToTestList() {

		Empleado employee = Empleado.builder().apellido("leal")
				.correoElectronico("Yopal").nombre("jakson")
				.documentoNumero("211").build();
		restTemplate
				.postForEntity("http://localhost:8080/api-empleados/create",
						employee, null);

		Empleado employee2 = Empleado.builder().apellido("1")
				.correoElectronico("1").nombre("1")
				.documentoNumero("1").build();
		restTemplate
				.postForEntity("http://localhost:8080/api-empleados/create",
						employee2, null);

		Empleado employee3 = Empleado.builder().apellido("2")
				.correoElectronico("2").nombre("2")
				.documentoNumero("2").build();
		restTemplate
				.postForEntity("http://localhost:8080/api-empleados/create",
						employee3, null);

		Empleado employee4 = Empleado.builder().apellido("3")
				.correoElectronico("3").nombre("3")
				.documentoNumero("3").build();
		restTemplate
				.postForEntity("http://localhost:8080/api-empleados/create",
						employee4, null);

		Empleado employee5 = Empleado.builder().apellido("4")
				.correoElectronico("4").nombre("4")
				.documentoNumero("4").build();
		restTemplate
				.postForEntity("http://localhost:8080/api-empleados/create",
						employee5, null);

		Empleado employee6 = Empleado.builder().apellido("5")
				.correoElectronico("5").nombre("5")
				.documentoNumero("5").build();
		restTemplate
				.postForEntity("http://localhost:8080/api-empleados/create",
						employee6, null);

		Empleado employee7 = Empleado.builder().apellido("6")
				.correoElectronico("6").nombre("6")
				.documentoNumero("6").build();
		restTemplate
				.postForEntity("http://localhost:8080/api-empleados/create",
						employee7, null);

		Empleado employee8 = Empleado.builder().apellido("7")
				.correoElectronico("7").nombre("7")
				.documentoNumero("7").build();
		restTemplate
				.postForEntity("http://localhost:8080/api-empleados/create",
						employee8, null);

	}

}
