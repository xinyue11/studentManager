package com.example.control;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.bean.Grade;
import com.example.bean.Lesson;
import com.example.bean.Student;
import com.example.bean.Teacher;
import com.example.dao.LessonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonControl {

    @Autowired
    private LessonDao lessonDao;

    //查询所有
    @GetMapping
    public List<Lesson> getAll(){
        List<Lesson> lessons = lessonDao.selectList(null);
        System.out.println(lessons);
        return lessons;
    }

    //编号查询
    @GetMapping("/{bh}")
    public Lesson getByBh(@PathVariable Integer bh){
        LambdaQueryWrapper<Lesson> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Lesson::getBh,bh);

        Lesson lesson = lessonDao.selectOne(lqw);
        return lesson;
    }

    //编号集合查询
    @GetMapping("/bh/{bh}")
    public List<Lesson> getByBhs(@PathVariable Integer bh){
        LambdaQueryWrapper<Lesson> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Lesson::getBh,bh);

        Lesson lesson = lessonDao.selectOne(lqw);
        ArrayList<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson);
        return lessons;
    }

    //添加
    @PostMapping
    public boolean addLesson(@RequestBody Lesson lesson){
        int insert = lessonDao.insert(lesson);
        boolean isFlag=true;
        if (insert==0){
            isFlag=false;
        }
        return isFlag;
    }

    @PutMapping
    public boolean updateLess(@RequestBody Lesson lesson){
        LambdaQueryWrapper<Lesson> lqw = new LambdaQueryWrapper<>();
        Integer bh = lesson.getBh();
        lqw.eq(Lesson::getBh,bh);
        int update = lessonDao.update(lesson, lqw);
        boolean isFlag=true;
        if (update==0){
            isFlag=false;
        }
        return isFlag;
    }

    @DeleteMapping("/{bh}")
    public boolean deleteLess(@PathVariable Integer bh) { //绑定路径参数与处理器方法形参间的关系，要求路径参数名与形参名一一对应
        LambdaQueryWrapper<Lesson> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Lesson::getBh,bh);
        int delete = lessonDao.delete(lqw);
        boolean isFlag=true;
        if (delete==0){
            isFlag=false;
        }
        return isFlag;
    }
}
