package me.grace.springboot17;

import me.grace.springboot17.models.Role;
import me.grace.springboot17.models.User;
import me.grace.springboot17.repositories.RoleRepo;
import me.grace.springboot17.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/secure")
    public String admin(){
        return "secure";
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult bResult, Model model)
    {
        model.addAttribute("user", new User());

        if (bResult.hasErrors())
        {
            return "registration";
        }
        else{
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "index";
    }


    //in route, the capital does matter
    @RequestMapping("/testRoles")
    public @ResponseBody String showRoles()
    {
        Iterable <Role> r = roleRepo.findAll();
        String x="<h2>ROLE DETAILS</h2>";
        for(Role item:r)
        {
            x+="Role details:"+item.getRole()+" has an ID of "+item.getId()+"<br/>";
        }

        Role findR = roleRepo.findByRole("ADMIN");
        x+=findR.getRole()+" was found with an ID of "+findR.getId();
        return x;

    }

}

//    INSERT INTO ROLE VALUES(1, 'user');
//    INSERT INTO ROLE VALUES(2, 'ADMIN');
//    INSERT INTO USER_DATA VALUES (1, 'jim@jim.com', TRUE, 'Jim', 'Jimmerson', 'password', 'jim');
//    INSERT INTO USER_DATA VALUES (2, 'bob@bob.com', TRUE, 'Bob', 'Bobberson', 'password', 'bob');
//    INSERT INTO USER_DATA VALUES (3, 'admin@admin.com', TRUE, 'Admin', 'User', 'password', 'admin');
//    INSERT INTO USER_DATA_ROLES VALUES(1,1);
//    INSERT INTO USER_DATA_ROLES VALUES(2,1);
//    INSERT INTO USER_DATA_ROLES VALUES(3,1);
//    INSERT INTO USER_DATA_ROLES VALUES(3,2);


//For Mysql
//    use lesson17to20;
//    INSERT INTO ROLE VALUES(1, 'user');
//    INSERT INTO ROLE VALUES(2, 'ADMIN');
//    INSERT INTO user_data VALUES (1, 'jim@jim.com', TRUE, 'Jim', 'Jimmerson', 'password', 'jim');
//    INSERT INTO user_data VALUES (2, 'bob@bob.com', TRUE, 'Bob', 'Bobberson', 'password', 'bob');
//    INSERT INTO user_data VALUES (3, 'admin@admin.com', TRUE, 'Admin', 'User', 'password', 'admin');
//    INSERT INTO user_data_roles VALUES(1,1);
//    INSERT INTO user_data_roles VALUES(2,1);
//    INSERT INTO user_data_roles VALUES(3,1);
//    INSERT INTO user_data_roles VALUES(3,2);