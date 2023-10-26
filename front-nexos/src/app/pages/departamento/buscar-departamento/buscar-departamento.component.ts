import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Departamento } from 'src/app/model/Departamento';
import { DepartamentoService } from 'src/app/services/departamento.service';

@Component({
  selector: 'app-buscar-departamento',
  templateUrl: './buscar-departamento.component.html',
  styleUrls: ['./buscar-departamento.component.css']
})
export class BuscarDepartamentoComponent {

  public auxBusqueda: boolean = false;
  public departamento: Departamento;
  
  constructor(private departamentoService: DepartamentoService) { }

  buscar(departamentoCodigo: String): void {
    this.departamentoService.buscarDepartamento(departamentoCodigo).subscribe(
      {
        next: (respuesta) => {
          console.log(respuesta);
          this.departamento = respuesta;
          this.auxBusqueda = true;
        },
        error: (error) => {
          alert(error.error.text);
        },
        complete: () => {
          console.log("exitoso");
        }
      }
    );

  }

  editar(departamento: Departamento): void { console.log(departamento); }

  eliminar(departamento: Departamento): void {
    this.departamentoService.eliminarDepartamento(departamento.departamentoCodigo).subscribe(
      {
        next: (respuesta) => {
          console.log(respuesta);
        },
        error: (error) => {
          alert(error.error.text);
          window.location.reload();
        },
        complete: () => {
          console.log("exitoso");
        }
      }
    );
  }
}
