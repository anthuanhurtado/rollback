package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import views.ViewMain;

public class ControllerMain implements ActionListener {

    ViewMain viewMain;
    JPanel views[];

    public ControllerMain(ViewMain viewMain, JPanel[] views) {

        this.viewMain = viewMain;
        this.views = views;
        this.viewMain.jmi_clientes.addActionListener(this);
        this.viewMain.jmi_proveedores.addActionListener(this);
        this.viewMain.jmi_productos.addActionListener(this);
        this.viewMain.jmi_iniciar.addActionListener(this);
        this.viewMain.jmi_usuarios.addActionListener(this);
        init_View();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewMain.jmi_clientes) {
            jmi_clientesActionPerformed();
        } else if (e.getSource() == viewMain.jmi_proveedores) {
            jmi_proveedoresActionPerformed();
        } else if (e.getSource() == viewMain.jmi_productos) {
            jmi_productosActionPerformed();
        } else if (e.getSource() == viewMain.jmi_iniciar) {
            jmi_iniciarActionPerformed();
        }
        
    }

    private void jmi_clientesActionPerformed() {
        this.viewMain.setContentPane(views[0]);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }

    private void jmi_proveedoresActionPerformed() {
        this.viewMain.setContentPane(views[1]);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }
    
    private void jmi_productosActionPerformed() {
        this.viewMain.setContentPane(views[2]);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }
    
    private void jmi_iniciarActionPerformed() {
        this.viewMain.setContentPane(views[3]);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }
    
    private void init_View() {
        this.viewMain.setTitle("Tienda");
        this.viewMain.setLocationRelativeTo(null);
        this.viewMain.setVisible(true);
    }

    
      
}
