package rest.textui.food;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;
import rest.textui.UnknownKeyException;

public class ChangeFoodProperties extends Command<Outlet> /* superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public ChangeFoodProperties(Outlet outlet) {
		super(false, MenuEntry.CHANGE_FOOD, outlet);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException, UnknownKeyException {
		String ID = IO.readString(Message.reqKey());
		int Calories = Integer.parseInt(IO.readString(Message.reqCalories()));
		
		if (_receiver.alimento_Existe(ID) == false)
		{
		  throw new UnknownKeyException(ID);		  
		}
		
		else
		{
		  _receiver.getAlimentos().get(ID).setCalorias(Calories);
		}		
	}
}
