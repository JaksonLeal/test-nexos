import { Component } from '@angular/core';
import { Empleado } from 'src/app/model/Empleado';
import { EmpleadoService } from 'src/app/services/empleado.service';

@Component({
  selector: 'app-buscar-empleado',
  templateUrl: './buscar-empleado.component.html',
  styleUrls: ['./buscar-empleado.component.css']
})
export class BuscarEmpleadoComponent {

  public auxBusqueda: boolean = false;
  public empleado: Empleado;

  constructor(private departamentoService: EmpleadoService) { }

  buscar(documentoNumero: String): void {
    this.departamentoService.buscarEmpleado(documentoNumero).subscribe(
      {
        next: (respuesta) => {
          console.log(respuesta);
          this.empleado = respuesta;
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

  editar(empleado: Empleado): void { console.log(empleado); }

  eliminar(empleado: Empleado): void {
    this.departamentoService.eliminarEmpleado(empleado.documentoNumero).subscribe(
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
