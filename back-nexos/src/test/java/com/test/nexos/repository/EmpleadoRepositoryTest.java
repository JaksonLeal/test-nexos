package com.test.nexos.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.test.nexos.model.Empleado;

@DataJpaTest // sirve para probar componentes solo de la capa de persistencia
				// o clases con la anotacion @Entity o @Repository
public class EmpleadoRepositoryTest {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	private Empleado empleado1, empleado2, empleado3;

	@BeforeEach // se ejecuta antes de cada metodo
	void setup() {
		empleado1 = Empleado.builder()
				.apellido("leal")
				.correoElectronico("Yopal")
				.nombre("jakson")
				.documentoNumero("1234")
				.build();

		empleado2 = Empleado.builder()
				.apellido("asd")
				.correoElectronico("asd")
				.nombre("asd")
				.documentoNumero("1111")
				.build();

		empleado3 = Empleado.builder()
				.apellido("qwe")
				.correoElectronico("qwe")
				.nombre("qwe")
				.documentoNumero("2222")
				.build();
	}

	@DisplayName("Test para guardar un empleado") // asigna nombre
	@Test // metodologia BDD
	void testSaveEmpleado() {
		// given - dado o condicion previa o configuracion

		// se ejecuta el metodo setup por defecto

		// when - accion o comportamiento que vamos a probar

		Empleado empleadoGuardado = empleadoRepository.save(empleado1);

		// then - verificar salida

		assertThat(empleadoGuardado).isNotNull();
		assertThat(empleadoGuardado.getId()).isGreaterThan(0);

	}

	@DisplayName("Test para listar a los empleados") // asigna nombre
	@Test
	void testListEmpleados() {

		// given - dado o condicion previa o configuracion

		empleadoRepository.save(empleado1);
		empleadoRepository.save(empleado2);
		empleadoRepository.save(empleado3);

		// when - accion o comportamiento que vamos a probar

		List<Empleado> listadoEmpleados = empleadoRepository.findAll();

		// then - verificar salida

		assertThat(listadoEmpleados).isNotNull();
		assertThat(listadoEmpleados.size()).isEqualTo(3);
	}

	@DisplayName("Test para obtener un empleado por cedula") // asigna nombre
	@Test
	void testGetEmpleadoByDocumentoNumero() {
		// given
		empleadoRepository.save(empleado1);
		// when
		Optional <Empleado> empleadoResultado = empleadoRepository.findByDocumentoNumero(empleado1.getDocumentoNumero());

		// then

		assertThat(empleadoResultado).isNotNull();
		assertThat(empleadoResultado.get().getId()).isGreaterThan(0);

	}

	@DisplayName("Test para eliminar un empleado por cedula") // asigna nombre
	@Test
	void testDeleteEmpleadoByDocumentoNumero() {
		// given
		empleadoRepository.save(empleado1);
		// when
		empleadoRepository.deleteByDocumentoNumero(empleado1.getDocumentoNumero());
		Optional <Empleado> empleadoResultado = empleadoRepository.findByDocumentoNumero(empleado1.getDocumentoNumero());
		// then

		assertThat(empleadoResultado).isNull();

	}

}
