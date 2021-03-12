package com.ecommerce.rooms.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MailDto {

  private String to;

  private String title;

  private String message;
}
