package rest;

import java.util.*;
import java.lang.Comparable;
import java.io.*;

public class Peixe extends Alimento implements Comparable<Alimento>, Serializable{

  public Peixe(String Nome, int Calorias)
  {
    super.setName(Nome);
    super.setCalorias(Calorias);
  }
  
  public String toString()
  {
    return "FISH" + "|" + super.getName() + "|" + super.getCaloriaspGrama();
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
   return  "FISH" + "|" + _Nome + "|" + percentagem;
 }
 
}
 