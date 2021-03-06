package com.ecommerce.rooms.domain;

import static javax.persistence.CascadeType.ALL;

import com.ecommerce.rooms.domain.base.BaseTimeEntity;
import com.ecommerce.rooms.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** 사용자
 * */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseTimeEntity {

  @Id @GeneratedValue
  @Column(name = "user_id")
  private Long id;

  private String account;

  private String phone;

  private String email;

  private int point;

  @OneToMany(mappedBy = "user", cascade = ALL)
  @JsonBackReference
  private List<Reservation> reservations;

  public User(UserDto userDto) {
    this.account = userDto.getAccount();
    this.phone = userDto.getPhone();
    this.email = userDto.getEmail();
    this.point = userDto.getPoint();
  }
}
