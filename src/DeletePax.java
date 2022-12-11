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
import com.mongodb.MongoClient;

public class DeletePax extends JFrame  implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String labelstr[]={"ONEWORLD AIRLINES","The world on wings!",
			"CANCEL YOUR TICKET HERE","CUSTOMER ID:",
			"FLIGHT NUMBER:","MOBILE NUMBER:"};
	JPanel p1,p2,p3,p4,p5;
	int i;
	JTextField t[];
	static String[] info=new String[3];
	JLabel lform[],con;
	JButton delete,home;
	Font fl=new Font("Arial",Font.BOLD,18);
	Font fh=new Font("Century Schoolbook",Font.BOLD,24);
	Font fh1=new Font("Century Schoolbook",Font.BOLD,20);
	GridLayout gl=new GridLayout(4,2,8,8);
	GridLayout gh=new GridLayout(3,1);

	public DeletePax(){
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

		lform=new JLabel[6];

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
		delete=new JButton("Cancel Booking");
		home=new JButton("Home");
		delete.setFont(fh1);
		home.setFont(fh1);
		JPanel rb=new JPanel();
		rb.setBackground(Color.white);
		GridLayout gb=new GridLayout(1,2,20,20);
		rb.setLayout(gb);
		rb.add(home);
		rb.add(delete);

		t=new JTextField[3];
		for(i=0;i<3;i++){
			t[i]=new JTextField(10);
			t[i].setFont(fh1);
		}

		JPanel rdob=new JPanel();
		rdob.setBackground(Color.white);
		Border border = BorderFactory.createLineBorder(Color.black, 1);
		for(i=0;i<3;i++){
			t[i].setBorder(border);
		}

		p2.add(lform[3]);
		p2.add(t[0]);
		p2.add(lform[4]);
		p2.add(t[1]);
		p2.add(lform[5]);
		p2.add(t[2]);
		p2.add(home);
		p2.add(delete);

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
		home.addActionListener(this);
		delete.addActionListener(this);
	}

	@SuppressWarnings("resource")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==delete){
			info[0]=t[0].getText();
			info[1] = t[1].getText();
			info[2] = t[2].getText();
			System.out.println(info[0]);
			if(t[0].getText().equals("") || t[1].getText().equals("") || t[2].getText().equals("")){
				JOptionPane.showMessageDialog(this,"PLEASE ENTER ENTIRE DETAILS","ERROR",JOptionPane.ERROR_MESSAGE);      
			}
			else{
				MongoClient mongoClient = null;
				mongoClient = new MongoClient( "localhost",27017);
				@SuppressWarnings("deprecation")
				DB db = mongoClient.getDB("airline");
				DBCollection coll = db.getCollection("pax");

				BasicDBObject document = new BasicDBObject();
				document.put("cust_id",info[0]);
				document.put("flight_no", info[1]);
				document.put("mob_no", info[2]);
				DBCursor cursor = coll.find(document);
				if(cursor.hasNext()){
					coll.remove(document);
					JOptionPane.showMessageDialog(this,"BOOKING CANCELED SUCCESSFULLY!!");
					this.dispose();
					new Index();
				}
				else {
					for(i=0;i<3;i++){
						t[i].setText("");
					}
					JOptionPane.showMessageDialog(this,"RECORD NOT FOUND..TRY AGAIN!!");
				}
			}
		}
		if(e.getSource()==home){
			this.dispose();
			new Index();
		}
	}

	public static void main(String str[]) {
		new DeletePax();
	}
}
