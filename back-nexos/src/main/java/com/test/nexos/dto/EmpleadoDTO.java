package com.test.nexos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EmpleadoDTO {
	
	private String documentoTipo;
	private String documentoNumero;
	private String nombre;
	private String apellido;
	private String ciudad;
	private String direccion;
	private String correoElectronico;
	private String telefono;

}
