package rest.textui;

import static ist.po.ui.Dialog.IO;

import rest.GestOutlet;
import rest.Outlet;
import java.util.*;
import java.lang.Comparable;
import java.io.*;

public class Manager {

  public static void main(String[] args) {
    
    GestOutlet gest = new GestOutlet();
    
    String file = System.getProperty("Import");
    if(file != null)
    {
      gest.openFile(file);
    }

    rest.textui.manager.MenuBuilder.menuFor(gest.getOutlet(), gest);
    IO.closeDown();
  }
}