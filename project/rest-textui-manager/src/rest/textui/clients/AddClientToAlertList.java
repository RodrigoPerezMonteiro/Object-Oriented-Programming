package rest.textui.clients;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.textui.UnknownKeyException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;

public class AddClientToAlertList extends Command<Outlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public AddClientToAlertList(Outlet outlet) {
		super(false, MenuEntry.SUBSCRIBE_ALERT_LIST, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException, UnknownKeyException {
		String Tipo, Chave;
		Chave = IO.readString(Message.reqKey());
		Tipo = IO.readString(Message.reqJoinAlertType());
		
		while ((Tipo.equals(Message.alertTypeDiscount()) == false) && ((Tipo.equals(Message.alertTypeNovelty())) == false))
		{
		  Tipo = IO.readString(Message.reqJoinAlertType());
		}
		
		if (_receiver.cliente_Existe(Chave) == false)
		{
		  UnknownKeyException uke = new UnknownKeyException(Chave);
		  IO.println(uke.getMessage());
		}
		
		else
		{
		  _receiver.cliente_AlertaON(Chave, Tipo);
		}
	}
}
