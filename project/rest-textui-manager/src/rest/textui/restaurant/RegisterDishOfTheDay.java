package rest.textui.restaurant;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.*;
import java.lang.*;
import java.lang.Integer;
import java.lang.Double;

import java.io.IOException;

import rest.Outlet;
import rest.Restaurante;
import rest.Cliente;
import rest.textui.DuplicateKeyException;
import rest.textui.UnknownKeyException;


public class RegisterDishOfTheDay extends Command<Restaurante> /*FIXME: superclass may be other */ {

	/**
	 * FIXME: there may be one or more receivers
	 */
	public RegisterDishOfTheDay(Restaurante restaurant) {
		super(false, MenuEntry.CREATE_DISH_OF_THE_DAY, restaurant);
	}

	/**
	 * @see ist.po.ui.Command#execute()
	 */
	@Override
	public final void execute() throws DialogException, IOException, NullPointerException, UnknownKeyException, DuplicateKeyException {
		String Nome;
		int Quantidade, Preco, Desconto;
		
		Nome = IO.readString(Message.reqFoodKey());
		Quantidade = Integer.parseInt(IO.readString(Message.reqQuantity()));
		Preco = Integer.parseInt(IO.readString(Message.reqPrice()));
		Desconto = 0;

		
		_receiver.addPDD(Quantidade, Preco, Nome, Desconto);
	}
}
