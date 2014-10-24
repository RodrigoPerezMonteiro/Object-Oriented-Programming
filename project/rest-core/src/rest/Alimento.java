package rest;

import java.util.*;
import java.lang.Comparable;
import java.io.*;

public class Alimento implements Comparable<Alimento>,  Serializable{

 protected String _Nome;
 protected int _CaloriasPorGrama;
 
 public Alimento()
 {}
 
 public void alterarAlimento(int Calorias)
 {
   this.setCalorias(Calorias);
 }
 
 public int compareTo(Alimento i) 
 {
    return _Nome.compareTo(i.getName());
 }
 
 public void descreverAgregado()
 {}
 
 public void setCalorias(int Calorias)
 {
    _CaloriasPorGrama = Calorias;
 }
 
 public int getCaloriaspGrama()
 {
    return _CaloriasPorGrama;
 }
 
 
 public void setName(String Nome)
 {
    _Nome = Nome;
 }
 
 public String getName()
 {
    return _Nome;
 }
 
 public String toString() /*OVERRIDE NAS SUBCLASSES*/
 {
   return  "ALIMENTO" + "|" + _Nome + "|" + _CaloriasPorGrama;
 }
 
 public String toString(int percentagem) /*OVERRIDE NAS SUBCLASSES*/
 {
   return  "ALIMENTO" + "|" + _Nome + "|" + percentagem;
 }

 
 public ArrayList<Alimento> getAlimentos()
 {
   ArrayList<Alimento> _a = new ArrayList<Alimento> ();
   return _a;
 }
}