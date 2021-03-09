package com.ecommerce.rooms.service;

import com.ecommerce.rooms.domain.User;
import com.ecommerce.rooms.dto.UserDto;
import com.ecommerce.rooms.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Rollback(false)
class UserServiceTest {

  @Autowired
  UserRepository userRepository;

  @Test
  public void getUser() throws Exception {
    // GIVEN
    UserDto userDto = new UserDto("dyshin", "010-0000-0000", "yoondeb93@gmail.com", 0);
    User user = new User(userDto);
    userRepository.save(user);

    long userId = 1;

    // WHEN
    User findUser = userRepository.findById(userId).orElseThrow();

    // THEN
    assertThat(user.getAccount()).isEqualTo(findUser.getAccount());
  }

  @Test
  @ExceptionHandler(NoSuchElementException.class)
  public void deleteUser() {
    // GIVEN
    UserDto userDto = new UserDto("dyshin", "010-0000-0000", "yoondeb93@gmail.com", 0);
    User user = new User(userDto);
    userRepository.save(user);

    long userId = 1;

    // WHEN
    userRepository.delete(user);

    // THEN
    assertThrows(NoSuchElementException.class, () -> userRepository.findById(userId).orElseThrow());
  }

  @Test
  @ExceptionHandler(NoSuchElementException.class)
  public void getCountUsers() {
    // GIVEN
    for (int i = 0; i < 100; i++) {
      UserDto userDto = new UserDto("dyshin" + i, "010-0000-" + i, "yoondeb93" + i + "@gmail.com",
          0 + i);
      User user = new User(userDto);
      userRepository.save(user);
    }
    PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "account"));

    // WHEN
    Page<User> users = userRepository.findAll(pageRequest);

    Page<UserDto> userdtos = users.map(UserDto::new);

    // THEN
    assertThat(userdtos.getTotalPages()).isEqualTo(5);
    assertThat(userdtos.getTotalElements()).isEqualTo(100);
    assertThat(userdtos.getNumberOfElements()).isEqualTo(20);
    assertThat(userdtos.isFirst()).isTrue();
    assertThat(userdtos.hasNext()).isTrue();
  }

  @Test
  @ExceptionHandler(NoSuchElementException.class)
  public void getSliceUsers() {
    // GIVEN
    for (int i = 0; i < 100; i++) {
      UserDto userDto = new UserDto("dyshin" + i, "010-0000-" + i, "yoondeb93" + i + "@gmail.com",
          0 + i);
      User user = new User(userDto);
      userRepository.save(user);
    }
    PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "account"));

    // WHEN
    Slice<User> users = userRepository.findAll(pageRequest);

    Slice<UserDto> userdtos = users.map(UserDto::new);

    // THEN
    assertThat(userdtos.getNumber()).isEqualTo(0);
    assertThat(userdtos.getNumberOfElements()).isEqualTo(20);
    assertThat(userdtos.isFirst()).isTrue();
    assertThat(userdtos.hasNext()).isTrue();
  }
}