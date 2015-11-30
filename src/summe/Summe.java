package summe;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

/**
 * Dies ist die einzige Klasse des Projektes Summe und generiert einerseits das Design und andererseits die Berechnung der Summen und Quadrate.
 * 
 * @author Lukas Schramm
 * @version 1.0
 * 
 */
public class Summe {
	
	private JFrame frame1 = new JFrame("Summe und Quadrate");
	private JLabel labelVon = new JLabel();
	private JLabel labelBis = new JLabel();
	private JLabel labelSumAusgabe = new JLabel();
	private NumberFormat format1 = NumberFormat.getInstance(); 
	private NumberFormat format2 = NumberFormat.getInstance();
	private NumberFormatter formatter1 = new NumberFormatter(format1);
    private NumberFormatter formatter2 = new NumberFormatter(format2);
	private JFormattedTextField feldZahlA = new JFormattedTextField(formatter1);
	private JFormattedTextField feldZahlB = new JFormattedTextField(formatter2);
	private JButton buttonRechnen = new JButton();
	private JList<String> quadratliste = new JList<String>();
	private DefaultListModel<String> quadratlisteModel = new DefaultListModel<String>();
	private JScrollPane quadratlisteScrollPane = new JScrollPane(quadratliste);

	public Summe() {
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setPreferredSize(new Dimension(400,300));
		frame1.setMinimumSize(new Dimension(400,300));
		frame1.setMaximumSize(new Dimension(600,450));
		frame1.setResizable(true);
		Container cp = frame1.getContentPane();
		cp.setLayout(new GridBagLayout());
		
		JPanel sumpanel = new JPanel();
		sumpanel.setLayout(new BorderLayout());
		JLabel jLabel1 = new JLabel();
		sumpanel.add(jLabel1,BorderLayout.NORTH);
		JPanel vonBis = new JPanel();
		vonBis.setLayout(new FlowLayout());
		vonBis.add(labelVon);
		vonBis.add(feldZahlA);
		vonBis.add(labelBis);
		vonBis.add(feldZahlB);
		JPanel zwisch1 = new JPanel();
		JPanel zwisch2 = new JPanel();
		zwisch1.setLayout(new BorderLayout());
		zwisch2.setLayout(new BorderLayout());
		zwisch1.add(vonBis,BorderLayout.NORTH);
		zwisch1.add(zwisch2,BorderLayout.CENTER);
		zwisch2.add(buttonRechnen,BorderLayout.NORTH);
		zwisch2.add(labelSumAusgabe,BorderLayout.CENTER);
		sumpanel.add(zwisch1,BorderLayout.CENTER);
		
		JPanel quadpanel = new JPanel();
		quadpanel.setLayout(new BorderLayout());
		JLabel jLabelQuadratzahltext = new JLabel("Quadratzahlen");
		quadpanel.add(jLabelQuadratzahltext,BorderLayout.NORTH);
		quadpanel.add(quadratlisteScrollPane,BorderLayout.CENTER);
		
	    labelVon.setText("Von");
	    feldZahlA.setText("100");
	    labelBis.setText("bis");
	    feldZahlB.setText("300");
	    buttonRechnen.setText("Berechne");
	    buttonRechnen.setMargin(new Insets(2, 2, 2, 2));
	    buttonRechnen.addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent evt) { 
	    		berechnen();
	    	}
	    });
	    labelSumAusgabe.setText("");
	    labelSumAusgabe.setHorizontalAlignment(SwingConstants.CENTER);
	    labelSumAusgabe.setVerticalAlignment(SwingConstants.NORTH);
	    quadratliste.setModel(quadratlisteModel);
	    
	    format1.setGroupingUsed(false);
	    format2.setGroupingUsed(false);
	    formatter1.setAllowsInvalid(false);
	    formatter2.setAllowsInvalid(false);
		
		frame1.add(sumpanel,new GridBagFelder(0,0,1,1,0.6,1));
	    frame1.add(quadpanel,new GridBagFelder(1,0,1,1,0.4,1));
	    sumpanel.setPreferredSize(new Dimension(0,0));
	    quadpanel.setPreferredSize(new Dimension(0,0));
		frame1.pack();
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
	}
	
	/**
	 * Diese Methode liest die Werte ein und addiert alle Zahlen im Intervall. Ausserdem gibt sie die Quadratzahlen aller Nummern in der Liste aus.
	 */
	private void berechnen() {
		try {
			int a = Integer.parseInt(feldZahlA.getText());
		    int b = Integer.parseInt(feldZahlB.getText());
		    if(a>b) {
		    	int temp = a;
		    	a = b;
		    	b = temp;
		    	feldZahlA.setText(String.valueOf(a));
		    	feldZahlB.setText(String.valueOf(b));
		    }
		    long summe = 0;
		    for(int i=a;i<=b;i++) {
		    	summe += i;
		    }
		    labelSumAusgabe.setText("Σ("+a+"–"+b+"): "+summe);
		    quadratliste.removeAll();
		    for(int i=a;i<=b;i++) {
				quadratlisteModel.addElement(i+"² = "+i*i);
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Du hast falsche Werte eingetragen."+System.getProperty("line.separator")+"Wenn Du dies nicht korrigierst"+System.getProperty("line.separator")+"bekommst Du kein Ergebnis!", "Falscheingabe", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		new Summe();
	}
}