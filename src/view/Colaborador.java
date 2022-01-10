/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dal.ConexaoBancoDeDados;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Omega
 */
public class Colaborador extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form membroColab
     */
    public Colaborador() {
        initComponents();
        formatarCampo();
        conn = ConexaoBancoDeDados.conector();
        login lo = new login();
        lo.InserirIcone(this);
    }

    private void adicionar() {
        String sql = "insert membroecolaborador values(?,?,?,?,?,?,?,?)";
        String dataString = txtDataMembroColab.getText();
        String[] dataSeparada = dataString.split("/");
        LocalDate dataBanco = LocalDate.of(Integer.parseInt(dataSeparada[2]), Integer.parseInt(dataSeparada[1]), Integer.parseInt(dataSeparada[0]));
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, jTextNomeMembroColab.getText());
            pst.setString(2, jTextEnderecoMembroColab.getText());
            pst.setString(3, txtTelefoneMembroColab.getText());
            pst.setString(4, dataBanco.toString());
            pst.setString(5, txtCpfMembro.getText());
            if (jRadioButtonSimMembroColab.isSelected()) {
                pst.setString(6, "SIM");
            } else if (jRadioButtonNaoMembroColab.isSelected()) {
                pst.setString(6, "NAO");
            }
            if (jRadioButtonMembro.isSelected()) {
                pst.setString(7, "Membro");
            } else if (jRadioButtonColaborador.isSelected()) {
                pst.setString(7, "Colaborador");
            }
            pst.setString(8, lbUrlImagem.getText());
            if (jTextNomeMembroColab.getText().isEmpty() || txtTelefoneMembroColab.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por Favor preencha todos os campos obrigatórios!");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0 && jRadioButtonMembro.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Membro Cadastrado com Sucesso!");
                    limpar();
                }
                if (adicionado > 0 && jRadioButtonColaborador.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Colaborador Cadastrado com Sucesso!");
                    limpar();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void remover() {
        if (jRadioButtonMembro.isSelected()) {
            int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse Membro?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                String sql = "delete from membroecolaborador where nome=?";
                try {
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, jTextNomeMembroColab.getText());
                    int apagado = pst.executeUpdate();

                    if (apagado > 0 && jRadioButtonMembro.isSelected()) {
                        JOptionPane.showMessageDialog(null, "Membro Removido com Sucesso!");
                        limpar();
                    } else if (apagado > 0 && jRadioButtonColaborador.isSelected()) {
                        JOptionPane.showMessageDialog(null, "Colaborador Removido com Sucesso!");
                        limpar();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

            }
        } else if (jRadioButtonColaborador.isSelected()) {
            int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse Colaborador?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                String sql = "delete from membroecolaborador where nome=?";
                try {
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, jTextNomeMembroColab.getText());
                    int apagado = pst.executeUpdate();

                    if (apagado > 0 && jRadioButtonMembro.isSelected()) {
                        JOptionPane.showMessageDialog(null, "Membro Removido com Sucesso!");
                        limpar();
                    } else if (apagado > 0 && jRadioButtonColaborador.isSelected()) {
                        JOptionPane.showMessageDialog(null, "Colaborador Removido com Sucesso!");
                        limpar();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

            }
        }

    }

    private void alterar() {
        String sql = "update membroecolaborador set nome=?,endereco=?,telefone=?,nascimento=?,cpf=?,batizado=?,tipo=?,imagem=? where nome=?";
        String dataString = txtDataMembroColab.getText();
        String[] dataSeparada = dataString.split("/");
        LocalDate dataBanco = LocalDate.of(Integer.parseInt(dataSeparada[2]), Integer.parseInt(dataSeparada[1]), Integer.parseInt(dataSeparada[0]));
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, jTextNomeMembroColab.getText());
            pst.setString(2, jTextEnderecoMembroColab.getText());
            pst.setString(3, txtTelefoneMembroColab.getText());
            pst.setString(4, dataBanco.toString());
            pst.setString(5, txtCpfMembro.getText());
            if (jRadioButtonSimMembroColab.isSelected()) {
                pst.setString(6, "SIM");
            } else if (jRadioButtonNaoMembroColab.isSelected()) {
                pst.setString(6, "NAO");
            }
            if (jRadioButtonMembro.isSelected()) {
                pst.setString(7, "Membro");
            } else if (jRadioButtonColaborador.isSelected()) {
                pst.setString(7, "Colaborador");
            }
            pst.setString(8, lbUrlImagem.getText());
            pst.setString(9, jTextNomeMembroColab.getText());
            if (jTextNomeMembroColab.getText().isEmpty() || txtTelefoneMembroColab.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por Favor preencha todos os campos obrigatórios!");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0 && jRadioButtonMembro.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Dados do Membro atualizado com Sucesso!");
                    limpar();
                } else if (adicionado > 0 && jRadioButtonColaborador.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Dados do Colaborador atualizado com Sucesso!");
                    limpar();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void pesquisar() {
        String sql = "select nome as Nome, endereco as Endereço, telefone as Telefone, nascimento as Aniversário, cpf as CPF, batizado as Batizado, tipo as Tipo, imagem as Imagem from membroecolaborador where nome like ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblMembros.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_campos() {
        int setar = tblMembros.getSelectedRow();

        Date dataTabela = (Date) tblMembros.getModel().getValueAt(setar, 3);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = sdf.format(dataTabela);

        jTextNomeMembroColab.setText(tblMembros.getModel().getValueAt(setar, 0).toString());
        jTextEnderecoMembroColab.setText(tblMembros.getModel().getValueAt(setar, 1).toString());
        txtTelefoneMembroColab.setText(tblMembros.getModel().getValueAt(setar, 2).toString());
        txtDataMembroColab.setText(dataFormatada.toString());
        txtCpfMembro.setText(tblMembros.getModel().getValueAt(setar, 4).toString());
        if ("SIM".equals(tblMembros.getModel().getValueAt(setar, 5))) {
            jRadioButtonSimMembroColab.setSelected(true);
            jRadioButtonNaoMembroColab.setSelected(false);
        } else if ("NAO".equals(tblMembros.getModel().getValueAt(setar, 5))) {
            jRadioButtonNaoMembroColab.setSelected(true);
            jRadioButtonSimMembroColab.setSelected(false);
        }
        if ("Membro".equals(tblMembros.getModel().getValueAt(setar, 6))) {
            jRadioButtonMembro.setSelected(true);
            jRadioButtonColaborador.setSelected(false);
        } else if ("Colaborador".equals(tblMembros.getModel().getValueAt(setar, 6))) {
            jRadioButtonColaborador.setSelected(true);
            jRadioButtonMembro.setSelected(false);
        }
        ImageIcon imagem = new ImageIcon(tblMembros.getModel().getValueAt(setar, 7).toString());
        lbImagem.setIcon(new ImageIcon(imagem.getImage().getScaledInstance(lbImagem.getWidth(), lbImagem.getHeight(), Image.SCALE_DEFAULT)));
        lbUrlImagem.setText(tblMembros.getModel().getValueAt(setar, 7).toString());
    }

    private void limpar() {
        jTextNomeMembroColab.setText(null);
        jTextEnderecoMembroColab.setText(null);
        txtTelefoneMembroColab.setText(null);
        txtDataMembroColab.setText(null);
        txtCpfMembro.setText(null);
        jRadioButtonSimMembroColab.setSelected(false);
        jRadioButtonNaoMembroColab.setSelected(false);
        jRadioButtonMembro.setSelected(false);
        jRadioButtonColaborador.setSelected(false);
        ImageIcon imagem = new ImageIcon(getClass().getResource("/view/imagems/imgCliente.png"));
        lbImagem.setIcon(imagem);
        ((DefaultTableModel) tblMembros.getModel()).setRowCount(0);
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
        txtPesquisar = new javax.swing.JTextField();
        jRadioButtonColaborador = new javax.swing.JRadioButton();
        jRadioButtonMembro = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMembros = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jButtonAtualizarColaborador = new javax.swing.JButton();
        jButtonRemoverColaborador = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        btnAddImg = new javax.swing.JButton();
        jButtonSairVisitante = new javax.swing.JButton();
        jRadioButtonNaoMembroColab = new javax.swing.JRadioButton();
        jRadioButtonSimMembroColab = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        txtCpfMembro = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTelefoneMembroColab = new javax.swing.JFormattedTextField();
        txtDataMembroColab = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jButtonCadastrarColaborador = new javax.swing.JButton();
        jTextEnderecoMembroColab = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextNomeMembroColab = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbImagem = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbUrlImagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarActionPerformed(evt);
            }
        });
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });
        getContentPane().add(txtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 360, -1));

        jRadioButtonColaborador.setBackground(new java.awt.Color(0, 153, 204));
        jRadioButtonColaborador.setForeground(new java.awt.Color(204, 204, 0));
        jRadioButtonColaborador.setText("Colaborador");
        jRadioButtonColaborador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonColaboradorActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButtonColaborador, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 350, -1, -1));

        jRadioButtonMembro.setBackground(new java.awt.Color(0, 153, 204));
        jRadioButtonMembro.setForeground(new java.awt.Color(204, 204, 0));
        jRadioButtonMembro.setText("Membro");
        jRadioButtonMembro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMembroActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButtonMembro, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 350, -1, -1));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 0));
        jLabel13.setText("*Usuário cadastrado é:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 350, -1, 20));

        tblMembros = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblMembros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "Endereço", "Telefone", "Aniversário", "CPF", "Batizado", "Tipo", "Imagem"
            }
        ));
        tblMembros.setFocusable(false);
        tblMembros.getTableHeader().setReorderingAllowed(false);
        tblMembros.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblMembrosAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblMembros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMembrosMouseClicked(evt);
            }
        });
        tblMembros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblMembrosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblMembros);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 1150, 150));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 0));
        jLabel12.setText("* Campos Obrigatórios");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 140, -1, -1));

        jButtonAtualizarColaborador.setBackground(new java.awt.Color(204, 204, 0));
        jButtonAtualizarColaborador.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonAtualizarColaborador.setForeground(new java.awt.Color(0, 153, 204));
        jButtonAtualizarColaborador.setText("ATUALIZAR");
        jButtonAtualizarColaborador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAtualizarColaborador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizarColaboradorActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAtualizarColaborador, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 400, 170, 50));

        jButtonRemoverColaborador.setBackground(new java.awt.Color(204, 204, 0));
        jButtonRemoverColaborador.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonRemoverColaborador.setForeground(new java.awt.Color(0, 153, 204));
        jButtonRemoverColaborador.setText("REMOVER");
        jButtonRemoverColaborador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRemoverColaborador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverColaboradorActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonRemoverColaborador, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 400, 160, 50));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 0));
        jLabel11.setText("* Foto");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, -1, -1));

        btnAddImg.setBackground(new java.awt.Color(204, 204, 0));
        btnAddImg.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAddImg.setForeground(new java.awt.Color(0, 153, 204));
        btnAddImg.setText("Adcionar Imagem");
        btnAddImg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddImgActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, 200, -1));

        jButtonSairVisitante.setBackground(new java.awt.Color(204, 204, 0));
        jButtonSairVisitante.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonSairVisitante.setForeground(new java.awt.Color(0, 153, 204));
        jButtonSairVisitante.setText("Sair");
        jButtonSairVisitante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSairVisitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairVisitanteActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSairVisitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 400, 130, 50));

        jRadioButtonNaoMembroColab.setBackground(new java.awt.Color(0, 153, 204));
        jRadioButtonNaoMembroColab.setForeground(new java.awt.Color(204, 204, 0));
        jRadioButtonNaoMembroColab.setText("NÃO");
        jRadioButtonNaoMembroColab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNaoMembroColabActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButtonNaoMembroColab, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 320, -1, 20));

        jRadioButtonSimMembroColab.setBackground(new java.awt.Color(0, 153, 204));
        jRadioButtonSimMembroColab.setForeground(new java.awt.Color(204, 204, 0));
        jRadioButtonSimMembroColab.setText("SIM");
        jRadioButtonSimMembroColab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSimMembroColabActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButtonSimMembroColab, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 320, 60, 20));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 0));
        jLabel10.setText("*Já é Batizado(a) nas Águas?");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 320, -1, -1));

        txtCpfMembro.setBackground(new java.awt.Color(255, 255, 255));
        txtCpfMembro.setForeground(new java.awt.Color(0, 0, 0));
        txtCpfMembro.setCaretColor(new java.awt.Color(0, 0, 0));
        getContentPane().add(txtCpfMembro, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, 390, 40));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 0));
        jLabel9.setText("CPF");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 320, -1, -1));

        txtTelefoneMembroColab.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefoneMembroColab.setForeground(new java.awt.Color(0, 0, 0));
        txtTelefoneMembroColab.setCaretColor(new java.awt.Color(0, 0, 0));
        txtTelefoneMembroColab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefoneMembroColabActionPerformed(evt);
            }
        });
        getContentPane().add(txtTelefoneMembroColab, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 390, 40));

        txtDataMembroColab.setBackground(new java.awt.Color(255, 255, 255));
        txtDataMembroColab.setForeground(new java.awt.Color(0, 0, 0));
        txtDataMembroColab.setCaretColor(new java.awt.Color(0, 0, 0));
        txtDataMembroColab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataMembroColabActionPerformed(evt);
            }
        });
        getContentPane().add(txtDataMembroColab, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 270, 460, 40));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 204, 0));
        jLabel8.setText("*Data Nascimento");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 240, -1, -1));

        jButtonCadastrarColaborador.setBackground(new java.awt.Color(204, 204, 0));
        jButtonCadastrarColaborador.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonCadastrarColaborador.setForeground(new java.awt.Color(0, 153, 204));
        jButtonCadastrarColaborador.setText("CADASTRAR");
        jButtonCadastrarColaborador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCadastrarColaborador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarColaboradorActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCadastrarColaborador, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 400, 160, 50));

        jTextEnderecoMembroColab.setBackground(new java.awt.Color(255, 255, 255));
        jTextEnderecoMembroColab.setForeground(new java.awt.Color(0, 0, 0));
        jTextEnderecoMembroColab.setCaretColor(new java.awt.Color(0, 0, 0));
        getContentPane().add(jTextEnderecoMembroColab, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 200, 460, 40));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 0));
        jLabel7.setText("Endereço");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 180, -1, -1));

        jTextNomeMembroColab.setBackground(new java.awt.Color(255, 255, 255));
        jTextNomeMembroColab.setForeground(new java.awt.Color(0, 0, 0));
        jTextNomeMembroColab.setCaretColor(new java.awt.Color(0, 0, 0));
        jTextNomeMembroColab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNomeMembroColabActionPerformed(evt);
            }
        });
        getContentPane().add(jTextNomeMembroColab, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 390, 40));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 0));
        jLabel6.setText("*Telefone");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, -1, 30));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 0));
        jLabel4.setText("*Nome");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 70, -1));

        lbImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagems/imgCliente.png"))); // NOI18N
        lbImagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(lbImagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 200, 200));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Cadastro de Membros e Colaboradores");
        jLabel3.setToolTipText("");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 1150, 30));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagems/fundoTelasDeMenu.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 1150, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagems/BackgroundMenusPrincipais.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lbUrlImagem.setText("jLabel5");
        getContentPane().add(lbUrlImagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextNomeMembroColabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNomeMembroColabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNomeMembroColabActionPerformed

    private void txtDataMembroColabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataMembroColabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataMembroColabActionPerformed

    private void jRadioButtonNaoMembroColabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNaoMembroColabActionPerformed
        // TODO add your handling code here:
        jRadioButtonSimMembroColab.setSelected(false);
    }//GEN-LAST:event_jRadioButtonNaoMembroColabActionPerformed

    private void jButtonSairVisitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairVisitanteActionPerformed
        // TODO add your handling code here:
        menuPrincipal volt = new menuPrincipal();
        int sair = JOptionPane.showConfirmDialog(null, "Realmente deseja voltar ao menu principal?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            volt.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButtonSairVisitanteActionPerformed

    private void jButtonAtualizarColaboradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarColaboradorActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_jButtonAtualizarColaboradorActionPerformed

    private void txtTelefoneMembroColabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneMembroColabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneMembroColabActionPerformed

    private void tblMembrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblMembrosKeyReleased

    }//GEN-LAST:event_tblMembrosKeyReleased

    private void tblMembrosAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblMembrosAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblMembrosAncestorAdded

    private void jButtonCadastrarColaboradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarColaboradorActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_jButtonCadastrarColaboradorActionPerformed

    private void jButtonRemoverColaboradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverColaboradorActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_jButtonRemoverColaboradorActionPerformed

    private void jRadioButtonMembroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMembroActionPerformed
        // TODO add your handling code here:
        jRadioButtonColaborador.setSelected(false);
    }//GEN-LAST:event_jRadioButtonMembroActionPerformed

    private void btnAddImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddImgActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Escolha uma Imagem");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagem", "jpg", "png");
        fileChooser.setFileFilter(filter);
        int retorno = fileChooser.showOpenDialog(this);
        if (retorno == JFileChooser.APPROVE_OPTION) {

            File file = new File("");
            file = fileChooser.getSelectedFile();
            String fileName = file.getAbsolutePath();
            lbUrlImagem.setText(fileName);
            ImageIcon imagem = new ImageIcon(file.getPath());
            lbImagem.setIcon(new ImageIcon(imagem.getImage().getScaledInstance(lbImagem.getWidth(), lbImagem.getHeight(), Image.SCALE_DEFAULT)));
        }
    }//GEN-LAST:event_btnAddImgActionPerformed

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarActionPerformed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void tblMembrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMembrosMouseClicked

        setar_campos();
    }//GEN-LAST:event_tblMembrosMouseClicked

    private void jRadioButtonSimMembroColabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSimMembroColabActionPerformed
        // TODO add your handling code here:
        jRadioButtonNaoMembroColab.setSelected(false);
    }//GEN-LAST:event_jRadioButtonSimMembroColabActionPerformed

    private void jRadioButtonColaboradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonColaboradorActionPerformed
        // TODO add your handling code here:
        jRadioButtonMembro.setSelected(false);
    }//GEN-LAST:event_jRadioButtonColaboradorActionPerformed

    /**
     * @param args the command line arguments
     */
    private void formatarCampo() {
        try {
            MaskFormatter mask = new MaskFormatter("##/##/####");
            mask.install(txtDataMembroColab);
            MaskFormatter maskFone = new MaskFormatter("(##)#####-####");
            maskFone.install(txtTelefoneMembroColab);
            MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
            maskCpf.install(txtCpfMembro);
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
            java.util.logging.Logger.getLogger(Colaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Colaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Colaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Colaborador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Colaborador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddImg;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonAtualizarColaborador;
    private javax.swing.JButton jButtonCadastrarColaborador;
    private javax.swing.JButton jButtonRemoverColaborador;
    private javax.swing.JButton jButtonSairVisitante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButtonColaborador;
    private javax.swing.JRadioButton jRadioButtonMembro;
    private javax.swing.JRadioButton jRadioButtonNaoMembroColab;
    private javax.swing.JRadioButton jRadioButtonSimMembroColab;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextEnderecoMembroColab;
    private javax.swing.JTextField jTextNomeMembroColab;
    private javax.swing.JLabel lbImagem;
    private javax.swing.JLabel lbUrlImagem;
    private javax.swing.JTable tblMembros;
    private javax.swing.JFormattedTextField txtCpfMembro;
    private javax.swing.JFormattedTextField txtDataMembroColab;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JFormattedTextField txtTelefoneMembroColab;
    // End of variables declaration//GEN-END:variables
}
