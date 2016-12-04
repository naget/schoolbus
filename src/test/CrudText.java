import com.schoolbus.SpringApplication1;
import com.schoolbus.model.User;
import com.schoolbus.repository.RoleRepository;
import com.schoolbus.repository.UserRepository;
import com.schoolbus.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by t on 2016/11/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringApplication1.class)
@WebAppConfiguration
public class CrudText {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
    @Test
    public void userAndRoleTest()
    {
        User user=userRepository.findByName("wangp7");
        System.out.println(user);

    }

    /**
     * 注册用户赋予权限测试
     */
//    @Test
//    public void manyToMany()
//    {
//       Role role = roleRepository.findByName("USER");
//        User user = new User();
//        List<Role> roles=new ArrayList<>();
//        roles.add(role);
//        user.setRoles(roles);
//        user.setName("用户1");
//        user.setPwd("456852");
//        user.setPhoneNumber("13656985642");
//        user.setStatus(0);
//
//        userService.save(user);
//    }
}
