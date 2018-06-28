import java.awt.BasicStroke;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;


import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class Design extends JFrame implements ActionListener{

	public static Color preSelectedColor;
	public static Color selectedColor;
	public static Color deleteColor;

	public static Painter myPainter=new Painter();
	//public static float thickness=10;
	public static float lineThickness=10;
	public static float eraseThickness=10;
	public static int eraseInt=0;
	public static int thicknessInt=0;
	public static String preInput="";
	
	public static int what=0;


	//JTextField tField;
	private JPanel panel;
	private JButton[] buttons;
	private String[] labels= {"Clear","erase","color"};
	public static Container contentPane;
	private JTextField textField;
	private JButton checkButton;

	public Design()
	{

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,600);
		this.getContentPane().setBackground(Color.WHITE);


		panel=new JPanel();
		panel.setLayout(new GridLayout(3,1));
		buttons=new JButton[3];

		contentPane=getContentPane();
		JToolBar toolBar = new JToolBar("Menu");
		toolBar.setBackground(Color.GRAY);

		JComboBox<String> combo=new JComboBox<String>();
		combo.addItem("저장");
		combo.addItem("다른 이름으로 저장");
		combo.addItem("열기");
		toolBar.add(combo);

		toolBar.add(new JButton("menu"));
		toolBar.addSeparator();
		toolBar.add(new JLabel("Enter thickness"));
		textField=new JTextField();
		toolBar.add(textField);
		checkButton=new JButton("확인");
		toolBar.add(checkButton);
		checkButton.addActionListener(this);



		contentPane.add(toolBar,BorderLayout.NORTH);



		int index=0;

		for(index=0; index<3; index++)
		{
			buttons[index]=new JButton(labels[index]);
			buttons[index].addActionListener(this);

			panel.add(buttons[index]);

		}



		contentPane.add(myPainter,BorderLayout.CENTER);
		contentPane.add(panel,BorderLayout.WEST);

		this.setVisible(true);
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Design();
		//Painter myPainter=new Painter();


	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String input = e.getActionCommand();

		if (input.equals("color")) {
			// System.out.println("color");

			what=2;
			eraseInt=0;
			selectedColor = JColorChooser.showDialog(null, "Color", Color.YELLOW);//색깔 선택
		
			preInput=input;


		}
		else if(input.equals("확인"))
		{
			/*thicknessInt=0;
			String inputThickness=textField.getText();
			thickness=Float.parseFloat(inputThickness);	
			thicknessInt=1;
			//preInput=input;*/
			
			if(preInput.equals("erase"))
			{
				String inputThickness=textField.getText();
				eraseThickness=Float.parseFloat(inputThickness);	
			}
			else if(preInput.equals("color"))
			{
				String inputThickness=textField.getText();
				lineThickness=Float.parseFloat(inputThickness);	
				
			}
			else
			{
				String inputThickness=textField.getText();
				lineThickness=Float.parseFloat(inputThickness);	
				
			}
		
		}

		else if(input.equals("erase"))
		{
			what=1;
			eraseInt=1;
			preInput=input;

		}
		else if(input.equals("Clear"))
		{
			this.setVisible(false);

			myPainter=new Painter();

			contentPane=getContentPane();
			contentPane.add(myPainter,BorderLayout.CENTER);
			this.setVisible(true);
			//preInput=input;

		}

	}


}

class Painter extends JPanel implements MouseMotionListener, MouseListener{

	static public Vector save = new Vector();
	int x1,y1,x2,y2;
	int startX, startY, endX, endY;
	public static Graphics2D eraseG;
	public static Graphics2D lineG;


	public Painter()
	{
		this.setBackground(Color.WHITE);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}


	public void paint(Graphics g)
	{
		System.out.println("paint method 안입니다.");

	}



	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

		endX=e.getX();
		endY=e.getY();
        Painter b= new Painter();

		b.setX1(startX);
		b.setX2(startY);
		b.setY1(endX);
		b.setY2(endY);


		Graphics g2=Design.myPainter.getGraphics();
		Graphics2D g3=(Graphics2D)g2;
		//g3.setStroke(new BasicStroke(Design.thickness));
		
		/*if(Design.thicknessInt==1)//굵기가 설정되면
		{
			System.out.println("Hello");
			g3.setStroke(new BasicStroke(Design.thickness));
		}*/



		//if(Design.colorChanged>0)
		//g2.setColor(Design.selectedColor);


		/*if(Design.selectedColor != null)//Color을 눌렀을 떄 만약 선택한 색깔이 있으면 
		{

			g2.setColor(Design.selectedColor);
			Design.preSelectedColor=Design.selectedColor;

			if(Design.eraseInt==1)
			{
				System.out.print("Hello");
				g2.setColor(Color.WHITE);

			}

		}
		else if(Design.selectedColor == null)//Color을 눌렀을 떄 선택한 색깔이 없으면
		{
			g2.setColor(Design.preSelectedColor);


			if(Design.eraseInt==1)
			{
				g2.setColor(Color.WHITE);

			}
		}  
		else//색깔을 안 바꾼 초기상태
		{
			if(Design.thicknessInt==1)//굵기가 설정되면
			{
				g3.setStroke(new BasicStroke(Design.thickness));
			}

		}*/
		
		if(Design.what==1)//erase가 눌러지면
		{
			g3.setStroke(new BasicStroke(Design.eraseThickness));
			g2.setColor(Color.WHITE);
		}
		else if(Design.what==2)//Color가 눌러지면
		{
			if(Design.selectedColor!=null)//Color을 눌렀을 떄 만약 선택한 색깔이 있으면 
			{
				g3.setStroke(new BasicStroke(Design.lineThickness));
				g2.setColor(Design.selectedColor);
				Design.preSelectedColor=Design.selectedColor;
			}
			else if(Design.selectedColor==null)//Color을 눌렀을 떄 선택한 색깔이 없으면
			{
				g3.setStroke(new BasicStroke(Design.lineThickness));
				g2.setColor(Design.preSelectedColor);
			}
		}
		else if(Design.what==0)//선택된 버튼이 아무것도 없을 때
		{
			g3.setStroke(new BasicStroke(Design.lineThickness));
		}




		g2.drawLine(b.getX1(),b.getX2(),b.getY1(),b.getY2());


		int saveSize=save.size();


		//this.repaint();

		startX=endX;
		startY=endY;

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		startX=e.getX();
		startY=e.getY();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

}
