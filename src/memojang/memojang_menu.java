package memojang;

import java.awt.*;
import javax.swing.undo.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;

public class memojang_menu extends Frame implements ActionListener, UndoableEditListener, KeyListener, WindowListener{
	//Panel p;
	Menu file, edit, form, view, help ;
	Menu mm1=new Menu();
	MenuItem f[]=new MenuItem[7];
	MenuItem e[]=new MenuItem[11];
	MenuItem o[]=new MenuItem[2];
	MenuItem v;
	MenuItem h[]=new MenuItem[2];
	MenuShortcut fs[]=new MenuShortcut[6];
	MenuShortcut es[]=new MenuShortcut[11];
	UndoManager undoManager=new UndoManager();
	JTextArea textcomp = new JTextArea();
	 Document doc = textcomp.getDocument();

	MenuBar mb;
	TextArea ta;
	

	memojang_menu(String title){
		super(title);
		mb=new MenuBar();
		this.addWindowListener(this);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image icon = toolkit.getImage("image.png");
		this.setIconImage(icon);
		doc.addUndoableEditListener(this);
	//	p=new Panel();
		fs[0]=new MenuShortcut(KeyEvent.VK_N);
		fs[1]=new MenuShortcut(KeyEvent.VK_O);
		fs[2]=new MenuShortcut(KeyEvent.VK_S);
		fs[3]=new MenuShortcut(KeyEvent.VK_A);
		fs[4]=new MenuShortcut(KeyEvent.VK_U);
		fs[5]=new MenuShortcut(KeyEvent.VK_P);
		
		file=new Menu("����");
		f[0]=new MenuItem("���θ����(N)",fs[0]);
		f[1]=new MenuItem("����(O)...",fs[1]);
		f[2]=new MenuItem("����(S)",fs[2]);
		f[3]=new MenuItem("�ٸ��̸���������(A)...",fs[3]);
		f[4]=new MenuItem("����������(U)...",fs[4]);
		f[5]=new MenuItem("�μ�(P)...",fs[5]);
		f[6]=new MenuItem("������");	
		for(int i=0;i<7;i++){
			file.add(f[i]);
			f[i].addActionListener(this); 
		//	mm1.addSeparator();
		}
		
		es[0]=new MenuShortcut(KeyEvent.VK_Z);
		es[1]=new MenuShortcut(KeyEvent.VK_X);
		es[2]=new MenuShortcut(KeyEvent.VK_C);
		es[3]=new MenuShortcut(KeyEvent.VK_V);
		es[4]=new MenuShortcut(KeyEvent.VK_DELETE);
		es[5]=new MenuShortcut(KeyEvent.VK_F);
		es[6]=new MenuShortcut(KeyEvent.VK_F3);
		es[7]=new MenuShortcut(KeyEvent.VK_H);
		es[8]=new MenuShortcut(KeyEvent.VK_G);
		es[9]=new MenuShortcut(KeyEvent.VK_A);
		es[10]=new MenuShortcut(KeyEvent.VK_F5);
		
		edit=new Menu("����(E)");
		e[0]=new MenuItem("���� ���(U)",es[0]);
		e[1]=new MenuItem("�߶󳻱�(T)",es[1]);
		e[2]=new MenuItem("����(C)",es[2]);
		e[3]=new MenuItem("�ٿ��ֱ�(V)",es[3]);
		e[4]=new MenuItem("����(L)",es[4]);
		e[5]=new MenuItem("ã��(F)",es[5]);
		e[6]=new MenuItem("���� ã��(N)",es[6]);
		e[7]=new MenuItem("�ٲٱ�(R)",es[7]);
		e[8]=new MenuItem("�̵�(G)",es[8]);
		e[9]=new MenuItem("��� ����(A)",es[9]);
		e[10]=new MenuItem("�ð�/��¥(D)",es[10]);
		
		ta=new TextArea(480,400);
		
		for(int i=0;i<11;i++){
			edit.add(e[i]);
			e[i].addActionListener(this); 
			//mm1.addSeparator();
		}
		
		form=new Menu("����(O)");
		o[0]=new MenuItem("�ڵ� �� �ٲ�(W)");
		o[1]=new MenuItem("�۲�(F)...");
		
		for(int i=0;i<2;i++){
			form.add(o[i]);
			//mm1.addSeparator();
		}
		
		view=new Menu("����(V)");
		v=new MenuItem("���� ǥ����(S)");
		view.add(v);
		
		help=new Menu("����(H)");
		h[0]=new MenuItem("���� ����(H)");
		h[1]=new MenuItem("�޸��� ����(A)");
		h[0].addActionListener(this); 
		h[1].addActionListener(this); 
		
		for(int i=0;i<2;i++){
			help.add(h[i]);
			//mm1.addSeparator();
		}
		
		 
		mb.add(file);
		mb.add(edit);
		mb.add(form);
		mb.add(view);
		mb.add(help);
		
		this.setMenuBar(mb);
		this.add(ta);
	//	this.add(p);
		setVisible(true);
		setSize(500,400);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		memojang_menu m=new memojang_menu("�޸���");
	}
	
	@Override
	public void undoableEditHappened(UndoableEditEvent e) {
		// TODO Auto-generated method stub
		  undoManager.addEdit(e.getEdit());  
	}
	String tf="";
	String tr="";
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command=e.getActionCommand();
		String s="";
		String t="";
		if(command.equals("����(O)...")){
			FileDialog fd=new FileDialog(this,"����",FileDialog.LOAD);
			fd.setSize(300,200);
			fd.setVisible(true);
			String fName=fd.getDirectory()+fd.getFile();
			try {
				System.out.println(fName);
				FileReader fr=new FileReader(fName);
				BufferedReader br=new BufferedReader(fr);
				do{
					s=br.readLine();
					if(s!=null){
						t+=s+"\n";
					}
				}while(s!=null);
			}  catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ta.setText(t);
		}
		if(command.equals("�ٸ��̸���������(A)...")){
			try{
				FileDialog fs=new FileDialog(this,"����",FileDialog.SAVE);
				fs.setSize(300,200);
				fs.setVisible(true);
				String fName=fs.getDirectory()+fs.getFile();	
				FileWriter fw=new FileWriter(fName+".txt",true);
				fw.write(ta.getText());
				fw.close();
				
			}
			catch(IOException e2){
				e2.printStackTrace();
			}
		}
		if(command.equals("����(S)")){
			try{
				FileDialog fs=new FileDialog(this,"����",FileDialog.SAVE);
				fs.setSize(300,200);
				fs.setVisible(true);
				if(fs.getFile()==null) return;
				String fName=fs.getDirectory()+fs.getFile();	
				FileWriter fw=new FileWriter(fName+".txt",true);
				fw.write(ta.getText());
				fw.close();
			}
			catch(IOException e2){
				e2.printStackTrace();
			}
		} //���� �������� ���+�̸� �ҷ����¹� ��
		if(command.equals("���θ����(N)")){
		
				ta.setText(" ");
		}  //�� ����, ���̾˷α� ����(if üũ ����� �ȵ�)*/
		
		if(command.equals("����(C)")){
			tf=ta.getSelectedText();	
		}
		if(command.equals("�ٿ��ֱ�(V)")){
			ta.append(tf);
		}
		if(command.equals("��� ����(A)")){
			ta.selectAll();
		}

		if(command.equals("���� ���(U)")){  
			 if(undoManager.canUndo())
				 undoManager.undo();
		}///error
		if(command.equals("�߶󳻱�(T)")){
			tr=ta.getText();
			tf=ta.getSelectedText();
			int start=ta.getSelectionStart();
			int end=ta.getSelectionEnd();
			String cut1=tr.substring(0,start);
			String cut2=tr.substring(end,tr.length());
			ta.setText(cut1+cut2);
		}
		if(command.equals("����(L)")){
			tf=ta.getSelectedText();
			ta.setText("");
		}
		if(command.equals("������")){
			System.exit(0);
		}
		if(command.equals("���� ����(H)")){
			//System.out.println(1231234);
			Dialog dl=new Dialog(this,"���� ����",true); 
			Label lb=new Label("���� ���� �Դϴ�.");
			dl.setSize(300,200);
			dl.add(lb);
			dl.addWindowListener(this);
			this.setVisible(true);
			dl.setVisible(true);
		}
		if(command.equals("�޸��� ����(A)")){
			//System.out.println(1231234);
			Dialog dl=new Dialog(this,"�޸��� ����",true); 
			Label lb=new Label("�޸��� ���� �Դϴ�.");
			dl.setSize(300,200);
			dl.add(lb);
			dl.addWindowListener(this);
			this.setVisible(true);
			dl.setVisible(true);
		}
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		String s="";
		String t="";
		if(e.getKeyCode()==KeyEvent.VK_O){
			FileDialog fd=new FileDialog(this,"����",FileDialog.LOAD);
			fd.setSize(300,200);
			fd.setVisible(true);
			String fName=fd.getDirectory()+fd.getFile();
			try {
				System.out.println(fName);
				FileReader fr=new FileReader(fName);
				BufferedReader br=new BufferedReader(fr);
				do{
					s=br.readLine();
					if(s!=null){
						t+=s+"\n";
					}
				}while(s!=null);
			}  catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ta.setText(t);
		}
		if(e.getKeyCode()==KeyEvent.VK_O){
			ta.setText(" ");
		}
		if(e.getKeyCode()==KeyEvent.VK_S){
			try{
				FileDialog fs=new FileDialog(this,"����",FileDialog.SAVE);
				fs.setSize(300,200);
				fs.setVisible(true);
				if(fs.getFile()==null) return;
				String fName=fs.getDirectory()+fs.getFile();	
				FileWriter fw=new FileWriter(fName+".txt",true);
				fw.write(ta.getText());
				fw.close();
			}
			catch(IOException e2){
				e2.printStackTrace();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_A){
			ta.selectAll();
		}
		if(e.getKeyCode()==KeyEvent.VK_Z){
			 if(undoManager.canUndo())
				 undoManager.undo();
		}
		if(e.getKeyCode()==KeyEvent.VK_X){
			tr=ta.getText();
			tf=ta.getSelectedText();
			int start=ta.getSelectionStart();
			int end=ta.getSelectionEnd();
			String cut1=tr.substring(0,start);
			String cut2=tr.substring(end,tr.length());
			ta.setText(cut1+cut2);
		}
		if(e.getKeyCode()==KeyEvent.VK_C){
			tf=ta.getSelectedText();
		}
		if(e.getKeyCode()==KeyEvent.VK_V){
			ta.append(tf);
		}
		if(e.getKeyCode()==KeyEvent.VK_DELETE){
			tf=ta.getSelectedText();
			ta.setText("");
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.exit(0);
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
