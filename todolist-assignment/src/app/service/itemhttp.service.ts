import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Item } from '../model/item';

@Injectable({
  providedIn: 'root'
})
export class ItemhttpService {
  
url:string = "http://localhost:3000/items";

constructor(private http:HttpClient) { }

get()
{
  return this.http.get<Item[]>(this.url);
}
create(payload: Item) {
  return this.http.post<Item>(this.url, payload);
}  
delete(id:number){    
  return this.http.delete<Item>(this.url+'/'+id);   
} 
}
