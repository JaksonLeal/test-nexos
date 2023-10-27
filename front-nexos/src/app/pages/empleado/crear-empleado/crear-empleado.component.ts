import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Departamento } from 'src/app/model/Departamento';
import { Empleado } from 'src/app/model/Empleado';
import { DepartamentoService } from 'src/app/services/departamento.service';
import { EmpleadoService } from 'src/app/services/empleado.service';

@Component({
  selector: 'app-crear-empleado',
  templateUrl: './crear-empleado.component.html',
  styleUrls: ['./crear-empleado.component.css']
})
export class CrearEmpleadoComponent implements OnInit {

  public empleado: Empleado;
  public fechaActual: String;
  public departamentos: Departamento[];
  //los departamentos no traen id para enlazar con empleado
  constructor(private empleadoService: EmpleadoService, private departamentoService: DepartamentoService) {
    this.empleado = new Empleado();
    this.empleado.departamento = new Departamento();
    this.fechaActual = formatDate(new Date(), 'yyyy-MM-dd', 'en-US');
  }
  
  ngOnInit(): void {
    this.departamentoService.listarDepartamentos().subscribe(
      {
        next: (respuesta) => {
          this.departamentos = respuesta;
        },
        error: (error) => {
          let mensaje = error.error.text;
          alert(mensaje);
        },
        complete: () => {
          console.log("exitoso");
        }
      }
    );
  }
  ngOnDestroy(): void {
  }
  crearEmpleado() {
    this.empleado.fechaHoraCrea = this.fechaActual;
    this.empleado.fechaHoraModifica = this.fechaActual;
    console.log(this.empleado);
    this.empleadoService.crearEmpleado(this.empleado).subscribe(
      {
        next: (respuesta) => {
          console.log(respuesta);
        },
        error: (error) => {
          let mensaje = error.error.text;
          let verificar = confirm(mensaje);
          verificar ? window.location.reload() : this.ngOnDestroy();
        },
        complete: () => {
          console.log("exitoso");
        }
      }
    );
  }

}
