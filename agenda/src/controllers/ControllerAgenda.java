package controllers;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import lib.Conection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import views.*;
import models.*;

public class ControllerAgenda implements ActionListener {

    public ViewAgenda viewAgenda;
    public ModelAgenda modelAgenda;

    private Statement st;
    private ResultSet rs;

    Conection conection = new Conection();
    Connection cn = conection.conexion();

    public ControllerAgenda(ViewAgenda viewAgenda, ModelAgenda modelAgenda) {

        this.viewAgenda = viewAgenda;
        this.modelAgenda = modelAgenda;
        this.viewAgenda.setVisible(true);
        
        this.viewAgenda.jtf_id.setVisible(false);

        this.viewAgenda.jbtn_primero.addActionListener(this);
        this.viewAgenda.jbtn_siguiente.addActionListener(this);
        this.viewAgenda.jbtn_anterior.addActionListener(this);
        this.viewAgenda.jbtn_ultimo.addActionListener(this);

        this.viewAgenda.jbtn_agregar.addActionListener(this);
        this.viewAgenda.jbtn_editar.addActionListener(this);
        this.viewAgenda.jbtn_eliminar.addActionListener(this);
        this.viewAgenda.jbtn_cancelar.addActionListener(this);
        this.viewAgenda.jbtn_terminar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == viewAgenda.jbtn_ultimo) {

        }

        if (e.getSource() == viewAgenda.jbtn_agregar) {
            agregar();

        } else if (e.getSource() == viewAgenda.jbtn_terminar) {
            terminar();
        }

        if (e.getSource() == viewAgenda.jbtn_cancelar) {
            cancelar();
        } else if (e.getSource() == viewAgenda.jbtn_primero) {
            first();
        } else if (e.getSource() == viewAgenda.jbtn_siguiente) {
            next();
        } else if (e.getSource() == viewAgenda.jbtn_anterior) {
            previous();
        } else if (e.getSource() == viewAgenda.jbtn_ultimo) {
            last();
        }

    }

    void agregar() {

        try {

            PreparedStatement ps = cn.prepareStatement("INSERT INTO contactos (nombre, telefono) VALUES (?,?)");
            cn.setAutoCommit(false);
            ps.setString(1, viewAgenda.jtf_nombre.getText());
            ps.setString(2, viewAgenda.jtf_telefono.getText());
            ps.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
        }
    }

    void cancelar() {

        try {
            Savepoint s = cn.setSavepoint();
            cn.rollback();
            cn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ControllerAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void first() {
        try {
            if (rs.isLast() == true) {
                rs.first();
                this.viewAgenda.jtf_nombre.setText(rs.getString("Nobre"));
                this.viewAgenda.jtf_telefono.setText(rs.getString("Telefono"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void terminar() {
        try {
            cn.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void inicio() {

        Statement st;
        try {
            st = conection.createStatement();
            rs = st.executeQuery("Select * from contactos");
            rs.next();
            this.viewAgenda.jtf_nombre.setText(rs.getString("Nombre"));
            this.viewAgenda.jtf_telefono.setText(rs.getString("Telefono"));
        } catch (Exception e) {
            Logger.getLogger(ControllerAgenda.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void previous() {
        try {
            if (rs.isFirst() == false) {
                rs.previous();
                this.viewAgenda.jtf_nombre.setText(rs.getString("Nobre"));
                this.viewAgenda.jtf_telefono.setText(rs.getString("Telefono"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void next() {
        try {
            if (rs.isLast() == false) {
                rs.next();
                this.viewAgenda.jtf_nombre.setText(rs.getString("Nobre"));
                this.viewAgenda.jtf_telefono.setText(rs.getString("Telefono"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void last() {
        try {
            if (rs.isLast() == true) {
                rs.last();
                viewAgenda.jtf_nombre.setText(rs.getString("nombre"));
                viewAgenda.jtf_telefono.setText(rs.getString("telefono"));
            }
        } catch (Exception e) {
            Logger.getLogger(ControllerAgenda.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
