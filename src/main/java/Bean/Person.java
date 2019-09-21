package Bean;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * ClassName Person
 *
 * @Auther: 赵繁旗
 * @Date: 2019/6/24 21:52
 * @Description:
 */
public class Person {
    private Integer id ;
    private String personName;

    public Person() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Person(String personName) {
        this.personName = personName;
    }
}
