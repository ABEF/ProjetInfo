package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import timeTableController.TimeTableController;
import userController.UserController;
import view.MainFrame;

public class MyMainFrame extends MainFrame implements ActionListener {
    private static final long serialVersionUID = -7588636915678812051L;
    public MyMainFrame(UserController arg0, TimeTableController arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
        JMenuBar menuBar=this.getJMenuBar();
        JMenu menu;
        JMenuItem itemMenu;
        menu = new JMenu("Test");
        menu.setMnemonic(KeyEvent.VK_T);
        itemMenu = new JMenuItem("itemTest",KeyEvent.VK_M);
        itemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
        itemMenu.setActionCommand("itemTest");
        itemMenu.addActionListener(this);
        menu.add(itemMenu);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
        setSize(1200,1000);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent arg0) {
        super.actionPerformed(arg0);
        if(arg0.getActionCommand().compareTo("itemTest")==0){
            JOptionPane.showMessageDialog(this,"User Login ! = "+this.getUserLogin());
        }
    }
}
