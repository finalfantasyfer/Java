package Tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Exclusao extends JFrame {

	private JPanel contentPane;
	private JTextField textCodigo;

	private final static String url = "jdbc:mysql://localhost:3306/impacta";

	private final static String username = "root";
	private final static String password = "Imp@ct@";

	private Connection con;
	private Statement stmt;

	private String nome = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Exclusao frame = new Exclusao();
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
	public Exclusao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(10, 22, 66, 26);
		contentPane.add(lblCdigo);

		textCodigo = new JTextField();
		textCodigo.setBounds(86, 25, 86, 20);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 64, 46, 14);
		contentPane.add(lblNome);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 95, 46, 14);
		contentPane.add(lblTelefone);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(10, 154, 91, 23);
		contentPane.add(btnExcluir);

		JLabel lblNomeC = new JLabel("");
		lblNomeC.setBounds(86, 64, 46, 14);
		contentPane.add(lblNomeC);

		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(113, 154, 91, 23);
		contentPane.add(btnSair);
	}

	public void openDB() {
		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			System.out.println("\nConexão estabelecida com sucesso!\n");
		} catch (SQLException e) {
			System.out.println("\nNão foi possível estabelecer conexão " + e
					+ "\n");
			System.exit(1);
		}
	}

	public void closeDB() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("\nNão foi possível fechar conexão " + e + "\n");
			System.exit(1);
		}
	}
	
	public void excluir() {
		try {
			
		} catch (SQLException e) {
			System.out.println("\nNão foi possível fechar conexão " + e + "\n");
			System.exit(1);
		}
	}

	// public void limpar(){
	// txtCodigo.setText("");
	// txtNome.setText("");
	// txtTelefone.setText("");
	// txtCodigo.requestFocus();
	// }
}
