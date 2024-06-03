import { Component } from '@angular/core';

import { AutoService } from 'src/app/services/auto.service';
import { Auto } from '../modelo/Auto';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.scss']
})
export class InicioComponent {
  autos: any

  aut: Auto = new Auto()

  modoGuardar: boolean = true;

  constructor(private autoService: AutoService){

  }

  ngOnInit(): void {
    this.autoService.getAutos().subscribe(
      autos => {
        this.autos = autos; // Asignar los autos obtenidos del servicio a this.autos
      },
      error => {
        console.error('Error al obtener la lista de autos:', error);
      }
    );
  }

  crearAuto(): void {
    const autos: Auto[] = this.autos as Auto[];

    const autoExistente = autos.find(auto => auto.codigo === this.aut.codigo);
    if (autoExistente) {
      alert("El ID del auto ya existe. No se puede agregar.");
      return;
    }
  
    this.autoService.saveAuto(this.aut).subscribe(data => {
      console.log(data);
      if (data.codigo !== 0) {
        this.ngOnInit();
      } else {
        alert("Error al insertar " + data.mensaje);
      }
    });
  }
  

  llenarFormulario(auto: Auto): void {
    this.aut = { ...auto }; // Copia los datos del auto seleccionado al objeto aut
    this.modoGuardar = false; // Cambia al modo de actualización
  }

  actualizarAuto() {
    this.autoService.actualizarAuto(this.aut).subscribe(data => {
      console.log(data);
      if (data.codigo !== 0) {
        this.ngOnInit();
        this.modoGuardar = true;
        this.aut = new Auto();
      } else {
        alert("Error al actualizar " + data.mensaje);
      }
    });
  }

  eliminar(codigo: number){
    this.autoService.deleteAuto(codigo).subscribe(data => {
      console.log(data);
      if (data.codigo !== 0) {
        // Utilizamos el operador as para convertir this.autos a un arreglo de tipo Auto[]
        this.autos = (this.autos as Auto[]).filter(auto => auto.codigo !== codigo);
      } else {
        alert("Error al eliminar " + data.mensaje);
      }
    });
  }
  
}
