package rest.textui.restaurant;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;
import rest.textui.UnknownKeyException;


public class ChangeDishOfTheDayDiscount extends Command<Restaurante> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public ChangeDishOfTheDayDiscount(Restaurante restaurant) {
		super(false, MenuEntry.CHANGE_DISCOUNT, restaurant);
	}


	public final void execute() throws DialogException, IOException, InvalidKeyException, UnknownKeyException {
		String Nome = IO.readString(Message.reqKey());
				
		if (!(_receiver.pDD_existe(Nome)))
		{
		   throw new UnknownKeyException(Nome);
		}
		
		else
		{
		
		  if (!(_receiver.pDD_estaDisponivel(Nome)))
		  {
		    throw new InvalidKeyException(Nome);
		  }
		  
		  else
		  {
		    int Desconto = Integer.parseInt(IO.readString(Message.reqDiscount()));
		    if ((Desconto>=0) && (Desconto<=100))
		    {
		      _receiver.aplicarDesconto(Nome, Desconto);
		    }
		  }
		}
	}
}
