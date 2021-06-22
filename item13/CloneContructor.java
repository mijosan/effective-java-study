package item13;

import java.util.ArrayList;
import java.util.List;

public class CloneContructor {

    public class Teacher{
        String name;
        int age;
        List<Student> studentList;
    
        public Teacher(){
        }

        public Teacher(Teacher src){
            this.name = new String(src.name);
            this.age = src.age;
            this.studentList = new ArrayList<>();
            for (int i = 0; i < src.studentList.size(); i++) {
                this.studentList.add(new Student(src.studentList.get(i)));
            }
        }
    }
    
    public class Student{
        String name;
        int age;
    
        public Student(){
        }
    
        public Student(Student src){
            this.name = new String(src.name);
            this.age = src.age;
        }
    }

}
