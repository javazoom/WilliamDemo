package com.william.demo.jdon.util;

import java.util.*;

public class MyCompare implements Comparator {

  public int compare(Object x, Object y) {

    Integer a = (Integer) x, b = (Integer) y;
    if (a.intValue() > b.intValue())
      return 1;
    else if (a.intValue() == b.intValue())
      return 0;
    else
      return -1;
  }

}