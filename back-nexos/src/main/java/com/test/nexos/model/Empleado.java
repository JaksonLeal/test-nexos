
package com.test.nexos.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String documentoTipo;
	@Column(unique = true) //unico en la tabla
	private String documentoNumero;
	private String nombre;
	private String apellido;
	private String ciudad;
	private String direccion;
	private String correoElectronico;
	private String telefono;
	private Date fechaHoraCrea;
	private Date fechaHoraModifica;

	@ManyToOne(fetch = FetchType.EAGER) //por defecto es fetch = FetchType.EAGER
	private Departamento departamento;
}
