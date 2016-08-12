package Tela;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Consulta extends JFrame {

	private final static String url = "jdbc:mysql://localhost:3306/impacta";

	private final static String username = "root";
	private final static String password = "Imp@ct@";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	private String nome = null;
	private String telefone = null;

	private JPanel contentPane;
	private JTextField txtCodigo;

	JLabel lblNome = new JLabel("");
	JLabel lblTelefone = new JLabel("");
	JLabel lblCodigo = new JLabel("C\u00F3digo:");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consulta frame = new Consulta();
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
	public Consulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// JLabel lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(52, 18, 78, 30);
		contentPane.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(140, 20, 98, 30);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblNom = new JLabel("Nome:");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNom.setBounds(52, 81, 46, 14);
		contentPane.add(lblNom);

		// JLabel lblNome = new JLabel("");
		lblNome.setBounds(140, 81, 149, 23);
		contentPane.add(lblNome);

		JLabel lblTele = new JLabel("Telefone:");
		lblTele.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTele.setBounds(49, 126, 81, 14);
		contentPane.add(lblTele);

		// JLabel lblTelefone = new JLabel("");
		lblTelefone.setBounds(136, 115, 153, 25);
		contentPane.add(lblTelefone);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openDB();
				mostrar();
				closeDB();

			}
		});
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConsultar.setBounds(52, 176, 112, 23);
		contentPane.add(btnConsultar);

		JButton btnSair = new JButton("Sair");
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);

			}
		});
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSair.setBounds(287, 176, 91, 23);
		contentPane.add(btnSair);
	}

	public void closeDB() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("\nNão foi possível fechar conexão " + e + "\n");
			System.exit(1);
		}
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

	
	

	
	public void mostrar() {
		String query;

		try {
			query = "SELECT * FROM alunos where codigo = '"
					+ txtCodigo.getText() + "'";

			System.out.println("\nMostrando dados.\n");

			System.out.println(txtCodigo.getText());

			rs = stmt.executeQuery(query);
			while (rs.next()) {
				lblNome.setText(rs.getString("nome"));
				lblTelefone.setText(rs.getString("telefone"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("problemas...");

		}
	}
}
