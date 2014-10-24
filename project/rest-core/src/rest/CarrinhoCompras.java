package rest;

import java.util.*;
import java.lang.Comparable;
import java.io.*;

public class CarrinhoCompras implements Serializable
{
  private ArrayList <Encomenda> _Encomendas;
  
  public CarrinhoCompras()
  {
    _Encomendas = new ArrayList<Encomenda>();
  }
  
  public void clienteQuerPagar()
  {
    enviaPagamentoRestaurantes();
    enviaDadosRestaurantes();
    enviaCobrancaClientes();
    enviaDadosClientes();
  }
  
  public void enviaPagamentoRestaurantes()
  {
    for (int i = 0; i<_Encomendas.size(); i++)
    {
      _Encomendas.get(i).enviaPagamentoRestaurante();
    }
  }
  
  public void enviaDadosRestaurantes()
  {
    for (int i = 0; i<_Encomendas.size(); i++)
    {
      _Encomendas.get(i).enviaDadosRestaurante();
    }
  }
  
  public void enviaCobrancaClientes()
  {
    for (int i = 0; i<_Encomendas.size(); i++)
    {
      _Encomendas.get(i).enviaCobrancaCliente();
    }
  }
  
  public void enviaDadosClientes()
  {
    for (int i = 0; i<_Encomendas.size(); i++)
    {
      _Encomendas.get(i).enviaDadosCliente();
    }
  }
  
  public void esvaziarCarrinho()
  {
    _Encomendas = new ArrayList<Encomenda>();
  }
  
  public ArrayList<Encomenda> getEncomendas()
  {
    return _Encomendas;
  }
  
  public void adicionarEncomenda(Encomenda enc)
  {
    _Encomendas.add(enc);    
  }
  
  public boolean getEmpty()
  {
    return _Encomendas.isEmpty();
  }
}