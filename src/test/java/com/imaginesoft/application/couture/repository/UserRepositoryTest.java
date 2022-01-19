package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.Gender;
import com.imaginesoft.application.couture.model.User;
import com.imaginesoft.application.couture.model.UserCategory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = {"classpath:application-h2-test.properties"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {

    private final Long ID_3 = 3L;
    private final String FIRST_NAME = "Pauline";
    private final String EDITED_FIRST_NAME = "Thierry";
    private final String LAST_NAME = "Phoenix";
    private final String TELEPHONE = "418-555-6699";
    private final String EDITED_TELEPHONE = "418-333-4488";
    private final Gender GENDER_MALE = Gender.FEMALE;
    private final UserCategory USER_CATEGORY = UserCategory.EMPLOYEE;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Test
    @Order(1)
    void givenClient_whenCreateUser_thenUserExists() {

        User newUser = createNewUser();
        User createdUser = entityManager.persist(newUser);

        Assertions.assertAll(
                () -> assertThat(createdUser).isNotNull(),
                () -> assertThat(createdUser.getFirstName()).isEqualTo(FIRST_NAME),
                () -> assertThat(createdUser.getLastName()).isEqualTo(LAST_NAME),
                () -> assertThat(createdUser.getTelephone()).isEqualTo(TELEPHONE),
                () -> assertThat(createdUser.getUserCategory()).isEqualTo(USER_CATEGORY)
        );
    }

    @Test
    @Order(2)
    void givenUser_whenUpdateUser_thenUserHasChanged() {

        User newUser = createNewUser();

        User userToUpdate = entityManager.persist(newUser);
        userToUpdate.setFirstName(EDITED_FIRST_NAME);
        userToUpdate.setTelephone(EDITED_TELEPHONE);

        User updatedUser = repository.save(userToUpdate);

        Assertions.assertAll(
                () -> assertThat(updatedUser.getId()).isEqualTo(userToUpdate.getId()),
                () -> assertThat(updatedUser.getFirstName()).isEqualTo(EDITED_FIRST_NAME),
                () -> assertThat(updatedUser.getTelephone()).isEqualTo(EDITED_TELEPHONE)
        );
    }

    @Test
    @Order(3)
    void givenUser_whenDeleteUser_thenUserDoesNotExists() {

        User newUser = createNewUser();

        User userToDelete = entityManager.persist(newUser);
        repository.delete(userToDelete);

        Optional<User> deletedUser = repository.findById(ID_3);

        assertThat(deletedUser).isNotPresent();
    }

    private User createNewUser() {
        User newUser = new User();
        newUser.setFirstName(FIRST_NAME);
        newUser.setLastName(LAST_NAME);
        newUser.setTelephone(TELEPHONE);
        newUser.setGender(GENDER_MALE);
        newUser.setUserCategory(USER_CATEGORY);
        return newUser;
    }

}