package rest.textui.clients;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

public class ShowAllClients extends Command<Outlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public ShowAllClients(Outlet outlet) {
		super(false, MenuEntry.SHOW_ALL_CLIENTS, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() {
		
		for (Cliente cl: _receiver.getClientes().values())
		{
		  IO.println(cl.toString());
		}
	}
}
