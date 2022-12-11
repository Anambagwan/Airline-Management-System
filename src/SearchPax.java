import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class SearchPax extends JFrame  implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String labelstr[]={"ONEWORLD AIRLINES","The world on wings!",
			"SEARCH YOUR FLIGHT HERE","CUSTOMER ID:",
			"FULL NAME:","MOBILE NUMBER:","ADDRESS:",
			"FLIGHT NUMBER:","DEPARTURE:","ARRIVALS",
			"FLIGHT DATE","CLASS","AMOUNT:"};
	JPanel p1,p2,p3,p4,p5;
	int i;
	JTextField t[];
	static String[] info=new String[1];
	JLabel lform[],lans[],con;
	JButton Search,reset,home;
	Font fl=new Font("Arial",Font.BOLD,18);
	Font fh=new Font("Century Schoolbook",Font.BOLD,24);
	Font fh1=new Font("Century Schoolbook",Font.BOLD,20);
	GridLayout gl=new GridLayout(11,2,8,8);
	GridLayout gh=new GridLayout(4,1);

	public SearchPax(){
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

		//ans of forms

		lans = new JLabel[10];
		lform=new JLabel[13];
		//		System.out.println(lans.length);

		for(i = 0; i < lans.length; i++){
			lans[i] = new JLabel("NaN");
			//			lans[i].setText("NaN");
			lans[i].setFont(fh1);
		}

		//labels of forms

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

		//BUTTON
		Search=new JButton("Search");
		home=new JButton("Home");
		reset=new JButton("Reset");
		Search.setFont(fh1);
		home.setFont(fh1);
		reset.setFont(fh1);
		JPanel rb=new JPanel();
		rb.setBackground(Color.white);
		GridLayout gb=new GridLayout(1,2,20,20);
		rb.setLayout(gb);
		rb.add(home);
		rb.add(reset);

		t=new JTextField[1];
		for(i=0;i<1;i++){
			t[i]=new JTextField(10);
			t[i].setFont(fh1);
		}

		JPanel rdob=new JPanel();
		rdob.setBackground(Color.white);

		GridLayout gdob=new GridLayout(1,3,5,5);
		gdob.setVgap(30);
		rdob.setLayout(gdob);
		JLabel temp = new JLabel("ENTER CUSTOMER ID: ");
		temp.setFont(new Font("Century Schoolbook",Font.BOLD,20));
		rdob.add(temp);
		rdob.add(t[0]);
		rdob.add(Search);
		rdob.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 0, Color.CYAN));

		Border border = BorderFactory.createLineBorder(Color.black, 1);
		for(i=0;i<1;i++){
			t[i].setBorder(border);
		}

		p2.add(lform[3]);
		p2.add(lans[0]);
		p2.add(lform[4]);
		p2.add(lans[1]);
		p2.add(lform[5]);
		p2.add(lans[2]);
		p2.add(lform[6]);
		p2.add(lans[3]);
		p2.add(lform[7]);
		p2.add(lans[4]);
		p2.add(lform[8]);
		p2.add(lans[5]);
		p2.add(lform[9]);
		p2.add(lans[6]);
		p2.add(lform[10]);
		p2.add(lans[7]);
		p2.add(lform[11]);
		p2.add(lans[8]);
		p2.add(lform[12]);
		p2.add(lans[9]);

		p2.add(home);
		p2.add(reset);
		//		p2.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.BLACK));
		//		p2.add(rb1);

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
		p1.add(rdob);

		//Bottom layout
		p3.setLayout(null);
		FlowLayout fo=new FlowLayout();
		p3.setLayout(fo);
		con=new JLabel("For any queries Contact: oneworld@airline.com");
		con.setFont(fl);
		p3.add(con);
		reset.addActionListener(this);
		home.addActionListener(this);
		Search.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Search){
			info[0]=t[0].getText();
			System.out.println(info[0]);
			if(t[0].getText().equals("")){
				JOptionPane.showMessageDialog(this,"PLEASE ENTER CUSTOMER ID","ERROR",JOptionPane.ERROR_MESSAGE);      
			}
			else{
				MongoClient mongoClient = null;
				DBCursor cursor = null;
				DBCursor cursor2 = null;
				//p2
				//DBCursor cursor3 = null;
				try {    
					mongoClient = new MongoClient( "localhost" , 27017 );
					@SuppressWarnings("deprecation")
					DB db = mongoClient.getDB( "airline" );
					DBCollection coll = db.getCollection("pax");
					//p2
					//DBCollection coll3 = db.getCollection("pax_information");
					DBCollection coll2 = db.getCollection("flight");
					//				cursor = coll.find();
					BasicDBObject search = new BasicDBObject();
					BasicDBObject search2 = new BasicDBObject();
					//p2
//					BasicDBObject search3 = new BasicDBObject();
					search.put("cust_id",info[0]);
					//p2
					//search3.put("cust_id",info[0]);
					cursor = coll.find(search);
					//p2
					//cursor3 = coll3.find(search3);
					/*if(cursor.hasNext()){
						System.out.println("In cs3");
						DBObject obj3 = cursor.next();
						System.out.println((String)obj3.get("cust_id"));
						lans[0].setText((String)obj3.get("cust_id"));
						lans[1].setText((String)obj3.get("name"));
						lans[2].setText((String)obj3.get("mob_no"));
						lans[3].setText((String)obj3.get("address"));
						if((String)obj3.get("flight_date") == null){
							lans[7].setText("NaN");
							lans[8].setText("NaN");
							lans[9].setText("NaN");
							//String flightno=(String)obj.get("flight_no");
							lans[4].setText("NaN");
						}
					}*/



					if(cursor.hasNext()) {
						DBObject obj = cursor.next();
//						DBObject obj3 = cursor3.next();
						lans[0].setText((String)obj.get("cust_id"));
						lans[1].setText((String)obj.get("name"));
						lans[2].setText((String)obj.get("mob_no"));
						lans[3].setText((String)obj.get("address"));
						if((String)obj.get("flight_date") == null){
							lans[7].setText("NaN");
							lans[8].setText("NaN");
							lans[9].setText("NaN");
							//String flightno=(String)obj.get("flight_no");
							lans[4].setText("NaN");
						}
					
							lans[7].setText((String)obj.get("flight_date"));
							lans[8].setText((String)obj.get("status"));
							lans[9].setText((String)obj.get("amount"));
							String flightno=(String)obj.get("flight_no");
							lans[4].setText((String)obj.get("flight_no"));


							search2.put("flight_no",flightno);
							cursor2 = coll2.find(search2);
							System.out.println(flightno+" in seatch");
							System.out.println(cursor2);
							System.out.println(cursor2.hasNext());
							if(cursor2.hasNext())
							{
								DBObject search11=cursor2.next();
								lans[5].setText((String)search11.get("flight_dept"));
								lans[6].setText((String)search11.get("flight_arrival"));
							}
						}
					
					else {

						for(i=0;i<1;i++){
							t[i].setText("");
						}
						for(i=0;i<lans.length;i++){
							//lans[i].setText("NaN");
						}
						JOptionPane.showMessageDialog(this,"Booking NOT FOUND!!");
					} 
					cursor.close(); 
					cursor2.close();
					mongoClient.close();
				}
				catch(Exception ex){
					System.out.println("Caught exception");
				}
			}
		}

		if(e.getSource()==reset){
			for(i=0;i<1;i++){
				t[i].setText("");
			}
			for(i=0;i<lans.length;i++){
				lans[i].setText("NaN");
			}
		}

		if(e.getSource()==home){
			this.dispose();
			new Index();
		}
	}

	public static void main(String str[]) {
		new SearchPax();
	}
}
