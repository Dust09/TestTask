package ui.pageObjects;

import lombok.Data;
@Data
public class LoginPage {
    private String loginField = "//input[@id='UserLogin_username']";
    private String passwordField = "//input[@id='UserLogin_password']";
    private String signInButton = "//input[@value='Sign in']";
}
