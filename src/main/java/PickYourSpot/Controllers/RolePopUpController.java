package PickYourSpot.Controllers;

import javafx.event.ActionEvent;

public class RolePopUpController {

    public void staffButtonClicked(){
        RegistrationController.setWhich("Staff");
        RegistrationController.getWindow().close();
    }
    public void adminButtonClicked(){
        RegistrationController.setWhich("Admin");
        RegistrationController.getWindow().close();
    }

}
