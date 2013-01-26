package com.william.demo.jdon.jserver.application.chat.client.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.william.demo.jdon.jserver.application.chat.client.action.MessageAction;

public class ChatPanel extends JPanel {



  JLabel sendLabel = new JLabel();
  JTextArea sendValue = new JTextArea();
  JButton connectButton = new JButton();

  public ChatPanel() {
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {

    sendLabel.setText("发送内容");
    sendLabel.setBounds(new Rectangle(20, 25, 68, 19));
    this.setLayout(null);
    sendValue.setText("请输入聊天内容");
    sendValue.setTabSize(8);
    sendValue.setBounds(new Rectangle(99, 19, 174, 105));
    connectButton.setBounds(new Rectangle(100, 131, 83, 29));
    connectButton.addActionListener(new ChatPanel_connectButton_actionAdapter(this));
    connectButton.setText("发送");
    this.add(sendValue, null);
    this.add(sendLabel, null);
    this.add(connectButton, null);


  }

  void connectButton_actionPerformed(ActionEvent e){
        MessageAction.send(sendValue.getText());
  }


  class ChatPanel_connectButton_actionAdapter implements
      ActionListener {
    ChatPanel adaptee;

    ChatPanel_connectButton_actionAdapter(ChatPanel adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.connectButton_actionPerformed(e);
    }
  }


}