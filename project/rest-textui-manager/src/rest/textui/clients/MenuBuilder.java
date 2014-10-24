package rest.textui.clients;

import ist.po.ui.Command;
import ist.po.ui.Menu;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

/**
 * Menu builder for clients.
 */
public abstract class MenuBuilder {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public static void menuFor(Outlet outlet) {
		Menu menu = new Menu(MenuEntry.TITLE, new Command<?>[] {
				new ShowAllClients(outlet),
				new RegisterClient(outlet),
				new ChangeClientType(outlet),
				new AddClientToAlertList(outlet),
				new RemoveClientFromAlertList(outlet),
				new ListClientAlerts(outlet),
		});
		menu.open();
	}

}
