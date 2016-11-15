import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class HomePage extends Page implements ActionListener{
    private GridLayout layout = new GridLayout(4,0);
    JButton playButton, manageSetsButton, viewScoreButton, exitButton;

	public HomePage(String name) {
        super(name);
    }

	public void drawPage(final Container pane) {
        
        panel.setLayout(layout);
        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
        panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 5.5),
                (int)(buttonSize.getHeight() * 6.5) * 2));
         
        //Add buttons to experiment with Grid Layout
        playButton = new JButton("Play Game");
        playButton.addActionListener(this);
        manageSetsButton = new JButton("Manage Sets");
        manageSetsButton.addActionListener(this);
        viewScoreButton = new JButton("View Score");
        viewScoreButton.addActionListener(this);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        
        panel.add(playButton);
        panel.add(manageSetsButton);
        panel.add(viewScoreButton);
        panel.add(exitButton);
       
        pane.add(panel, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent e){
    	Object obj = e.getSource();
    	if(obj == playButton){
    		GameOptionsPage gameOptionsPage = new GameOptionsPage("GameOptionsPage");
    		createAndShowGUI(gameOptionsPage);
    	} 
    	else if(obj == manageSetsButton){
    		ManageSetsPage manageSetsPage = new ManageSetsPage("ManageSetsPage");
    		createAndShowGUI(manageSetsPage);
    	}
    	else if(obj == viewScoreButton){
    		ViewScorePage viewScorePage = new ViewScorePage("ViewScorePage");
    		createAndShowGUI(viewScorePage);
    	}
    	else if(obj == exitButton){
    		this.dispose();
    	}
    	this.dispose();
    }
}
