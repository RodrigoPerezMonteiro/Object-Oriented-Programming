package rest.textui.food;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Alimento;
import rest.Cliente;

public class ShowAllFood extends Command<Outlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public ShowAllFood(Outlet outlet) {
		super(false, MenuEntry.LIST_FOOD, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException {
		
		for (Alimento al : _receiver.getAlimentos().values())
		{
		  IO.println(al.toString());
		}
	}
}
