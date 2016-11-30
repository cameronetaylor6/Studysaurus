package studysaurus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;


public class GameOptionsPage extends Page implements ActionListener {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridLayout layout = new GridLayout(2,2);
	  JButton playButton;
	  JComboBox<String> defaultSets, customSets;
	  JRadioButton easyLevel, hardLevel;
	  GameClient gc = GameClient.getInstance();
	     
	    public GameOptionsPage(String name) {
	        super(name);
	    }
	    
	    @SuppressWarnings("unchecked")
		@Override
	    public void drawPage(final Container pane) {
	    	
	    	panel.setLayout(layout);
	    	
	    	//Set up sub-panels
	        JPanel defaultSetPanel = new JPanel(new GridLayout(1,1));
	        JPanel customSetPanel = new JPanel(new GridLayout(1,1));
	        JPanel radioButtonPanel = new JPanel(new GridLayout(0,2));
	        JPanel levelPanel = new JPanel(new GridLayout(2,2));
	        panel.add(defaultSetPanel);
	        panel.add(radioButtonPanel);
	        panel.add(customSetPanel);

	         
	        //Set up components preferred size
	        JButton b = new JButton("Just fake button");
	        Dimension buttonSize = b.getPreferredSize();
	        panel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 5.5),
	                (int)(buttonSize.getHeight() * 6.5) * 2));
	         
	        //TODO: Make it so that only one set can be selected
	        
	        //Add Default Set stuff
	        defaultSetPanel.add(new JLabel("Default Sets"));
	        DatabaseConnector dc = new DatabaseConnector();
	        ArrayList<String> defaultSetList = dc.getSets("default");
	        defaultSets = new JComboBox(defaultSetList.toArray());
	        defaultSetPanel.add(defaultSets);
	        
	        //Add Custom Set stuff
	        customSetPanel.add(new JLabel("Custom Sets"));
	        ArrayList<String> customSetList = dc.getSets("custom");
	        customSets = new JComboBox(customSetList.toArray());
	        customSetPanel.add(customSets);
	        
	        //Add Level stuff
	        //TODO: Make it so that only one can be selected
	        easyLevel = new JRadioButton("Easy");
	        hardLevel = new JRadioButton("Hard");
	        levelPanel.add(easyLevel);
	        levelPanel.add(hardLevel);
	        radioButtonPanel.add(new JLabel("Select a Level:"));
	        radioButtonPanel.add(levelPanel);

	        
	        //Add Play Button stuff
	        playButton = new JButton("Play Game!");
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
			this.dispose();
		}
		else if (obj == defaultSets){
			String selectedSetName = (String)defaultSets.getSelectedItem();
			//set Set
			//gc.setCurrentSet(selectedSet);
		}
		else if (obj == customSets){
			//set Set
		}
		else if (obj == easyLevel){
			//set Level
			state = 8;
			notifyObservers();
		}
		else if (obj == hardLevel){
			//set Level
			state = 4;
			notifyObservers();
		}
	}


}
