package wanggd;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JScrollPane;

public class RecommendList extends JScrollPane{

	private static final long serialVersionUID = 1751204086973463064L;
	private ChoiceList list ;
	
	public RecommendList(ChoiceList list) {
		super(list);
		this.list=list;
		setPosition(new Point(0,0));
		setLayout(null);
		add(list);
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
				list.dispatchEvent(e);
				int keyCode = e.getKeyCode();
				if(keyCode==KeyEvent.VK_ENTER){
					list.replaceWord(getSelection());
					removeAllHighlights();
					setVisible(false);
				}
				else if(keyCode!=KeyEvent.VK_DOWN&&keyCode!=KeyEvent.VK_UP){
					setVisible(false);
					removeAllHighlights();
				}
					
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
		list.setBounds(0, 0, getWidth(), getHeight());
		this.setBounds(p.x, p.y, getWidth(), getHeight()-100);
	}
	
	public String getSelection() {
		return list.getSelection();
	}
	
	public String[] getWords() {
		return list.getWords();
	}
	public void setWords(String[] words) {
		list.setListData(words);
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
