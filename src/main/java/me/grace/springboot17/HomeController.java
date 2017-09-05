package me.grace.springboot17;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
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
