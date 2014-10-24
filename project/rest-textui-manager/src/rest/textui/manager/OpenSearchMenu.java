package rest.textui.manager;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

public class OpenSearchMenu extends Command<Outlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public OpenSearchMenu(Outlet outlet) {
		super(false, MenuEntry.MENU_SEARCH, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException {
		rest.textui.search.MenuBuilder.menuFor(_receiver);
	}

}
