package service;

import model.User;

import static util.Resolver.resolveTemplate;

public class UserCreator {
    public static final String USER_NAME = "test.data.user.name";
    public static final String USER_EMAIL = "test.data.user.email";
    public static final String USER_SEX = "test.data.user.sex";
    public static final String USER_ID = "test.data.user.id";
    public static final String USER_PASSWORD = "test.data.user.password";


    public static User withCredentialsFromProperty(){

        String name = TestDataReader.getTestData(USER_NAME);
        String sex = TestDataReader.getTestData(USER_SEX);
        String email = TestDataReader.getTestData(USER_EMAIL);
        String id = TestDataReader.getTestData(USER_ID);
        String password = TestDataReader.getTestData(USER_PASSWORD);


        return User.of(name, sex, email,id, password);
    }
}
