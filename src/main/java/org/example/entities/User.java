package org.example.entities;

/**
 * @ClassName User
 * @Author niejun
 * @Date 2022/6/7
 * @Description:
 * @Version 1.0
 **/
public class User {

    private Integer id;

    private String name;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static class UserBuilder {
        private User user;

        public UserBuilder() {
            this.user = new User();
        }

        public UserBuilder id(Integer id) {
            user.setId(id);
            return this;
        }

        public UserBuilder age(Integer age) {
            user.setAge(age);
            return this;
        }

        public UserBuilder name(String name) {
            user.setName(name);
            return this;
        }

        public User build() {
            return user;
        }
    }
}
