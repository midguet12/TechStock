package com.example.techstock.logic.Log;

import java.io.FileWriter;
import java.io.IOException;

public class LogWriting {

    public static void writeLog(String exceptionMessage){
        try {
            FileWriter fileWriter = new FileWriter("com/example/techstock/logic/Log/log.txt", true);
            fileWriter.write(exceptionMessage);
            fileWriter.write("\n");
            fileWriter.close();

            System.out.println("Mensaje escrito en bitacora");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
