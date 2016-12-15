package wanggd;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Point;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChoiceList extends JList<String>{

	private static final long serialVersionUID = 1L;
	private String[] words;
	private String selection;
	private MyTextArea textArea;

	
	public ChoiceList(Point p,String words[],MyTextArea textArea){//x,y是坐标，words[]是需要显示的内容
		super(words);
		this.textArea=textArea;
		setWords(words);
		setFont(textArea.getFont());
		setPosition(p);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setBackground(Color.getHSBColor(0.45f,0.07f,1f));
		addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				selection=getSelectedValue();
			}
		});
	}
	
	public void highlight(){
		textArea.highlight();
	}

	
	public void removeAllHighlights(){
		textArea.removeAllHighlights();
	}

	public void replaceWord(String newWord){
		textArea.replaceWord(newWord);
	}
	
	public void toggle(){
		if(isVisible())
			setVisible(false);
		else 
			setVisible(true);
	}
	
	public int getWidth(){
		if(words==null) return 0;
		FontMetrics fm=getFontMetrics(getFont());
		int width=words[0].length(),height=words.length;
		int index=0;
		for(int i=1;i<height;i++){
			if(width<words[i].length()){
				width=words[i].length();
				index=i;
			}
		}
		width=SwingUtilities.computeStringWidth(fm,words[index])+10;
		//width=width*8<70?70:width*8;
		//width=width>300?300:width;
		return width;
	}
	public int getHeight(){
		if(words==null) return 0;
		int height=words.length;
		FontMetrics fm=getFontMetrics(getFont());
		return height*fm.getHeight()+20;
		//return (height>20)?200:height*20;
	}
	
	public void setPosition(Point p){
		this.setBounds(p.x, p.y, getWidth(), getHeight());
	}
	
	public String getSelection() {
		return selection;
	}
	
	public String[] getWords() {
		return words;
	}
	public void setWords(String[] words) {
		setListData(words);
		this.words = words;
	}
}