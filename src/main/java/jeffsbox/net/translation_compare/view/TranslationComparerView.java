package jeffsbox.net.translation_compare.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.JSplitPane;
import java.awt.GridLayout;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JTabbedPane;
import javax.swing.JTable;

import jeffsbox.net.translation_compare.FileParser;
import jeffsbox.net.translation_compare.KeyValueDiff;
import jeffsbox.net.translation_compare.TranslationComparer;

public class TranslationComparerView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6310852927293970205L;
	private JPanel contentPane;
	private JTextField textFieldFileAPath;
	private JTextField textFieldFileBPath;
	
	private TranslationComparer comparer = null;
	private JTabbedPane tabbedPaneReports;
	private Map<String,String> aKeyValues;
	private Map<String,String> bKeyValues;
	
	private final JFileChooser fileChooser = new JFileChooser();
	
	private final JFrame thisFrame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TranslationComparerView frame = new TranslationComparerView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TranslationComparerView() {
		super("Translation Comparer");
		
		thisFrame = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 683);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panelFilePaths = new JPanel();
		splitPane.setLeftComponent(panelFilePaths);
		GridBagLayout gbl_panelFilePaths = new GridBagLayout();
		gbl_panelFilePaths.columnWidths = new int[]{256, 256, 256, 0};
		gbl_panelFilePaths.rowHeights = new int[]{25, 0, 0, 0};
		gbl_panelFilePaths.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelFilePaths.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelFilePaths.setLayout(gbl_panelFilePaths);
		
		JLabel lblFileAPath = new JLabel("File A:");
		GridBagConstraints gbc_lblFileAPath = new GridBagConstraints();
		gbc_lblFileAPath.anchor = GridBagConstraints.EAST;
		gbc_lblFileAPath.insets = new Insets(0, 0, 5, 5);
		gbc_lblFileAPath.gridx = 0;
		gbc_lblFileAPath.gridy = 0;
		panelFilePaths.add(lblFileAPath, gbc_lblFileAPath);
		
		textFieldFileAPath = new JTextField();
		GridBagConstraints gbc_textFieldFileAPath = new GridBagConstraints();
		gbc_textFieldFileAPath.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFileAPath.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFileAPath.gridx = 1;
		gbc_textFieldFileAPath.gridy = 0;
		panelFilePaths.add(textFieldFileAPath, gbc_textFieldFileAPath);
		textFieldFileAPath.setColumns(10);
		
		JButton btnBrowseFileAPath = new JButton("Browse");
		btnBrowseFileAPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.showOpenDialog(thisFrame);
				textFieldFileAPath.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}
		});
		GridBagConstraints gbc_btnBrowseFileAPath = new GridBagConstraints();
		gbc_btnBrowseFileAPath.insets = new Insets(0, 0, 5, 0);
		gbc_btnBrowseFileAPath.gridx = 2;
		gbc_btnBrowseFileAPath.gridy = 0;
		panelFilePaths.add(btnBrowseFileAPath, gbc_btnBrowseFileAPath);
		
		JLabel lblFileBPath = new JLabel("File B:");
		GridBagConstraints gbc_lblFileBPath = new GridBagConstraints();
		gbc_lblFileBPath.anchor = GridBagConstraints.EAST;
		gbc_lblFileBPath.insets = new Insets(0, 0, 5, 5);
		gbc_lblFileBPath.gridx = 0;
		gbc_lblFileBPath.gridy = 1;
		panelFilePaths.add(lblFileBPath, gbc_lblFileBPath);
		
		textFieldFileBPath = new JTextField();
		GridBagConstraints gbc_textFieldFileBPath = new GridBagConstraints();
		gbc_textFieldFileBPath.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFileBPath.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFileBPath.gridx = 1;
		gbc_textFieldFileBPath.gridy = 1;
		panelFilePaths.add(textFieldFileBPath, gbc_textFieldFileBPath);
		textFieldFileBPath.setColumns(10);
		
		JButton btnBrowseFileBPath = new JButton("Browse");
		btnBrowseFileBPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.showOpenDialog(thisFrame);
				textFieldFileBPath.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}
		});
		GridBagConstraints gbc_btnBrowseFileBPath = new GridBagConstraints();
		gbc_btnBrowseFileBPath.insets = new Insets(0, 0, 5, 0);
		gbc_btnBrowseFileBPath.gridx = 2;
		gbc_btnBrowseFileBPath.gridy = 1;
		panelFilePaths.add(btnBrowseFileBPath, gbc_btnBrowseFileBPath);
		
		JButton btnCompareFiles = new JButton("Compare");
		btnCompareFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateTables();
			}
		});
		GridBagConstraints gbc_btnCompareFiles = new GridBagConstraints();
		gbc_btnCompareFiles.insets = new Insets(0, 0, 0, 5);
		gbc_btnCompareFiles.gridx = 1;
		gbc_btnCompareFiles.gridy = 2;
		panelFilePaths.add(btnCompareFiles, gbc_btnCompareFiles);
		
		JPanel panelReports = new JPanel();
		splitPane.setRightComponent(panelReports);
		panelReports.setLayout(new GridLayout(0, 1, 0, 0));
		
		tabbedPaneReports = new JTabbedPane();
		panelReports.add(tabbedPaneReports);

	}
	
	private boolean parseFiles() {
		String aPathStr = textFieldFileAPath.getText();
		String bPathStr = textFieldFileBPath.getText();
		
		try {
			aKeyValues = FileParser.getKeyValues(aPathStr);
			bKeyValues = FileParser.getKeyValues(bPathStr);
			comparer = new TranslationComparer(aKeyValues, bKeyValues);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error reading the files", "Error", JOptionPane.ERROR);
			return false;
		}
		
		return true;
	}
	
	
	private void populateTables() {
		tabbedPaneReports.removeAll();
		
		parseFiles();
		populateUniqueTable("Unique Keys (A)", comparer.getUniqueKeysToA(), aKeyValues);
		populateUniqueTable("Unique Keys (B)", comparer.getUniqueKeysToB(), bKeyValues);
		populateCommonKeyDiffTable();
	}

	private void populateUniqueTable(String tabName, Set<String> uniqueKeys, Map<String,String> keyValues) {
		// Add header columns
		String[] columnNames = new String[]{"Key", "Value"};
		
		// Get row data
		List<String> uniqueKeysList = new ArrayList<>(uniqueKeys);
		Collections.sort(uniqueKeysList);
		
		String[][] rowData = new String[uniqueKeys.size()][2];
		
		int i = 0;
		for(String key : uniqueKeys) {
			rowData[i][0] = key;
			rowData[i][1] = keyValues.get(key);
			i++;
		}
		
		JTable tableUniqueKeys = new JTable(rowData, columnNames);
		JScrollPane panelUniqueKeys = new JScrollPane(tableUniqueKeys);		
		
		tabbedPaneReports.addTab(tabName, null, panelUniqueKeys, null);		
	}
	
	private void populateCommonKeyDiffTable() {
		// Add header columns
		String[] columnNames = new String[]{"Key", "A Value", "B Value"};

		// Get row data
		Map<String,KeyValueDiff> diffs = comparer.getCommonKeyValueDiffs();
		List<String> keysList = new ArrayList<>(diffs.keySet());
		Collections.sort(keysList);
		
		String[][] rowData = new String[keysList.size()][3];
		
		int i = 0;
		for(String key : keysList) {
			rowData[i][0] = key;
			
			KeyValueDiff diff = diffs.get(key);
			rowData[i][1] = diff.getValueA();
			rowData[i][2] = diff.getValueB();
			i++;
		}
		
		
		JTable table = new JTable(rowData, columnNames);
		JScrollPane panel = new JScrollPane(table);		
		
		tabbedPaneReports.addTab("Common Key Differences", null, panel, null);				
	}
}
