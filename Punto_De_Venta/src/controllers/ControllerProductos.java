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

import views.*;
import model.*;

public class ControllerProductos implements ActionListener {

    public ViewProductos viewProductos;
    public ModelProductos modelProductos;

    DefaultTableModel model;

    private Statement st;
    private ResultSet rs;

    Conection conection = new Conection();
    Connection cn = conection.conexion();

    public ControllerProductos(ViewProductos viewProductos, ModelProductos modelProductos) {

        this.viewProductos = viewProductos;
        this.modelProductos = modelProductos;

        this.viewProductos.jtf_id.setVisible(false);

        this.viewProductos.jbtn_agregar.addActionListener(this);
        this.viewProductos.jbtn_borrar.addActionListener(this);
        this.viewProductos.jbtn_editar.addActionListener(this);
        this.viewProductos.jbtn_buscar.addActionListener(this);
        this.viewProductos.jbtn_mostrar.addActionListener(this);
        this.viewProductos.jbtn_actualizar.addActionListener(this);
    }

    void editar() {
        try {
            int filaseleccionada;
            filaseleccionada = viewProductos.jt_productos.getSelectedRow();

            if (filaseleccionada >= 0) {

                DefaultTableModel modelotabla = (DefaultTableModel) viewProductos.jt_productos.getModel();

                String id = (String) modelotabla.getValueAt(filaseleccionada, 0);
                String producto = (String) modelotabla.getValueAt(filaseleccionada, 1);
                String descripcion = (String) modelotabla.getValueAt(filaseleccionada, 2);
                String compra = (String) modelotabla.getValueAt(filaseleccionada, 3);
                String venta = (String) modelotabla.getValueAt(filaseleccionada, 4);
                String existencias = (String) modelotabla.getValueAt(filaseleccionada, 5);
                
                viewProductos.jtf_id.setText(id);
                viewProductos.jtf_producto.setText(producto);
                viewProductos.jtf_descripcion.setText(descripcion);
                viewProductos.jtf_compra.setText(compra);
                viewProductos.jtf_venta.setText(venta);
                viewProductos.jtf_existencias.setText(existencias); 
                }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datos editados correctamente ");
        }

    }

    void Limpiar() {
        viewProductos.jtf_producto.setText("");
        viewProductos.jtf_compra.setText("");
        viewProductos.jtf_descripcion.setText("");
        viewProductos.jtf_venta.setText("");
        viewProductos.jtf_existencias.setText("");
        }

    void Actualizar() {

        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE productos SET Producto = '" + viewProductos.jtf_producto.getText()+ "', descripcion = '" + viewProductos.jtf_descripcion.getText() + "', precio_compra= '" + viewProductos.jtf_compra.getText() + "', precio_venta = '" + viewProductos.jtf_venta.getText() + "', existencias = '" + viewProductos.jtf_existencias.getText() + "' WHERE Id_Producto= '" + viewProductos.jtf_id.getText() + "'");
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    void guardar() {

        try {
            PreparedStatement ps = cn.prepareStatement("INSERT INTO productos (Producto,Descripcion,Precio_compra,Precio_venta,Existencias) VALUES (?,?,?,?,?)");
            ps.setString(1, viewProductos.jtf_producto.getText());
            ps.setString(2, viewProductos.jtf_descripcion.getText());
            ps.setString(3, viewProductos.jtf_compra.getText());
            ps.setString(4, viewProductos.jtf_venta.getText());
            ps.setString(5, viewProductos.jtf_existencias.getText());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar ");
            System.out.print(e.getMessage());
        }
    }

    void modificar() {

        try {
            String sql = "UPDATE productos producto=?, descripcion=?, precio_compra=?, precio_venta=?, existencias?" + "where id_producto=?";
            int fila = viewProductos.jt_productos.getSelectedRow();
            String dao = (String) viewProductos.jt_productos.getValueAt(fila, 0);
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, viewProductos.jtf_producto.getText());
            ps.setString(2, viewProductos.jtf_descripcion.getText());
            ps.setString(3, viewProductos.jtf_compra.getText());
            ps.setString(4, viewProductos.jtf_venta.getText());
            ps.setString(5, viewProductos.jtf_existencias.getText());

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
            int fila = viewProductos.jt_productos.getSelectedRow();
            if (fila >= 0) {

                DefaultTableModel modelotabla = (DefaultTableModel) viewProductos.jt_productos.getModel();

                String id = (String) modelotabla.getValueAt(fila, 0);
                String sql = ("DELETE FROM productos WHERE Id_Producto='" + id + "'");
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
        modelo.addColumn("Descripcion");
        modelo.addColumn("Precio compra");
        modelo.addColumn("Precio venta");
        modelo.addColumn("Existencias");

        viewProductos.jt_productos.setModel(modelo);
        String sql = "";

        if (valor.equals("")) {
            sql = "SELECT  * FROM productos";
        } else {

            sql = "SELECT  * FROM productos WHERE Id_Producto='" + valor + "'";
        }
        String[] datos = new String[6];

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

                modelo.addRow(datos);
            }
            viewProductos.jt_productos.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == viewProductos.jbtn_agregar) {

            if (viewProductos.jtf_producto.getText().equals("") || viewProductos.jtf_descripcion.getText().equals("") || viewProductos.jtf_compra.getText().equals("") || viewProductos.jtf_venta.getText().equals("") || viewProductos.jtf_existencias.getText().equals("")) {
                JOptionPane.showMessageDialog(null, " No has llenado todos los campos");
            } else {
                guardar();
                mostrardatos("");
                Limpiar();
                JOptionPane.showMessageDialog(null, "Datos agregados correctamente");
            }
        }

        if (e.getSource() == viewProductos.jbtn_borrar) {
            eliminar();
            JOptionPane.showMessageDialog(null, "Datos borrados exitosamente");
            System.out.print("e");
        }
        
        if (e.getSource() == viewProductos.jbtn_actualizar) {
            
            if (viewProductos.jtf_producto.getText().equals("") || viewProductos.jtf_descripcion.getText().equals("") || viewProductos.jtf_compra.getText().equals("") || viewProductos.jtf_venta.getText().equals("") || viewProductos.jtf_existencias.getText().equals("")) {
                JOptionPane.showMessageDialog(null, " No has llenado todos los campos");
            } else {
                Actualizar();
                mostrardatos("");
                Limpiar();
                JOptionPane.showMessageDialog(null, "Los datos han sido actualizados");
            }
        }

        if (e.getSource() == viewProductos.jbtn_editar) {
            editar();
        }
        if (e.getSource() == viewProductos.jbtn_buscar) {
            String id = String.format(JOptionPane.showInputDialog(viewProductos, "Ingresa el id que deseas buscar"));
            mostrardatos(id);
        }
        if (e.getSource() == viewProductos.jbtn_mostrar) {
            mostrardatos("");
        }
    }
}
