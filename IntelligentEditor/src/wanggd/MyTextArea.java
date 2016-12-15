package wanggd;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Point;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class MyTextArea extends JTextArea{

	private static final long serialVersionUID = 6665164454483952027L;
	/*
	 * row,column represent the position of the cursor respectively.
	 */
	private int row;
	private int column;
	private String lastWord;
	private int start,end;
	private int lastWord_start_pos,lastWord_end_pos;
	
	private final Highlighter.HighlightPainter painter=new DefaultHighlighter.DefaultHighlightPainter(Color.PINK);
	private Highlighter h;

	public MyTextArea(){
		h=getHighlighter();
	}

	public void processEvent(CaretEvent e){
		try {
			// Show the caret's position in the status bar.
			row=getLineOfOffset(e.getDot());
			column=e.getDot() - getLineStartOffset(getRow());
			//System.out.println("Line: " + row + ", Column: " + column);
			String text = getText();
			if(column==0) return ;
			int length = text.length();
			char lastChar = text.charAt(length-1);
			start = length-2;
			end = length-1;
			if(start<0) return ;
			if(lastChar==' '||lastChar==','||lastChar=='.'
					||lastChar=='?'||lastChar=='!'){
				if(lastChar==' '&&(text.charAt(start)=='.'||text.charAt(start)=='?'||
						text.charAt(start)=='!')) 
					return ;
				while(start>=1&&text.charAt(start)!=' '&&text.charAt(start)!='\n') start--;
				lastWord = text.substring(start, end).trim().toLowerCase();
				lastWord_start_pos=start;
				lastWord_end_pos=end;
				if(lastWord_start_pos!=0)
					lastWord_start_pos++;
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public void removeAllHighlights(){
		h.removeAllHighlights();
	}

	public void highlight(){
		removeAllHighlights();
		try {
			h.addHighlight(lastWord_start_pos, lastWord_end_pos, painter);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getLastWord(){
		String copy = new String(lastWord);
		lastWord=null;
		return copy;
	}


	public void replaceWord(String newWord){
		if(start==0)
			replaceRange(newWord,start,end);
		else 
			replaceRange(newWord,start+1,end);
	}

	public Point getPanelPosition(){
		FontMetrics fm=getFontMetrics(getFont());
		String lastLine=getText().split("\n")[row];
		String text=getText();
		int act_row = row;
		int width=SwingUtilities.computeStringWidth(fm,lastLine)-
				SwingUtilities.computeStringWidth(fm, text.substring(lastWord_start_pos, text.length()));
		int height=fm.getHeight();

		return new Point(width,(act_row+1)*height+10);
	}

	public Point getCursorPosition(){
		FontMetrics fm=getFontMetrics(getFont());
		String lastLine=getText().split("\n")[row];
		int width=SwingUtilities.computeStringWidth(fm,lastLine);
		int height=fm.getHeight();
		return new Point(width,(row+1)*height+10);
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

}
