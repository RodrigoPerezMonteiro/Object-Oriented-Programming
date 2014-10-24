package rest;

import java.io.*;
import java.util.*;
import java.lang.*;

import java.io.Serializable;

public class PredicateClientWithNoOrders implements ClientPredicate, Serializable {

  public boolean OK (Cliente C)
  {
    return C.getCarrinhoCompras().getEmpty();
  }
} 
