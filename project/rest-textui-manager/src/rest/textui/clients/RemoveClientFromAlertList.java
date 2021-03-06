package rest.textui.clients;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;
import rest.textui.UnknownKeyException;

public class RemoveClientFromAlertList extends Command<Outlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public RemoveClientFromAlertList(Outlet outlet) {
		super(false, MenuEntry.UNSUBSCRIBE_ALERT_LIST, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException, UnknownKeyException {
		String Tipo, Chave;
		Chave = IO.readString(Message.reqKey());
		Tipo = IO.readString(Message.reqLeaveAlertType());
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
		  _receiver.cliente_AlertaOFF(Chave, Tipo);
		}		
	}
}
