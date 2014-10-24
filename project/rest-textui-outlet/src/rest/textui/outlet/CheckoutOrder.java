package rest.textui.outlet;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;
import rest.textui.UnknownKeyException;

public class CheckoutOrder extends Command<Outlet> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public CheckoutOrder(Outlet outlet) {
		super(false, MenuEntry.CHECKOUT, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException, UnknownKeyException {
		String Client = IO.readString(Message.reqClientId());
		
		if ((_receiver.cliente_Existe(Client)) == false)
		{
		  throw new UnknownKeyException(Client);
		}
		else
		{
		  _receiver.cliente_Seleccionar(Client).pagar();
		}
	}
}
