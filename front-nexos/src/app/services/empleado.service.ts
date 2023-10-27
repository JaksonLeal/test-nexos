import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Empleado } from '../model/Empleado';
import baseUrl from './URL';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  private API: String = "/api-empleados";

  constructor(private http: HttpClient) { }

  public crearEmpleado(empleado: Empleado) {
    return this.http.post(baseUrl + this.API + "/create", empleado);
  }

  public listarEmpleados() {
    return this.http.get<Empleado[]>(baseUrl + this.API);
  }

  public buscarEmpleado(documentoNumero: String) {
    return this.http.get<Empleado>(baseUrl + this.API + "/" + documentoNumero);
  }

  public eliminarEmpleado(documentoNumero: String) {
    return this.http.delete(baseUrl + this.API + "/" + documentoNumero);
  }

}
