package app;

import controllers.*;
import models.*;
import views.*;

public class App {
    public static void main(String[] args) throws Exception {
        VistaEvento vista = new VistaEvento();
        AccionEvento modelo = new AccionEvento();
        ControladorEvento controlador = new ControladorEvento(vista, modelo);
        controlador.iniciar();
    }
}
