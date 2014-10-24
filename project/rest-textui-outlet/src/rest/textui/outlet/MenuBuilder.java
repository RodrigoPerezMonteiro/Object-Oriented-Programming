package rest.textui.outlet;

import ist.po.ui.Command;
import ist.po.ui.Menu;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

/**
 * Menu builder.
 */
public abstract class MenuBuilder {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public static void menuFor(Outlet outlet) {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new ShowDishesOfTheDay(outlet),
				new AddDishOfTheDayToOrder(outlet),
				new CheckoutOrder(outlet),
				});
		menu.open();
	}

}
