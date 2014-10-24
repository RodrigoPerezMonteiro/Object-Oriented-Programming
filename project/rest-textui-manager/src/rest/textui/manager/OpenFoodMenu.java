package rest.textui.manager;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

public class OpenFoodMenu extends Command<Outlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public OpenFoodMenu(Outlet outlet) {
		super(false, MenuEntry.MENU_FOOD, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException {
		rest.textui.food.MenuBuilder.menuFor(_receiver);
	}

}
