package maze.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.awt.FlowLayout;

import javax.swing.JCheckBox;

import java.awt.GridLayout;

import javax.swing.JSlider;

import java.awt.Dialog;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import maze.gui.MainMenuPanel.Options;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class OptionsMenu extends JPanel {
	private JTextField rowSelection;
	private JTextField colSelection;
	private boolean changed;
	Options unsavedOptions;
	
	JSlider rowSlider;
	JSlider colSlider;
	JSpinner spinner;
	JCheckBox chckbxDragonMove;
	JCheckBox chckbxDragonSleep;
	JCheckBox chckbxDragonAttack;

	
	public static void main(String[] args ) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionsMenu frame = new OptionsMenu(new MainMenuPanel.Options());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	private void SaveOptions(Options options){
		options = unsavedOptions;
		System.out.println("Rows: " + options.getRows());
		System.out.println("Cols: " + options.getCols());
		System.out.println("NumberDragons: " + options.getNumberDragons());
		System.out.println("DMove: " + options.isDragonMove());
		System.out.println("DSleep: " + options.isDragonSleep());
		System.out.println("Dattack: " + options.isDragonAttack());


	}

	/**
	 * Create the frame.
	 */
	public OptionsMenu(final MainMenuPanel.Options options) {
		unsavedOptions = options.clone();
		changed = false;
		
		
		setBounds(100, 100, 329, 448);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel optionsPane = new JPanel();
		add(optionsPane);
		optionsPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel rowPanel = new JPanel();
		optionsPane.add(rowPanel);
		rowPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		JLabel Rows = new JLabel("Rows");
		Rows.setHorizontalAlignment(SwingConstants.CENTER);
		rowPanel.add(Rows);
		
		JPanel panel_1 = new JPanel();
		rowPanel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		this.rowSlider = new JSlider();
		panel_1.add(rowSlider);
		rowSlider.setPaintLabels(true);
		rowSlider.setMaximum(30);
		rowSlider.setMinimum(10);
		rowSlider.setValue(unsavedOptions.getRows());
		rowSlider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider source = (JSlider) arg0.getSource();
				unsavedOptions.setRows((Integer) source.getValue());
				rowSelection.setText(((Integer) source.getValue()).toString());
				changed = true;
			}
			
		});
		
		rowSelection = new JTextField();
		panel_1.add(rowSelection);
		rowSelection.setHorizontalAlignment(SwingConstants.CENTER);
		rowSelection.setEditable(false);
		rowSelection.setColumns(10);
		rowSelection.setText(((Integer)unsavedOptions.getRows()).toString());
		
				JPanel ColPanel = new JPanel();
				ColPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
				ColPanel.setAlignmentY(Component.TOP_ALIGNMENT);
				optionsPane.add(ColPanel);
				ColPanel.setLayout(new GridLayout(0, 1, 0, 0));
				
				JLabel lblCol = new JLabel("Columns");
				lblCol.setHorizontalAlignment(SwingConstants.CENTER);
				lblCol.setAlignmentY(Component.TOP_ALIGNMENT);
				ColPanel.add(lblCol);
				
				JPanel panel_2 = new JPanel();
				ColPanel.add(panel_2);
				panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
				
				this.colSlider = new JSlider();
				panel_2.add(colSlider);
				colSlider.setMaximum(30);
				colSlider.setMinimum(10);
				colSlider.setValue(unsavedOptions.getCols());
				colSlider.addChangeListener(new ChangeListener(){

					@Override
					public void stateChanged(ChangeEvent arg0) {
						JSlider source = (JSlider) arg0.getSource();
						
						unsavedOptions.setCols( ((Integer)source.getValue()).intValue() );
						colSelection.setText(((Integer) source.getValue()).toString());
						changed = true;
					}
					
				});
				colSelection = new JTextField();
				colSelection.setHorizontalAlignment(SwingConstants.CENTER);
				panel_2.add(colSelection);
				colSelection.setEditable(false);
				colSelection.setText(((Integer)unsavedOptions.getCols()).toString());
				colSelection.setColumns(10);
				
				JPanel panel_3 = new JPanel();
				optionsPane.add(panel_3);
				panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				JLabel lblNumberOfDragons = new JLabel("Number of Dragons");
				lblNumberOfDragons.setEnabled(false);
				lblNumberOfDragons.setHorizontalAlignment(SwingConstants.CENTER);
				panel_3.add(lblNumberOfDragons);
				
				this.spinner = new JSpinner();
				//TODO Mudar o spinner para mudar o seu máximo consuante as rows e cols
				spinner.setModel(new SpinnerNumberModel(0, 0, ((unsavedOptions.getCols()-3)*(unsavedOptions.getRows()-3)/2), 1));
				spinner.setValue(unsavedOptions.getNumberDragons());
				spinner.addChangeListener(new ChangeListener(){

					@Override
					public void stateChanged(ChangeEvent arg0) {
						JSpinner source = (JSpinner) arg0.getSource();
						unsavedOptions.setNumberDragons(((Integer)source.getValue()).intValue());
						changed = true;				
					}
					
				});
				panel_3.add(spinner);
				
				JPanel panel = new JPanel();
				optionsPane.add(panel);
				panel.setLayout(new GridLayout(3, 0, 0, 0));
				
				this.chckbxDragonMove = new JCheckBox("Dragons can move");
				panel.add(chckbxDragonMove);
				chckbxDragonMove.setSelected(unsavedOptions.isDragonMove());
				chckbxDragonMove.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						JCheckBox source = (JCheckBox) arg0.getSource();
						unsavedOptions.setDragonMove(source.isSelected());
						changed = true;
					}
				});
				
				this.chckbxDragonSleep = new JCheckBox("Dragons can asleep");
				panel.add(chckbxDragonSleep);
				chckbxDragonSleep.setSelected(unsavedOptions.isDragonSleep());
				chckbxDragonSleep.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						JCheckBox source = (JCheckBox) arg0.getSource();
						unsavedOptions.setDragonSleep(source.isSelected());
						changed = true;
					}
				});
				
				this.chckbxDragonAttack = new JCheckBox("Dragons can attack");
				panel.add(chckbxDragonAttack);
				chckbxDragonAttack.setSelected(unsavedOptions.isDragonAttack());
				
				
				JPanel buttonPane = new JPanel();
				add(buttonPane);
				
				JButton btnSave = new JButton("Save");
				buttonPane.add(btnSave);
				btnSave.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						int option = JOptionPane.showConfirmDialog(null,"Warning!","Are you sure you want to save this options?" , JOptionPane.OK_CANCEL_OPTION);
						if(option == JOptionPane.YES_OPTION){
							SaveOptions(options);
						}
					}
					
				});
				
						
						JButton btnDefault = new JButton("Default");
						buttonPane.add(btnDefault);
						btnDefault.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								unsavedOptions = new Options();
								
								rowSlider.setValue(unsavedOptions.getRows());
								colSlider.setValue(unsavedOptions.getCols());
								spinner.setValue(unsavedOptions.getNumberDragons());
								chckbxDragonMove.setSelected(unsavedOptions.isDragonMove());
								chckbxDragonSleep.setSelected(unsavedOptions.isDragonSleep());
								chckbxDragonAttack.setSelected(unsavedOptions.isDragonAttack());

							}
							
						});
				chckbxDragonAttack.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						JCheckBox source = (JCheckBox) arg0.getSource();
						unsavedOptions.setDragonAttack(source.isSelected());
						changed = true;
					}
				});
		
	}
}