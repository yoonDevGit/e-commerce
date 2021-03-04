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

  public UserDto(User user) {
    this.account = user.getAccount();
    this.phone = user.getPhone();
    this.email = user.getEmail();
  }
}
