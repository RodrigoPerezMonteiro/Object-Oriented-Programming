package rest.textui.search;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

public class ShowRestaurantsWithDiscounts extends Command<Outlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public ShowRestaurantsWithDiscounts(Outlet outlet) {
		super(false, MenuEntry.SHOW_RESTAURANTS_WITH_DISCOUNTS, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException {
		//FIXME: implement command
	}
}
