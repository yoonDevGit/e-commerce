package com.ecommerce.rooms.service;

import com.ecommerce.rooms.domain.User;
import com.ecommerce.rooms.dto.UserDto;
import com.ecommerce.rooms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserDto getUser(Long userId) {
    User user = userRepository.findById(userId).orElseThrow();
    return new UserDto(user);
  }

  public void createUser(UserDto userDto) {
    User user = new User(userDto);
    userRepository.save(user);
  }

  public void deleteUser(Long userId) {
    User user = userRepository.findById(userId).orElseThrow();
    userRepository.delete(user);
  }

  public Page<UserDto> getPageAll(Pageable pageable) {
    Page<User> users = userRepository.findAll(pageable);
    return users.map(u -> new UserDto(u.getAccount(), u.getPhone(), u.getEmail(), u.getPoint()));
  }

  public Slice<UserDto> getSliceAll(Pageable pageable) {
    Slice<User> users = userRepository.findAll(pageable);
    return users.map(UserDto::new);
  }
}
