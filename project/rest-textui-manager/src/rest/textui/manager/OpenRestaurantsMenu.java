package rest.textui.manager;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

public class OpenRestaurantsMenu extends Command<Outlet>  {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public OpenRestaurantsMenu(Outlet outlet) {
		super(false, MenuEntry.MENU_RESTAURANTS, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException {
		rest.textui.restaurants.MenuBuilder.menuFor(_receiver);
	}

}
