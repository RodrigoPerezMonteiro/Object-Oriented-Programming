package rest.textui.search;

import ist.po.ui.Command;
import ist.po.ui.Menu;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

/**
 * Menu builder for searching.
 */
public class MenuBuilder {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public static void menuFor(Outlet outlet) {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new ShowVegetarianFood(outlet),
				new ShowClientsWithoutAlerts(outlet),
				new ShowClientsWithClosedOrders(outlet),
				new ShowRestaurantsWithDiscounts(outlet),
				});
		menu.open();
	}

}
