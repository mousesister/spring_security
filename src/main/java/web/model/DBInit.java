//package web.model;
//
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import web.dao.RoleDao;
//import web.dao.UserDao;
//import javax.annotation.PostConstruct;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class DBInit {
//    private final UserDao userDao;
//    private final RoleDao roleDao;
//    public DBInit(UserDao userDao, RoleDao roleDao) {
//        this.userDao = userDao;
//        this.roleDao = roleDao;
//    }
//
//    @PostConstruct
//    @Transactional
//    public void initApiRoleData() {
//        Role role1 = new Role();
//        role1.setId(1);
//        role1.setNameRole("ROLE_USER");
//        roleDao.addRole(role1);
//
//        Role role2 = new Role();
//        role2.setId(2);
//        role2.setNameRole("ROLE_ADMIN");
//        roleDao.addRole(role2);
//    }
//
//    @PostConstruct
//    @Transactional
//    public void initApiUserData() {
//        User user1 = new User();
//        user1.setCount(1);
//        user1.setName("Jonh");
//        user1.setSurname("Dow");
//        user1.setAge(42);
//        user1.setEmail("test1@mail.ru");
//        user1.setPassword("Jonh");
//        user1.setRoles(new HashSet<Role>(Collections.singleton(roleDao.getRoleByName("ROLE_USER"))));
//        userDao.save(user1);
//
//        User user2 = new User();
//        user2.setCount(2);
//        user2.setName("Jane");
//        user2.setSurname("Dow");
//        user2.setAge(51);
//        user2.setEmail("test2@mail.ru");
//        user2.setPassword("Jane");
//        user2.setRoles(new HashSet<Role>(Collections.singleton(roleDao.getRoleByName("ROLE_USER"))));
//        userDao.save(user2);
//
//        User user3 = new User();
//        user3.setCount(3);
//        user3.setName("Thomas");
//        user3.setSurname("Hanks");
//        user3.setAge(65);
//        user3.setEmail("test3@mail.ru");
//        user3.setPassword("Thomas");
//        user3.setRoles(new HashSet<Role>(Collections.singleton(roleDao.getRoleByName("ROLE_USER"))));
//        userDao.save(user3);
//
//        User user4 = new User();
//        user4.setCount(4);
//        user4.setName("Rita");
//        user4.setSurname("Hayworth");
//        user4.setAge(103);
//        user4.setEmail("test4@mail.ru");
//        user4.setPassword("Rita");
//        Set<Role> roles4 = new HashSet<>();
//        roles4.add(roleDao.getRoleByName("ROLE_USER"));
//        roles4.add(roleDao.getRoleByName("ROLE_ADMIN"));
//        user4.setRoles(roles4);
//        userDao.save(user4);
//    }
//}
