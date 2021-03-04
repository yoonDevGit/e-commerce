package com.ecommerce.rooms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class UserDto {

  private String account;

  private String phone;

  private String email;
}
