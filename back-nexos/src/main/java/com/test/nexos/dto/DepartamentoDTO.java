package com.test.nexos.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartamentoDTO {
	private String departamentoCodigo;
	private String departamentoNombre;
	private Set<EmpleadoDTO> empleados;
}
