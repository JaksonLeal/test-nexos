package com.test.nexos.model;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Departamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String departamentoCodigo;
	private String departamentoNombre; 
	private Date fechaHoraCrea;
	private Date fechaHoraModifica;
	
	@OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
	private Set <Empleado> empleados = new LinkedHashSet<>();

}
