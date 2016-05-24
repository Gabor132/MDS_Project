package Interface;

import Networking.Client.ConnectionHandler;
import Console.ConsoleFrame;
import Networking.Server.Match;
import Networking.Server.Player;
import java.awt.Color;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;

/**
 *
 * @author Dragos-Alexandru
 */
public class MultiplayerCreateMatch extends javax.swing.JPanel {

    private final MainFrame rootFrame;
    
    private Match createdMatch;
    
    /**
     * Creates new form MultiplayerCreateMatch
     * @param rootFrame
     */
    public MultiplayerCreateMatch(MainFrame rootFrame) {
        initComponents();
        
        this.rootFrame = rootFrame;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createMatchLabel = new javax.swing.JLabel();
        serverNameLabel = new javax.swing.JLabel();
        serverNameField = new javax.swing.JTextField();
        serverPortLabel = new javax.swing.JLabel();
        serverPasswordLabel = new javax.swing.JLabel();
        serverPasswordField = new javax.swing.JTextField();
        createMatchButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        serverPortField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(600, 400));
        setMinimumSize(new java.awt.Dimension(600, 400));

        createMatchLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        createMatchLabel.setText("Create Match");

        serverNameLabel.setText("Server name:");

        serverPortLabel.setText("Server port:");

        serverPasswordLabel.setText("Server password:");

        serverPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverPasswordFieldActionPerformed(evt);
            }
        });

        createMatchButton.setBackground(new java.awt.Color(255, 255, 255));
        createMatchButton.setText("Create match");
        createMatchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createMatchButtonActionPerformed(evt);
            }
        });

        backButton.setBackground(new java.awt.Color(255, 255, 255));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(serverPasswordLabel)
                            .addComponent(serverPortLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(serverNameLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(serverNameField)
                            .addComponent(serverPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addComponent(serverPortField)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(createMatchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addComponent(createMatchLabel)))
                .addContainerGap(188, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(createMatchLabel)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverNameLabel)
                    .addComponent(serverNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverPortLabel)
                    .addComponent(serverPortField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverPasswordLabel)
                    .addComponent(serverPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addComponent(createMatchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void serverPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serverPasswordFieldActionPerformed

    private void createMatchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createMatchButtonActionPerformed
        if (serverNameField.getText().isEmpty() || serverPortField.getText().isEmpty()){
            ConsoleFrame.showError("Must give server name and port");
            if(serverNameField.getText().isEmpty()){
                serverNameLabel.setForeground(Color.red);
            }else{
                serverNameLabel.setForeground(Color.black);
            }
            if(serverPortField.getText().isEmpty()){
                serverPortLabel.setForeground(Color.red);
            }else{
                serverPortLabel.setForeground(Color.black);
            }
            return;
        }
        CreateServerWorker worker = new CreateServerWorker();
        
        try {
            worker.execute();
            if(worker.get()){
                if(rootFrame.localServerName == null){
                    rootFrame.localServerName = serverNameField.getText();
                }else{
                    ConsoleFrame.showError("Already opened a server");
                }
                rootFrame.changePanel(new MultiplayerMatchPanel(rootFrame, createdMatch));
            }
        } catch (InterruptedException | ExecutionException ex) {
            ConsoleFrame.sendMessage(this.getClass().getSimpleName(), "Failed to create match");
            ConsoleFrame.showError("Failed to create match");
        }
        
    }//GEN-LAST:event_createMatchButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        rootFrame.changePanel(new MultiplayerServerPanel(rootFrame));
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * This worker creates a local server and registers it with the master server
     */
    private class CreateServerWorker extends SwingWorker<Boolean, Object>{

            @Override
            protected Boolean doInBackground() throws Exception {
                createdMatch = new Match(serverNameField.getText(),
                        "localhost", Integer.parseInt(serverPortField.getText()),
                        Player.getInstance().getUsername(), 20);
                Boolean succes = ConnectionHandler.getInstance().hostMatch(createdMatch);
                
                return succes;
            }

    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton createMatchButton;
    private javax.swing.JLabel createMatchLabel;
    private javax.swing.JTextField serverNameField;
    private javax.swing.JLabel serverNameLabel;
    private javax.swing.JTextField serverPasswordField;
    private javax.swing.JLabel serverPasswordLabel;
    private javax.swing.JTextField serverPortField;
    private javax.swing.JLabel serverPortLabel;
    // End of variables declaration//GEN-END:variables
}