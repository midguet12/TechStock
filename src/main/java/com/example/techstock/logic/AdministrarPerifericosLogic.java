package com.example.techstock.logic;

import com.example.techstock.dao.CentroComputoDAO;
import com.example.techstock.dao.PerifericoDAO;
import com.example.techstock.domain.CentroComputo;
import com.example.techstock.domain.Periferico;

import java.sql.SQLException;
import java.util.List;

public class AdministrarPerifericosLogic {
    private PerifericoDAO perifericoDAO;
    private CentroComputoDAO centroComputoDAO;

    public AdministrarPerifericosLogic() {
        this.perifericoDAO = new PerifericoDAO();
        this.centroComputoDAO = new CentroComputoDAO();
    }

    public boolean agregarPeriferico(Periferico periferico) {
        try {
            return perifericoDAO.create(periferico);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CentroComputo> obtenerCentrosComputo() {
        try {
            return centroComputoDAO.readAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
