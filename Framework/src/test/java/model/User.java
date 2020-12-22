package model;

import lombok.Getter;
import lombok.Setter;
import sun.java2d.pipe.SpanShapeRenderer;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
@Setter
public class User {
    private String name;
    private String sex;
    private String email;
    private String id;
    private String password;

    private User(String name, String sex, String email, String id, String password) {

        checkNotNull(name);
        checkNotNull(sex);
        checkNotNull(email);
        checkNotNull(id);
        checkNotNull(password);

        this.name = name;
        this.sex = sex;
        this.email = email;
        this.id = id;
        this.password=password;
    }

    public static User of(String name,String sex,String email, String id, String password){
        return new User(name,sex,email,id, password);
    }
}
