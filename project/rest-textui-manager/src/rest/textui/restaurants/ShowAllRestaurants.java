package rest.textui.restaurants;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

public class ShowAllRestaurants extends Command<Outlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public ShowAllRestaurants(Outlet outlet) {
		super(false, MenuEntry.SHOW_RESTAURANTS, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() {
		for (Restaurante rest: _receiver.getRestaurantes().values())
		{
		  IO.println(rest.toString());
		}			
	}
}
