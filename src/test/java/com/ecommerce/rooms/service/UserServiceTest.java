package com.ecommerce.rooms.service;

import com.ecommerce.rooms.domain.User;
import com.ecommerce.rooms.dto.UserDto;
import com.ecommerce.rooms.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

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
        UserDto userDto = new UserDto("dyshin", "010-0000-0000", "yoondeb93@gmail.com");
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
        UserDto userDto = new UserDto("dyshin", "010-0000-0000", "yoondeb93@gmail.com");
        User user = new User(userDto);
        userRepository.save(user);

        long userId = 1;

        // WHEN
        userRepository.delete(user);

        // THEN
        assertThrows(NoSuchElementException.class, () -> userRepository.findById(userId).orElseThrow());
    }
}