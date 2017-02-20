package edu.kosta.network;

import java.io.Serializable;

/**
 * Created by sejun on 17. 2. 20.
 */
public class Person implements Serializable{

    /*
    * 이 클래스와 상속관계에 있는 클래스들은 모두 Serializable 을 구현해야 한다.
    * transient 를 해당 변수 앞에 선언해 주면 해당 필드는 serialize에서 제외된다.
    * transient private String name;
    * */

    private String name;
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
