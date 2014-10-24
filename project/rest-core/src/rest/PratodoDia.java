package rest;

import java.util.*;
import java.io.*;
import java.lang.Comparable;

public class PratodoDia implements Serializable, Comparable<PratodoDia>{

 private Alimento _Alimento;
 
 private boolean _Disponivel;
 private boolean _Vegetariano;
 private int _Quantidade;
 private int _Preco;
 private int _Desconto;
 private int _Calorias;
 private String _Nome;
 private String _Veget;
 private String _DisponivelString;
 
 public int compareTo(PratodoDia p)
 {
  return _Nome.compareTo(p.getName());
 }
 
 public PratodoDia()
 {}
 
 public PratodoDia(int Quantidade, int Preco, String Nome, boolean Vegetariano, int Desconto, Alimento a)
 {
    _Quantidade = Quantidade;
    _Preco = Preco;
    _Nome = Nome;
    _Disponivel = false;
    setIndisponivel();
    _Desconto = Desconto;
    _Vegetariano = Vegetariano;
    _Alimento = a;
    
    if (_Vegetariano == true)
    {
      _Veget = "VEG";
    }
    
    if (_Vegetariano == false)
    {
      _Veget = "NOVEG";
    }
        
 }
 
 public int getDesconto()
 {
    return _Desconto;
 }
 
 public String getName()
 {
    return _Nome;
 }
 
 public int getPrecoFinal()
 {
    return ((_Preco * (100-_Desconto)) / 100);
 }
 
 public int getQuantidade()
 {
    return _Quantidade;
 }
 
 public int calcCalorias()
 {	
    return ((_Alimento.getCaloriaspGrama() * _Quantidade) / 100);
 }
 
 public boolean getDisponivel()
 {
    return _Disponivel;
 }
 
 public void setDisponivel()
 {
    _Disponivel = true;
    _DisponivelString = "AVAILABLE";
 }
 
 public void setIndisponivel()
 {
    _Disponivel = false;
    _DisponivelString = "UNAVAILABLE";
 }
 
 public void aplicarDesconto(int Desconto)
 {
   _Desconto = Desconto;
 }
 
 public String toString ()
 {
   return _Veget + "|" + _Alimento.getName() + "|" +  _Preco + "|" +  _Desconto + "|" +  _DisponivelString;
 }
 
 public String toStringOutlet ()
 {
   return _Veget + "|" + _Alimento.getName();
 }
 
 public boolean getVegetariano()
 {
   return _Vegetariano;
 }
 
 public String printCalorias()
  {
    return getName() + "|" + calcCalorias();
  }

} 
