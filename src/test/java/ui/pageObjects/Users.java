package ui.pageObjects;

import lombok.Data;

@Data
public class Users {
    private String allTables = "//th";
    private String registrationDateColumn = "//a[contains(text(),'Registration date')]";
    private String allDataInRegistrationColumn = "//td[contains(text(),'2019')]";
}
