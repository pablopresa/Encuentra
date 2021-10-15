/*
 * VentanaVales.java
 *
 * Created on __DATE__, __TIME__
 */

package logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author  __USER__
 */
public class VentanaVales extends javax.swing.JFrame {

	/** Creates new form VentanaVales */
	public VentanaVales() {
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		label1 = new java.awt.Label();
		jTextField1 = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jLabel1 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		menuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		exitMenuItem = new javax.swing.JMenuItem();
		helpMenu = new javax.swing.JMenu();
		aboutMenuItem = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		label1.setText("Impresion de Vales");

		jTextField1.setText("Vales.csv");
		jTextField1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		jLabel1.setText("Resultados.");

		jButton1.setText("Procesar");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		fileMenu.setText("Archivo");

		exitMenuItem.setText("Salir");
		exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		helpMenu.setText("Help");

		aboutMenuItem.setText("About");
		helpMenu.add(aboutMenuItem);

		menuBar.add(helpMenu);

		setJMenuBar(menuBar);

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING)
																						.add(
																								jScrollPane1,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								738,
																								Short.MAX_VALUE)
																						.add(
																								layout
																										.createSequentialGroup()
																										.add(
																												jTextField1,
																												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																												196,
																												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												org.jdesktop.layout.LayoutStyle.UNRELATED)
																										.add(
																												jButton1))
																						.add(
																								jLabel1)))
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				313,
																				313,
																				313)
																		.add(
																				label1,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup().add(label1,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(31,
						31, 31).add(
						layout.createParallelGroup(
								org.jdesktop.layout.GroupLayout.BASELINE).add(
								jTextField1,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
								.add(jButton1)).add(9, 9, 9).add(jLabel1)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.UNRELATED).add(
								jScrollPane1,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								296, Short.MAX_VALUE).addContainerGap()));

		label1.getAccessibleContext().setAccessibleName("lblTitulo");

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		procesar();
	}

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
		System.exit(0);
	}//GEN-LAST:event_exitMenuItemActionPerformed

	private void procesar() {
		jTextArea1.append("Archivo Seleccionado" + "\n");
		jTextArea1.append(jTextField1.getText());
		jTextArea1
				.append("Cargando desde ubicacion: C:\\Program Files\\Reclamos"
						+ "\n");

		try {

			String path = "C:/Program Files/Reclamos/";
			FileReader fr = new FileReader(path + jTextField1.getText());
			BufferedReader bf = new BufferedReader(fr);
			String sCadena;
			String fecha = null;
			imprimir_Vale iv = new imprimir_Vale();
			int local = 0;
			while ((sCadena = bf.readLine()) != null) {

				String[] values = sCadena.split(";");
				boolean entroLinea = false;
				if (values.length > 0)

				{
					String pos0 = values[0];
					if (fecha == null) {
						if (pos0.contains("Fecha")) {
							fecha = pos0.substring(6);
						}
					} else {

						try {
							local = Integer.parseInt(pos0);
							iv.impresionEtiquetaUbica(null, fecha, values[2],values[3], Integer.parseInt(values[1]), "");

							jTextArea1.append("Imprimiendo Func." + values[1]
									+ values[2] + " " + values[3] + "\n");

						} catch (Exception e) {
							if (pos0.contains("Total")) {

								try {
									iv
											.impresionEtiquetaUbica(String
													.valueOf(local), "", "",
													" ", 0, "");
									jTextArea1.append("Corte del local"
											+ String.valueOf(local) + "\n");
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
									jTextArea1.append(e.getMessage());
								}
							}
						}

					}

				}

				System.out.println(sCadena);
			}

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			jTextArea1.append(fnfe.getMessage());
		} catch (IOException ioe) {
			ioe.printStackTrace();
			jTextArea1.append(ioe.getMessage());
		}

		jTextArea1.append("\n");
		jTextArea1.append("\n");
		jTextArea1.append("\n");
		jTextArea1.append("\n");
		jTextArea1.append("\n");

		jTextArea1.append("Proceso Finalizado");
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new VentanaVales().setVisible(true);

			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JMenuItem aboutMenuItem;
	private javax.swing.JMenuItem exitMenuItem;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JMenu helpMenu;
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextField jTextField1;
	private java.awt.Label label1;
	private javax.swing.JMenuBar menuBar;
	// End of variables declaration//GEN-END:variables

}