package wanggd;

import javax.swing.JFrame;

public class Test extends JFrame{

	private static final long serialVersionUID = -6039886719411071521L;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(1040, 550);
		VTextArea vTextArea = new VTextArea(1000, 500);
		frame.add(vTextArea);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}









