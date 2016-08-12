package Tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Cadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtTelefone;
	
	private final static String url = "jdbc:mysql://localhost:3306/impacta";

	private final static String username = "root";
	private final static String password = "Imp@ct@";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	private String nome = null;
	private String telefone = null;
	JLabel lblNome = new JLabel("Nome:");
	JLabel lblTelefone = new JLabel("Telefone:");



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro frame = new Cadastro();
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
	public Cadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(10, 23, 72, 14);
		contentPane.add(lblCdigo);
		
		//JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 61, 46, 14);
		contentPane.add(lblNome);
		
		//JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 97, 46, 14);
		contentPane.add(lblTelefone);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(76, 20, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setBounds(76, 58, 86, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(76, 94, 86, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				openDB();
				cadastrar();
				closeDB();
			}
		});
		btnCadastrar.setBounds(10, 143, 120, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSair.setBounds(312, 143, 120, 23);
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
	
	public void limpar(){
		txtCodigo.setText("");
		txtNome.setText("");
		txtTelefone.setText("");
		txtCodigo.requestFocus();
	}
	public void cadastrar(){
		
		try{
			String comandoSQL = "INSERT INTO alunos(CODIGO,NOME,TELEFONE)"
					+ "VALUES (' " + txtCodigo.getText() + " ',' " 
					+ txtNome.getText() + " ',' " + txtTelefone.getText() + "')";
			
			stmt.executeUpdate(comandoSQL);
			JOptionPane.showMessageDialog(null,
					"Dados cadastrados com sucesso!",
				"Parabens", JOptionPane.INFORMATION_MESSAGE);
			limpar();
			
		}catch (SQLException e){
			System.out.println("Não Cadastrou");
			JOptionPane.showMessageDialog(null, "Verifique o registro","Atenção",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
			limpar();
			
		}
	}
	
}
