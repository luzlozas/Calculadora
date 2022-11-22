package vistaCalculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Calculadora extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	JTextField pantalla;
	double resultado;
	String operacion;
	JPanel panelNumeros, panelOperaciones;
	boolean nuevaOperacion = true;
	
	public  Calculadora() {
		
		super();
		setSize(300,400);
		setTitle("Calculadora");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(new BorderLayout());
		
		pantalla = new JTextField("0", 20);
		pantalla.setBorder(new EmptyBorder(4,4,4,4));
		pantalla.setFont(new Font("Plain", Font.BOLD, 25));
		pantalla.setHorizontalAlignment(JTextField.RIGHT);
		pantalla.setBackground(Color.WHITE);
		panel.add("North",pantalla);
		
		panelNumeros = new JPanel();
		panelNumeros.setLayout(new GridLayout(4,3));
		panelNumeros.setBorder(new EmptyBorder(4,4,4,4));
		
		
		for ( int n = 9; n >= 0; n--) {
			
			nuevoBotonNumerico(""+n);
		}
		nuevoBotonNumerico(".");
		
		panel.add("Center", panelNumeros);
		
		panelOperaciones = new JPanel();
		
		panelOperaciones.setLayout(new GridLayout(8,2));
		panelOperaciones.setBorder(new EmptyBorder(5,5,5,5));
		
		nuevoBotonOperacion("+");
		nuevoBotonOperacion("-");
		nuevoBotonOperacion("*");
		nuevoBotonOperacion("/");
		nuevoBotonOperacion("=");
		nuevoBotonOperacion("C");
		nuevoBotonOperacion("Raiz2");
		nuevoBotonOperacion("Raiz3");
		
		
		
		panel.add("East", panelOperaciones);
		validate();
		
		
	}
	
	private void nuevoBotonNumerico(String numero) {
		
		JButton button = new JButton();
		button.setText(numero);
		button.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				numeroPulsado(button.getText());
			}
			
		});
		
		panelNumeros.add(button);
	}
	
	private void nuevoBotonOperacion(String operacion) {
		
		JButton button = new JButton (operacion);
		button.setForeground(Color.RED);
		
		button.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				operacionPulsado(button.getText());
			}
			
			
		});
		
		panelOperaciones.add(button);
		
	}
	
	private void numeroPulsado(String numero) {
		if (pantalla.getText().equals("0") || nuevaOperacion) {
			pantalla.setText(numero);
		}else {
			pantalla.setText(pantalla.getText() + numero);
		}
		nuevaOperacion = false;
		
	}
	
	
	private void operacionPulsado(String tecla) {
		
		if(tecla.equals("=")) {
			calcularResultado();
		} else if (tecla.equals("C")) {
			resultado = 0;
			pantalla.setText("");
			nuevaOperacion = true;
		}else if (tecla.equals("Raiz2")){
			
			JOptionPane.showMessageDialog(null, "Funcionalidad no disponible", "Información", JOptionPane.INFORMATION_MESSAGE);
			
		}else if(tecla.equals("Raiz3")) {
			
			String pass = JOptionPane.showInputDialog("Contraseña: ");
			if (!pass.equals("1234")) {
				JOptionPane.showMessageDialog(null, "Contraseña Incorrecta","Contraseña", JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Contraseña correcta","Contraseña",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		else {
			operacion = tecla;
			if((resultado > 0) && !nuevaOperacion) {
				calcularResultado();
			}else {
				resultado = Double.parseDouble(pantalla.getText()) ;
			}
		}
		
		nuevaOperacion = true;
		
	}
	
	private void calcularResultado() {
		
		
		
		if(operacion.equals("+")) {
			resultado += Double.parseDouble(pantalla.getText());
		}else if (operacion.equals("-")) {
			resultado -= Double.parseDouble(pantalla.getText());
		}else if (operacion.equals("/")) {
			resultado /= Double.parseDouble(pantalla.getText());
		}else if (operacion.equals("*")){
			resultado *= Double.parseDouble(pantalla.getText());
		}
		
		pantalla.setText(""+ resultado);
		operacion = "";
	}
	

}
