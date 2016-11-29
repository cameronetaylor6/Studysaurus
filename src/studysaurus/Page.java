package studysaurus;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public abstract class Page extends JFrame implements ActionListener, Subject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final JPanel panel = new JPanel(); 
	//private JLabel title;
    private GridLayout layout;
    private ArrayList<Observer> observers; 
    private Object state;
    
    public Page(String name) {
		super(name);
		setResizable(false);
	}
    
    abstract void drawPage(final Container pane);
     
    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    public static void createAndShowGUI(Page page) {
        //Create and set up the window.
        page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        page.drawPage(page.getContentPane());
        //Display the window.
        page.pack();
        page.setVisible(true);
    }
    
    @Override
    public void register(Observer obj) {
        if(obj == null) throw new NullPointerException("null observer - page");
    
        if(!observers.contains(obj)) observers.add(obj);
    }

    @Override
    public void unregister(Observer obj) {
        observers.remove(obj);
    }

    @Override
    public void notifyObservers() {     
        for (Observer obj : observers) {
            obj.update(this);
        }
    }

    @Override
    public Object getUpdate(Observer obj) {
        return state;
    }

    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HomePage page = new HomePage("Studysaurus");
                createAndShowGUI(page);
            }
        });
    }
}