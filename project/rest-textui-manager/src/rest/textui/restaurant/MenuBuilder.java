package rest.textui.restaurant;

import ist.po.ui.Command;
import ist.po.ui.Menu;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

/**
 * Menu builder for a restaurant.
 */
public abstract class MenuBuilder {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public static void menuFor(Restaurante restaurant) {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new ShowDishesOfTheDay(restaurant),
				new RegisterDishOfTheDay(restaurant),
				new ShowDishOfTheDayCalories(restaurant),
				new AddDishOfTheDayToOutlet(restaurant),
				new RemoveDishOfTheDayFromOutlet(restaurant),
				new ChangeDishOfTheDayDiscount(restaurant), });
		menu.open();
	}

}
