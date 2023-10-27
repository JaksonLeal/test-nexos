import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Empleado } from 'src/app/model/Empleado';
import { EmpleadoService } from 'src/app/services/empleado.service';

@Component({
  selector: 'app-listar-empleado',
  templateUrl: './listar-empleado.component.html',
  styleUrls: ['./listar-empleado.component.css']
})
export class ListarEmpleadoComponent implements OnInit, OnDestroy {

  public displayedColumns: string[] = ['documentoTipo', 'documento', 'nombreCompleto', 'ciudad', 'direccion', 'correoElectronico', 'telefono', 'acciones'];
  public dataSource: MatTableDataSource<Empleado>;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private empleadoService: EmpleadoService) { }

  ngOnDestroy(): void { }

  ngOnInit(): void {
    this.empleadoService.listarEmpleados().subscribe(
      {
        next: (respuesta) => {
          console.log(respuesta)
          this.cargarDataSource(respuesta);
        },
        error: (error) => {
          let mensaje = error.error.text;
          let verificar = confirm(mensaje); // recarga la pagina si no esta montado el servidor
          verificar ? window.location.reload() : this.ngOnDestroy();
        },
        complete: () => {
          console.log("exitoso");
        }
      }
    );
  }
  posicion(empleado: Empleado): number { return this.dataSource.data.findIndex(e => e == empleado); }

  private cargarDataSource(empleados: Empleado[]): void {
    this.dataSource = new MatTableDataSource<Empleado>(empleados);
    this.dataSource.paginator = this.paginator;
  }

  editar(empleado: Empleado): void { console.log(empleado); }

  eliminar(empleado: Empleado): void {
    this.empleadoService.eliminarEmpleado(empleado.documentoNumero).subscribe(
      {
        next: (respuesta) => {
          console.log(respuesta);
        },
        error: (error) => {
          alert(error.error.text);
        },
        complete: () => {
          console.log("exitoso");
        }
      }
    );
    this.dataSource.data.splice(this.posicion(empleado), 1);
    this.cargarDataSource(this.dataSource.data);
  }

}
