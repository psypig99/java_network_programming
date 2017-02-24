package edu.kosta.chatSample.client.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import edu.kosta.chatSample.core.message.Message;
import edu.kosta.chatSample.client.room.ClientChatRoom;
import edu.kosta.chatSample.core.message.MessageType;

public class ChatClientDialog extends JFrame implements ChatClientView {

    private static final long serialVersionUID = 5666216361168574776L;
    
    private String sender;
    private ClientChatRoom chatRoom;
    private DefaultListModel<String> participantListModel;
    private JTextArea messageArea;
    private JButton inputButton;
    private JTextArea inputArea;
    private JList<String> userListBox;
    
    public ChatClientDialog(String sender) {
        //
        this.sender = sender;
        
        setTitle("NamooChat");
        setPreferredSize(new Dimension(500, 550));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createMainPanel();
        
        pack();
    }
    
    public void createMainPanel() {
        //
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY);
        
        GridBagConstraints grid = new GridBagConstraints();
        grid.insets = new Insets(10, 10, 10, 10);
        
        JPanel panel = new JPanel(new GridBagLayout());
        add(panel);
        
        // 타이틀 영역
        JLabel titleLabel = new JLabel("Namoo Chat for Group");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 25));
        
        grid.fill = GridBagConstraints.VERTICAL;
        grid.weightx = 2;
        panel.add(titleLabel, grid);
        
        // 채팅 영역
        JLabel chatLabel = new JLabel("대화창");
        
        grid.fill = GridBagConstraints.BOTH;
        grid.weightx = 1;
        grid.gridx = 0;
        grid.gridy = 1;
        panel.add(chatLabel, grid);
        
        messageArea = new JTextArea(20, 20);
        messageArea.setEditable(false);
        JScrollPane chatPane = new JScrollPane(messageArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        grid.gridx = 0;
        grid.gridy = 2;
        panel.add(chatPane, grid);
        
        // 접속자 목록 영역
        JLabel userListLabel = new JLabel("접속자");
        grid.gridx = 1;
        grid.gridy = 1;
        panel.add(userListLabel, grid);
        
        this.participantListModel = new DefaultListModel<String>();
        userListBox = new JList<String>(participantListModel);
        userListBox.setBorder(border);
        grid.gridx = 1;
        grid.gridy = 2;
        panel.add(userListBox, grid);
        
        // 채팅 영역이랑 입력 영역 여백 넣기 위해 사용
        grid.insets = new Insets(10, 10, 10, 10);
        
        // 입력창 영역
        inputArea = new JTextArea(3, 20);
        inputArea.setBorder(border);
        
        // TODO: 입력창 세로 크기 고정
        grid.gridx = 0;
        grid.gridy = 3;
        panel.add(inputArea, grid);
        
        // 입력버튼 영역
        inputButton = new JButton("메시지 보내기");
        grid.gridx = 1;
        grid.gridy = 3;
        
        panel.add(inputButton, grid);
    }

    @Override
    public void bindEventAndShow(final ClientChatRoom chatRoom) {
        // 
        this.chatRoom = chatRoom;
        
        inputButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = inputArea.getText();
                
                if(message != null && message.length() > 0) {
                    ChatClientDialog.this.chatRoom.sendMessage(MessageType.SimpleMessage, message);
                    inputArea.setText("");
                }
            }
        });
        
        userListBox.addMouseListener(new MouseAdapter() {
            //
            @Override
            public void mouseClicked(MouseEvent e) {
                //
                if (e.getClickCount() == 2) { // double click
                    String selectUser = userListBox.getSelectedValue();
                    
                    if (!sender.equals(selectUser)) {
                        
                        String popupMessage = selectUser + "님에게 보낼 귓속말을 입력하세요.";
                        String secretMessage = (String) JOptionPane.showInputDialog(
                                ChatClientDialog.this, 
                                popupMessage, 
                                "비밀메시지", 
                                JOptionPane.INFORMATION_MESSAGE);
                        
                        if (secretMessage != null && secretMessage.length() > 0) {
                            Message message = new Message(MessageType.SecretMessage, secretMessage);
                            message.setReceiver(selectUser);
                            message.setSender(sender);
                            message.setContents(secretMessage);
                            
                            ChatClientDialog.this.chatRoom.sendMessage(message);
                        }
                    }
                    
                }
            }
        });
        
        
        setVisible(true);
    }

    @Override
    public void addParticipant(String name) {
        // 
        if (!participantListModel.contains(name)) {
            participantListModel.addElement(name);
        }
    }

    @Override
    public void removeParticipant(String name) {
        // 
        participantListModel.removeElement(name);
    }
    
    @Override
    public void refreshParticipants(String[] participants) {
        //
        participantListModel.clear();
        for (String participant : participants) {
            participantListModel.addElement(participant);
        }
    }

    @Override
    public void appendMessage(final String contents) {
        //
        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                messageArea.append(contents);
            }
        });
    }
    
    @Override
    public void showMessagePopup(String title, String message) {
        // 
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
