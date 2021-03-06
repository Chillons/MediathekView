/*    
 *    MediathekView
 *    Copyright (C) 2008   W. Xaver
 *    W.Xaver[at]googlemail.com
 *    http://zdfmediathk.sourceforge.net/
 *    
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package mediathek.gui.dialog;

import com.jidesoft.utils.SystemInfo;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import mSearch.tool.Log;
import mediathek.config.Icons;
import mediathek.tool.EscBeenden;
import mediathek.tool.GuiFunktionen;
import mediathek.tool.MVMessageDialog;

public class DialogProgrammOrdnerOeffnen extends javax.swing.JDialog {

    public boolean ok = false;
    public String ziel;
    private Frame parentComponent = null;

    /**
     *
     * @param parent
     * @param modal
     * @param zziel
     * @param titel
     * @param text
     */
    public DialogProgrammOrdnerOeffnen(java.awt.Frame parent, boolean modal, String zziel, String titel, String text) {
        super(parent, modal);
        parentComponent = parent;
        initComponents();
        jButtonZiel.setIcon(Icons.ICON_BUTTON_FILE_OPEN);
         setTitle(titel);
        jTextArea1.setText(text);
        jButtonOk.addActionListener(new OkBeobachter());
        jButtonAbbrechen.addActionListener(new AbbrechenBeobachter());
        jButtonZiel.addActionListener(new ZielBeobachter());
        jTextFieldProgramm.setText(zziel);
        ziel = zziel;
        if (parent != null) {
            setLocationRelativeTo(parent);
        }
        new EscBeenden(this) {
            @Override
            public void beenden_() {
                ok = false;
                beenden();
            }
        };
    }

    private boolean check() {
        boolean ret = false;
        String programm = jTextFieldProgramm.getText();
        if (!programm.equals("")) {
            try {
                if (!new File(programm).exists()) {
                    MVMessageDialog.showMessageDialog(parentComponent, "Das Programm:  " + "\"" + programm + "\"" + "  existiert nicht!", "Fehler", JOptionPane.ERROR_MESSAGE);
                } else if (!new File(programm).canExecute()) {
                    MVMessageDialog.showMessageDialog(parentComponent, "Das Programm:  " + "\"" + programm + "\"" + "  kann nicht ausgeführt werden!", "Fehler", JOptionPane.ERROR_MESSAGE);
                } else {
                    ziel = programm;
                    ret = true;
                }
            } catch (Exception ignored) {
            }
        }
        return ret;
    }

    private void beenden() {
        this.dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        jButtonZiel = new javax.swing.JButton();
        jTextFieldProgramm = new javax.swing.JTextField();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        jButtonAbbrechen = new javax.swing.JButton();
        jButtonOk = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonZiel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/muster/button-file-open.png"))); // NOI18N
        jButtonZiel.setToolTipText("Programm auswählen");

        jLabel1.setText("Programm:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldProgramm, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonZiel)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldProgramm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonZiel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonZiel, jTextFieldProgramm});

        jButtonAbbrechen.setText("Abbrechen");

        jButtonOk.setText("Ok");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(4);
        jTextArea1.setText("\n Der Dateimanager zum Anzeigen des Speicherordners wird nicht gefunden.\n Dateimanager selbst auswählen.\n");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAbbrechen))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonAbbrechen, jButtonOk});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAbbrechen)
                    .addComponent(jButtonOk))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAbbrechen;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JButton jButtonZiel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldProgramm;
    // End of variables declaration//GEN-END:variables

    private class OkBeobachter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (check()) {
                ok = true;
                beenden();
            }
        }
    }

    private class AbbrechenBeobachter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ok = false;
            beenden();
        }
    }

    private class ZielBeobachter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //we can use native chooser on Mac...
            if (SystemInfo.isMacOSX()) {
                FileDialog chooser = new FileDialog(parentComponent, "Dateimanager suchen");
                chooser.setMode(FileDialog.LOAD);
                chooser.setVisible(true);
                if (chooser.getFile() != null) {
                    try {
                        File destination = new File(chooser.getDirectory() + chooser.getFile());
                        jTextFieldProgramm.setText(destination.getAbsolutePath());
                    } catch (Exception ex) {
                        Log.errorLog(398762109, ex);
                    }
                }
            } else {
                int returnVal;
                JFileChooser chooser = new JFileChooser();
                if (!jTextFieldProgramm.getText().equals("")) {
                    chooser.setCurrentDirectory(new File(jTextFieldProgramm.getText()));
                } else {
                    chooser.setCurrentDirectory(new File(GuiFunktionen.getHomePath()));
                }
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        jTextFieldProgramm.setText(chooser.getSelectedFile().getAbsolutePath());
                    } catch (Exception ex) {
                        Log.errorLog(107458930, ex);
                    }
                }
            }
        }
    }
}
