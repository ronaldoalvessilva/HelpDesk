/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.ChamadoSuporte;
import static Visao.TelaChamadoSuporte.jIdChamado;
import static Visao.TelaEnvioChamadoSuporteDesenvolvimento.qtdChamados;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronal
 */
public class ListaChamadosSuporteDao {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    ChamadoSuporte objCHSup = new ChamadoSuporte();

    public List<ChamadoSuporte> read() throws Exception {
        String pUtili = "Não";
        conecta.abrirConexao();
        List<ChamadoSuporte> listaInternosNaoSelec = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT * FROM ITENS_CHAMADOS_SUPORTE "
                    + "INNER JOIN CHAMADOS_SUPORTE "
                    + "ON ITENS_CHAMADOS_SUPORTE.IdCHSup=CHAMADOS_SUPORTE.IdCHSup "
                    + "WHERE ITENS_CHAMADOS_SUPORTE.IdCHSup='" + jIdChamado.getText() + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pLista = new ChamadoSuporte();
                pLista.setIdItemCh(conecta.rs.getInt("IdItem"));
                pLista.setDataItemCh(conecta.rs.getDate("DataItemCh"));
                pLista.setHorarioInicio(conecta.rs.getString("HorarioInicio"));
                pLista.setHorarioTermino(conecta.rs.getString("HorarioTermino"));
                pLista.setTextoSuporte(conecta.rs.getString("TextoSuporte"));
                listaInternosNaoSelec.add(pLista);
                qtdChamados = qtdChamados + 1;
            }
            return listaInternosNaoSelec;
        } catch (SQLException ex) {
            Logger.getLogger(ListaChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }
}
