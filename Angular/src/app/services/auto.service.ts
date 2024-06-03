import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environments } from '../environments/environments';
import { Auto } from '../pages/modelo/Auto';
import { Observable, catchError, of } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class AutoService {
  

  constructor(private http: HttpClient) { }

  getAutos(): Observable<Auto[]> {
    const url = environments.WS_PATH + "/Autos/lista";
    return this.http.get<Auto[]>(url).pipe(
      catchError(error => {
        if (error.status === 404) {
          // Si la respuesta es un error 404, significa que no hay autos, devolver una lista vacía
          return of([]);
        } else {
          // Propagar el error si es otro tipo de error
          throw error;
        }
      })
    );
  }

  saveAuto(auto: Auto){
    let url = environments.WS_PATH + "/Autos"
    return this.http.post<any>(url, auto)
  }

  deleteAuto(codigo: number) {
    let url = environments.WS_PATH + '/Autos?id=' + codigo;
    console.log(url);
    return this.http.delete<any>(url)
  }

  actualizarAuto(auto: Auto){
    let url = environments.WS_PATH + "/Autos";
    return this.http.put<any>(url, auto);
  }

}
