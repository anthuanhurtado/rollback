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

import views.ViewClientes;
import model.ModelClientes;

public class ControllerClientes implements ActionListener {

    public ViewClientes viewClientes;
    public ModelClientes modelClientes;

    DefaultTableModel model;

    private Statement st;
    private ResultSet rs;

    Conection conection = new Conection();
    Connection cn = conection.conexion();

    public ControllerClientes(ViewClientes viewClientes, ModelClientes modelClientes) {

        this.viewClientes = viewClientes;
        this.modelClientes = modelClientes;

        this.viewClientes.jtf_id.setVisible(false);

        this.viewClientes.jbtn_agregar.addActionListener(this);
        this.viewClientes.jbtn_borrar.addActionListener(this);
        this.viewClientes.jbtn_editar.addActionListener(this);
        this.viewClientes.jbtn_buscar.addActionListener(this);
        this.viewClientes.jbtn_mostar_todo.addActionListener(this);
        this.viewClientes.jbtn_actualizar.addActionListener(this);
    }

    void editar() {
        try {
            int filaseleccionada;
            filaseleccionada = viewClientes.jt_clientes.getSelectedRow();

            if (filaseleccionada >= 0) {

                DefaultTableModel modelotabla = (DefaultTableModel) viewClientes.jt_clientes.getModel();

                String id = (String) modelotabla.getValueAt(filaseleccionada, 0);
                String nombre = (String) modelotabla.getValueAt(filaseleccionada, 1);
                String ap_Paterno = (String) modelotabla.getValueAt(filaseleccionada, 2);
                String ap_Materno = (String) modelotabla.getValueAt(filaseleccionada, 3);
                String telefono = (String) modelotabla.getValueAt(filaseleccionada, 4);
                String email = (String) modelotabla.getValueAt(filaseleccionada, 5);
                String rfc = (String) modelotabla.getValueAt(filaseleccionada, 6);
                String calle = (String) modelotabla.getValueAt(filaseleccionada, 7);
                String numero = (String) modelotabla.getValueAt(filaseleccionada, 8);
                String colonia = (String) modelotabla.getValueAt(filaseleccionada, 9);
                String ciudad = (String) modelotabla.getValueAt(filaseleccionada, 10);
                String estado = (String) modelotabla.getValueAt(filaseleccionada, 11);

                viewClientes.jtf_id.setText(id);
                viewClientes.jtf_nombre.setText(nombre);
                viewClientes.jtf_paterno.setText(ap_Paterno);
                viewClientes.jtf_materno.setText(ap_Materno);
                viewClientes.jtf_telefono.setText(telefono);
                viewClientes.jtf_email.setText(email);
                viewClientes.jtf_rfc.setText(rfc);
                viewClientes.jtf_calle.setText(calle);
                viewClientes.jtf_numero.setText(numero);
                viewClientes.jtf_colonia.setText(colonia);
                viewClientes.jtf_ciudad.setText(ciudad);
                viewClientes.jtf_estado.setText(estado);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Por favor llena todos los campos");
        }
    }

    void Limpiar() {
        viewClientes.jtf_nombre.setText("");
        viewClientes.jtf_paterno.setText("");
        viewClientes.jtf_materno.setText("");
        viewClientes.jtf_telefono.setText("");
        viewClientes.jtf_email.setText("");
        viewClientes.jtf_rfc.setText("");
        viewClientes.jtf_calle.setText("");
        viewClientes.jtf_numero.setText("");
        viewClientes.jtf_colonia.setText("");
        viewClientes.jtf_ciudad.setText("");
        viewClientes.jtf_estado.setText("");
    }

    void Actualizar() {

        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE clientes SET Nombre = '" + viewClientes.jtf_nombre.getText() + "', Ap_Paterno= '" + viewClientes.jtf_paterno.getText() + "', Ap_Materno = '" + viewClientes.jtf_materno.getText() + "', Telefono = '" + viewClientes.jtf_telefono.getText() + "', Email = '" + viewClientes.jtf_email.getText() + "', RFC = '" + viewClientes.jtf_rfc.getText() + "', Calle = '" + viewClientes.jtf_calle.getText() + "', Numero = '" + viewClientes.jtf_numero.getText() + "', Colonia = '" + viewClientes.jtf_colonia.getText() + "', Ciudad = '" + viewClientes.jtf_ciudad.getText() + "', Estado = '" + viewClientes.jtf_estado.getText() + "' WHERE Id_Cliente= '" + viewClientes.jtf_id.getText() + "'");
            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    void guardar() {

        try {
            PreparedStatement ps = cn.prepareStatement("INSERT INTO clientes (Nombre,Ap_Paterno,Ap_Materno,Telefono,Email,RFC,Calle,Numero,Colonia,Ciudad,Estado) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, viewClientes.jtf_nombre.getText());
            ps.setString(2, viewClientes.jtf_paterno.getText());
            ps.setString(3, viewClientes.jtf_materno.getText());
            ps.setString(4, viewClientes.jtf_telefono.getText());
            ps.setString(5, viewClientes.jtf_email.getText());
            ps.setString(6, viewClientes.jtf_rfc.getText());
            ps.setString(7, viewClientes.jtf_calle.getText());
            ps.setString(8, viewClientes.jtf_numero.getText());
            ps.setString(9, viewClientes.jtf_colonia.getText());
            ps.setString(10, viewClientes.jtf_ciudad.getText());
            ps.setString(11, viewClientes.jtf_estado.getText());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar ");
            System.out.print(e.getMessage());
        }
    }

    void modificar() {

        try {
            String sql = "UPDATE clientes nombre=?, ap_paterno=?, ap_materno=?, telefono=?, email=?, rfc=?, calle=?, numero=?, colonia=?, ciudad=?, estado=?" + "where id_cliente=?";
            int fila = viewClientes.jt_clientes.getSelectedRow();
            String dao = (String) viewClientes.jt_clientes.getValueAt(fila, 0);
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, viewClientes.jtf_nombre.getText());
            ps.setString(2, viewClientes.jtf_paterno.getText());
            ps.setString(3, viewClientes.jtf_materno.getText());
            ps.setString(4, viewClientes.jtf_telefono.getText());
            ps.setString(5, viewClientes.jtf_email.getText());
            ps.setString(6, viewClientes.jtf_rfc.getText());
            ps.setString(7, viewClientes.jtf_calle.getText());
            ps.setString(8, viewClientes.jtf_numero.getText());
            ps.setString(9, viewClientes.jtf_colonia.getText());
            ps.setString(10, viewClientes.jtf_ciudad.getText());
            ps.setString(11, viewClientes.jtf_estado.getText());

            ps.setString(1, dao);
            int n = ps.executeUpdate();

            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Datos modificados");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        guardar();
        Limpiar();
    }

    void eliminar() {
        try {
            int fila = viewClientes.jt_clientes.getSelectedRow();
            if (fila >= 0) {

                DefaultTableModel modelotabla = (DefaultTableModel) viewClientes.jt_clientes.getModel();

                String id = (String) modelotabla.getValueAt(fila, 0);
                String sql = ("DELETE FROM clientes WHERE Id_Cliente='" + id + "'");
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
        modelo.addColumn("Ap Paterno");
        modelo.addColumn("Ap Materno");
        modelo.addColumn("Telefono");
        modelo.addColumn("Email");
        modelo.addColumn("RFC");
        modelo.addColumn("Calle");
        modelo.addColumn("No");
        modelo.addColumn("Colonia");
        modelo.addColumn("Ciudad");
        modelo.addColumn("Estado");

        viewClientes.jt_clientes.setModel(modelo);
        String sql = "";

        if (valor.equals("")) {
            sql = "SELECT  * FROM clientes";
        } else {

            sql = "SELECT  * FROM clientes WHERE Id_Cliente='" + valor + "'";
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
                datos[11] = rs.getString(12);

                modelo.addRow(datos);
            }
            viewClientes.jt_clientes.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == viewClientes.jbtn_agregar) {

            if (viewClientes.jtf_nombre.getText().equals("") || viewClientes.jtf_paterno.getText().equals("") || viewClientes.jtf_materno.getText().equals("") || viewClientes.jtf_telefono.getText().equals("") || viewClientes.jtf_email.getText().equals("") || viewClientes.jtf_rfc.getText().equals("") || viewClientes.jtf_calle.getText().equals("") || viewClientes.jtf_numero.getText().equals("") || viewClientes.jtf_colonia.getText().equals("") || viewClientes.jtf_ciudad.getText().equals("") || viewClientes.jtf_estado.getText().equals("")) {
                JOptionPane.showMessageDialog(null, " No has llenado todos los campos");
            } else {
                guardar();
                mostrardatos("");
                Limpiar();
                JOptionPane.showMessageDialog(null, "Datos agregados correctamente");
            }

        }

        if (e.getSource() == viewClientes.jbtn_borrar) {
            eliminar();
            JOptionPane.showMessageDialog(null, "Datos borrados correctamente");
            System.out.print("e");
        }
        if (e.getSource() == viewClientes.jbtn_actualizar) {
            if (viewClientes.jtf_nombre.getText().equals("") || viewClientes.jtf_paterno.getText().equals("") || viewClientes.jtf_materno.getText().equals("") || viewClientes.jtf_telefono.getText().equals("") || viewClientes.jtf_email.getText().equals("") || viewClientes.jtf_rfc.getText().equals("") || viewClientes.jtf_calle.getText().equals("") || viewClientes.jtf_numero.getText().equals("") || viewClientes.jtf_colonia.getText().equals("") || viewClientes.jtf_ciudad.getText().equals("") || viewClientes.jtf_estado.getText().equals("")) {
                JOptionPane.showMessageDialog(null, " No has llenado todos los campos");
            } else {
                Actualizar();
                mostrardatos("");
                Limpiar();
                JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
            }
        }

        if (e.getSource() == viewClientes.jbtn_editar) {
            editar();
        }
        if (e.getSource() == viewClientes.jbtn_buscar) {
            String id = String.format(JOptionPane.showInputDialog(viewClientes, "Ingresa el id que deseas buscar"));
            mostrardatos(id);
        }
        if (e.getSource() == viewClientes.jbtn_mostar_todo) {
            mostrardatos("");
        }
    }
}
