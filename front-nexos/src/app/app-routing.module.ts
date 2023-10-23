import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { EmpleadoComponent } from './pages/empleado/empleado.component';
import { DepartamentoComponent } from './pages/departamento/departamento.component';
import { CrearDepartamentoComponent } from './pages/departamento/crear-departamento/crear-departamento.component';
import { BuscarDepartamentoComponent } from './pages/departamento/buscar-departamento/buscar-departamento.component';
import { ListarDepartamentoComponent } from './pages/departamento/listar-departamento/listar-departamento.component';
import { BuscarEmpleadoComponent } from './pages/empleado/buscar-empleado/buscar-empleado.component';
import { CrearEmpleadoComponent } from './pages/empleado/crear-empleado/crear-empleado.component';
import { ListarEmpleadoComponent } from './pages/empleado/listar-empleado/listar-empleado.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full',
  },
  {
    path: 'departamento',
    component: DepartamentoComponent,
    children: [
      {
        path: 'crear',
        component: CrearDepartamentoComponent
      },
      {
        path: 'buscar',
        component: BuscarDepartamentoComponent
      },
      {
        path: 'listar',
        component: ListarDepartamentoComponent
      }

    ]
  },
  {
    path: 'empleado',
    component: EmpleadoComponent,
    children: [
      {
        path: 'crear',
        component: CrearEmpleadoComponent
      },
      {
        path: 'buscar',
        component: BuscarEmpleadoComponent
      },
      {
        path: 'listar',
        component: ListarEmpleadoComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
