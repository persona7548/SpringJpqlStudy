package com.taehyun.springdata.jpqlandnativesql;

import com.taehyun.springdata.jpqlandnativesql.entities.Student;
import com.taehyun.springdata.jpqlandnativesql.repos.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class JpqlandnativesqlApplicationTests {

    @Autowired
    StudentRepository repository;

    @Test
    public void testStudentCreate() {
        Student student= new Student();
        student.setFirstName("John");
        student.setLastName("Ferguson");
        student.setScore(88);

        Student student2= new Student();
        student2.setFirstName("Bill");
        student2.setLastName("Gates");
        student2.setScore(75);
        repository.save(student);
        repository.save(student2);
    }

    @Test
    public void testFindAllStudents(){
        System.out.println(repository.findAllStudents());
    }

    @Test
    public void testFindAllStudentsPartial(){
        //System.out.println(repository.findAllStudentsPartialData());
        List<Object[]> partialData = repository.findAllStudentsPartialData();
        for(Object[] objects : partialData){
            System.out.println(objects[0]);
            System.out.println(objects[1]);
        }
    }

    @Test
    public void testFindAllStudentsByFirstName(){
        System.out.println(repository.findAllStudentByFirstName("John"));
    }

    @Test
    public void testFindAllStudentsByScore(){
        System.out.println(repository.findStudentForGivenScores(70,80));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testDeleteStudentsByFirstName(){
        repository.deleteStudentsByFirstName("Bill");
    }

}
