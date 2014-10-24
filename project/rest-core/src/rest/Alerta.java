package rest;

import java.util.*;
import java.lang.Comparable;
import java.io.*;

public abstract class Alerta implements Serializable{

  public abstract String getMessage();  
  public abstract String getTipo();  
  public abstract String toString();
}
