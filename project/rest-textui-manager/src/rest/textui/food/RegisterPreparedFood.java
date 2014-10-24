package rest.textui.food;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import java.io.*;
import java.util.*;
import java.lang.*;

import java.lang.Integer;
import java.lang.Double;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;
import rest.textui.DuplicateKeyException;
import rest.textui.UnknownKeyException;

public class RegisterPreparedFood extends Command<Outlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public RegisterPreparedFood(Outlet outlet) {
		super(false, MenuEntry.REGISTER_PREPRARED, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException, PercentageTooHighException, UnknownKeyException {
		
		String Nome;
		
		ArrayList<String> NomesPratos = new ArrayList<String>();
		ArrayList<Integer> Percentagens = new ArrayList<Integer>();
		
		int PercentagemTotal = 0;
		Nome = IO.readString(Message.reqName());
		boolean stop = false;
		
		if (_receiver.alimento_Existe(Nome) == true)
		{
		  stop = true;
		  throw new DuplicateKeyException(Nome);
		}

		while ((PercentagemTotal < 100) && (stop == false))
		{	
		  String key;
		  int perc;
		  
		  key = IO.readString(Message.reqKey());
		  
		  if(_receiver.alimento_Existe(key) == false)
		  {
		    stop = true;
		    throw new UnknownKeyException(key);
		  }
		  
		  if(stop == false)
		  {
		    perc = Integer.parseInt(IO.readString(Message.reqPercentage()));
		   
		    NomesPratos.add(key);
		    Percentagens.add(perc);
		  
		    PercentagemTotal += perc;
		  }
		  
		  if (PercentagemTotal > 100)
		  {
		     stop = true;
		     throw new PercentageTooHighException(PercentagemTotal);
		  }		   
		}
		
		if (stop == false)
		{
		  _receiver.alimento_ConverterAgregado(Nome, NomesPratos, Percentagens);
		}		
	}
}
