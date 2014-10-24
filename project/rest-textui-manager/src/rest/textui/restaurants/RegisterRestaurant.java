package rest.textui.restaurants;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;
import rest.textui.DuplicateKeyException;

public class RegisterRestaurant extends Command<Outlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public RegisterRestaurant(Outlet outlet) {
		super(false, MenuEntry.REGISTER_RESTAURANT, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException, DuplicateKeyException {
		String Nome, Email;
		
		Nome = IO.readString(Message.reqName());
		Email = IO.readString(Message.reqEmail());
		
		if (_receiver.restaurante_Existe(Nome))
		{
		  throw new DuplicateKeyException(Nome);
		}
		
		if (_receiver.restaurante_Existe(Nome) == false)
		{
		  _receiver.restaurante_Registar(Nome, Email);
		}
		
	}
}
