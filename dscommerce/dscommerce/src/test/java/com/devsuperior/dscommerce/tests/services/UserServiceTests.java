package com.devsuperior.dscommerce.tests.services;

import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.projections.UserDetailsProjection;
import com.devsuperior.dscommerce.repositories.UserRepository;
import com.devsuperior.dscommerce.services.UserService;
import com.devsuperior.dscommerce.tests.factory.UserDetailsFactory;
import com.devsuperior.dscommerce.tests.factory.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository repository;

    private String existingUsername, nonExistingUsername;
    private User user, userAdmin, userClient;
    private List<UserDetailsProjection> userDetailsProjectionList;

    @BeforeEach
    void setUp() throws Exception {

        existingUsername = "maria@gmail.com";
        nonExistingUsername = "user@gmail.com";

        userClient = UserFactory.createClientUser();
        userClient = UserFactory.createCustomClientUser(1L, existingUsername);
        userDetailsProjectionList = UserDetailsFactory.createCustomAdminUser(existingUsername);

        userAdmin = UserFactory.createAdminUser();
        userAdmin = UserFactory.createCustomAdminUser(2L, existingUsername);

        when(repository.searchUserAndRolesByEmail(existingUsername)).thenReturn(userDetailsProjectionList);
        when(repository.searchUserAndRolesByEmail(nonExistingUsername)).thenReturn(new ArrayList<>());

    }

    @Test
    public void loadUserByUsernameShouldReturnUserDetailsWhenUserExists() {

        UserDetails result = userService.loadUserByUsername(existingUsername);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getUsername(), existingUsername);
    }

    @Test
    public void loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExist() {

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(nonExistingUsername));
    }
}
