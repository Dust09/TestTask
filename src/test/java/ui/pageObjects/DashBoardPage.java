package ui.pageObjects;

import lombok.Data;
@Data
public class DashBoardPage {
    private String playersOnlineOrTotal = "//p[contains(text(),'Players online')]";
}
