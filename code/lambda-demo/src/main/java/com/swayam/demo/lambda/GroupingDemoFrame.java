package com.swayam.demo.lambda;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

import org.jdesktop.swingx.JXTreeTable;

/**
 *
 * @author paawak
 */
public class GroupingDemoFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	private final BankDetailService bankDetailService;

	/**
	 * Creates new form GroupingDemoFrame
	 */
	public GroupingDemoFrame() {

		if (false) {
			bankDetailService = new BankDetailServiceImplJava8();
		} else {
			bankDetailService = new BankDetailServiceImplPreJava8();
		}

		initComponents();
		attachListeners();
	}

	private void attachListeners() {

		btSubmit.addActionListener((ActionEvent e) -> {
			BankDetailGroups selectedGroup;
			if (rdBtEducation.isSelected()) {
				selectedGroup = BankDetailGroups.EDUCATION;
			} else if (rdBtMaritalStatus.isSelected()) {
				selectedGroup = BankDetailGroups.MARITAL_STATUS;
			} else {
				selectedGroup = BankDetailGroups.JOB;
			}

			Map<String, List<BankDetail>> groupedBankDetails = bankDetailService
					.getBankDetails(selectedGroup);

			tblBankDetails.setTreeTableModel(new BankDetailTreeTableModel(
					groupedBankDetails));

		});

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		btGrpBankDetails = new javax.swing.ButtonGroup();
		jLabel1 = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		rdBtJob = new javax.swing.JRadioButton();
		rdBtMaritalStatus = new javax.swing.JRadioButton();
		rdBtEducation = new javax.swing.JRadioButton();
		btSubmit = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		tblBankDetails = new JXTreeTable();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Grouping Demo");
		setPreferredSize(new java.awt.Dimension(800, 550));

		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Grouping Demo");

		jPanel1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Group By"));

		btGrpBankDetails.add(rdBtJob);
		rdBtJob.setSelected(true);
		rdBtJob.setText("Job");

		btGrpBankDetails.add(rdBtMaritalStatus);
		rdBtMaritalStatus.setText("Marital Status");

		btGrpBankDetails.add(rdBtEducation);
		rdBtEducation.setText("Education");

		btSubmit.setText("Submit");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								rdBtJob)
																						.addComponent(
																								rdBtMaritalStatus)
																						.addComponent(
																								rdBtEducation)))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(42,
																				42,
																				42)
																		.addComponent(
																				btSubmit)))
										.addContainerGap(11, Short.MAX_VALUE)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(rdBtJob)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(rdBtMaritalStatus)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(rdBtEducation)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												9, Short.MAX_VALUE)
										.addComponent(btSubmit)
										.addContainerGap()));

		// tblBankDetails.setModel(new javax.swing.table.DefaultTableModel(
		// new Object[][] {
		//
		// }, new String[] {
		//
		// }));
		jScrollPane1.setViewportView(tblBankDetails);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jPanel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										709, Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel1)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										249, Short.MAX_VALUE).addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(
					GroupingDemoFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(
					GroupingDemoFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(
					GroupingDemoFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(
					GroupingDemoFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(() -> {
			new GroupingDemoFrame().setVisible(true);
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.ButtonGroup btGrpBankDetails;
	private javax.swing.JButton btSubmit;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JRadioButton rdBtEducation;
	private javax.swing.JRadioButton rdBtJob;
	private javax.swing.JRadioButton rdBtMaritalStatus;
	private JXTreeTable tblBankDetails;
	// End of variables declaration//GEN-END:variables
}
