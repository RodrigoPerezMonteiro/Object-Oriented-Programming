package rest.textui.restaurant;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

public class ShowDishesOfTheDay extends Command<Restaurante> {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public ShowDishesOfTheDay(Restaurante restaurant) {
		super(false, MenuEntry.SHOW_DISHES_OF_THE_DAY,restaurant);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException {
		
		for (int i=0; i<_receiver.getPratosdoDia().size(); i++)
		{
		    IO.println(_receiver.getPratosdoDia().get(i).toString());
		}
	}
}
