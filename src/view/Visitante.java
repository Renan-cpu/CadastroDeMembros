/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dal.ConexaoBancoDeDados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Omega
 */
public class Visitante extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form visitante
     */
    public Visitante() {
        initComponents();
        formatarCampo();
        conn = ConexaoBancoDeDados.conector();
        login lo = new login();
        lo.InserirIcone(this);
    }

    private void pesquisar() {
        String sql = "select nomeVisitante as Nome, enderecoVisitante as Endereço, telefoneVisitante as Telefone,algumaigreja as AlgumaIgreja from visitante where nomeVisitante like ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblVisitantes.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void adicionar() {
        String sql = "insert visitante values(?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, jTextNomeVisitante.getText());
            pst.setString(2, jTextEndereçoVisitante.getText());
            pst.setString(3, txtTelefoneVisitante.getText());
            if (jRadioButtonSimVisitante.isSelected()) {
                pst.setString(4, "SIM");
            } else if (jRadioButtonNaoVisitante.isSelected()) {
                pst.setString(4, "NAO");
            }
            if (jTextNomeVisitante.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por Favor preencha todos os campos obrigatórios!");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Visitante Cadastrado com Sucesso!");
                    limpar();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void alterar() {
        String sql = "update visitante set nomeVisitante=?,enderecoVisitante=?,telefoneVisitante=?,algumaigreja=? where nomeVisitante=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, jTextNomeVisitante.getText());
            pst.setString(2, jTextEndereçoVisitante.getText());
            pst.setString(3, txtTelefoneVisitante.getText());
            if (jRadioButtonSimVisitante.isSelected()) {
                pst.setString(4, "SIM");
            } else if (jRadioButtonNaoVisitante.isSelected()) {
                pst.setString(4, "NAO");
            }
            pst.setString(5, jTextNomeVisitante.getText());
            if (jTextNomeVisitante.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por Favor preencha todos os campos obrigatórios!");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do Visitante atualizado com Sucesso!");
                    limpar();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void remover() {
        int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse Visitante?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            String sql = "delete from visitante where nomeVisitante=?";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, jTextNomeVisitante.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Visitante Removido com Sucesso!");
                    limpar();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }

    private void setar_campos() {
        int setar = tblVisitantes.getSelectedRow();

        jTextNomeVisitante.setText(tblVisitantes.getModel().getValueAt(setar, 0).toString());
        jTextEndereçoVisitante.setText(tblVisitantes.getModel().getValueAt(setar, 1).toString());
        txtTelefoneVisitante.setText(tblVisitantes.getModel().getValueAt(setar, 2).toString());
        if ("SIM".equals(tblVisitantes.getModel().getValueAt(setar, 3))) {
            jRadioButtonSimVisitante.setSelected(true);
            jRadioButtonNaoVisitante.setSelected(false);
        } else if ("NAO".equals(tblVisitantes.getModel().getValueAt(setar, 3))) {
            jRadioButtonNaoVisitante.setSelected(true);
            jRadioButtonSimVisitante.setSelected(false);
        }
    }

    private void limpar() {
        jTextNomeVisitante.setText(null);
        jTextEndereçoVisitante.setText(null);
        txtTelefoneVisitante.setText(null);
        jRadioButtonSimVisitante.setSelected(false);
        jRadioButtonNaoVisitante.setSelected(false);
        ((DefaultTableModel) tblVisitantes.getModel()).setRowCount(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVisitantes = new javax.swing.JTable();
        txtPesquisar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButtonRemoverVisitante = new javax.swing.JButton();
        jButtonaTUALIZARVisitante = new javax.swing.JButton();
        jButtonSairVisitante = new javax.swing.JButton();
        txtTelefoneVisitante = new javax.swing.JFormattedTextField();
        jButtonCadastrarVisitante = new javax.swing.JButton();
        jRadioButtonSimVisitante = new javax.swing.JRadioButton();
        jRadioButtonNaoVisitante = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jTextEndereçoVisitante = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextNomeVisitante = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visitante");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblVisitantes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblVisitantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "Endereço", "Telefone", "AlgumaIgreja"
            }
        ));
        tblVisitantes.setFocusable(false);
        tblVisitantes.getTableHeader().setReorderingAllowed(false);
        tblVisitantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVisitantesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVisitantes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 1150, 150));

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 330, 30));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 0));
        jLabel5.setText("* Campos Obrigatórios");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 150, -1, -1));

        jButtonRemoverVisitante.setBackground(new java.awt.Color(204, 204, 0));
        jButtonRemoverVisitante.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonRemoverVisitante.setForeground(new java.awt.Color(0, 153, 204));
        jButtonRemoverVisitante.setText("REMOVER");
        jButtonRemoverVisitante.setToolTipText("Remover");
        jButtonRemoverVisitante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRemoverVisitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverVisitanteActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonRemoverVisitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 380, 200, 50));

        jButtonaTUALIZARVisitante.setBackground(new java.awt.Color(204, 204, 0));
        jButtonaTUALIZARVisitante.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonaTUALIZARVisitante.setForeground(new java.awt.Color(0, 153, 204));
        jButtonaTUALIZARVisitante.setText("ATUALIZAR");
        jButtonaTUALIZARVisitante.setToolTipText("Atualizar");
        jButtonaTUALIZARVisitante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonaTUALIZARVisitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonaTUALIZARVisitanteActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonaTUALIZARVisitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, 200, 50));

        jButtonSairVisitante.setBackground(new java.awt.Color(204, 204, 0));
        jButtonSairVisitante.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonSairVisitante.setForeground(new java.awt.Color(0, 153, 204));
        jButtonSairVisitante.setText("Sair");
        jButtonSairVisitante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSairVisitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairVisitanteActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSairVisitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 380, 150, 50));

        txtTelefoneVisitante.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefoneVisitante.setForeground(new java.awt.Color(0, 0, 0));
        txtTelefoneVisitante.setCaretColor(new java.awt.Color(0, 0, 0));
        getContentPane().add(txtTelefoneVisitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 420, 40));

        jButtonCadastrarVisitante.setBackground(new java.awt.Color(204, 204, 0));
        jButtonCadastrarVisitante.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonCadastrarVisitante.setForeground(new java.awt.Color(0, 153, 204));
        jButtonCadastrarVisitante.setText("CADASTRAR");
        jButtonCadastrarVisitante.setToolTipText("Cadastrar");
        jButtonCadastrarVisitante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCadastrarVisitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarVisitanteActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCadastrarVisitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 200, 50));

        jRadioButtonSimVisitante.setBackground(new java.awt.Color(0, 153, 204));
        jRadioButtonSimVisitante.setForeground(new java.awt.Color(204, 204, 0));
        jRadioButtonSimVisitante.setText("SIM");
        jRadioButtonSimVisitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSimVisitanteActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButtonSimVisitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 310, -1, -1));

        jRadioButtonNaoVisitante.setBackground(new java.awt.Color(0, 153, 204));
        jRadioButtonNaoVisitante.setForeground(new java.awt.Color(204, 204, 0));
        jRadioButtonNaoVisitante.setText("NÃO");
        jRadioButtonNaoVisitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNaoVisitanteActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButtonNaoVisitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 310, -1, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 0));
        jLabel8.setText("É de alguma igreja?");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 310, -1, -1));

        jTextEndereçoVisitante.setBackground(new java.awt.Color(255, 255, 255));
        jTextEndereçoVisitante.setForeground(new java.awt.Color(0, 0, 0));
        jTextEndereçoVisitante.setCaretColor(new java.awt.Color(0, 0, 0));
        getContentPane().add(jTextEndereçoVisitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 230, 400, 40));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 0));
        jLabel7.setText("Endereço");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 200, -1, -1));

        jTextNomeVisitante.setBackground(new java.awt.Color(255, 255, 255));
        jTextNomeVisitante.setForeground(new java.awt.Color(0, 0, 0));
        jTextNomeVisitante.setCaretColor(new java.awt.Color(0, 0, 0));
        jTextNomeVisitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNomeVisitanteActionPerformed(evt);
            }
        });
        getContentPane().add(jTextNomeVisitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 420, 40));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 0));
        jLabel6.setText("Telefone");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, -1, 30));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 0));
        jLabel4.setText("* Nome");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 70, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Cadastro de Visitante");
        jLabel3.setToolTipText("");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 1150, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagems/fundoTelasDeMenu.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 1150, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagems/BackgroundMenusPrincipais.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextNomeVisitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNomeVisitanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNomeVisitanteActionPerformed

    private void jButtonSairVisitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairVisitanteActionPerformed
        // TODO add your handling code here:
        menuPrincipal volt = new menuPrincipal();
        int sair = JOptionPane.showConfirmDialog(null, "Realmente deseja voltar ao menu principal?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            volt.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButtonSairVisitanteActionPerformed

    private void jRadioButtonSimVisitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSimVisitanteActionPerformed
        jRadioButtonNaoVisitante.setSelected(false);
    }//GEN-LAST:event_jRadioButtonSimVisitanteActionPerformed

    private void jRadioButtonNaoVisitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNaoVisitanteActionPerformed
        jRadioButtonSimVisitante.setSelected(false);
    }//GEN-LAST:event_jRadioButtonNaoVisitanteActionPerformed

    private void jButtonCadastrarVisitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarVisitanteActionPerformed
        // Cadastra um Visitante
        adicionar();
    }//GEN-LAST:event_jButtonCadastrarVisitanteActionPerformed

    private void jButtonaTUALIZARVisitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonaTUALIZARVisitanteActionPerformed
        // Altera o Visitante
        alterar();
    }//GEN-LAST:event_jButtonaTUALIZARVisitanteActionPerformed

    private void jButtonRemoverVisitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverVisitanteActionPerformed
        // Remove o Visitante
        remover();
    }//GEN-LAST:event_jButtonRemoverVisitanteActionPerformed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void tblVisitantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVisitantesMouseClicked
        // TODO add your handling code here:
        setar_campos();
    }//GEN-LAST:event_tblVisitantesMouseClicked

    /**
     * @param args the command line arguments
     */
    private void formatarCampo() {
        try {
            MaskFormatter maskFone = new MaskFormatter("(##)#####-####");
            maskFone.install(txtTelefoneVisitante);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao formatar campo de texto", "Erro", JOptionPane.ERROR);
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Visitante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Visitante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Visitante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Visitante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Visitante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonCadastrarVisitante;
    private javax.swing.JButton jButtonRemoverVisitante;
    private javax.swing.JButton jButtonSairVisitante;
    private javax.swing.JButton jButtonaTUALIZARVisitante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRadioButtonNaoVisitante;
    private javax.swing.JRadioButton jRadioButtonSimVisitante;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextEndereçoVisitante;
    private javax.swing.JTextField jTextNomeVisitante;
    private javax.swing.JTable tblVisitantes;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JFormattedTextField txtTelefoneVisitante;
    // End of variables declaration//GEN-END:variables
}
