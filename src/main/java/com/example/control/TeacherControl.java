package com.example.control;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bean.RO;
import com.example.bean.Student;
import com.example.bean.Teacher;
import com.example.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherControl {

    @Autowired
    private TeacherDao teacherDao;

    //查询所有
    @GetMapping("/{currentPage}/{pageSize}")
    public RO getAll(@PathVariable Integer currentPage,@PathVariable Integer pageSize){
        Page<Teacher> tp = new Page<>(currentPage, pageSize);
        teacherDao.selectPage(tp,null);
        long current = tp.getCurrent();
        long size = tp.getSize();
        long total = tp.getTotal();
        List<Teacher> records = tp.getRecords();

        if(records.size()==0){
            Page<Teacher> tpage = new Page<>(currentPage - 1, pageSize);
            teacherDao.selectPage(tpage,null);
            RO ro = new RO(tpage.getCurrent(), tpage.getSize(), tpage.getTotal(), tpage.getRecords());
            return ro;

        }

        RO ro = new RO(current, size, total, records);
        return ro;
    }
    //id查询
    @GetMapping("/{id}")
    public Teacher getById(@PathVariable Integer id){
        Teacher teacher = teacherDao.selectById(id);
        System.out.println(teacher);
        return teacher;
    }

    //学号查询
    @GetMapping("/tno/{tno}")
    public List<Teacher> getByTno(@PathVariable Integer tno){
        ArrayList<Teacher> teachers = new ArrayList<>();

        LambdaQueryWrapper<Teacher> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Teacher::getTno,tno);

        Teacher teacher = teacherDao.selectOne(lqw);
        System.out.println(teacher);
        teachers.add(teacher);

        System.out.println(teacher);
        return teachers;
    }


    @PostMapping
    public boolean addTea(@RequestBody Teacher teacher){
        int insert = teacherDao.insert(teacher);
        boolean isFlag=true;
        if (insert==0){
            isFlag=false;
        }
        return isFlag;
    }

    @PutMapping
    public boolean updateTea(@RequestBody Teacher teacher){
        int update = teacherDao.updateById(teacher);
        boolean isFlag=true;
        if (update==0){
            isFlag=false;
        }
        return isFlag;
    }

    @DeleteMapping("/{id}")
    public boolean deleteTea(@PathVariable Integer id) { //绑定路径参数与处理器方法形参间的关系，要求路径参数名与形参名一一对应
        int deleteById = teacherDao.deleteById(id);
        boolean isFlag=true;
        if (deleteById==0){
            isFlag=false;
        }
        return isFlag;
    }
}
