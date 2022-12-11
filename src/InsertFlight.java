import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class InsertFlight extends JFrame  implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String labelstr[]={"ONEWORLD AIRLINES","The world on wings!",
			"ENTER NEW FLIGHT HERE","FLIGHT NUMBER:",
			"DEPARTURE:","ARRIVAL:","NUMBER OF SEATS:"};

	JPanel p1,p2,p3,p4,p5;
	JTextArea add;
	String n1,n2,n3,n4,n5,d1[],dy[];
	int i;
	JTextField t[];
	static String[] info=new String[4];
	JComboBox<String> flightno,d,m,y;
	JLabel lform[],img,emp,con,img1;
	JButton register,reset,home;
	Font fl=new Font("Arial",Font.BOLD,18);
	Font fh=new Font("Century Schoolbook",Font.BOLD,24);
	Font fh1=new Font("Century Schoolbook",Font.BOLD,20);
	GridLayout gl=new GridLayout(5,2,8,8);
	GridLayout gh=new GridLayout(3,1);
	String city[]={"101","102","103","104","105"};
	String dm[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
	CheckboxGroup c=new CheckboxGroup();
	Checkbox c1=new Checkbox("BUSINESS",c,false);
	Checkbox c2=new Checkbox("ECONOMY",c,false);

	public InsertFlight(){
		super("BOOK YOUR FLIGHT");
		setSize(950,670);
		setVisible(true);

		BorderLayout b=new BorderLayout();

		p1=new JPanel();
		p1.setBackground(Color.red);

		p2=new JPanel();
		p2.setBackground(Color.white);

		p4=new JPanel();
		p4.setBackground(Color.white);

		p5=new JPanel();
		p5.setBackground(Color.white);

		p3=new JPanel();
		p3.setBackground(Color.yellow);

		setLayout(b);
		add(BorderLayout.NORTH,p1);
		add(BorderLayout.CENTER,p2);
		add(BorderLayout.SOUTH,p3);
		add(BorderLayout.EAST,p4);
		add(BorderLayout.WEST,p5);	
		p5.setBackground(Color.CYAN);
		p4.setBackground(Color.CYAN);
		
		//********CENTER LAYOUT********
		p2.setLayout(null);
		p2.setLayout(gl);

		lform=new JLabel[7];
		for(i=0;i<lform.length;i++){
			if(i<3){
				lform[i]=new JLabel(labelstr[i],JLabel.CENTER);
			}
			else{			
				lform[i]=new JLabel(labelstr[i]);
			}			
			if(i>2){
				lform[i].setFont(fh1);
			}		
		}

		//text
		t=new JTextField[4];
		for(i=0;i<4;i++){
			t[i]=new JTextField(10);
			t[i].setFont(fh1);
		}
		flightno=new JComboBox<>(city);
		add=new JTextArea(2,5);
		add.setFont(fh1);

		d1=new String[31];
		for(i=0;i<31;i++){
			d1[i]=Integer.toString(i+1);
		}
		int n=2022;
		dy=new String[5];
		for(i=0;i<5;i++){			
			dy[i]=Integer.toString(n);
			n-=-1;
		}

		d=new JComboBox<>(d1);
		m=new JComboBox<>(dm);
		y=new JComboBox<>(dy);
		
		d.setFont(fh1);
		m.setFont(fh1);
		y.setFont(fh1);
		flightno.setFont(fh1);
		
		JPanel rdob=new JPanel();
		rdob.setBackground(Color.white);
		GridLayout gdob=new GridLayout(1,3);
		rdob.setLayout(gdob);
		rdob.add(d);
		rdob.add(m);
		rdob.add(y);

		Border border = BorderFactory.createLineBorder(Color.black, 1);
		add.setBorder(border);
		for(i=0;i<4;i++){
			t[i].setBorder(border);
		}

		c1.setFont(fh1);
		c2.setFont(fh1);

		JPanel rd=new JPanel();
		rd.setBackground(Color.white);
		GridLayout gpnl=new GridLayout(1,2);
		rd.setLayout(gpnl);

		rd.add(c1);
		rd.add(c2);

		//BUTTON

		register=new JButton("SUBMIT");
		home=new JButton("Home");
		reset=new JButton("Reset");
		register.setFont(fh1);
		home.setFont(fh1);
		reset.setFont(fh1);
		JPanel rb=new JPanel();
		rb.setBackground(Color.white);
		GridLayout gb=new GridLayout(1,3,20,20);
		rb.setLayout(gb);
		rb.add(home);
		rb.add(reset);
		rb.add(register);


		JPanel rb1=new JPanel();
		rb1.setBackground(Color.white);
		GridLayout gb1=new GridLayout(1,3,10,50);
		rb1.setLayout(gb1);
		rb1.add(register);

		p2.add(lform[3]);
		p2.add(t[0]);
		p2.add(lform[4]);
		p2.add(t[1]);
		p2.add(lform[5]);
		p2.add(t[2]);
		p2.add(lform[6]);
		p2.add(t[3]);
//		p2.add(lform[7]);
//		p2.add(flightno);
//		p2.add(lform[8]);		
//		p2.add(rd);
//		p2.add(lform[9]);
//		p2.add(rdob);
//		p2.add(lform[10]);
//		p2.add(t[3]);
		p2.add(rb);
		p2.add(rb1);

		//******TOP LAYOUT*********
		p1.setLayout(gh);		
		p1.add(lform[0]);
		lform[0].setFont(fh);
		lform[0].setForeground(Color.yellow);
		lform[1].setForeground(Color.yellow);
		lform[2].setForeground(Color.WHITE);
		lform[1].setFont(fl);
		p1.add(lform[1]);
		lform[2].setFont(fl);
		p1.add(lform[2]);

		//Bottom layout
		p3.setLayout(null);
		FlowLayout fo=new FlowLayout();
		p3.setLayout(fo);
		con=new JLabel("For any queries Contact: oneworld@airline.com");
		con.setFont(fl);
		p3.add(con);
		reset.addActionListener(this);
		home.addActionListener(this);
		register.addActionListener(this);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==register){
			int res;
			res=JOptionPane.showConfirmDialog(InsertFlight.this, "Flight Registered Successfully!! Continue?");
			System.out.println(res);
			switch(res){
			case 0:
				//FIRST NAME:","LAST NAME:","DATE OF BIRTH:","GENDER:","STATE:","CITY:","ADDRESS:","E-MAIL:","USER NAME:","PASSWORD:","BALANCE:"};
				info[0]=t[0].getText();
				info[0] = "OA "+info[0];
				info[1]=t[1].getText();
				info[2]=t[2].getText();
				info[3]=t[3].getText();
				for(int i = 0; i < 4; i++){
					System.out.println(info[i]);
				}
				MongoClient mongoClient = null;
				try {
					mongoClient = new MongoClient( "localhost",27017);
					DB db = mongoClient.getDB("airline");

					DBCollection coll = db.getCollection("flight");
					System.out.println("Collection Accessed");

					BasicDBObject doc = new BasicDBObject("flight_no", info[0]).append("flight_dept",info[1]).append("flight_arrival",info[2]).append("flight_seats",info[3]);
					if(t[0].getText().equals("")){
						JOptionPane.showMessageDialog(this,"PLEASE ENTER FLIGHT NUMBER","ERROR",JOptionPane.ERROR_MESSAGE);      
					}  
					if(t[1].getText().equals("")){
						JOptionPane.showMessageDialog(this,"PLEASE ENTER FLIGHT DEPARTURE","ERROR",JOptionPane.ERROR_MESSAGE);      
					}  
					if(t[2].getText().equals("")){
						JOptionPane.showMessageDialog(this,"PLEASE ENTER FLIGHT ARRIVAL","ERROR",JOptionPane.ERROR_MESSAGE);      
					}  
					if(t[3].getText().equals("")){
						JOptionPane.showMessageDialog(this,"PLEASE ENTER FLIGHT SEATS","ERROR",JOptionPane.ERROR_MESSAGE);      
					}  
					coll.insert(doc);
					JOptionPane.showMessageDialog(this,"RECORD INSERTED!!!");  
					this.dispose();
					new Index();
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(this,"INSERTION FAILED!!!","MESSAGE",JOptionPane.ERROR_MESSAGE);  
					
				} 
				
				
				this.dispose();
				new Index();
				break;
			}



		}
		if(e.getSource()==reset){
			for(i=0;i<4;i++){
				t[i].setText("");
			}
			add.setText("");
		
		}
		if(e.getSource()==home){
			this.dispose();
			new Index();
		}

	}
	public static void main(String str[]) {
		new InsertFlight();
	}
}
