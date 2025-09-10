package app;

import controllers.*;
import models.*;
import org.apache.logging.log4j.Logger;
import views.*;

public class    App {
    public static void main(String[] args) throws Exception {
        Log.initLogger();
        VistaEvento vista = new VistaEvento();
        AccionEvento modelo = new AccionEvento();
        ObtenerReporte modeloRep = new ObtenerReporte();
        GestionarUsuario gestionarUsuario = new GestionarUsuario();
        ControladorEvento controlador = new ControladorEvento(vista, modelo,modeloRep,gestionarUsuario);
        controlador.iniciar();
    }
}
