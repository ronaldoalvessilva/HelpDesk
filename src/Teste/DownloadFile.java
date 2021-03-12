/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import static Teste.DownloadFileJava.gravaArquivoDeURL;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author ronaldo.silva7
 */
public class DownloadFile {

    public static void main(String[] args) throws IOException {
            
        URL url = new URL("C:\\Users\\ronaldo.silva7\\Documents\\NetBeansProjects\\HelpDesk\\dist\\HelpDesk.jar");
        File file = new File("C:\\Users\\ronaldo.silva7\\Downloads\\DownloadJava\\HelpDesk.jar");

        InputStream is = url.openStream();
        FileOutputStream fos = new FileOutputStream(file);

        int bytes = 0;

        while ((bytes = is.read()) != -1) {
            fos.write(bytes);
        }
        is.close();

        fos.close();
    }
}
