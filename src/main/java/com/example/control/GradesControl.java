package com.example.control;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.bean.Grade;

import com.example.bean.Grades;
import com.example.bean.Student;
import com.example.dao.GradeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradesControl {
    @Autowired
    private GradeDao gradeDao;


    //查询所有
    @GetMapping
    public List<Grades> getAllGrads(){
        List<Grade> gradeList = gradeDao.selectList(null);
        int count=0;
        ArrayList<Grades> gradess = new ArrayList<>();
        for (Grade gra:gradeList) {
            count=0;
            Integer son = gra.getSon();
            String name = gra.getName();
            Integer fs = gra.getFs();
            Integer xx = gra.getXx();
            Integer blz = gra.getBlz();

            Integer[] bjg= new Integer[]{fs,xx,blz};
            for (int i = 0; i < bjg.length; i++) {
                if(bjg[i]<60){
                    count++;
                }
            }
            //小数乘100
            Double bjgl= ((double)count/bjg.length)*100;

            //取整
            int bjgi = bjgl.intValue();

            String  bjgls =bjgi+"";
            bjgls=bjgls+"%";

            //平均分计算
            Double ave= (fs+xx+blz)/3.0;
            Integer avei= (int)Math.rint(ave);

            //总分计算
            Integer total=fs+xx+blz;

            //添加到对象
            Grades grades = new Grades(son, name, xx, fs, blz, avei, total, bjgls);

            gradess.add(grades);
        }
        System.out.println(gradess);
        return gradess;
    }
    //学号查询
    @GetMapping("/son/{son}")
    public List<Grades> getByGra(@PathVariable Integer son){
        ArrayList<Grade> gradeList = new ArrayList<>();
        LambdaQueryWrapper<Grade> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Grade::getSon,son);
        Grade grade = gradeDao.selectOne(lqw);

        gradeList.add(grade);

        int count=0;
        ArrayList<Grades> gradess = new ArrayList<>();
        for (Grade gra:gradeList) {
            count=0;
          //  Integer son = gra.getSon();
            String name = gra.getName();
            Integer fs = gra.getFs();
            Integer xx = gra.getXx();
            Integer blz = gra.getBlz();

            Integer[] bjg= new Integer[]{fs,xx,blz};
            for (int i = 0; i < bjg.length; i++) {
                if(bjg[i]<60){
                    count++;
                }
            }
            //小数乘100
            Double bjgl= ((double)count/bjg.length)*100;

            //取整
            int bjgi = bjgl.intValue();

            String  bjgls =bjgi+"";
            bjgls=bjgls+"%";

            //平均分计算
            Double ave= (fs+xx+blz)/3.0;
            Integer avei= (int)Math.rint(ave);

            //总分计算
            Integer total=fs+xx+blz;

            //添加到对象
            Grades grades = new Grades(son, name, xx, fs, blz, avei, total, bjgls);

            gradess.add(grades);
        }
        System.out.println(gradess);
        return gradess;
    }

    //录入成绩
    @PostMapping
    public boolean addGra(@RequestBody Grade grade){
        int insert = gradeDao.insert(grade);
        boolean isFlag=true;
        if (insert==0){
            isFlag=false;
        }
        return isFlag;
    }

    //修改成绩
    //先查询，再修改
    @GetMapping("/{son}")
    public Grade getById(@PathVariable Integer son){
        LambdaQueryWrapper<Grade> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Grade::getSon,son);

        Grade grade = gradeDao.selectOne(lqw);
        return grade;
    }


    @PutMapping
    public boolean updateGra(@RequestBody Grade grade){
        System.out.println("fdsaf");
        LambdaQueryWrapper<Grade> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Grade::getSon,grade.getSon());

        int update = gradeDao.update(grade,lqw);
        boolean isFlag=true;
        if (update==0){
            isFlag=false;
        }
        return isFlag;
    }

    @DeleteMapping("/{son}")
    public boolean deleteGra(@PathVariable Integer son) { //绑定路径参数与处理器方法形参间的关系，要求路径参数名与形参名一一对应
        LambdaQueryWrapper<Grade> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Grade::getSon,son);
        int delete = gradeDao.delete(lqw);
        boolean isFlag=true;
        if (delete==0){
            isFlag=false;
        }
        return isFlag;
    }
}
