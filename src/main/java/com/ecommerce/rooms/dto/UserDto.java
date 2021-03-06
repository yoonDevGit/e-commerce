package com.ecommerce.rooms.dto;

import com.ecommerce.rooms.domain.User;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {

  private String account;

  private String phone;

  private String email;

  private int point;

  public UserDto(User user) {
    this.account = user.getAccount();
    this.phone = user.getPhone();
    this.email = user.getEmail();
    this.point = user.getPoint();
  }
}
