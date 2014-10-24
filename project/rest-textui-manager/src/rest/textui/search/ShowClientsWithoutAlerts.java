package rest.textui.search;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

public class ShowClientsWithoutAlerts extends Command<Outlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public ShowClientsWithoutAlerts(Outlet outlet) {
		super(false, MenuEntry.SHOW_CLIENTS_WITHOUT_ALERTS, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException {
		for (Cliente cl : _receiver.getClientesSemAlertas().values())
		{
		  IO.println(cl.toString());
		}
	}
}
