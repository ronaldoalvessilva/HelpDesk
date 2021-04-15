/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.SQL.Utilitarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author ronal
 */
public class CompararHoras {

    public final static SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
    public final static String closeTime = "19:00";

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("What time is it? ");
        String presentTime = reader.next();
        boolean answer = checkIfClosed(presentTime);
        String textRepresentation = null;
        if (answer == true) {
            textRepresentation = "Nope! We're closed!";
        } else {
            textRepresentation = "It's open!";
        }
        System.out.println(textRepresentation);
        reader.close();
    }

    public static boolean checkIfClosed(String time) {
        try {
            Date present = parser.parse(time);
            Date closed = parser.parse(closeTime);
            if (present.after(closed)) {
                return true;
            }
        } catch (ParseException e) {
            // Invalid date was entered
        }
        return false;
    }
}
