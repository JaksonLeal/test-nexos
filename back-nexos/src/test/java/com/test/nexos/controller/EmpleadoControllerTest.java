package com.test.nexos.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.nexos.model.Empleado;
import com.test.nexos.service.EmpleadoService;

@RunWith(SpringRunner.class)
@WebMvcTest(EmpleadoController.class)
public class EmpleadoControllerTest { //test for each of cases in controller

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
  private EmpleadoService empleadoService;

	private Empleado empleado1;

	@DisplayName("Controller Test to save an employee") // asigna nombre
	@Test
	public void testSaveEmployee() throws Exception {

		// given ---> configuracion o condicion previa
		empleado1 = Empleado.builder().apellido("leal")
				.correoElectronico("Yopal").nombre("jakson")
				.documentoNumero("1234").build();
		given(empleadoService.createEmpleado(any(Empleado.class)))
				.willReturn(ResponseEntity.ok(null));

		// when ---> comportamiento que se va a probar
		var response = mockMvc.perform(post("/api-empleados/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(empleado1)));

		// then ----> verifica la salida
		response.andDo(print())
				.andExpect(status().isOk());

	}

	@DisplayName("Controller Test to get employee by DocumentoNumero") 
	@Test
	public void testGetEmpleadoByDocumentoNumero() throws Exception {

		// given ---> configuracion o condicion previa
		String numDoc = "1234";
		given(empleadoService.getEmpleadoByDocumentoNumero(numDoc))
				.willReturn(ResponseEntity.ok(null));

		// when ---> comportamiento que se va a probar
		var response = mockMvc.perform(get("/api-empleados/{documentoNumero}", numDoc));

		// then ----> verifica la salida
		response.andDo(print())
				.andExpect(status().isOk());

	}

	@DisplayName("Controller Test to delete employee") 
	@Test
	public void testDeleteEmployee() throws Exception {

		// given ---> configuracion o condicion previa
		String numDoc = "1234";
		given(empleadoService.deleteEmpleado(numDoc))
				.willReturn(ResponseEntity.ok(null));

		// when ---> comportamiento que se va a probar
		var response = mockMvc.perform(delete("/api-empleados/{documentoNumero}", numDoc));

		// then ----> verifica la salida
		response.andDo(print())
				.andExpect(status().isOk());

	}

	@DisplayName("Controller Test to get list employee") 
	@Test
	public void testListEmployee() throws Exception {

		// given ---> configuracion o condicion previa
		given(empleadoService.listEmpleados())
				.willReturn(ResponseEntity.ok(null));

		// when ---> comportamiento que se va a probar
		var response = mockMvc.perform(get("/api-empleados"));

		// then ----> verifica la salida
		response.andDo(print())
				.andExpect(status().isOk());

	}

}
