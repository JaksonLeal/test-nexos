import { Empleado } from "./Empleado";

export class Departamento {

    public id: number;
    public departamentoCodigo: String;
    public departamentoNombre: String;
    public fechaHoraCrea: String;
    public fechaHoraModifica: String;
    public empleados: Empleado[];

}
