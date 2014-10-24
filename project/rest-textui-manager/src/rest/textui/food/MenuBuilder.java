package rest.textui.food;

import ist.po.ui.Command;
import ist.po.ui.Menu;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

/**
 * Menu builder for food.
 */
public abstract class MenuBuilder {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public static void menuFor(Outlet outlet) {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] { 
				new ShowAllFood(outlet),
				new RegisterFood(outlet),
				new RegisterPreparedFood(outlet),
				new ChangeFoodProperties(outlet),
				new DescribePreparedFood(outlet),
				});
		menu.open();
	}

}
