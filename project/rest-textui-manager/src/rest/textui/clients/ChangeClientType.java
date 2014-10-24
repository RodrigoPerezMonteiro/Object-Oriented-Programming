package rest.textui.clients;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.textui.UnknownKeyException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

public class ChangeClientType extends Command<Outlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public ChangeClientType(Outlet outlet) {
		super(false, MenuEntry.CHANGE_TYPE, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException, UnknownKeyException {
		
		String ID = IO.readString(Message.reqKey());
		String Tipo = IO.readString(Message.reqType());
		while (((Tipo.equals(Message.clientTypeOmnivorous())) == false) && ((Tipo.equals(Message.clientTypeVegetarian())) == false))
		{
		  Tipo = IO.readString(Message.reqType());
		}
		
		if (_receiver.cliente_Existe(ID) == false)
		{
		   throw new UnknownKeyException(ID);
		}
		
		else
		{
		  _receiver.cliente_AlterarTipo(ID, Tipo);
		}
	}
}
