package models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.appender.ConsoleAppender;

public class Log {

    public static void initLogger() {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration config = context.getConfiguration();

        // Limpiar todos los appenders existentes
        config.getRootLogger().getAppenders().keySet()
                .forEach(name -> config.getRootLogger().removeAppender(name));

        // Forzar nivel del root logger a DEBUG
        config.getRootLogger().setLevel(Level.DEBUG);

        // Crear layout con patrón
        PatternLayout layout = PatternLayout.newBuilder()
                .withPattern("%d{dd/MM/yyyy HH:mm:ss} %-5level: %msg%n")
                .build();

        // Crear console appender
        ConsoleAppender consoleAppender = ConsoleAppender.newBuilder()
                .setName("Console")
                .setLayout(layout)
                .setTarget(ConsoleAppender.Target.SYSTEM_OUT)
                .build();
        consoleAppender.start();

        // Asignar appender al root logger
        config.getRootLogger().addAppender(consoleAppender, null, null);
        context.updateLoggers();
    }

    // Devuelve un logger para cualquier clase
    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }
}
