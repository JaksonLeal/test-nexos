import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource, MatTableDataSourcePaginator } from '@angular/material/table';
import { Departamento } from 'src/app/model/Departamento';
import { DepartamentoService } from 'src/app/services/departamento.service';

@Component({
  selector: 'app-listar-departamento',
  templateUrl: './listar-departamento.component.html',
  styleUrls: ['./listar-departamento.component.css']
})
export class ListarDepartamentoComponent implements OnInit, OnDestroy {

  public displayedColumns: string[] = ['contador', 'departamentoCodigo', 'departamentoNombre', 'acciones'];
  public dataSource: MatTableDataSource<Departamento>;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private departamentoService: DepartamentoService) { }

  ngOnDestroy(): void { }

  ngOnInit(): void {
    this.departamentoService.listarDepartamentos().subscribe(
      {
        next: (respuesta) => {
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
  posicion(departamento: Departamento): number { return this.dataSource.data.findIndex(d => d == departamento); }

  cargarDataSource(departamentos: Departamento[]): void {
    this.dataSource = new MatTableDataSource<Departamento>(departamentos);
    this.dataSource.paginator = this.paginator;
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
        },
        complete: () => {
          console.log("exitoso");
        }
      }
    );
    this.dataSource.data.splice(this.posicion(departamento), 1);
    this.cargarDataSource(this.dataSource.data);
  }

}

