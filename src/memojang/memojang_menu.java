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
		
		file=new Menu("파일");
		f[0]=new MenuItem("새로만들기(N)",fs[0]);
		f[1]=new MenuItem("열기(O)...",fs[1]);
		f[2]=new MenuItem("저장(S)",fs[2]);
		f[3]=new MenuItem("다른이름으로저장(A)...",fs[3]);
		f[4]=new MenuItem("페이지설정(U)...",fs[4]);
		f[5]=new MenuItem("인쇄(P)...",fs[5]);
		f[6]=new MenuItem("끝내기");	
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
		
		edit=new Menu("편집(E)");
		e[0]=new MenuItem("실행 취소(U)",es[0]);
		e[1]=new MenuItem("잘라내기(T)",es[1]);
		e[2]=new MenuItem("복사(C)",es[2]);
		e[3]=new MenuItem("붙여넣기(V)",es[3]);
		e[4]=new MenuItem("삭제(L)",es[4]);
		e[5]=new MenuItem("찾기(F)",es[5]);
		e[6]=new MenuItem("다음 찾기(N)",es[6]);
		e[7]=new MenuItem("바꾸기(R)",es[7]);
		e[8]=new MenuItem("이동(G)",es[8]);
		e[9]=new MenuItem("모두 선택(A)",es[9]);
		e[10]=new MenuItem("시간/날짜(D)",es[10]);
		
		ta=new TextArea(480,400);
		
		for(int i=0;i<11;i++){
			edit.add(e[i]);
			e[i].addActionListener(this); 
			//mm1.addSeparator();
		}
		
		form=new Menu("서식(O)");
		o[0]=new MenuItem("자동 줄 바꿈(W)");
		o[1]=new MenuItem("글꼴(F)...");
		
		for(int i=0;i<2;i++){
			form.add(o[i]);
			//mm1.addSeparator();
		}
		
		view=new Menu("보기(V)");
		v=new MenuItem("상태 표시줄(S)");
		view.add(v);
		
		help=new Menu("도움말(H)");
		h[0]=new MenuItem("도움말 보기(H)");
		h[1]=new MenuItem("메모장 정보(A)");
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
		memojang_menu m=new memojang_menu("메모장");
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
		if(command.equals("열기(O)...")){
			FileDialog fd=new FileDialog(this,"열기",FileDialog.LOAD);
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
		if(command.equals("다른이름으로저장(A)...")){
			try{
				FileDialog fs=new FileDialog(this,"저장",FileDialog.SAVE);
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
		if(command.equals("저장(S)")){
			try{
				FileDialog fs=new FileDialog(this,"저장",FileDialog.SAVE);
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
		} //저장 현재파일 경로+이름 불러오는법 모름
		if(command.equals("새로만들기(N)")){
		
				ta.setText(" ");
		}  //덜 만듦, 다이알로그 부터(if 체크 제대로 안됨)*/
		
		if(command.equals("복사(C)")){
			tf=ta.getSelectedText();	
		}
		if(command.equals("붙여넣기(V)")){
			ta.append(tf);
		}
		if(command.equals("모두 선택(A)")){
			ta.selectAll();
		}

		if(command.equals("실행 취소(U)")){  
			 if(undoManager.canUndo())
				 undoManager.undo();
		}///error
		if(command.equals("잘라내기(T)")){
			tr=ta.getText();
			tf=ta.getSelectedText();
			int start=ta.getSelectionStart();
			int end=ta.getSelectionEnd();
			String cut1=tr.substring(0,start);
			String cut2=tr.substring(end,tr.length());
			ta.setText(cut1+cut2);
		}
		if(command.equals("삭제(L)")){
			tf=ta.getSelectedText();
			ta.setText("");
		}
		if(command.equals("끝내기")){
			System.exit(0);
		}
		if(command.equals("도움말 보기(H)")){
			//System.out.println(1231234);
			Dialog dl=new Dialog(this,"도움말 보기",true); 
			Label lb=new Label("도움말 보기 입니다.");
			dl.setSize(300,200);
			dl.add(lb);
			dl.addWindowListener(this);
			this.setVisible(true);
			dl.setVisible(true);
		}
		if(command.equals("메모장 정보(A)")){
			//System.out.println(1231234);
			Dialog dl=new Dialog(this,"메모장 정보",true); 
			Label lb=new Label("메모장 정보 입니다.");
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
			FileDialog fd=new FileDialog(this,"열기",FileDialog.LOAD);
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
				FileDialog fs=new FileDialog(this,"저장",FileDialog.SAVE);
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
