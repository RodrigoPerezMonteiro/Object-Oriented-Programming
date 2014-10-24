package rest.textui.restaurants;

import ist.po.ui.Command;
import ist.po.ui.Menu;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

/**
 * Menu builder for restaurants.
 */
public abstract class MenuBuilder {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public static void menuFor(Outlet outlet) {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new ShowAllRestaurants(outlet),
				new RegisterRestaurant(outlet),
				new ManageRestaurant(outlet) });
		menu.open();
	}

}
