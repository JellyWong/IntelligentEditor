package wanggd;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class VTextArea extends JPanel{

	private static final long serialVersionUID = -6039886719411071521L;
	private static final int PADDING = 10;

	static String[] candidates = null;
	private String[][] doc ;
	
	private int width,height;

	private MyTextArea textArea;
	private RecommendList list;
	
	private String lastWord;

	public VTextArea(int width,int height){
		
		this.width=width;
		this.height=height;
		
		initDictionary();
		initComponent();
		
		setLayout(null);
		setSize(height,width);

		add(list);
		list.setVisible(false);
		add(textArea);
		setVisible(true);
	}

	public void initDictionary(){
		doc = Utils.getWordList(); 
		ErrorCorrection.init(doc);
	}	
	
	public void initComponent(){
		textArea = new MyTextArea();
		textArea.setLineWrap(true);
		textArea.setFont(new Font(null, Font.BOLD, 20));
		list = new RecommendList(new ChoiceList(new String[]{"Hello","World"},textArea));
		
		textArea.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				// TODO Auto-generated method stub
				try {
					textArea.processEvent(e);

					lastWord=textArea.getLastWord();

					candidates=ErrorCorrection.hint(lastWord);
					if(candidates!=null){
						list.setWords(candidates);
						list.setSelectedIndex(0);
						list.setPosition(textArea.getPanelPosition());
						list.setVisible(true);
						list.highlight();
						//one needs to set the component visible before requesting focus
						if(list.isVisible())
							list.requestFocus();
					}
				}
				catch (Exception e2) {
					//e2.printStackTrace();
				}
			}
		});


		textArea.setBounds(PADDING,PADDING,width-PADDING,height-PADDING);

	}
	
}





