package rest;

import java.util.*;
import java.lang.Comparable;
import java.io.*;

public class AlertaDiscount extends Alerta implements Serializable
{
  private String _Message;
  private String _Tipo;

  public AlertaDiscount(String Message)
  {
    _Message = Message;
    _Tipo = "DISCOUNT";
  }
  
  public String getMessage()
  {
    return _Message;
  }
  
  public String getTipo()
  {
    return _Tipo;
  }
  
  public String toString()
  {
    return _Tipo + "|";
  }
  
}