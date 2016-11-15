import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.border.Border;


public class GameOptionsPage extends Page implements ActionListener {
	  GridLayout layout = new GridLayout(2,2);
	  JButton playButton;
	  JComboBox defaultSets, customSets;
	  JRadioButton easyLevel, hardLevel;
	     
	    public GameOptionsPage(String name) {
	        super(name);
	    }
	    
	    @Override
	    public void drawPage(final Container pane) {
	    	
	    	panel.setLayout(layout);
	    	
	    	//Set up sub-panels
	        JPanel defaultSetPanel = new JPanel(new GridLayout(1,1));
	        JPanel customSetPanel = new JPanel(new GridLayout(1,1));
	        JPanel levelPanel = new JPanel(new GridLayout(2,2));
	        JPanel radioButtonPanel = new JPanel(new GridLayout(0,2));
	        radioButtonPanel.add(levelPanel);
	        panel.add(defaultSetPanel);
	        panel.add(radioButtonPanel);
	        panel.add(customSetPanel);

	         
	        //Set up components preferred size
	        JButton b = new JButton("Just fake button");
	        Dimension buttonSize = b.getPreferredSize();
	        panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 5.5),
	                (int)(buttonSize.getHeight() * 6.5) * 2));
	         
	        //Add Default Set stuff
	        defaultSetPanel.add(new JLabel("Default Sets"));
	        String[] defaultSetList = {"2s Times Tables", "3s Times Tables"};
	        defaultSets = new JComboBox(defaultSetList);
	        defaultSetPanel.add(defaultSets);
	        
	        //Add Custom Set stuff
	        customSetPanel.add(new JLabel("Custon Sets"));
	        String[] customSetList = {"Jenny's List", "Cam's List", "Jace's List", "Noodles"};
	        customSets = new JComboBox(customSetList);
	        customSetPanel.add(customSets);
	        
	        //Add Level stuff 
	        radioButtonPanel.add(new JLabel("Select a Level:"));
	        easyLevel = new JRadioButton("Easy");
	        hardLevel = new JRadioButton("Hard");
	        levelPanel.add(easyLevel);
	        levelPanel.add(hardLevel);
	        
	        //Add Play Button stuff
	        JButton playButton = new JButton("Play Game!");
	        playButton.addActionListener(this);
	        panel.add(playButton);

	        //Display to the pane
	        pane.add(panel, BorderLayout.CENTER);
	        pane.add(new JSeparator(), BorderLayout.NORTH);
	}
	    
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == playButton){
			PlayGamePage playGamePage = new PlayGamePage("Studysaurus");
			createAndShowGUI(playGamePage);
		}
		else if (obj == defaultSets){
			//set Set
		}
		else if (obj == customSets){
			//set Set
		}
		else if (obj == easyLevel){
			//set Level
		}
		else if (obj == hardLevel){
			//set Level
		}
	}


}
