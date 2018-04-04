package test;

import java.util.HashMap;
import java.util.HashSet;

public class Student {

	public Student(int id,String name){
		this.id = id ;
		this.name = name ;
	}
	
	public int id = 0 ;
	public String name = "" ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return id ;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true ;
		if(obj instanceof Student){
			Student stu = (Student) obj ;
			return this.id == stu.id && this.name.equals(stu.name) ;
			//return this.id == stu.id ;
		}
		return false ;
	}
	
	@Override
	public String toString() {
		return "ID = " + id + " & NAME = " + name;
	}
	
	public static void main(String[] args) {
		Student stu1 = new Student(1,"张三") ;
		Student stu2 = new Student(1,"李四") ;
		System.out.println(stu1.hashCode());
		System.out.println(stu2.hashCode());
		System.out.println(stu2.equals(stu1));
		HashSet<Student> hashSet = new HashSet<Student>() ;
		hashSet.add(stu1) ;
		hashSet.add(stu2) ;
		stu1.setId(2);
		hashSet.remove(stu1) ;
		System.out.println(hashSet.contains(stu1)) ;
		System.out.println(hashSet.size());
		for(Student stu : hashSet)
			System.out.println(stu.toString());
		HashMap<Student, Student> map = new HashMap<Student, Student>() ;
		map.put(stu1, stu1) ;
		map.put(stu2, stu2) ;
		System.out.println(map);
	}
}
