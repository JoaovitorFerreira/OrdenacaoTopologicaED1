import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import 		java.awt.print.PageFormat;
import 		java.awt.print.Printable;
import 		java.awt.print.PrinterException;
import 		java.awt.print.PrinterJob;


public class Debug extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	private class Elo {
		/* Identificação do elemento. */
		public int chave;

		/* Número de predecessores. */
		public int contador;

		/* Aponta para o próximo elo da lista. */
		public Elo prox;

		/* Aponta para o primeiro elemento da lista de sucessores. */
		public EloSuc listaSuc;

		public Elo(int chave) {
			prox = null;
			contador = 0;
			listaSuc = null;
			this.chave = chave;
		}

		public Elo(int chave, int contador, Elo prox, EloSuc listaSuc) {
			this.chave = chave;
			this.contador = contador;
			this.prox = prox;
			this.listaSuc = listaSuc;
		}

	}

	private class EloSuc {
		/* Aponta para o elo que é sucessor. */
		public Elo id;

		/* Aponta para o próximo elemento. */
		public EloSuc prox;

		public EloSuc() {
			id = null;
			prox = null;
		}

		public EloSuc(Elo id, EloSuc prox) {
			this.id = id;
			this.prox = prox;
		}
	}
	
	private Elo prim;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Debug() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDebug = new JButton("Debug");
		btnDebug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdenacaoTopologica ord = new OrdenacaoTopologica();
				String nomeEntrada = "entrada.txt";			
				try {
					Elo p = null;
					EloSuc x = null;
					for (p = prim; p != null; p = p.prox) {
						PrinterJob printerJob	= PrinterJob.getPrinterJob();
						printerJob.setPrintable(null);
		                printerJob.setJobName("Imprimindo uma JTable");
						for (x = p.listaSuc; x != null; x = x.prox) {
							JOptionPane.showMessageDialog(null, x.id.chave + " -> ");
						}
						if (x == null) {
							JOptionPane.showMessageDialog(null, "null");
						}
						System.out.println("");
					}
					ord.realizaLeitura(nomeEntrada);
					if(ord.executa()) {
						JOptionPane.showMessageDialog(null, "O conjunto é parcialmente ordenado.");
					}else {
						JOptionPane.showMessageDialog(null, "O conjunto não é parcialmente ordenado.");
					}
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDebug.setBounds(173, 105, 89, 23);
		contentPane.add(btnDebug);
	}
}
