package rest.textui.clients;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.textui.UnknownKeyException;

import rest.Alerta;
import rest.AlertaNovelty;
import rest.AlertaDiscount;
import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

public class ListClientAlerts extends Command<Outlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public ListClientAlerts(Outlet outlet) {
		super(false, MenuEntry.SHOW_ALERTS, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException, UnknownKeyException {
	  String Nome;
	  Nome = IO.readString(Message.reqKey());
		
	  int size = _receiver.cliente_Seleccionar(Nome).getAlertas().size();
	  
	  if (_receiver.cliente_Existe(Nome) == false)
	  {
	    throw new UnknownKeyException(Nome);
	  }
		
	  else
	  {		
	    for (int i = 0; i < size; i++)
	    {
	      Alerta a = _receiver.cliente_Seleccionar(Nome).getAlertas().get(i);
		
	      if(a instanceof rest.AlertaNovelty)
	      {
		AlertaNovelty aNov = (AlertaNovelty) a;
		IO.println(aNov.toString() + Message.alertNovelty(aNov.getMessage()));
	      }
		  
	      else
	      {
		if (a instanceof rest.AlertaDiscount)
		{
		  AlertaDiscount aDis = (AlertaDiscount) a;
		  IO.println(aDis.toString() + Message.alertDiscount(aDis.getMessage()));
		}
	      }
	    }
	  }	  
	}
      }
