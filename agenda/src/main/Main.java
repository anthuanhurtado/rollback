package main;

import models.*;
import views.*;
import controllers.*;

public class Main {

    public static void main(String[] amh) {

        ModelAgenda modelAgenda = new ModelAgenda();
        ViewAgenda viewAgenda = new ViewAgenda();
        ControllerAgenda controllerAgenda = new ControllerAgenda(viewAgenda, modelAgenda);
    }
}