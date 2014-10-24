package rest.textui.outlet;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import java.io.*;
import java.util.*;
import java.lang.*;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;
import rest.PratodoDia;

import rest.textui.UnknownKeyException;
import rest.textui.outlet.Message;
import rest.textui.outlet.InvalidKeyException;
import rest.textui.outlet.NotAdequateException;
import rest.textui.outlet.MenuEntry;


public class AddDishOfTheDayToOrder extends Command<Outlet> {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public AddDishOfTheDayToOrder(Outlet outlet) {
		super(false, MenuEntry.ADD_TO_BASKET, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException, InvalidKeyException, UnknownKeyException, NotAdequateException {
		
	String Id, Nome, Restaur, MaisBarato;
		
	Restaur = MaisBarato = " ";
		
	int Doses = 0;
	int Barato = 100000;
	
	Id = IO.readString(Message.reqClientId());
	
	if(!(_receiver.cliente_Existe(Id)))
	{
	  throw new UnknownKeyException(Id);
	}
	
	else
	{
	
	Nome = IO.readString(Message.reqDishOfTheDay());
	Doses = Integer.parseInt(IO.readString(Message.reqQuantity()));
			
		/*Lista Todos os Restaurantes que tem o Prato do Dia em Causa.*/
	int nr = 0;
	
	ArrayList<String> _strings = new ArrayList<String> ();
		
	  for (Restaurante rest : _receiver.getRestaurantes().values())
	  {
		for (int i = 0; i< rest.getPratosdoDia().size(); i++)
		{
		  if (Nome.equals(rest.getPratosdoDia().get(i).getName()))
		  {
		    nr++;		  
		    String s = _receiver.concatenaStringsPDD(rest.getName(), rest.getPratosdoDia().get(i).getPrecoFinal(),
							     rest.getPratosdoDia().get(i).getQuantidade());
		    
		    _strings.add(s);
		    
		    if(rest.getPratosdoDia().get(i).getPrecoFinal() < Barato)
		    {
		      Barato = rest.getPratosdoDia().get(i).getPrecoFinal();
		      MaisBarato = rest.getName();
		    }
		    
		    Restaur = rest.getName();
		  }
		}
	  }
		
		/*Se houver apenas 1 Restaurante, selecciona forçosamente esse e regista encomenda.*/
		
		if (nr == 1)
		{
		  _receiver.cliente_ADDEncomenda(Id, Nome, Restaur, Doses);
		}
		
		/*Se houverem multiplos Restaurantes, pede ao cliente para seleccionar 1*/
		
		else if (nr > 1)
		{
		  for (int i = 0; i < _strings.size(); i ++)
		  {
		    IO.println(_strings.get(i));
		  }
		
		  Restaur = IO.readString(Message.reqRestId());
		  
		  /*Após o pedido, verifica se o restaurante existe. Se existir regista a encomenda nele.*/
		
		  if (_receiver.restaurante_Existe(Restaur))
		  {
		    _receiver.cliente_ADDEncomenda(Id, Nome, Restaur, Doses);
		  }
		  
		  /*Se não existir, regista a encomenada no Restaurante cujo preço final para esse prato for menor.*/
		  
		  if(_receiver.restaurante_Existe(Restaur) == false)
		  {
		    _receiver.cliente_ADDEncomenda(Id, Nome, MaisBarato, Doses);
		  }
		}
	      }
	  }
}
