package rest;

import java.io.*;
import java.util.*;
import java.lang.*;

import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import java.lang.Comparable;
import java.lang.Integer;
import java.lang.Double;
import java.lang.ClassNotFoundException;
import java.lang.Math;

public class Outlet implements Serializable{

  private String _ficheiro;
  private TreeMap<String, Restaurante> _Restaurantes;
  private TreeMap<String, Cliente> _Clientes;
  private TreeMap<String, Alimento> _Alimentos;
  private int _IdAlerta;
  
  private boolean _changed;
  
  public Outlet()
  {
      _IdAlerta = 0;
      _Restaurantes = new TreeMap<String, Restaurante> (String.CASE_INSENSITIVE_ORDER);
      _Clientes = new TreeMap<String, Cliente> (String.CASE_INSENSITIVE_ORDER);
      _Alimentos = new TreeMap<String, Alimento> (String.CASE_INSENSITIVE_ORDER);
  } 
  
  public void openFile(String _ficheiro){
		try {
			if(_ficheiro != null){ 
				BufferedReader ficheiro = new BufferedReader(new FileReader(_ficheiro));			
				ficheiro = new java.io.BufferedReader(new java.io.FileReader(_ficheiro));

				String line = "";
				String[] splits;
				
				while((line = ficheiro.readLine()) != null) {
					
					splits = line.split("\\|");
					String primeiro = splits[0];
// 				
					if(primeiro.equals("OMNIV")) {
					
						cliente_Registar(splits[1], splits[2], "OMNIVOROUS"); 
						
					} 
					if(primeiro.equals("VEGET")) {
						
						cliente_Registar(splits[1], splits[2], "VEGETARIAN"); 

					}
					if(primeiro.equals("RESTAURANT")) {
						restaurante_Registar(splits[1], splits[2]);
						
					}
					if(primeiro.equals("MEAT")) {
						alimento_RegistarMeat(splits[1], Integer.parseInt(splits[2]));
						
					}
					if(primeiro.equals("FISH")) {
						alimento_RegistarFish(splits[1], Integer.parseInt(splits[2]));
						
					}
					if(primeiro.equals("VEGETABLE")) {
						alimento_RegistarVegetable(splits[1], Integer.parseInt(splits[2]));
						
					}
					if(primeiro.equals("DOTD")) {
						restaurante_pratodoDia_Adicionar(splits[1], splits[2], Integer.parseInt(splits[3]), Integer.parseInt(splits[4]),
										 Integer.parseInt(splits[5]));
					
					}
					if(primeiro.equals("PREPARED")) {
					
						ArrayList<Alimento> Componentes = new ArrayList();
						ArrayList<Integer> Percentagens = new ArrayList();
						int percentagem = 0;						
						int size = splits.length;
						int i;
						
						String[] composto;
				
						for (i = 2; ((i<size) && (percentagem < 100)) ; i++)
						
						{
						
						String linha = splits[i];
						
						composto = linha.split("\\=");
						
						Componentes.add(alimento_Seleccionar(composto[0]));
						Percentagens.add(Integer.parseInt(composto[1]));
						
						percentagem += Integer.parseInt(composto[1]);
						
						}
						
						alimento_RegistarAgregado(splits[1], Componentes, Percentagens);
					}
				}
			}	
		}	
		catch (NullPointerException npe) {
		}
		catch (IOException ioe) {
		}
		catch (java.lang.ArrayIndexOutOfBoundsException aiobe) {
		}
		catch(ClassCastException cce) {}
	}
	
  public boolean getChanged()
  {
    return _changed;
  }
  
  public void setChanged()
  {
    _changed = true;
  }

  public void setChanged(boolean muda)
  {
    _changed = muda;
  }
   
  public void setFilename(String filename)
  {
    _ficheiro = filename;
    setChanged();
  }
  
  public String getFilename()
  {
    return _ficheiro;
  }
  
  public TreeMap<String, Alimento> getAlimentos()
  {
    return _Alimentos;
  }
  
  public TreeMap<String, Cliente> getClientes()
  {
    return _Clientes;
  }
  
  public TreeMap<String, Restaurante> getRestaurantes()
  {
    return _Restaurantes;
  }
  
  public TreeMap<String, Cliente> getClientesComEncomendas()
  {
    TreeMap<String, Cliente> _ClientesComEncomendas = new TreeMap<String, Cliente> ();
    
    for(Cliente cl: _Clientes.values())
    {
      if(cl.getnrEncomendas() != 0)
      {
	_ClientesComEncomendas.put(cl.getEmail(), cl);
      }
    }
    
    return _ClientesComEncomendas;    
  }
  
  public TreeMap<String, Cliente> getClientesSemAlertas()
  {
    TreeMap<String, Cliente> _ClientesSemAlertas = new TreeMap<String, Cliente> ();
    
    for(Cliente cl: _Clientes.values())
    {
      if(cl.hasAlerts() == false)
      {
	_ClientesSemAlertas.put(cl.getEmail(), cl);
      }
    }
    
    return _ClientesSemAlertas;    
  }
  
  public void clear()
  {
      _IdAlerta = 0;
      _Restaurantes = new TreeMap<String, Restaurante> (String.CASE_INSENSITIVE_ORDER);
      _Clientes = new TreeMap<String, Cliente> (String.CASE_INSENSITIVE_ORDER);
      _Alimentos = new TreeMap<String, Alimento> (String.CASE_INSENSITIVE_ORDER);
      setChanged(false);
  }
  
  public void reload (TreeMap<String, Restaurante> Restaurantes, TreeMap<String, Cliente> Clientes, TreeMap<String, Alimento> Alimentos, String Ficheiro)
  {
    _Restaurantes = Restaurantes;
    _Clientes = Clientes;
    _Alimentos = Alimentos;
    _ficheiro = Ficheiro;
    setChanged();
    
  }
  
  public void alerta_Add(String Tipo, String Message, boolean Vegetariano)
  {
  
      if (alerta_VerificaNovelty(Tipo))
      {
	AlertaNovelty al = new AlertaNovelty(Message);
	alerta_NewNovelty(al, Vegetariano);
      }

      if(alerta_VerificaNovelty(Tipo) == false)
      {
	AlertaDiscount al = new AlertaDiscount(Message);
	alerta_NewDiscount(al, Vegetariano);
      }
      setChanged();
  }
  
  public boolean alerta_VerificaNovelty(String Tipo)
  {
    boolean a = false;
    if (Tipo.equals("NOVELTY"))
    {
      a = true;
    }
    
    if (Tipo.equals("DISCOUNT"))
    {
      a = false;
    }
    
    return a;
  }
  
  public void alerta_NewDiscount(Alerta al, boolean Vegetariano)
  {
    if (Vegetariano == false)
    {
      for (Cliente cl : _Clientes.values())
      {
	if ((cl.getDiscount()) && (cl.getTipo() == false))
	{
	  cl.addAlerta(al);
	  cl.setHasAlerts();
	}
      }	
    }
    
    if (Vegetariano)
    {
      for (Cliente cl : _Clientes.values())
      {
	if (cl.getDiscount())
	{
	  cl.addAlerta(al);
	  cl.setHasAlerts();
	}
      }	
    }
    
  }
  
  public void alerta_NewNovelty(Alerta al, boolean Vegetariano)
  {
    if (Vegetariano == false)
    {
      for (Cliente cl : _Clientes.values())
      {
	if ((cl.getNovelty()) && (cl.getTipo() == false))
	{
	  cl.addAlerta(al);
	  cl.setHasAlerts();
	}
      }	
    }
    
    if (Vegetariano)
    {
      for (Cliente cl : _Clientes.values())
      {
	if (cl.getNovelty())
	{
	  cl.addAlerta(al);
	  cl.setHasAlerts();
	}
      }	
    }	
  }  
  
  public void alimento_RegistarNOVO(String Tipo, String Nome, int Calorias)
  {
    if (Tipo.equals("MEAT"))
    {
      alimento_RegistarMeat(Nome, Calorias);
    }
    
    else
    {
      if (Tipo.equals("FISH"))
      {	
	 alimento_RegistarFish(Nome, Calorias);
      }
      
      else
      {
	 alimento_RegistarVegetable(Nome, Calorias);
      }
    }
    setChanged();
  }
   
  public void alimento_RegistarMeat(String Nome, int Calorias) 
  {
      Alimento c = new Carne(Nome, Calorias);
      c.toString();
      alimento_RegistarSimples(c);
  }
 
  public void alimento_RegistarFish(String Nome, int Calorias) 
  {
     Alimento p = new Peixe(Nome, Calorias);
     alimento_RegistarSimples(p);
  }
  
  public void alimento_RegistarVegetable(String Nome, int Calorias) 
  {
      Alimento v = new Vegetal(Nome, Calorias);
      alimento_RegistarSimples(v);
  }
  
  public void alimento_RegistarSimples(Alimento al) 
  {
      if (alimento_Existe(al.getName()) == false)
      {
	_Alimentos.put(al.getName(), al);
	setChanged();
      }
  }
  
  public boolean alimento_Existe(String Alimento)
  {
    boolean existe = false;
    for (Alimento al : _Alimentos.values())
    {
      if (Alimento.equals(al.getName()))
      {
	existe = true;
      }
    }
    
    return existe;
  }
  
  public void alimento_RegistarAgregado(String Nome, ArrayList<Alimento> ListaAl, ArrayList<Integer> ListaPercentagens)
  {
     AlimentoAgregado alAgregado = new AlimentoAgregado(Nome, ListaAl, ListaPercentagens);
     if (alimento_Existe(alAgregado.getName()) == false)
      {
	_Alimentos.put(Nome, alAgregado);
	setChanged();
      }
  }
  
  public void alimento_ConverterAgregado(String Nome, ArrayList<String> strings, ArrayList<Integer> ListaPercentagens)
  {
  
    ArrayList<Alimento> ListaAl = new ArrayList<Alimento>();
    
    for (int i = 0; i <strings.size(); i++)
    {
      ListaAl.add(alimento_Seleccionar(strings.get(i)));
    }
    
     alimento_RegistarAgregado(Nome, ListaAl, ListaPercentagens);
  }
  
  public void alimento_AlterarSimples(Alimento al, int Calorias)
  {
    al.alterarAlimento(Calorias);
    setChanged();
  }
  
  public void alimento_DescreverAgregado(String Nome)
  {
     Alimento al = _Alimentos.get(Nome);
     System.out.println(al.toString());
  }
  
  public Alimento alimento_Seleccionar(String Nome)
  {
      return _Alimentos.get(Nome);
  }
  
  public Alimento alimento_SeleccionarAgregado(String Nome)
  {
      AlimentoAgregado ag = (AlimentoAgregado) _Alimentos.get(Nome);
      return ag;
  }
   
  public boolean alimento_VerificaVeget(Alimento al)
  {
    boolean state = false;
    
    if (al instanceof Vegetal) 
    {
	state = true;
    }
    
    else
    {
	if (al instanceof AlimentoAgregado)
	{
	  AlimentoAgregado alAgr = (AlimentoAgregado) al; 
	  state = alAgr.getVeg();
	}
    }
    return state;    
  }
 
  public void cliente_Visualizar(String Email)
  {
      Cliente cl = _Clientes.get(Email);
      System.out.println(cl.toString());
  }
   
  public void cliente_Registar(String Nome, String Email, String Tipo)
  {
      boolean boolTipo = false;
      
      if (Tipo.equals("OMNIVOROUS"))
      {
	boolTipo = false;
      }
      
      else
      {
	if (Tipo.equals("VEGETARIAN"))
	{
	  boolTipo = true;
	}
      }
      
      Cliente cl = new Cliente(Nome, Email, boolTipo); 
      
      if (cliente_Existe(Email) == false)
      {
	_Clientes.put(Email, cl);
	setChanged();
      }
  }
 
  public void cliente_AlterarTipo(String Email, String Tipo)
  {
      boolean boolTipo = false;
      
      if (Tipo.equals("OMNIVOROUS"))
      {
	boolTipo = false;
      }
      
      if (Tipo.equals("VEGETARIAN"))
      {
	boolTipo = true;
      }
      
      _Clientes.get(Email).setTipo(boolTipo);
      setChanged();
  }
  
  public boolean cliente_Existe(String Email)
  {
    boolean existe = false;
    for (Cliente c : _Clientes.values())
    {
      if (c.getEmail().equals(Email))
      {
	existe = true;
      }
    }	
    return existe;
  }
  
  public Cliente cliente_Seleccionar(String Email)
  {
      Cliente Unknown = new Cliente();
      
      for (Cliente cl : _Clientes.values())
      {
	 if (cl.getEmail().equals(Email))
	 {
	   Unknown = cl;
	 }
      }
      
      return Unknown;
  }
  
  public void cliente_Pagar(String Email)
  {
      _Clientes.get(Email).pagar();
      setChanged();
  }
  
  public void cliente_EsvaziaCarrinho(String Email)
   {
      _Clientes.get(Email).getCarrinhoCompras().esvaziarCarrinho();
      setChanged();
   }
   
  public void cliente_ADDEncomenda(String Email, String Prato, String restaurante, int Doses)
  {
      PratodoDia Pdd = _Restaurantes.get(restaurante).getPDD(Prato);
      Restaurante Rest = _Restaurantes.get(restaurante);
      Cliente Client = _Clientes.get(Email);
      
      Encomenda enc = new Encomenda(Client, Pdd, Rest, Doses);
      
      Client.getCarrinhoCompras().adicionarEncomenda(enc); 
      setChanged();
  }
  
  public ArrayList<Encomenda> cliente_ListaEncomendas(String Email)
  {
     return  _Clientes.get(Email).getCarrinhoCompras().getEncomendas();
  }
    
  public void cliente_AlertaON(String Email, String Tipo)
  {
      
      if (alerta_VerificaNovelty(Tipo))
      {
	_Clientes.get(Email).setNovelty(true);
      }
      
      if (alerta_VerificaNovelty(Tipo) == false)
      {
	_Clientes.get(Email).setDiscount(true);
      }

      setChanged();
  }
  
  public void cliente_AlertaOFF(String Email, String Tipo)
  {
      if (alerta_VerificaNovelty(Tipo))
      {
	_Clientes.get(Email).setNovelty(false);
      }
      
      if (alerta_VerificaNovelty(Tipo) == false)
      {
	_Clientes.get(Email).setDiscount(false);
      }

      setChanged();
  }  
  
  public void cliente_ListaAlertas(String Email)
  {
      _Clientes.get(Email).getAlertas();
  }
  
  public ArrayList<String> cliente_ListaClientesSemEncomendas()
  {
    ArrayList<String> _Client = new ArrayList<String>();
    PredicateClientWithNoOrders PCWNO = new PredicateClientWithNoOrders();
    
    for (Cliente cl : _Clientes.values())
    {
      if PCWNO.OK(cl)
      {
	_Client.add(cl.toString);
      }
    }
    return _Client;    
  }
  
  public ArrayList<String> cliente_ListaClientesComEncomendas()
  {
    ArrayList<String> _Client = new ArrayList<String>();
    PredicateClientWithClosedOrders PCWCO = new PredicateClientWithClosedOrders();
    
    for (Cliente cl : _Clientes.values())
    {
      if PCWCO.OK(cl)
      {
	_Client.add(cl.toString);
      }
    }
    return _Client;    
  }
  
  public boolean restaurante_Existe(String Nome)
  {
    boolean existe = false;
    for (Restaurante rest : _Restaurantes.values())
    {
      if (Nome.equals(rest.getName()))
      {
	existe = true;
      }
    }
    
    return existe;
  }
  
  public void restaurante_pratodoDia_Adicionar (String Restaurante, String Nome, int Quantidade, int Preco, int Desconto)
  {
    _Restaurantes.get(Restaurante).addPDD(Quantidade, Preco, Nome, Desconto);
    setChanged();
  }
  
  public void restaurante_pratodoDia_ON (String Restaurante, String Nome)
  {
    _Restaurantes.get(Restaurante).pdd_ON(Nome);
    setChanged();
  }
  
  public void restaurante_pratodoDia_OFF (String Restaurante, String Nome)
  {
    _Restaurantes.get(Restaurante).pdd_OFF(Nome);
    setChanged();
  }
  
  public Restaurante restaurante_Seleccionar(String Nome)
  {
    return _Restaurantes.get(Nome);
  }
  
  public void restaurante_Registar(String Nome, String Email)
  {
    Restaurante rest = new Restaurante(Nome, Email, this);
    
    if (restaurante_Existe(Nome) == false)
    {
      _Restaurantes.put(Nome, rest);
      setChanged();
    }
  }
  
  public String concatenaStringsPDD(String a, int b, int c)
  {
    return a + "|" + b + "|" + c;
  }
}