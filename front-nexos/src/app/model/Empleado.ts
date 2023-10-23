import { Departamento } from "./Departamento";

export class Empleado {

    public id: number;
    public documentoTipo: String;
    public documentoNumero: String;
    public nombre: String;
    public apellido: String;
    public ciudad: String;
    public direccion: String;
    public correoElectronico: String;
    public telefono: String;
    public fechaHoraCrea: Date;
    public fechaHoraModifica: Date;
    public departamento: Departamento;

}