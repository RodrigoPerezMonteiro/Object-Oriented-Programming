package rest.textui.restaurants;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;
import rest.textui.UnknownKeyException;

public class ManageRestaurant extends Command<Outlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public ManageRestaurant(Outlet outlet) {
		super(false, MenuEntry.OPEN_RESTAURANT_MENU, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException, UnknownKeyException {
	  
	String chave = IO.readString(Message.reqKey());
	  
	if (_receiver.restaurante_Existe(chave) == false)
	{
	   throw new UnknownKeyException(chave);
	}
	
	else
	{
	  rest.textui.restaurant.MenuBuilder.menuFor(_receiver.restaurante_Seleccionar(chave));
	}	   
      }
}
