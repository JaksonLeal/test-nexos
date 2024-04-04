package com.test.nexos.service.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.nexos.dto.EmpleadoDTO;
import com.test.nexos.exception.DepartamentoException;
import com.test.nexos.exception.EmpleadoException;
import com.test.nexos.model.Empleado;
import com.test.nexos.repository.EmpleadoRepository;
import com.test.nexos.service.EmpleadoService;

@Service
public class EmpleadoServImpl implements EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<?> createEmpleado(Empleado empleado) {
		try {
			empleadoRepository.save(empleado);
			return ResponseEntity.status(HttpStatus.CREATED).body("empleado " + empleado.getNombre() + " creado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new EmpleadoException().getMessage());
		}
	}

	@Override
	public ResponseEntity<?> getEmpleadoByDocumentoNumero(String documentoNumero) {
		try {
			Optional<Empleado> empleadoEncontrado = empleadoRepository.findByDocumentoNumero(documentoNumero);
			if (empleadoEncontrado != null) {
				EmpleadoDTO empleadoDTO = modelMapper.map(empleadoEncontrado,
						EmpleadoDTO.class);
				return ResponseEntity.status(HttpStatus.OK).body(empleadoDTO);
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(new DepartamentoException("No se encontro el empleado").getMessage());
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new DepartamentoException("Error al realizar la busqueda").getMessage());
		}
	}

	@Override
	public ResponseEntity<String> deleteEmpleado(String documentoNumero) {
		try {
			empleadoRepository.deleteByDocumentoNumero(documentoNumero);
			System.out.println("el documento en esi: "+ documentoNumero);
			Optional<Empleado> empleadoEliminado = empleadoRepository.findByDocumentoNumero(documentoNumero);
			System.out.println(empleadoEliminado.toString());
			if (empleadoEliminado.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new DepartamentoException("Empleado eliminado").getMessage());
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(new DepartamentoException("No se pudo eliminar el empleado").getMessage());
			}
		} catch (Exception e) {
			System.out.println(e);

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new DepartamentoException("Error al eliminar el empleado").getMessage());
		}
	}

	@Override
	public ResponseEntity<?> listEmpleados() {
		try {
			List<Empleado> listadoEmpleado = empleadoRepository.findAll();
			if (!listadoEmpleado.isEmpty()) {
				List<EmpleadoDTO> listadoEmpleadoDTO = listadoEmpleado.stream()
						.map(empleado -> modelMapper.map(empleado, EmpleadoDTO.class)).collect(Collectors.toList());
				return ResponseEntity.status(HttpStatus.OK).body(listadoEmpleadoDTO);
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(new DepartamentoException("No hay empleados para mostrar").getMessage());
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new DepartamentoException("Error obtener el listado de empleados").getMessage());
		}
	}

	/* TEST LISTAR EMPLEADOS */
	@Override
	public ResponseEntity<List<Empleado>> testListEmpleados() {
		List<Empleado> listadoEmpleado = null;
		try {
			listadoEmpleado = empleadoRepository.findAll();
			if (!listadoEmpleado.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(listadoEmpleado);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/* TEST PARA OBTENER EMPLEADO POR ID */
	@Override
	public ResponseEntity<Optional<Empleado>> testGetEmpleadoByDocumentoNumero(String documentoNumero) {
		try {
			Optional<Empleado> empleadoEncontrado = empleadoRepository.findByDocumentoNumero(documentoNumero);
			if (empleadoEncontrado != null) {
				return ResponseEntity.status(HttpStatus.OK).body(empleadoEncontrado);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

}
