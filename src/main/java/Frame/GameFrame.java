/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frame;

import game.Player;
import game.Territory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import server.Client;

/**
 *
 * @author Ali Haydar
 */
public class GameFrame extends javax.swing.JFrame {

    /**
     * Creates new form GameFrame
     */
    Client client;
    DefaultListModel<String> lst_model = new DefaultListModel<>();
    ArrayList<JButton> buttons = new ArrayList<>();
    ArrayList<JLabel> soldierCount = new ArrayList<>();
    DefaultListModel<String> territories = new DefaultListModel<>();
    DefaultListModel<String> destTerritories = new DefaultListModel<>();
    DefaultListModel<String> attackSourceTerritories = new DefaultListModel<>();
    DefaultListModel<String> attackDestTerritories = new DefaultListModel<>();

    public GameFrame(Client client) {
        initComponents();
        this.client = client;
        lst_messages.setModel(lst_model);
        lst_sendSoldierTerritories.setModel(territories);
        lst_sendSoldierSourceTerritory.setModel(destTerritories);
        lst_attackDest.setModel(attackDestTerritories);
        lst_attackSource.setModel(attackSourceTerritories);
        lst_assignSoldier.setModel(destTerritories);

        buttons.add(btn_b);//1
        buttons.add(btn_a);//2
        //buttons.add(btn_c);
        buttons.add(btn_d);//3
        buttons.add(btn_e);//4
        buttons.add(btn_f);//5
        buttons.add(btn_g);//6
        buttons.add(btn_h);//7
        buttons.add(btn_i);//8
        buttons.add(btn_j);//9
        buttons.add(btn_k);//10
        buttons.add(btn_l);//11
        buttons.add(btn_m);//12
        buttons.add(btn_n);//13
        buttons.add(btn_o);//14
        buttons.add(btn_p);//15
        buttons.add(btn_r);//15
        buttons.add(btn_s);//16
        buttons.add(btn_u);//17
        buttons.add(btn_y);//18
        buttons.add(btn_x);//19
        buttons.add(btn_w);//20
        buttons.add(btn_z);//21
        buttons.add(btn_t);//22
        //21
        //22

        soldierCount.add(soldierA);
        soldierCount.add(soldierB);
        soldierCount.add(soldierD);
        soldierCount.add(soldierE);
        soldierCount.add(soldierF);
        soldierCount.add(soldierG);
        soldierCount.add(soldierH);
        soldierCount.add(soldierI);
        soldierCount.add(soldierJ);
        soldierCount.add(soldierK);
        soldierCount.add(soldierL);
        soldierCount.add(soldierM);
        soldierCount.add(soldierN);
        soldierCount.add(soldierO);
        soldierCount.add(soldierP);
        soldierCount.add(soldierR);
        soldierCount.add(soldierS);
        soldierCount.add(soldierU);
        soldierCount.add(soldierY);
        soldierCount.add(soldierZ);
        soldierCount.add(soldierW);
        soldierCount.add(soldierX);
        soldierCount.add(soldierT);

    }

    private Color parseColor(String colorStr) {
        try {

            // Küçük harfe çevir ve string isimlere göre karşılaştır
            switch (colorStr.toLowerCase()) {
                case "red":
                    return Color.RED;
                case "blue":
                    return Color.BLUE;
                case "green":
                    return Color.GREEN;
                case "yellow":
                    return Color.YELLOW;
                case "orange":
                    return Color.ORANGE;
                case "pink":
                    return Color.PINK;
                default:
                    return null;
            }
        } catch (Exception e) {
            return Color.LIGHT_GRAY;
        }
    }

    public void setEnabledAllComponents(Container container, boolean enabled) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enabled);
            if (component instanceof Container) {
                setEnabledAllComponents((Container) component, enabled);
            }
        }
    }

    public void updateView() {

        //System.out.println("GAME TEROORTORY" + this.client.getGame().getTerritories());
        //System.out.println("GAME PLAYERS" + this.client.getGame().getPlayers());
        //OWNER GUNCELLE
        //Thread.sleep(3000);
        for (int i = 0; i < this.client.getGame().getTerritories().size(); i++) {
            this.buttons.get(i).setBackground(parseColor(this.client.getGame().getTerritories().get(i).getOwner().getColor()));

        }
        //MESAJLARI GUNCELLE
        lst_model.removeAllElements();
        for (String message : this.client.getGame().getMessages()) {
            lst_model.addElement(message);
        }

        Player p = null;
        for (Player player : this.client.getGame().getPlayers()) {
            if (player.getPlayerID() == client.getClientId()) {
                p = player;
                break;
            }
        }

        txt_userID.setText("" + p.getPlayerID());
        int i = 0;
        for (Territory territory : this.client.getGame().getTerritories()) {
            soldierCount.get(i).setText("SOLDIER : " + territory.getSoldierCount());
            i++;
        }

        territories.removeAllElements();
        i = 0;
        for (Territory territory : p.getTerritories()) {
            territories.addElement(territory.getName());
            i++;
        }

        destTerritories.removeAllElements();
        for (Territory territory : p.getTerritories()) {
            destTerritories.addElement(territory.getName());
        }

        attackSourceTerritories.removeAllElements();

        for (Territory territory : p.getTerritories()) {
            attackSourceTerritories.addElement(territory.getName());
        }
        for (String message : this.client.getGame().getMessages()) {
            lst_model.addElement(message);
        }

        txt_soldierCount.setText(p.getSoldierCount() + "");

        if (p.getIsTurn()) {
            txt_isTurn.setText("YOUR TURN");
            setEnabledAllComponents(this.getContentPane(), true);
        } else {
            txt_isTurn.setText("NOT YOUR TURN");
            setEnabledAllComponents(this.getContentPane(), false);
        }

    }

    public GameFrame() {
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lst_messages = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        btn_b = new javax.swing.JButton();
        btn_a = new javax.swing.JButton();
        btn_d = new javax.swing.JButton();
        btn_h = new javax.swing.JButton();
        btn_e = new javax.swing.JButton();
        btn_f = new javax.swing.JButton();
        btn_i = new javax.swing.JButton();
        btn_j = new javax.swing.JButton();
        btn_k = new javax.swing.JButton();
        btn_l = new javax.swing.JButton();
        btn_m = new javax.swing.JButton();
        btn_n = new javax.swing.JButton();
        btn_o = new javax.swing.JButton();
        btn_p = new javax.swing.JButton();
        btn_g = new javax.swing.JButton();
        btn_s = new javax.swing.JButton();
        btn_t = new javax.swing.JButton();
        btn_u = new javax.swing.JButton();
        btn_y = new javax.swing.JButton();
        btn_z = new javax.swing.JButton();
        btn_x = new javax.swing.JButton();
        btn_w = new javax.swing.JButton();
        btn_r = new javax.swing.JButton();
        soldierA = new javax.swing.JLabel();
        soldierB = new javax.swing.JLabel();
        soldierD = new javax.swing.JLabel();
        soldierE = new javax.swing.JLabel();
        soldierF = new javax.swing.JLabel();
        soldierG = new javax.swing.JLabel();
        soldierH = new javax.swing.JLabel();
        soldierI = new javax.swing.JLabel();
        soldierJ = new javax.swing.JLabel();
        soldierK = new javax.swing.JLabel();
        soldierL = new javax.swing.JLabel();
        soldierM = new javax.swing.JLabel();
        soldierN = new javax.swing.JLabel();
        soldierO = new javax.swing.JLabel();
        soldierP = new javax.swing.JLabel();
        soldierR = new javax.swing.JLabel();
        soldierS = new javax.swing.JLabel();
        soldierU = new javax.swing.JLabel();
        soldierY = new javax.swing.JLabel();
        soldierW = new javax.swing.JLabel();
        soldierX = new javax.swing.JLabel();
        soldierT = new javax.swing.JLabel();
        soldierZ = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_userID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lst_sendSoldierTerritories = new javax.swing.JList<>();
        txt_isTurn = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_sendSoldierCount = new javax.swing.JTextField();
        btn_sendSoldier = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lst_sendSoldierSourceTerritory = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lst_attackSource = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        lst_attackDest = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btn_attack = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txt_soldierCount = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_assignSoldier = new javax.swing.JTextField();
        btn_assignSoldier = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        lst_assignSoldier = new javax.swing.JList<>();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(lst_messages);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_b.setText("B");
        jPanel3.add(btn_b, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 40, 20));

        btn_a.setText("A");
        btn_a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aActionPerformed(evt);
            }
        });
        jPanel3.add(btn_a, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 40, 20));

        btn_d.setText("D");
        jPanel3.add(btn_d, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 30, 30));

        btn_h.setText("H");
        jPanel3.add(btn_h, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 50, -1));

        btn_e.setText("E");
        jPanel3.add(btn_e, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 60, -1));

        btn_f.setText("F");
        jPanel3.add(btn_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 60, -1));

        btn_i.setText("I");
        jPanel3.add(btn_i, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 40, 20));

        btn_j.setText("J");
        jPanel3.add(btn_j, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, 30, 30));

        btn_k.setText("K");
        jPanel3.add(btn_k, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 243, 40, 20));

        btn_l.setText("L");
        jPanel3.add(btn_l, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 50, 20));

        btn_m.setText("M");
        jPanel3.add(btn_m, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 50, -1));

        btn_n.setText("N");
        jPanel3.add(btn_n, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 40, -1));

        btn_o.setText("O");
        jPanel3.add(btn_o, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 40, 30));

        btn_p.setText("P");
        jPanel3.add(btn_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 40, 40));

        btn_g.setText("G");
        jPanel3.add(btn_g, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 50, -1));

        btn_s.setText("S");
        jPanel3.add(btn_s, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 50, -1));

        btn_t.setText("T");
        jPanel3.add(btn_t, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 50, -1));

        btn_u.setText("U");
        jPanel3.add(btn_u, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, 50, -1));

        btn_y.setText("Y");
        jPanel3.add(btn_y, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, 50, -1));

        btn_z.setText("Z");
        jPanel3.add(btn_z, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 320, 40, -1));

        btn_x.setText("X");
        jPanel3.add(btn_x, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 360, 30, 30));

        btn_w.setText("W");
        jPanel3.add(btn_w, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 423, 40, 20));

        btn_r.setText("R");
        jPanel3.add(btn_r, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 50, -1));

        soldierA.setText("jLabel10");
        jPanel3.add(soldierA, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, -1, -1));

        soldierB.setText("jLabel10");
        jPanel3.add(soldierB, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, -1, -1));

        soldierD.setText("jLabel10");
        jPanel3.add(soldierD, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, -1, -1));

        soldierE.setText("jLabel10");
        jPanel3.add(soldierE, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, -1, -1));

        soldierF.setText("jLabel10");
        jPanel3.add(soldierF, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, -1, -1));

        soldierG.setText("jLabel10");
        jPanel3.add(soldierG, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, -1, -1));

        soldierH.setText("jLabel10");
        jPanel3.add(soldierH, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, -1, -1));

        soldierI.setText("jLabel10");
        jPanel3.add(soldierI, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, -1, -1));

        soldierJ.setText("jLabel10");
        jPanel3.add(soldierJ, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 220, -1, -1));

        soldierK.setText("jLabel10");
        jPanel3.add(soldierK, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, -1, -1));

        soldierL.setText("jLabel10");
        jPanel3.add(soldierL, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, -1, -1));

        soldierM.setText("jLabel10");
        jPanel3.add(soldierM, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, -1, -1));

        soldierN.setText("jLabel10");
        jPanel3.add(soldierN, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 50, 20));

        soldierO.setText("jLabel10");
        jPanel3.add(soldierO, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, -1, -1));

        soldierP.setText("jLabel10");
        jPanel3.add(soldierP, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, -1, -1));

        soldierR.setText("jLabel10");
        jPanel3.add(soldierR, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, -1, -1));

        soldierS.setText("jLabel10");
        jPanel3.add(soldierS, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, -1, -1));

        soldierU.setText("jLabel10");
        jPanel3.add(soldierU, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 430, -1, -1));

        soldierY.setText("jLabel10");
        jPanel3.add(soldierY, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 340, -1, -1));

        soldierW.setText("jLabel10");
        jPanel3.add(soldierW, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 460, -1, -1));

        soldierX.setText("jLabel10");
        jPanel3.add(soldierX, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 350, -1, -1));

        soldierT.setText("jLabel10");
        jPanel3.add(soldierT, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, -1, -1));

        soldierZ.setText("jLabel10");
        jPanel3.add(soldierZ, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frame/RiskGame_map.png"))); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 750, 644));

        jLabel1.setText("YOUR ID");

        txt_userID.setEditable(false);
        txt_userID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_userIDActionPerformed(evt);
            }
        });

        jLabel3.setText("DESTINATION");

        jScrollPane2.setViewportView(lst_sendSoldierTerritories);

        txt_isTurn.setText("jLabel4");

        jLabel4.setText("SEND SOLDIER");

        btn_sendSoldier.setText("SEND SOLDIER");
        btn_sendSoldier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sendSoldierActionPerformed(evt);
            }
        });

        jLabel5.setText("SOURCE");

        jScrollPane3.setViewportView(lst_sendSoldierSourceTerritory);

        jLabel6.setText("DESTINATION");

        jScrollPane4.setViewportView(lst_attackSource);

        lst_attackDest.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lst_attackDestValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(lst_attackDest);

        jLabel7.setText("SOURCE");

        jLabel8.setText("ATTACK MENU");

        btn_attack.setText("ATTACK");
        btn_attack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_attackActionPerformed(evt);
            }
        });

        jLabel9.setText("SOLDIER COUNT");

        txt_soldierCount.setEditable(false);

        jLabel10.setText("ASSIGN SOLDIER");

        txt_assignSoldier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_assignSoldierActionPerformed(evt);
            }
        });

        btn_assignSoldier.setText("ASSIGN SOLDIER");
        btn_assignSoldier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_assignSoldierActionPerformed(evt);
            }
        });

        jScrollPane6.setViewportView(lst_assignSoldier);

        jLabel11.setText("DESTINATION");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_sendSoldierCount))
                                            .addComponent(btn_sendSoldier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(32, 32, 32)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel5)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(19, 19, 19))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(38, 38, 38)
                                        .addComponent(txt_userID, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(jLabel6))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addComponent(jLabel8)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addComponent(btn_attack, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(93, 93, 93)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGap(20, 20, 20)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(30, 30, 30))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_assignSoldier, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txt_assignSoldier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txt_soldierCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_isTurn, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_isTurn)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_userID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txt_sendSoldierCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_sendSoldier))
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane2))
                        .addGap(38, 38, 38)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane4)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(69, 69, 69)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txt_soldierCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txt_assignSoldier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_assignSoldier))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(btn_attack)
                                .addGap(54, 54, 54)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(503, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_userIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_userIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_userIDActionPerformed

    private void btn_sendSoldierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sendSoldierActionPerformed
        // TODO add your handling code here:

        if (!lst_sendSoldierTerritories.isSelectionEmpty() && !lst_sendSoldierSourceTerritory.isSelectionEmpty()) {
            int soldierCount = Integer.parseInt(txt_sendSoldierCount.getText());
            String name = lst_sendSoldierTerritories.getSelectedValue();
            Territory t = null;

            System.out.println("NAME : " + name);
            for (Territory territory : this.client.getGame().getTerritories()) {
                if (territory.getName().equals(name)) {
                    t = territory;
                    break;
                }
            }
            System.out.println("T BURDA " + t.getName());
            Territory source = null;
            name = lst_sendSoldierSourceTerritory.getSelectedValue();
            for (Territory territory : this.client.getGame().getTerritories()) {
                if (territory.getName().equals(name)) {
                    source = territory;
                    break;
                }
            }

            System.out.println("KONTROL :: " + soldierCount);

            try {
                this.client.MessageTypeSendSoldierTerritory(t, source, soldierCount);
            } catch (IOException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "SELECT A TERRITORY");
        }


    }//GEN-LAST:event_btn_sendSoldierActionPerformed

    private void btn_attackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_attackActionPerformed
        // TODO add your handling code here:

        if (!lst_attackDest.isSelectionEmpty() && !lst_attackSource.isSelectionEmpty()) {
            String name = lst_attackSource.getSelectedValue();
            Territory source = null;
            for (Territory territory : this.client.getGame().getTerritories()) {
                if (territory.getName().equals(name)) {
                    source = territory;
                    break;
                }
            }

            name = lst_attackSource.getSelectedValue();
            Territory dest = null;
            for (Territory territory : this.client.getGame().getTerritories()) {
                if (territory.getName().equals(name)) {
                    dest = territory;
                    break;
                }
            }

            //this.client.mess
        } else {
            JOptionPane.showMessageDialog(rootPane, "Choose territories!");
        }

    }//GEN-LAST:event_btn_attackActionPerformed

    private void lst_attackDestValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lst_attackDestValueChanged
        // TODO add your handling code here:
        String element = lst_attackSource.getSelectedValue();

        Player p = null;
        for (Player player : this.client.getGame().getPlayers()) {
            if (player.getPlayerID() == this.client.getClientId()) {
                p = player;
                break;
            }
        }

        attackDestTerritories.removeAllElements();

        Territory t = null;

        for (Territory territory : this.client.getGame().getTerritories()) {
            if (territory.getName().equals(element)) {
                t = territory;
                break;
            }
        }

        for (Territory territory : p.getTerritories()) {
            if (t.getNeighbors().contains(territory)) {
                attackDestTerritories.addElement(territory.getName());
            }
        }

    }//GEN-LAST:event_lst_attackDestValueChanged

    private void btn_aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_aActionPerformed

    private void btn_assignSoldierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_assignSoldierActionPerformed
        // TODO add your handling code here:
        int assign = Integer.parseInt(txt_assignSoldier.getText());

        if (!lst_assignSoldier.isSelectionEmpty()) {
            String name = lst_assignSoldier.getSelectedValue();
            Territory t = null;

            for (Territory territory : this.client.getGame().getTerritories()) {
                if (territory.getName().equals(name)) {
                    t = territory;
                    break;
                }
            }

            Player p = null;
            for (Player player : this.client.getGame().getPlayers()) {
                if (player.getPlayerID() == client.getClientId()) {
                    p = player;
                    break;
                }
            }

            try {
                this.client.MessageTypeSendAssignSoldier(t, p, assign);
            } catch (IOException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btn_assignSoldierActionPerformed

    private void txt_assignSoldierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_assignSoldierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_assignSoldierActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_a;
    private javax.swing.JButton btn_assignSoldier;
    private javax.swing.JButton btn_attack;
    private javax.swing.JButton btn_b;
    private javax.swing.JButton btn_d;
    private javax.swing.JButton btn_e;
    private javax.swing.JButton btn_f;
    private javax.swing.JButton btn_g;
    private javax.swing.JButton btn_h;
    private javax.swing.JButton btn_i;
    private javax.swing.JButton btn_j;
    private javax.swing.JButton btn_k;
    private javax.swing.JButton btn_l;
    private javax.swing.JButton btn_m;
    private javax.swing.JButton btn_n;
    private javax.swing.JButton btn_o;
    private javax.swing.JButton btn_p;
    private javax.swing.JButton btn_r;
    private javax.swing.JButton btn_s;
    private javax.swing.JButton btn_sendSoldier;
    private javax.swing.JButton btn_t;
    private javax.swing.JButton btn_u;
    private javax.swing.JButton btn_w;
    private javax.swing.JButton btn_x;
    private javax.swing.JButton btn_y;
    private javax.swing.JButton btn_z;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JList<String> lst_assignSoldier;
    private javax.swing.JList<String> lst_attackDest;
    private javax.swing.JList<String> lst_attackSource;
    private javax.swing.JList<String> lst_messages;
    private javax.swing.JList<String> lst_sendSoldierSourceTerritory;
    private javax.swing.JList<String> lst_sendSoldierTerritories;
    private javax.swing.JLabel soldierA;
    private javax.swing.JLabel soldierB;
    private javax.swing.JLabel soldierD;
    private javax.swing.JLabel soldierE;
    private javax.swing.JLabel soldierF;
    private javax.swing.JLabel soldierG;
    private javax.swing.JLabel soldierH;
    private javax.swing.JLabel soldierI;
    private javax.swing.JLabel soldierJ;
    private javax.swing.JLabel soldierK;
    private javax.swing.JLabel soldierL;
    private javax.swing.JLabel soldierM;
    private javax.swing.JLabel soldierN;
    private javax.swing.JLabel soldierO;
    private javax.swing.JLabel soldierP;
    private javax.swing.JLabel soldierR;
    private javax.swing.JLabel soldierS;
    private javax.swing.JLabel soldierT;
    private javax.swing.JLabel soldierU;
    private javax.swing.JLabel soldierW;
    private javax.swing.JLabel soldierX;
    private javax.swing.JLabel soldierY;
    private javax.swing.JLabel soldierZ;
    private javax.swing.JTextField txt_assignSoldier;
    private javax.swing.JLabel txt_isTurn;
    private javax.swing.JTextField txt_sendSoldierCount;
    private javax.swing.JTextField txt_soldierCount;
    private javax.swing.JTextField txt_userID;
    // End of variables declaration//GEN-END:variables
}
