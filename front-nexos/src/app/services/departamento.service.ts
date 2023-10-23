import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Departamento } from '../model/Departamento';
import baseUrl from './URL';

@Injectable({
  providedIn: 'root'
})
export class DepartamentoService {

  private API: String = "/api-departamentos";

  constructor(private http: HttpClient) { }

  public crearDepartamento(departamento: Departamento) {
    return this.http.post(baseUrl + this.API + "/create", departamento);
  }

  public listarDepartamentos() {
    return this.http.get<Departamento[]>(baseUrl + this.API);
  }

}
