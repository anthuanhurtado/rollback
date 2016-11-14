package controllers;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import lib.Conection;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import views.ViewProveedores;
import model.ModelProveedores;

public class ControllerProveedores implements ActionListener {

    public ViewProveedores viewProveedores;
    public ModelProveedores modelProveedores;

    DefaultTableModel model;

    private Statement st;
    private ResultSet rs;

    Conection conection = new Conection();
    Connection cn = conection.conexion();

    public ControllerProveedores(ViewProveedores viewProveedores, ModelProveedores modelProveedores) {

        this.viewProveedores = viewProveedores;
        this.modelProveedores = modelProveedores;

        this.viewProveedores.jtf_id.setVisible(false);
        this.viewProveedores.jbtn_agregar.addActionListener(this);
        this.viewProveedores.jbtn_borrar.addActionListener(this);
        this.viewProveedores.jbtn_editar.addActionListener(this);
        this.viewProveedores.jbtn_buscar.addActionListener(this);
        this.viewProveedores.jbtn_mostrar.addActionListener(this);
    }

    void editar() {
        try {
            int filaseleccionada;
            filaseleccionada = viewProveedores.jt_proveedores.getSelectedRow();

            if (filaseleccionada >= 0) {

                DefaultTableModel modelotabla = (DefaultTableModel) viewProveedores.jt_proveedores.getModel();

                String id = (String) modelotabla.getValueAt(filaseleccionada, 0);
                String nombre = (String) modelotabla.getValueAt(filaseleccionada, 1);
                String rfc = (String) modelotabla.getValueAt(filaseleccionada, 2);
                String calle = (String) modelotabla.getValueAt(filaseleccionada, 3);
                String numero = (String) modelotabla.getValueAt(filaseleccionada, 4);
                String colonia = (String) modelotabla.getValueAt(filaseleccionada, 5);
                String ciudad = (String) modelotabla.getValueAt(filaseleccionada, 6);
                String estado = (String) modelotabla.getValueAt(filaseleccionada, 7);
                String contacto = (String) modelotabla.getValueAt(filaseleccionada, 8);
                String telefono = (String) modelotabla.getValueAt(filaseleccionada, 9);
                String email = (String) modelotabla.getValueAt(filaseleccionada, 10);

                viewProveedores.jtf_id.setText(id);
                viewProveedores.jtf_nombre.setText(nombre);
                viewProveedores.jtf_rfc.setText(rfc);
                viewProveedores.jtf_calle.setText(calle);
                viewProveedores.jtf_numero.setText(numero);
                viewProveedores.jtf_colonia.setText(colonia);
                viewProveedores.jtf_ciudad.setText(ciudad);
                viewProveedores.jtf_estado.setText(estado);
                viewProveedores.jtf_contacto.setText(contacto);
                viewProveedores.jtf_telefono.setText(telefono);
                viewProveedores.jtf_email.setText(email);
            }

        } catch (Exception ex) {
        }
    }

    void limpiar() {
        viewProveedores.jtf_nombre.setText("");
        viewProveedores.jtf_rfc.setText("");
        viewProveedores.jtf_calle.setText("");
        viewProveedores.jtf_numero.setText("");
        viewProveedores.jtf_colonia.setText("");
        viewProveedores.jtf_ciudad.setText("");
        viewProveedores.jtf_estado.setText("");
        viewProveedores.jtf_contacto.setText("");
        viewProveedores.jtf_telefono.setText("");
        viewProveedores.jtf_email.setText("");
    }

    void actualizar() {

        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE provedores SET Nombre = '" + viewProveedores.jtf_nombre.getText() + "', rfc= '" + viewProveedores.jtf_rfc.getText() + "', calle = '" + viewProveedores.jtf_calle.getText() + "', numero = '" + viewProveedores.jtf_numero.getText() + "', colonia = '" + viewProveedores.jtf_colonia.getText() + "', ciudad= '" + viewProveedores.jtf_ciudad.getText() + "', estado = '" + viewProveedores.jtf_estado.getText() + "', contacto = '" + viewProveedores.jtf_contacto.getText() + "', telefono = '" + viewProveedores.jtf_telefono.getText() + "', email= '" + viewProveedores.jtf_email.getText() + "' WHERE id_provedores= '" + viewProveedores.jtf_id.getText() + "'");
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    void guardar() {

        try {
            PreparedStatement ps = cn.prepareStatement("INSERT INTO provedores (Nombre,rfc,calle,numero,colonia,ciudad,estado,contacto,telefono,email) VALUES (?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, viewProveedores.jtf_nombre.getText());
            ps.setString(2, viewProveedores.jtf_rfc.getText());
            ps.setString(3, viewProveedores.jtf_calle.getText());
            ps.setString(4, viewProveedores.jtf_numero.getText());
            ps.setString(5, viewProveedores.jtf_colonia.getText());
            ps.setString(6, viewProveedores.jtf_ciudad.getText());
            ps.setString(7, viewProveedores.jtf_estado.getText());
            ps.setString(8, viewProveedores.jtf_contacto.getText());
            ps.setString(9, viewProveedores.jtf_telefono.getText());
            ps.setString(10, viewProveedores.jtf_email.getText());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar ");
            System.out.print(e.getMessage());
        }
    }

    void modificar() {

        try {
            String sql = "UPDATE provedores nombre=?, rfc=?, calle=?, numero=?,colonia=?, ciudad=?, estado=?, contacto=?, telefono=?, email=?" + "where id_provedores=?";
            int fila = viewProveedores.jt_proveedores.getSelectedRow();
            String dao = (String) viewProveedores.jt_proveedores.getValueAt(fila, 0);
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, viewProveedores.jtf_nombre.getText());
            ps.setString(2, viewProveedores.jtf_rfc.getText());
            ps.setString(3, viewProveedores.jtf_calle.getText());
            ps.setString(4, viewProveedores.jtf_numero.getText());
            ps.setString(5, viewProveedores.jtf_colonia.getText());
            ps.setString(6, viewProveedores.jtf_ciudad.getText());
            ps.setString(7, viewProveedores.jtf_estado.getText());
            ps.setString(8, viewProveedores.jtf_contacto.getText());
            ps.setString(9, viewProveedores.jtf_telefono.getText());
            ps.setString(10, viewProveedores.jtf_email.getText());

            ps.setString(1, dao);
            int n = ps.executeUpdate();

            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Datos modificados");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        guardar();
        limpiar();
    }

    void eliminar() {
        try {
            int fila = viewProveedores.jt_proveedores.getSelectedRow();
            if (fila >= 0) {

                DefaultTableModel modelotabla = (DefaultTableModel) viewProveedores.jt_proveedores.getModel();

                String id = (String) modelotabla.getValueAt(fila, 0);
                String sql = ("DELETE FROM provedores WHERE id_Provedores='" + id + "'");
                st = cn.createStatement();
                st.executeUpdate(sql);
                mostrardatos("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }

    void mostrardatos(String valor) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Rfc");
        modelo.addColumn("Calle");
        modelo.addColumn("Numero");
        modelo.addColumn("Colonia");
        modelo.addColumn("Ciudad");
        modelo.addColumn("Estado");
        modelo.addColumn("Contacto");
        modelo.addColumn("telefono");
        modelo.addColumn("Email");

        viewProveedores.jt_proveedores.setModel(modelo);
        String sql = "";

        if (valor.equals("")) {
            sql = "SELECT  * FROM provedores";
        } else {

            sql = "SELECT  * FROM provedores WHERE Id_provedores='" + valor + "'";
        }
        String[] datos = new String[12];

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);

                modelo.addRow(datos);
            }
            viewProveedores.jt_proveedores.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewProveedores.jbtn_agregar) {

            if (viewProveedores.jtf_nombre.getText().equals("") || viewProveedores.jtf_rfc.getText().equals("") || viewProveedores.jtf_calle.getText().equals("") || viewProveedores.jtf_numero.getText().equals("") || viewProveedores.jtf_contacto.getText().equals("") || viewProveedores.jtf_colonia.getText().equals("") || viewProveedores.jtf_ciudad.getText().equals("") || viewProveedores.jtf_estado.getText().equals("") || viewProveedores.jtf_telefono.getText().equals("") || viewProveedores.jtf_email.getText().equals("")) {
                JOptionPane.showMessageDialog(null, " No has llenado todos los campos");
            } else {
                guardar();
                mostrardatos("");
                limpiar();
            }
        }

        if (e.getSource() == viewProveedores.jbtn_borrar) {
            eliminar();
            limpiar();
            JOptionPane.showMessageDialog(null, "Datos borrados correctamente");
        }
        if (e.getSource() == viewProveedores.jbtn_actualizar) {
            
            if (viewProveedores.jtf_nombre.getText().equals("") || viewProveedores.jtf_rfc.getText().equals("") || viewProveedores.jtf_calle.getText().equals("") || viewProveedores.jtf_numero.getText().equals("") || viewProveedores.jtf_contacto.getText().equals("") || viewProveedores.jtf_colonia.getText().equals("") || viewProveedores.jtf_ciudad.getText().equals("") || viewProveedores.jtf_estado.getText().equals("") || viewProveedores.jtf_telefono.getText().equals("") || viewProveedores.jtf_email.getText().equals("")) {
                JOptionPane.showMessageDialog(null, " No has llenado todos los campos");
            } else {
            actualizar();
            mostrardatos("");
            limpiar();
                JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
            }
        }
            

        if (e.getSource() == viewProveedores.jbtn_editar) {
            
            editar();
        }
        if (e.getSource() == viewProveedores.jbtn_buscar) {
            String id = String.format(JOptionPane.showInputDialog(viewProveedores, "Ingresa el id que deseas buscar"));
            mostrardatos(id);
        }
        if (e.getSource() == viewProveedores.jbtn_mostrar) {
            mostrardatos("");
        }
    }
}