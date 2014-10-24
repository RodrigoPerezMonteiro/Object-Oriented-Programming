package rest;

import java.util.*;
import java.lang.Comparable;
import java.io.*;

public class AlimentoAgregado extends Alimento implements Comparable<Alimento>, Serializable{ 

  private ArrayList<Alimento> _Constituintes;
  private ArrayList<Integer> _Percentagens;
  private int _TotalCalorias;
  private String _Veg;

  public int compareTo(AlimentoAgregado i) 
  {
    return super.getName().compareTo(i.getName());
  }
  
  public AlimentoAgregado(String Nome, ArrayList<Alimento> Constituintes, ArrayList<Integer> Percentagens)
  {
      super.setName(Nome);
      _Constituintes = Constituintes;
      _Percentagens = Percentagens;
      alteraCaloriasSuper();
      setVegNoVeg();
  }
  
  public void alteraCaloriasSuper()
  {
    super.setCalorias(this.calcCaloriaspGrama());
  }
  
  public ArrayList<Alimento> getAlimentos()
  {
    return _Constituintes;
  }
  
  
  public int calcCaloriaspGrama()
  {
    int Calorias = 0;
    int i = 0;
    
    for(i=0; i< _Constituintes.size(); i++)
    {
      if (_Constituintes.get(i) instanceof AlimentoAgregado)
      {
	AlimentoAgregado al = (AlimentoAgregado) _Constituintes.get(i);
	Calorias += (al.calcCaloriaspGrama() * _Percentagens.get(i));
      }
    
      else
      {
	Calorias += (_Constituintes.get(i).getCaloriaspGrama() * _Percentagens.get(i));
      }
    }	
  
    return Calorias;
  }
 
  public void setCalorias(int Calorias)
  {
    super.setCalorias(Calorias);
  }
 
  public int getCaloriaspGrama()
  {
    return calcCaloriaspGrama();
  }
 
  public String getName()
  {
    return super.getName();
  }
  
 
  public void setVegNoVeg()
  {
    boolean Veg = verificaVeg();
    
    if (Veg)
    {
      _Veg = "VEG";
    }
    
    if (! Veg)
    {
      _Veg = "NOVEG";
    }
  }
  
  public boolean verificaVeg()
  {
    boolean Veg = true;
    
    for (int i = 0; i<_Constituintes.size(); i++)
    {
      if (_Constituintes.get(i) instanceof AlimentoAgregado)
      { 
	AlimentoAgregado al = (AlimentoAgregado) _Constituintes.get(i);
	
	if ((al.verificaVeg()) == false)
	{
	  Veg = false;
	}
      }
      
      else
      {
    	if (((_Constituintes.get(i)) instanceof Vegetal) == false)
	{
	  Veg = false;
	}
      }
    }
    return Veg;
  }
  
  public String getVegNoVeg()
  {
    return _Veg;
  }
  
  public boolean getVeg()
  {
    return _Veg.equals("VEG");
  }
  
  public ArrayList<String> toStringAgregado()
  {
    ArrayList<String> _Componentes = new ArrayList<String> ();
    AlimentoAgregado agr = (AlimentoAgregado) this;
    _Componentes.add(agr.toString());
    
    for (int i = 0; i<_Constituintes.size(); i++)
    {
      if(_Constituintes.get(i) instanceof AlimentoAgregado)
      {
	AlimentoAgregado ag = (AlimentoAgregado) _Constituintes.get(i);
	_Componentes = ag.acrescentaString(_Componentes, _Percentagens.get(i));
      }
      
      else
      {
	_Componentes.add(_Constituintes.get(i).toString(_Percentagens.get(i)));
      }
    }
    return _Componentes;
  }
  
  public ArrayList<String> acrescentaString(ArrayList<String> _Componentes, int percentagem)
  {
     AlimentoAgregado agr = (AlimentoAgregado) this;
     _Componentes.add(agr.toString(percentagem));
     _Componentes.add(agr.toString());
     for (int i = 0; i<_Constituintes.size(); i++)
    {
      if(_Constituintes.get(i) instanceof AlimentoAgregado)
      {
	AlimentoAgregado ag = (AlimentoAgregado) _Constituintes.get(i);
	_Componentes = ag.acrescentaString(_Componentes, _Percentagens.get(i));
      }
      
      else
      {
	_Componentes.add(_Constituintes.get(i).toString(_Percentagens.get(i)));
      }
    }
    return _Componentes;
  }
  
  public String toString()
  {
    return getVegNoVeg() + "|" + super.getName();
  }
  
  public String toString(int percentagem)
  {
    return getVegNoVeg() + "|" + super.getName() + "|" + percentagem;
  }
}
