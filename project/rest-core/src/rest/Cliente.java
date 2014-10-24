package rest;

import java.util.*;
import java.lang.Comparable;
import java.io.*;

public class Cliente implements Comparable<Cliente>, Serializable{

  private CarrinhoCompras _Carrinho_Compras;
  private ArrayList<Alerta> _Alertas;
  private String _Email;
  private String _Nome;
  private boolean _Vegetariano;
  private int _nrEncomendas;
  private int _nrPDD;
  
  private boolean _Discount;
  private boolean _Novelty;
  private boolean _HasAlerts;
  
  private int _TotalGasto;
  
  public Cliente()
  {
    _Alertas = new ArrayList<Alerta> ();
    _nrEncomendas = 0;
    _nrPDD = 0;
    _TotalGasto = 0;
    
    _Carrinho_Compras = new CarrinhoCompras();
    
    _Discount = false;
    _Novelty = false;
    _HasAlerts = false;
  }
    
  public Cliente(String Nome, String Email, boolean Tipo)
  {
    _Alertas = new ArrayList<Alerta> ();
    _nrEncomendas = 0;
    _nrPDD = 0;
    _TotalGasto = 0;
    
    _Carrinho_Compras = new CarrinhoCompras();
    
    _Nome = Nome;
    _Email = Email;
    _Vegetariano = Tipo;
    
    _Discount = false;
    _Novelty = false;
    _HasAlerts = false;
  }
  
  public CarrinhoCompras getCarrinhoCompras()
  {
    return _Carrinho_Compras;
  }
  
  public int compareTo(Cliente i) 
  {
    return this.getName().compareTo(i.getName());
  }
  
  public int nAlertas()
  {
    return _Alertas.size();
  }
  
  public ArrayList<Alerta> getAlertas()
  {
    return _Alertas;
  }
  
  public boolean hasAlerts()
  {
    return _HasAlerts;
  }
  
  public void setHasAlerts()
  {
    _HasAlerts = true;
  }
  
  public boolean getDiscount()
  {
    return _Discount;
  }
  
  public boolean getNovelty()
  {
    return _Novelty;
  }
  
  public void setDiscount(boolean Discount)
  {
    _Discount = Discount;
  }
  
  public void setNovelty(boolean Novelty)
  {
    _Novelty = Novelty;
  }
  
  public String getEmail()
  {
    return _Email;  
  }
  
  public String getName()
  {
    return _Nome;  
  }
  
  public boolean getTipo()
  {
    return _Vegetariano;
  }
  
  public void setTipo(boolean Vegetariano)
  {
    _Vegetariano = Vegetariano;
  }
  
  public int getTotalGasto()
  {
    return _TotalGasto;
  }
  
  public void recebeCobranca(int valor)
  {
    _TotalGasto += valor;
  }
  
  public int getnrPDD()
  {
    return _nrPDD;
  }
  
  public void setPDD(int valor)
  {
    _nrPDD += valor;
  }

  public int getnrEncomendas()
  {
    return _nrEncomendas;
  }
  
  public void setnrEncomendas()
  {
    _nrEncomendas ++;
  }

  
  public void addAlerta(Alerta al)
  {
    _Alertas.add(al);
  }
  
  public void pagar()
  {
    _Carrinho_Compras.clienteQuerPagar();
    _Carrinho_Compras.esvaziarCarrinho();
    setnrEncomendas();
  }
  
  public String toString()
  {
    String Tipo;
    
    if (_Vegetariano == true)
    {
      return "VEGETARIAN" + "|" + _Nome + "|" + _Email + "|" + _nrEncomendas + "|" + _nrPDD + "|" + _TotalGasto;
    }
    
    else
    {
      return "OMNIVOROUS" + "|" + _Nome + "|" + _Email + "|" + _nrEncomendas + "|" + _nrPDD + "|" + _TotalGasto;
    }
  }
}