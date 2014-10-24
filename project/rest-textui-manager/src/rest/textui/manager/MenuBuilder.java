package rest.textui.manager;

import ist.po.ui.Command;
import ist.po.ui.Menu;

import rest.GestOutlet;
import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

/**
 * Menu builder for managers.
 */
public abstract class MenuBuilder {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public static void menuFor(Outlet mgr, GestOutlet gest) {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new New(gest),
				new Open(gest),
				new Save(gest),
				new SaveAs(gest),
				new OpenClientsMenu(mgr),
				new OpenFoodMenu(mgr),
				new OpenRestaurantsMenu(mgr),
				new OpenSearchMenu(mgr), });
		menu.open();
	}

}
