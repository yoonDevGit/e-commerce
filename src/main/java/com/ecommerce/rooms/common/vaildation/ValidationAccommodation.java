package com.ecommerce.rooms.common.vaildation;

import com.ecommerce.rooms.common.Constant.AccommodationType;
import org.springframework.stereotype.Component;

@Component
public class ValidationAccommodation {

  public void type(String accommodationType ) {
    boolean result = false;
    AccommodationType[] types = AccommodationType.values();
    for (AccommodationType t : types) {
      String type = t.toString().toLowerCase();
      if (accommodationType.contains(type)) {
        result = true;
        break;
      }
    }

    if (!result) {
      throw new IllegalStateException("The accommodation type is not correct.");
    }
  }

}
