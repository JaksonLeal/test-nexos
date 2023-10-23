package com.test.nexos.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.nexos.dto.DepartamentoDTO;
import com.test.nexos.exception.DepartamentoException;
import com.test.nexos.model.Departamento;
import com.test.nexos.repository.DepartamentoRepository;
import com.test.nexos.service.DepartamentoService;

@Service
public class DepartamentoServImpl implements DepartamentoService {

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<?> createDepartamento(Departamento departamento) {
		try {
			departamentoRepository.save(departamento);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("departamento " + departamento.getDepartamentoNombre() + " creado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new DepartamentoException().getMessage());
		}
	}

	@Override
	public ResponseEntity<?> getDepartamentoByCodigo(String departamentoCodigo) {
		try {
			Departamento resultado = departamentoRepository.findByCodigo(departamentoCodigo);
			if (resultado != null) {
				DepartamentoDTO departamentoDTO = modelMapper.map(resultado, DepartamentoDTO.class);
				return ResponseEntity.status(HttpStatus.OK).body(departamentoDTO);
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(new DepartamentoException("No se encontro el departamento").getMessage());
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new DepartamentoException("No se pudo realizar la busqueda").getMessage());
		}
	}

	@Override
	public ResponseEntity<?> deleteDepartamento(String departamentoCodigo) {
		try {
			Departamento busqueda = departamentoRepository.findByCodigo(departamentoCodigo);
			departamentoRepository.delete(busqueda); 
			Departamento departamentoEliminado = departamentoRepository.findByCodigo(departamentoCodigo);
			if (departamentoEliminado == null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new DepartamentoException("Departamento eliminado").getMessage());
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(new DepartamentoException("No se pudo eliminar el departamento").getMessage());
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new DepartamentoException("Error al eliminar el departamento").getMessage());
		}
	}

	@Override
	public ResponseEntity<?> listDepartamentos() {
		try {
			List<Departamento> listadoDepartamento = departamentoRepository.findAll();
			if (!listadoDepartamento.isEmpty()) {
				List<DepartamentoDTO> listadoDepartamentoDTO = listadoDepartamento.stream()
						.map(departamento -> modelMapper.map(departamento, DepartamentoDTO.class))
						.collect(Collectors.toList());
				return ResponseEntity.status(HttpStatus.OK).body(listadoDepartamentoDTO);
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(new DepartamentoException("No hay departamentos para mostrar").getMessage());
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new DepartamentoException("No se pudo obtener el listado de departamentos").getMessage());
		}
	}
}
