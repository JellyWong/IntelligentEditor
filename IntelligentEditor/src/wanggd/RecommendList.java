package wanggd;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JScrollPane;

public class RecommendList extends JScrollPane{

	private static final long serialVersionUID = 1751204086973463064L;
	private ChoiceList list ;
	
	public RecommendList(ChoiceList list) {
		this.list=list;
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setViewportView(list);
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int keyCode = e.getKeyCode();
				if(keyCode==KeyEvent.VK_ENTER){
					list.replaceWord(getSelection());
					setVisible(false);
				}
				else if(keyCode!=KeyEvent.VK_DOWN&&keyCode!=KeyEvent.VK_UP){
					setVisible(false);
				}
				removeAllHighlights();
			}
		});

	}
	
	public void removeAllHighlights(){
		list.removeAllHighlights();
	}
	
	public void highlight(){
		list.highlight();
	}
	
	public int getWidth(){
		return list.getWidth();
	}
	public int getHeight(){
		return list.getHeight();
	}
	
	public void setPosition(Point p){
		list.setBounds(0, 0, 0, 0);
		this.setBounds(p.x+10, p.y, getWidth(), getHeight()/2);
	}
	
	public String getSelection() {
		return list.getSelection();
	}
	
	public String[] getWords() {
		return list.getWords();
	}
	public void setWords(String[] words) {
		list.setWords(words);
	}
	
	public void toggle(){
		if(isVisible())
			setVisible(false);
		else 
			setVisible(true);
	}
	
	public void setSelectedIndex(int index){
		list.setSelectedIndex(index);
	}

}
