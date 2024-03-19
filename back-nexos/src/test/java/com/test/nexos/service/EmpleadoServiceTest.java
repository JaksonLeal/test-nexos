package com.test.nexos.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.test.nexos.model.Empleado;
import com.test.nexos.repository.EmpleadoRepository;
import com.test.nexos.service.implement.EmpleadoServImpl;

@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceTest {

	@Mock
	private EmpleadoRepository empleadoRepository;

	@InjectMocks
	private EmpleadoServImpl empleadoServImpl;

	private Empleado empleado1, empleado2, empleado3;

	@BeforeEach // se ejecuta antes de cada metodo
	void setup() {
		empleado1 = Empleado.builder().apellido("leal").correoElectronico("Yopal").nombre("jakson")
				.documentoNumero("1234").build();

		empleado2 = Empleado.builder().apellido("asd").correoElectronico("asd").nombre("asd").documentoNumero("1111")
				.build();

		empleado3 = Empleado.builder().apellido("qwe").correoElectronico("qwe").nombre("qwe").documentoNumero("2222")
				.build();
	}

	@DisplayName("test para guardar un empleado")
	@Test
	void testGuardarEmpleado() {

		// given
		given(empleadoRepository.save(empleado1)).willReturn(empleado1);

		// when
		ResponseEntity<?> empleadoGuardado = empleadoServImpl.createEmpleado(empleado1);

		// then
		assertThat(empleadoGuardado.getBody()).isEqualTo("empleado " + empleado1.getNombre() + " creado");

	}

	@DisplayName("test para guardar un empleado con throwException")
	@Test
	void testGuardarEmpleadoThrowException() {

		// given
		given(empleadoRepository.findByDocumentoNumero(empleado1.getDocumentoNumero()))
				.willReturn(Optional.of(empleado1));

		// when
		assertThrows(RuntimeException.class, () -> {
			empleadoServImpl.createEmpleado(empleado1);
		});

		// then
		verify(empleadoRepository, never()).save(any(Empleado.class));
	}

	@DisplayName("test para listar empleados")
	@Test
	void testListarEmpleado() {

		// given
		given(empleadoRepository.findAll()).willReturn(List.of(empleado1, empleado2, empleado3));

		// when
		ResponseEntity<List<Empleado>> listEmpleados = empleadoServImpl.testListEmpleados();

		// then
		assertThat(listEmpleados.getBody()).isNotNull();
		assertThat(listEmpleados.getBody().size()).isEqualTo(3);
	}

	@DisplayName("test para retornar una lista de empleados vacia")
	@Test
	void testListarColeccionEmpleadosVacia() {

		// given
		given(empleadoRepository.findAll()).willReturn(Collections.emptyList());

		// when //pruebas descomentar repository
		List<Empleado> listEmpleados = empleadoRepository.findAll();

		// then
		assertThat(listEmpleados).isEmpty();
		assertThat(listEmpleados.size()).isEqualTo(0);

	}

	@DisplayName("test para obtener un empleado por ID")
	@Test
	void testObtenerEmpleadoById() {

		// given
		given(empleadoRepository.findByDocumentoNumero("1234")).willReturn(Optional.of(empleado1));

		// when
		Object empleadoGuardado = empleadoServImpl.getEmpleadoByDocumentoNumero(empleado1.getDocumentoNumero()).getBody();

		// then
		assertThat(empleadoGuardado).isEqualTo(Optional.of(empleado1));

	}

	@DisplayName("test para actualizar un empleado")
	@Test
	void testActualizarEmpleado() {

		// given
		// (empleadoRepository.save(empleado1)).willReturn(empleado1);
		// empleado1.setCorreoElectronico("jak@prueba.com");
		// empleado1.setNombre("rakson");
		// when
		// Empleado empleadoActualizado = empleadoServImpl.updateEmpleado(empleado1);

		// then
		// assertThat(empleadoActualizado.getCorreoElectronico()).isEqualTo("jak@prueba.com");
		// assertThat(empleadoActualizado.getNombre()).isEqualTo("rakson");

	}

	@DisplayName("test para eliminar un empleado")
	@Test
	void testEliminarEmpleado() {

		// given
		willDoNothing().given(empleadoRepository).deleteByDocumentoNumero("1234");

		// when
		empleadoServImpl.deleteEmpleado("1234");

		// then
		verify(empleadoRepository, times(1)).deleteByDocumentoNumero("1234");

	}

}
