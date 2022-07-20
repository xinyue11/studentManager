package com.example.control;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.bean.Student;
import com.example.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stu")
public class StudentControl {
    @Autowired
    private StudentDao studentDao;
    @GetMapping
    public List<Student> getAllStu(){
        List<Student> students = studentDao.selectList(null);
        System.out.println(students);
        return students;
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Integer id){
        Student student = studentDao.selectById(id);
        System.out.println(student);
        return student;
    }

    //学号查询
    @GetMapping("/son/{son}")
    public List<Student> getBySon(@PathVariable Integer son){
        ArrayList<Student> students = new ArrayList<>();

        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Student::getSon,son);

        Student student = studentDao.selectOne(lqw);
        System.out.println(student);
        students.add(student);

        System.out.println(students);
        return students;
    }

    @PostMapping
    public boolean addStu(@RequestBody Student student){
        int insert = studentDao.insert(student);
        boolean isFlag=true;
        if (insert==0){
            isFlag=false;
        }
        return isFlag;
    }



    @PutMapping
    public boolean updateStu(@RequestBody Student student){
        int update = studentDao.updateById(student);
        boolean isFlag=true;
        if (update==0){
            isFlag=false;
        }
        return isFlag;
    }

    @DeleteMapping("/{id}")
    public boolean deleteStu(@PathVariable Integer id) { //绑定路径参数与处理器方法形参间的关系，要求路径参数名与形参名一一对应
        int deleteById = studentDao.deleteById(id);
        boolean isFlag=true;
        if (deleteById==0){
            isFlag=false;
        }
        return isFlag;
    }
}
