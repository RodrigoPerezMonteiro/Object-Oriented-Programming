package rest;

import java.util.*;
import java.lang.Comparable;
import java.io.*;

public class Vegetal extends Alimento implements Comparable<Alimento>, Serializable{


  public Vegetal(String Nome, int Calorias)
  {
    super.setName(Nome);
    super.setCalorias(Calorias);
  }
  
  public String toString()
  {
    return "VEGETABLE" + "|" + super.getName() + "|" + super.getCaloriaspGrama();
  }
  
  public int compareTo(Vegetal i) 
  {
    return super.getName().compareTo(i.getName());
  }
  
  public void setCalorias(int Calorias)
  {
    super.setCalorias(Calorias);
  }
  
  public int getCaloriaspGrama()
  {
    return super.getCaloriaspGrama();
  }
  
  public String getName()
 {
    return super.getName();
 }
 
 public String toString(int percentagem) /*OVERRIDE NAS SUBCLASSES*/
 {
   return  "VEGETABLE" + "|" + _Nome + "|" + percentagem;
 }
 
}
 
