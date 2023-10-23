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
  public dataSource: MatTableDataSource<Departamento, MatTableDataSourcePaginator>;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private departamentoService: DepartamentoService) { }

  ngOnDestroy(): void { }

  ngOnInit(): void {
    this.departamentoService.listarDepartamentos().subscribe(
      {
        next: (respuesta) => {
          console.log(respuesta);
          this.dataSource = new MatTableDataSource<Departamento>(respuesta);
          this.dataSource.paginator = this.paginator;
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

