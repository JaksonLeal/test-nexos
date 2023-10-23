import { formatDate } from '@angular/common';
import { Component, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Departamento } from 'src/app/model/Departamento';
import { DepartamentoService } from 'src/app/services/departamento.service';

@Component({
  selector: 'app-crear-departamento',
  templateUrl: './crear-departamento.component.html',
  styleUrls: ['./crear-departamento.component.css']
})
export class CrearDepartamentoComponent implements OnDestroy {

  public departamento: Departamento;
  public fechaActual: String;

  constructor(private departamentoService: DepartamentoService, private router: Router) {
    this.departamento = new Departamento();
    this.fechaActual = formatDate(new Date(), 'yyyy-MM-dd', 'en-US');
  }
  ngOnDestroy(): void {
  }

  public crearDepartamento() {
    this.departamento.fechaHoraCrea = this.fechaActual;
    this.departamento.fechaHoraModifica = this.fechaActual;
    this.departamentoService.crearDepartamento(this.departamento).subscribe(
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
      });
  }

}
