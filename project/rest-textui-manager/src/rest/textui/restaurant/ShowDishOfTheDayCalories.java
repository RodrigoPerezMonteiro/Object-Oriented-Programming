package rest.textui.restaurant;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

public class ShowDishOfTheDayCalories extends Command<Restaurante> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public ShowDishOfTheDayCalories(Restaurante restaurant) {
		super(false, MenuEntry.COMPUTE_CALORIES, restaurant);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException {
		
		String Nome = IO.readString(Message.reqKey());
		IO.println(_receiver.getPDD(Nome).printCalorias());
	}
}
