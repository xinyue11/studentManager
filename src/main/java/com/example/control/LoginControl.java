package com.example.control;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.bean.Teacher;
import com.example.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@RestController
public class LoginControl {
    @Autowired
    private TeacherDao teacherDao;

    @PostMapping("/loginser")
    public void login(String username, String password, HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {

        System.out.println("ser");
        LambdaQueryWrapper<Teacher> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Teacher::getTno,username);

        Teacher teacher = teacherDao.selectOne(lqw);
        if (teacher==null) {

            response.sendRedirect("/errlogin.html");
            //req.getRequestDispatcher("/errlogi1n.html").forward(req,response);
        } else {
            String password1 = teacher.getPassword();
            if(password1.equals(password)){
               HttpSession session = req.getSession();
               session.setAttribute("username",username);

                response.sendRedirect("/index.html");
               // req.getRequestDispatcher("/index.html").forward(req,response);
            }else {
               // response.sendRedirect("/errlogin.html");
                req.getRequestDispatcher("/errlogin.html").forward(req,response);
            }

        }


    }

}
