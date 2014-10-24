package rest.textui.food;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Alimento;
import rest.AlimentoAgregado;
import rest.Cliente;
import rest.textui.UnknownKeyException;

import java.lang.*;
import java.util.*;
import java.io.*;

public class DescribePreparedFood extends Command<Outlet> /*superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public DescribePreparedFood(Outlet outlet) {
		super(false, MenuEntry.DESCRIBE_PREPARED, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException, InvalidKeyException, UnknownKeyException {
		
		String Nome = IO.readString(Message.reqKey());
		
		if (_receiver.alimento_Existe(Nome) == false)
		{
		  throw new UnknownKeyException(Nome);
		}
		
		if (((_receiver.alimento_Seleccionar(Nome)) instanceof AlimentoAgregado) == false)
		{
		  throw new InvalidKeyException(Nome);
		}
	
		else
		{
		  AlimentoAgregado alg = (AlimentoAgregado) _receiver.alimento_SeleccionarAgregado(Nome);
		  ArrayList<String> stringsAl = alg.toStringAgregado();
		
		  for (int i = 0; i<stringsAl.size(); i++)
		  {
		    IO.println(stringsAl.get(i));
		  }
		}
	}

}
