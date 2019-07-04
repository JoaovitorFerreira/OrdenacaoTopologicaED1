import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class TelaInicio extends JFrame {

	private JPanel contentPane;
	private JTextField txtOrdenaoTopolgica;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicio frame = new TelaInicio();
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
	public TelaInicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtOrdenaoTopolgica = new JTextField();
		txtOrdenaoTopolgica.setText("Ordena\u00E7\u00E3o Topol\u00F3gica");
		txtOrdenaoTopolgica.setBounds(142, 64, 118, 43);
		contentPane.add(txtOrdenaoTopolgica);
		txtOrdenaoTopolgica.setColumns(10);
		
		JButton btnLerArquivo = new JButton("Ler Arquivo");
		Main main = new Main();
		btnLerArquivo.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				Debug debug = new Debug();
				debug.setVisible(true);
			}
		});
		btnLerArquivo.setBounds(165, 151, 89, 23);
		contentPane.add(btnLerArquivo);
	}

}
