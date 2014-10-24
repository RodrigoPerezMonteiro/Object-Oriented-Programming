package rest.textui.outlet;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;
import java.io.*;
import java.lang.*;
import java.util.*;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;
import rest.PratodoDia;

public class ShowDishesOfTheDay extends Command<Outlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public ShowDishesOfTheDay(Outlet outlet) {
		super(false, MenuEntry.SHOW_DISHES_OF_THE_DAY, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException {
		
		ArrayList<String> _pratos = new ArrayList<String> ();
		
		for (Restaurante rest : _receiver.getRestaurantes().values())
		{
		    for (int i=0; i<rest.getPratosdoDia().size(); i++)
		    {
		      if(rest.getPratosdoDia().get(i).getDisponivel())
		      {
			if ((_pratos.contains(rest.getPratosdoDia().get(i).toStringOutlet()) == false))
			{
			  _pratos.add(rest.getPratosdoDia().get(i).toStringOutlet());
			}
		      }
		    }
		}
		
		for (int j = 0; j<_pratos.size(); j++)
		{
		  IO.println(_pratos.get(j));
		}
	}
}
