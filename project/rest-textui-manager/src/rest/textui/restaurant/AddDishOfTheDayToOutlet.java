package rest.textui.restaurant;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

public class AddDishOfTheDayToOutlet extends Command<Restaurante> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public AddDishOfTheDayToOutlet(Restaurante restaurant) {
		super(false, MenuEntry.MAKE_AVAILABLE, restaurant);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException, InvalidKeyException {
		String Nome = IO.readString(Message.reqKey());
		if(_receiver.pDD_existe(Nome) == false)
		{
		  throw new InvalidKeyException(Nome);
		}
		
		else
		{
		  _receiver.pDD_ON(Nome);
		}
	}
}
