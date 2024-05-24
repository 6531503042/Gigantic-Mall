//package com.gigantic.entity.user;
//
//import com.gigantic.admin.Repository.UserRepository;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.annotation.Rollback;
//
//@DataJpaTest(showSql = false)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = false)
//
//public class UserRepositoryTest {
//
//    private final UserRepository userRepository;
//    private final TestEntityManager entityManager;
//
//    public UserRepositoryTest(UserRepository userRepository, TestEntityManager entityManager) {
//        this.userRepository = userRepository;
//        this.entityManager = entityManager;
//    }
//
//    public void testCreateNewUserWithOnerole() {
//        Role roleAdmin = entityManager.find()
//    }
//}
