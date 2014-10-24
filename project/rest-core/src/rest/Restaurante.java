package rest;

import java.util.*;
import java.io.*;
import java.lang.*;
import java.lang.Comparable;

public class Restaurante implements Comparable<Restaurante>, Serializable{

  private ArrayList<PratodoDia> _PratosdoDia; 
  
  private String _Nome;	
  
  private String _Email;	
  
  private int _nrPDDVendidos;		    
  
  private int _TotalVendas;     
  
  private Outlet _Outlet;

  public Restaurante(String Nome, String Email, Outlet outlet)
  {
      _PratosdoDia = new ArrayList<PratodoDia> ();
      _Email = Email;
      _Nome = Nome;
      _nrPDDVendidos = 0;
      _TotalVendas = 0;
      _Outlet = outlet;
  }
  
  public ArrayList<PratodoDia> getPratosdoDia()
  {
    return _PratosdoDia;
  }
  
  public PratodoDia getPDD(String Nome)
  {
    PratodoDia prato = new PratodoDia();
  
    for (int i=0; i<_PratosdoDia.size(); i++)
    {
      if(Nome.equals(_PratosdoDia.get(i).getName()))
      {
	 prato = _PratosdoDia.get(i);
	 break;
      }
    }
    return prato;
  }
  
  public int compareTo(Restaurante i) 
  {
    return _Nome.compareTo(i.getName());
  }
  
  public void recebePagamento(int montante)
  {
    _TotalVendas += montante;
  }
  
  public void addPDD(int Quantidade, int Preco, String Nome, int Desconto) 
  {
    Alimento a = _Outlet.alimento_Seleccionar(Nome);
    boolean vegetariano = _Outlet.alimento_VerificaVeget(a);	
    PratodoDia PDD = new PratodoDia(Quantidade, Preco, Nome, vegetariano, Desconto, a); 
    _PratosdoDia.add(PDD);
    organizaPDD();    
  }
  
  public void organizaPDD()
  {
    Collections.sort(_PratosdoDia);
  }
  
  public void listarPDD() 
  {
    int i;
    for (i=0; i<(_PratosdoDia.size()); i++)
    {
      System.out.println(_PratosdoDia.get(i).toString());
    }
  }
  
  public void listarPDD_Diponiveis() 
  {
    int i;
    for (i=0; i<(_PratosdoDia.size()); i++)
    {
      if (_PratosdoDia.get(i).getDisponivel() == true)
      {
	System.out.println(_PratosdoDia.get(i).toString());
      }
    }
  }
  
  public String getName() 
  {
     return _Nome;
  }
  
  public int getTotalVendas()
  {
    return _TotalVendas;
  }
  
  public void setPDDVendidos(int valor) 
  {
    _nrPDDVendidos += valor;
  }
  
  public int getPDDVendidos() 
  {
    return _nrPDDVendidos;
  }
  
  public void pDD_ON(String NomePDD) 
  {
    int i;
    for (i=0; i<_PratosdoDia.size(); i++)
      if(_PratosdoDia.get(i).getName().equals(NomePDD))
      {
	_PratosdoDia.get(i).setDisponivel();
	_Outlet.alerta_Add("NOVELTY", NomePDD, _PratosdoDia.get(i).getVegetariano());
      }
  }
  
  public void pDD_OFF(String NomePDD) 
  {
    int i;
    for (i=0; i<_PratosdoDia.size(); i++)
      if(_PratosdoDia.get(i).getName().equals(NomePDD))
      {
	_PratosdoDia.get(i).setIndisponivel();
      }
  }
  
  public void aplicarDesconto(String NomePDD, int Desconto) 
  {
    int i;
    for (i=0; i<_PratosdoDia.size(); i++)
      if(_PratosdoDia.get(i).getName().equals(NomePDD))
      {
	if(_PratosdoDia.get(i).getDesconto() < Desconto)
	{
	    _Outlet.alerta_Add("DISCOUNT", NomePDD, _PratosdoDia.get(i).getVegetariano());
	}
	_PratosdoDia.get(i).aplicarDesconto(Desconto);
	
      }
      
  }
  
  public boolean pDD_existe(String Nome)
  {
    boolean existe = false;
    int i;
    for (i=0; i<_PratosdoDia.size(); i++)
    if(_PratosdoDia.get(i).getName().equals(Nome))
    {
      existe = true;
    }
    return existe;
  }
  
  
  public boolean pDD_estaDisponivel(String Nome)
  {
    boolean disponivel = false;
    int i;
    for (i=0; i<_PratosdoDia.size(); i++)
      if(_PratosdoDia.get(i).getName().equals(Nome))
      {	
	if(_PratosdoDia.get(i).getDisponivel())
	{
	  disponivel = true;
	}
      }
      return disponivel;
  }   

  public String toString() 
  {
    return _Nome + "|" + _nrPDDVendidos + "|" + _TotalVendas;
  }
}