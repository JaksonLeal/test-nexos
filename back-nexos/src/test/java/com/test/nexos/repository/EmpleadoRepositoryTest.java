package com.test.nexos.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.test.nexos.model.Empleado;

@DataJpaTest //sirve para probar componentes solo de la capa de persistencia
			 // o clases con la anotacion @Entity
public class EmpleadoRepositoryTest {
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Test //metodologia BDD
	void testGuardarEmpleado() {
		//given - dado o condicion previa o configuracion
		
		Empleado empleado1 = Empleado.builder()
				.apellido("leal")
				.correoElectronico("Yopal")
				.nombre("jakson")
				.documentoNumero("1234")
				.build();
		
		//when - accion o comportamiento que vamos a probar
		
		Empleado empleadoGuardado = empleadoRepository.save(empleado1);
		assertThat(empleadoGuardado).isNotNull();
		assertThat(empleadoGuardado.getId()).isGreaterThan(0);
		//then - verificar salida
		
	}

}
