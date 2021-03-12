/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import static Teste.DownloadFileJava.gravaArquivoDeURL;

/**
 *
 * @author ronaldo.silva7
 */
public class DownloadFileNovo {

    public static void main(String[] args) {
        
        String pCAMINHO_origem = "/Users/ronaldo.silva7/Documents/NetBeansProjects/HelpDesk/dist/HelpDesk.jar";
        String pCAMINHO_destino = "/Users/ronaldo.silva7/Downloads/DownloadJava/HelpDesk.jar";
        gravaArquivoDeURL(pCAMINHO_origem, pCAMINHO_destino);
    }
}
