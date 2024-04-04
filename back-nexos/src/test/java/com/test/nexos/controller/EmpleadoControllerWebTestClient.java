package com.test.nexos.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.test.nexos.dto.EmpleadoDTO;
import com.test.nexos.model.Empleado;

import java.util.List;

//para trabjar con webflux o peticiones asincronas se usa webTestClient

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmpleadoControllerWebTestClient {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  @Order(1)
  void testSaveEmployee() {
    // given
    Empleado empleado = Empleado.builder()
        .documentoNumero("211")
        .nombre("jakson")
        .apellido("leal")
        .correoElectronico("jaklebu@gmail.com")
        .build();

    // when
    webTestClient.post().uri("http://localhost:8080/api-empleados/create")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(empleado)
        .exchange() // envia el request

        // then
        .expectStatus().isCreated()
        .expectHeader().contentLength(22);
  }

  @Test
  @Order(2)
  void testGetEmployee() {
    String numDoc = "211";
    webTestClient.get().uri("http://localhost:8080/api-empleados/{documentoNumero}", numDoc).exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBody()
        .jsonPath("$.documentoNumero").isEqualTo("211")
        .jsonPath("$.nombre").isEqualTo("jakson")
        .jsonPath("$.apellido").isEqualTo("leal")
        .jsonPath("$.correoElectronico").isEqualTo("jaklebu@gmail.com");
    ;
  }

  @Test
  @Order(3)
  void testGetListEmployee() {
    webTestClient.get().uri("http://localhost:8080/api-empleados").exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBodyList(EmpleadoDTO.class)
        .consumeWith(response -> {
          List<EmpleadoDTO> listEmployee = response.getResponseBody();
          Assertions.assertEquals(1, listEmployee.size());
          Assertions.assertNotNull(listEmployee);
        });
  }

  @Test
  @Order(4)
  void testDeleteEmployee() {
    webTestClient.get().uri("http://localhost:8080/api-empleados").exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBodyList(EmpleadoDTO.class)
        .hasSize(1);

    String numDoc = "211";
    webTestClient.delete().uri("http://localhost:8080/api-empleados/{documentoNumero}", numDoc)
        .exchange()
        .expectStatus().isOk();

    webTestClient.get().uri("http://localhost:8080/api-empleados").exchange()
        .expectStatus().is4xxClientError();

    webTestClient.get().uri("http://localhost:8080/api-empleados/{documentoNumero}", numDoc).exchange()
        .expectStatus().isOk();
  }

}
