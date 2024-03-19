import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';

import { EmpleadoComponent } from './pages/empleado/empleado.component';
import { DepartamentoComponent } from './pages/departamento/departamento.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { HomeComponent } from './pages/home/home.component';
import { CrearDepartamentoComponent } from './pages/departamento/crear-departamento/crear-departamento.component';
import { BuscarDepartamentoComponent } from './pages/departamento/buscar-departamento/buscar-departamento.component';
import { EditarDepartamentoComponent } from './pages/departamento/editar-departamento/editar-departamento.component';
import { EditarEmpleadoComponent } from './pages/empleado/editar-empleado/editar-empleado.component';
import { CrearEmpleadoComponent } from './pages/empleado/crear-empleado/crear-empleado.component';
import { BuscarEmpleadoComponent } from './pages/empleado/buscar-empleado/buscar-empleado.component';
import { ListarEmpleadoComponent } from './pages/empleado/listar-empleado/listar-empleado.component';
import { ListarDepartamentoComponent } from './pages/departamento/listar-departamento/listar-departamento.component';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';


@NgModule({
  declarations: [
    AppComponent,
    EmpleadoComponent,
    DepartamentoComponent,
    NavbarComponent,
    SidebarComponent,
    HomeComponent,
    CrearDepartamentoComponent,
    BuscarDepartamentoComponent,
    EditarDepartamentoComponent,
    EditarEmpleadoComponent,
    CrearEmpleadoComponent,
    BuscarEmpleadoComponent,
    ListarEmpleadoComponent,
    ListarDepartamentoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatListModule,
    MatTableModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    HttpClientModule,
    MatPaginatorModule,
    MatSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
