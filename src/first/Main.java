package first;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;

class Person {
	String name, address, phone;
	String id1, id2;

	// �ּ�, ��ȣ ����
	public void setAddress(String address) {this.address = address;}
	public void setPhone(String phone) {this.phone = phone;}
}

public class Main extends JFrame {
	// �ʵ�
	Vector<Person> v = new Vector<Person>();
	JPanel panel1, panel2, panel3, panel4;
	JPanel nameP, idP, phoneP, addressP;
	JPanel searchP, arrowsP, resultP;
	JPanel seeAllP, printP, orderP;
	JTextField nameT, idT1, idT2, phoneT, addressT, searchT1, searchT2;
	JTextArea printT, resultT;
	JButton leftB, rightB;
	JButton searchB, seeAllB;
	JButton addB, editB, deleteB, clearB, saveB, loadB, exitB;
	JLabel seeAllL, idL;

	// ������
	public Main() {
//		// �����ư Ȱ��ȭ
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// �����ư ��Ȱ��ȭ (�����ư�� �����Ƿ�?) (�� �����ư�� 2�� ���� ������?)
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setTitle("����� ����");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		/*** ū Ʋ : �г� (1 (3,4), 2) ***/
		// �¿� �г� ������ ���̱� (1: ����, 2: ������)
		panel1 = new JPanel();
		panel2 = new JPanel();
		c.add(panel1, BorderLayout.WEST);
		c.add(panel2, BorderLayout.CENTER);

		// ���� �г� ������ ���̱� (3: ����, 4: �Ʒ���)
		panel1.setLayout(new BorderLayout());
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel1.add(panel3, BorderLayout.NORTH);
		panel1.add(panel4, BorderLayout.CENTER);

		/*** �г� 3 ***/
		// �г� 3: (��������, �̸�, �ֹι�ȣ, ��ȭ��ȣ, �ּ�) �г� ����
		panel3.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED), "��������"));
		nameP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		idP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		phoneP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		addressP = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		JLabel nameL = new JLabel("�̸�:");
		nameT = new JTextField(20);
		nameP.add(nameL);
		nameP.add(nameT);

		// �ֹι�ȣ �г�
		idL = new JLabel("�ֹι�ȣ: ");
		idT1 = new JTextField(10);
		idT2 = new JTextField(9);
		idP.add(idL);
		idP.add(idT1);
		idP.add(idT2);

		// �ֹε�Ϲ�ȣ 6�ڷ� ����
		idT1.setName("idT1");
		idT2.setName("idT2");
		idT1.addKeyListener(new TextLimitC());
		idT2.addKeyListener(new TextLimitC());

		// ��ȭ��ȣ �г�
		JLabel phoneL = new JLabel("��ȭ��ȣ:");
		phoneT = new JTextField(20);
		phoneP.add(phoneL);
		phoneP.add(phoneT);

		// �ּ� �г�
		JLabel addressL = new JLabel("�ּ�:");
		addressT = new JTextField(20);
		addressP.add(addressL);
		addressP.add(addressT);

		panel3.add(nameP);
		panel3.add(idP);
		panel3.add(phoneP);
		panel3.add(addressP);
		panel3.setLayout(new GridLayout(4, 1));

		/*** �г� 4 ***/
		// �г� 4: (�˻�, ���, ȭ��ǥ) �г� ����
		panel4.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED), "�����˻�"));
		searchP = new JPanel();
		resultP = new JPanel();
		arrowsP = new JPanel();

		panel4.setLayout(new BorderLayout());
		panel4.add(searchP, BorderLayout.NORTH);
		panel4.add(resultP, BorderLayout.CENTER);
		panel4.add(arrowsP, BorderLayout.SOUTH);

		// �г� 4 ����: �� �г� ���� ������Ʈ
		// �˻� �г�
		JLabel searchL = new JLabel("�˻�:");
		searchT1 = new JTextField(10);
		searchT2 = new JTextField(10);
		searchB = new JButton("�˻�");
		searchP.add(searchL);
		searchP.add(searchT1);
		searchP.add(searchT2);
		searchP.add(searchB);
		searchP.setLayout(new FlowLayout());

		// �ֹι�ȣ ���ڼ� ����
		searchT1.setName("idT1");
		searchT2.setName("idT2");
		searchT1.addKeyListener(new TextLimitC());
		searchT2.addKeyListener(new TextLimitC());

		// ��� �г�
		resultT = new JTextArea(10, 30);
		resultP.add(resultT);
		resultP.add(new JScrollPane(resultT));

		// ȭ��ǥ �г�
		leftB = new JButton("<<");
		rightB = new JButton(">>");
		arrowsP.add(leftB);
		arrowsP.add(rightB);

		// �˻� ��ư �׼� ������ ����
		searchB.addActionListener(new SearchC());

		// ȭ��ǥ ��ư �׼� ������ ����

		/*** �г� 2 ***/
		// �г� 2: (��ü���� �г�, ������ �г�, ��� ��ư �г�) ���� + ����
		panel2.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED), "��������Ȯ��"));
		seeAllP = new JPanel();
		printP = new JPanel();
		orderP = new JPanel();

		panel2.add(seeAllP);
		panel2.add(printP);
		panel2.add(orderP);

		// ��ü���� �г�
		seeAllB = new JButton("��ü����");
		seeAllP.add(seeAllB);
		seeAllL = new JLabel("<== �̰��� ������ ��ü���Ⱑ �˴ϴ�.");
		seeAllP.add(seeAllL);
		seeAllP.setLayout(new FlowLayout());
		seeAllB.addActionListener(new SeeAllC());

		// ������ �г�
		printT = new JTextArea(18, 50);
		printP.add(printT);
		printP.add(new JScrollPane(printT));

		// ��� ��ư �г�
		addB = new JButton("���");
		editB = new JButton("����");
		deleteB = new JButton("����");
		clearB = new JButton("Clear");
		saveB = new JButton("����");
		loadB = new JButton("�ε�");
		exitB = new JButton("����");
		orderP.setLayout(new FlowLayout());

		orderP.add(addB);
		orderP.add(editB);
		orderP.add(deleteB);
		orderP.add(clearB);
		orderP.add(saveB);
		orderP.add(loadB);
		orderP.add(exitB);

		// ��ư�� �׼Ǹ����� ���̱�
		addB.addActionListener(new AddC());
		editB.addActionListener(new EditC());
		deleteB.addActionListener(new DeleteC());
		clearB.addActionListener(new ClearC());
		saveB.addActionListener(new SaveC());
		loadB.addActionListener(new LoadC());
		exitB.addActionListener(new ExitC());

		// ������, ���̱�, ũ�� ����
		setSize(900, 500);
		setVisible(true);
		setResizable(false);

	} // ������ ��
	
	
	
	/******************************************************************/
	// ���� �޼ҵ�
	public static void main(String[] args) {
		new Main();
	}
	/******************************************************************/
	
	
	// �˻� ��ư Ŭ����
	class SearchC implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Person p = new Person();
			p.id1 = searchT1.getText();
			p.id2 = searchT2.getText();

			for (int i = 0; i < v.size(); i++) {
				boolean b1 = p.id1.equals(v.get(i).id1);
				boolean b2 = p.id2.equals(v.get(i).id2);

				if (b1 && b2 == true) { // ���� ����� ������ (�ֹε�Ϲ�ȣ�� ����)
					int index = i;
					p = v.get(index); // p �� �ش� ���� ���Ҹ� ����ִ´�.
				}
			}

			if (v.contains(p) == true) { // ã�� ������ ������
				String s1, s2, s3 = "", s4, s5, s6;
				int year, month, date;
				s1 = "�̸�: " + p.name + "\n";
				s2 = "�ֹι�ȣ: " + p.id1 + "-" + p.id2 + "\n";

				if (p.phone.length() == 11) { // ���� ��ȭ��ȣ�� ���
					s3 += "��ȭ��ȣ: " + p.phone.substring(0, 3) + "-" + p.phone.substring(3, 7) + "-"+ p.phone.substring(7, 11) + "\n";
				} else if (p.phone.length() == 10) { // �� ��ȭ��ȣ�� ���
					s3 += "��ȭ��ȣ: " + p.phone.substring(0, 3) + "-" + p.phone.substring(3, 6) + "-"+ p.phone.substring(6, 10) + "\n";
				}else {
					s3 = "��ȭ��ȣ: " + p.phone + "\n";
				}

				s4 = "�ּ�: " + p.address + "\n";
				year = Integer.parseInt(p.id1.substring(0, 2)); // �� �ڸ��� ����, ������ ���ڴ� �������� X
				month = date = 0;

				// ��� ���� ����
				if (p.id1.charAt(0) != '0') { // 00����� �ƴϸ�
					year += 1900;	// 1900 �����ش�.
				} else {
					year += 2000;
				}

				// ��� �� ����
				if (p.id1.charAt(2) == '0') { // 10�� ~ 12�� ���� �ƴϸ� (���ڸ��� 0�̸�)
					month = Integer.parseInt(p.id1.substring(3, 4)); // �ѱ��ڸ� �߶����
				} else {
					month = Integer.parseInt(p.id1.substring(2, 4));
				}

				// ��� ��¥ ����
				if (p.id1.charAt(4) == '0') { // ���� �ڸ� ���� 0�̸�
					date = Integer.parseInt(p.id1.substring(5, 6));
				} else { // �ƴϸ�
					date = Integer.parseInt(p.id1.substring(4, 6));
				}

				s5 = "�������: " + year + "�� " + month + "�� " + date + "��";
				s6 = s1 + s2 + s3 + s4 + s5;

				// ȭ�鿡 ���
				resultT.setText(s6);

			} else { // ã�� ������ ������
				JOptionPane.showMessageDialog(null, "������ ���� ������ ���� ����� �����ϴ�.");
			}

		}
	}

	/******************************************************************/

	// �ε� ��ư Ŭ����
	class LoadC implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String s1, s2, s3;
			JFrame jf = new JFrame();

			// printT�� ���� ���
			s1 = "���� �б� �Ϸ�!\n";
			s2 = "�� ���Ϸκ��� �б⸦ �Ϸ��Ͽ����ϴ�.\n";
			s3 = "�� ������ ���� : " + v.size() + "��";
			printT.setText(s1 + s2 + s3);

			// ��ٷȴٰ�
			try {
				Thread.sleep(1000); // 1�� ���
				printT.setText(""); // ȭ�� �����
				Thread.sleep(1000); // 1�� ���
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			// 1. FileDialog�� ���� �ҷ��� ���� ����
			FileDialog dialog = new FileDialog(jf, s3, FileDialog.LOAD);
			dialog.setDirectory(".");
			dialog.setVisible(true);

			// 2. FileDialog�� ������ ����Ǿ�����
			if (dialog.getFile() == null)
				return;

			// 3. �ҷ��� ������ ��θ� ����
			String dfName = dialog.getDirectory() + dialog.getFile();

			// 4. ���� ����, TextArea�� �ѷ��ֱ�
			try {
				BufferedReader reader = new BufferedReader(new FileReader(dfName));
				printT.setText("");
				String line;
				while ((line = reader.readLine()) != null) { // ���� ���ڿ��� ���������� �ݺ�
					printT.append(line + "\n"); // ���پ� TextArea�� �߰�
				}
				reader.close();
				setTitle(dialog.getFile());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "���� ����");

			}
		}
	}

	/******************************************************************/

	private static File fileOpenDlg() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("C:/test"));
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) { // �����ϸ� ������ ������ �������� �����Ѵ�.
			File f = chooser.getSelectedFile();
			return f;
		}
		return null;
	}

	/******************************************************************/
	// ���� ��ư Ŭ����
	class SaveC implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// ���Ͽ� ���� ���� s5
			String s1, s2, s3, s4, s5;
			s1 = "��ü �ο��� �������� �Դϴ�.\n";
			s2 = "�̸�\t�ֹι�ȣ\t\t��ȭ��ȣ\t\t�ּ�\n";
			s3 = "============================================================================\n";
			s4 = "";
			for (int i = 0; i < v.size(); i++) {
				Person p = v.get(i);
				s4 += p.name + "\t" + p.id1 + "-" + p.id2 + "\t" + p.phone + "\t\t\t" + p.address + "\n";
			}
			s5 = s1 + s2 + s3 + s4;

			// ���� �̸�
			String fileName = "project01.txt";
			try {
				// ���� ��ü ����
				File f = new File(fileName);

				// false ���� �� ������ ���� ���뿡 �̾ �ۼ����� ����
				FileWriter fw = new FileWriter(f, false);
				fw.write(s5);
				fw.close();

				String s6 = "���� �Ϸ�!\n";
				String s7 = f.getAbsolutePath() + "�� �̸����� ����Ǿ����ϴ�.";
				System.out.println(s7);
				printT.setText(s6 + s7);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/******************************************************************/

// Clear ��ư Ŭ����
	class ClearC implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("Clear")) { // ���� �Է� ���� �����
				nameT.setText("");
				idT1.setText("");
				idT2.setText("");
				phoneT.setText("");
				addressT.setText("");
			}
		}
	}

	/******************************************************************/

// ��� ��ư Ŭ����
	class AddC implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("���")) {
				Person p = new Person();
				p.name = nameT.getText();
				p.address = addressT.getText();
				p.phone = phoneT.getText();
				p.id1 = idT1.getText();
				p.id2 = idT2.getText();

				boolean b1, b2, b3, b4, b5, b6;
				b1 = (p.name.length() == 0); // �̸� �Է� ���ϸ�
				b2 = (p.address.length() == 0); // �ּ� �Է� ���ϸ�
				b3 = (p.phone.length() == 0); // ��ȭ��ȣ �Է� ���ϸ�
				b4 = (p.id1.length() == 6); // �ֹε�Ϲ�ȣ ���ڸ��� 6�ڸ����� �˻�
				b5 = (p.id2.length() == 7); // �ֹε�Ϲ�ȣ ���ڸ��� 7�ڸ����� �˻�
				b6 = ((b4 && b5) != true); // �ֹε�Ϲ�ȣ �ڸ����� �ȸ�����

				// �̹� ���� �̸��� �ֹι�ȣ�� ���� ����� �ִٸ� �߰����� ����
				for (int i = 0; i < v.size(); i++) {	
					boolean b7 = p.id1.equals(v.get(i).id1);
					boolean b8 = p.id2.equals(v.get(i).id2);

					if (b7 && b8 == true) { // ���� ����� ������
						JOptionPane.showMessageDialog(null, "�̹� ��ϵ� ����Դϴ�.");
						return;
					}
				}

				// �̸��� �Է����� ������
				if (b1) {
					JOptionPane.showMessageDialog(null, "�̸��� �Է��� �ּ���.");
				} else {

					// �ּ�/��ȭ��ȣ�� �Է����� ������
					if (b2) {
						p.address = "���Է�";
					}
					if (b3) {
						p.phone = "���Է�";
					}

					// �ֹε�Ϲ�ȣ �ڸ��� �˻�
					if (b6) { // �ֹε�Ϲ�ȣ �ڸ����� ���� ������
						JOptionPane.showMessageDialog(null, "�ֹε�Ϲ�ȣ�� �߸� �Է��Ͽ����ϴ�.");
					}
				}

				// ��� ������ ����Ѵ�.
				try {
					if (!b1 && !b6) { // �̸��� �� ���ڶ� �ԷµǾ� �ְ� �ֹε�Ϲ�ȣ �ڸ����� �´ٸ�
						v.add(p);
						String str = "������ �� �Ǿ����ϴ�.\n";
						printT.setText(str + "���� " + v.size() + "���� �����Ͱ� �����մϴ�.");
					}
				} catch (Exception ee) {
					ee.getStackTrace();
					b.addActionListener(new ErrC());
				}
			}
		}
	}

	/******************************************************************/
// ��ü���� Ŭ����
	class SeeAllC implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			String s1, s2, s3, s4, s5, s6;
			if (b.getText().equals("��ü����")) {
				s1 = "��ü �ο��� �������� �Դϴ�.\n";
				s2 = "�̸�\t�ֹι�ȣ\t\t��ȭ��ȣ\t\t�ּ�\n";
				s3 = "============================================================================\n";
				s4 = "";
				s5 = s3 + "�� " + v.size() + "��";
				for (int i = 0; i < v.size(); i++) {
					Person p = v.get(i);
					s4 += p.name + "\t" + p.id1 + "-" + p.id2 + "\t" + p.phone + "\t\t" + p.address + "\n";
				}
				s6 = s1 + s2 + s3 + s4 + s5;
				if (v.size() == 0) {
					printT.setText("�����Ͱ� �����ϴ�.");
				} else {
					printT.setText(s6);
				}

			}
		}

	}

	/******************************************************************/
// ���� ��ư Ŭ����
	class EditC implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new EditWindowC();
		}
	}

	/******************************************************************/
// ���� ��ư Ŭ����
	class DeleteC implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new DeleteWindowC();
		}
	}

	/******************************************************************/
	@SuppressWarnings("serial")
// ���ο� ���� â
	class DeleteWindowC extends JFrame {
		public DeleteWindowC() {
			WindowC w = new WindowC();
			Container c = w.getContentPane();
			w.setTitle("����");
			c.setLayout(new GridLayout(3, 1));

			JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));

			JButton deleteB = new JButton("����");
			JButton cancelB = new JButton("���");

			p.add(deleteB);
			p.add(cancelB);

			// ���� ��ư�� �׼� ������ ����
			deleteB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// �Է��� �̸��� �ֹι�ȣ�� ���� Person ��ü ����
					Person p = new Person();
					p.name = w.getNameT().getText();
					p.id1 = w.getIdT1().getText();
					p.id2 = w.getIdT2().getText();

					for (int i = 0; i < v.size(); i++) {
						boolean b1 = p.name.equals(v.get(i).name);
						boolean b2 = p.id1.equals(v.get(i).id1);
						boolean b3 = p.id2.equals(v.get(i).id2);

						if (b1 && b2 && b3 == true) { // ���� ����� ������
							int index = i;
							p = v.get(index); // p �� �ش� ���� ���Ҹ� ����ִ´�.
						}
					}

					if (v.contains(p)) { // ã�� ������ ������
						// ȭ�� ���
						String s1, s2, s3, s4, s5, s6, s7;
						s1 = "������ ����ڸ� ������ ����ҿ��� �����Ͽ����ϴ�.\n";
						s2 = "���� !!!\n";
						s3 = "�̸� : " + p.name + "\n";
						s4 = "�ֹι�ȣ : " + p.id1 + "-" + p.id2 + "\n";
						s5 = "�ּ� : " + p.address + "\n";
						s6 = "���� ���� ������ ���� : " + (v.size() - 1);
						s7 = s1 + s2 + s3 + s4 + s5 + s6;
						printT.setText(s7);

						// ã�� ������ ��Ҹ� �����Ѵ�.
						v.remove(p);
						return;

					} else { // ã�� ������ ������
						JOptionPane.showMessageDialog(null, "������ ���� ������ ���� ����� �����ϴ�.");
					}
				}

			});

			// ��� ��ư�� �׼� ������ ����
			cancelB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					w.dispose();
				}
			});

			c.add(p);
		}
	}

	/******************************************************************/
	// ���ο� ���� â
	@SuppressWarnings("serial")
	class EditWindowC extends JFrame {
		WindowC w = new WindowC();
		Person p = new Person();

		public EditWindowC() {
			w.setTitle("����");
			Container c = w.getContentPane();
			c.setLayout(new GridLayout(3, 1));
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

			JButton editB = new JButton("����");
			JButton cancelB = new JButton("���");
			panel.add(editB);
			panel.add(cancelB);
			c.add(panel);

			// ���� ��ư �׼� ������
			editB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// �Է��� �̸��� �ֹι�ȣ�� ���� Person ��ü ����
					p.name = w.getNameT().getText();
					p.id1 = w.getIdT1().getText();
					p.id2 = w.getIdT2().getText();

					// ��� �˻��ϱ�
					for (int i = 0; i < v.size(); i++) {
						boolean b1, b2, b3;
						b1 = p.name.equals(v.get(i).name);
						b2 = p.id1.equals(v.get(i).id1);
						b3 = p.id2.equals(v.get(i).id2);

						if (b1 && b2 && b3 == true) { // ���� ����� ������
							int index = i;
							p = v.get(index); // p �� �ش� ���� ���Ҹ� ����ִ´�.
							printT.setText("�ش� ����ڸ� ã�ҽ��ϴ�.. �����ϼ���.");
						}
					}

					// �Է��� �̸��� �ֹι�ȣ ���� ���� ���������� �ִ��� Ȯ��
					if (v.contains(p) == true) { // �Է��� ������ ���� ����� �ִٸ�
						// �Է��� ������ �󺧷� �����
						Container c = w.getContentPane();
						CardLayout card = new CardLayout(); // ī�� ���̾ƿ� ����
						c.setLayout(card);

						JPanel newPanel = new JPanel();
						JPanel panel1 = new JPanel(new GridLayout(1, 1));
						JPanel panel2 = new JPanel(new GridLayout(1, 1));
						JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

						JLabel nameT = new JLabel("                �̸�:   " + p.name);
						JLabel idNumT = new JLabel("        �ֹι�ȣ:   " + p.id1 + "-" + p.id2);
						JLabel phoneT = new JLabel("��ȭ��ȣ: ");
						JTextField phoneTF = new JTextField(20);
						JLabel addressT = new JLabel("�ּ�: ");
						JTextField addressTF = new JTextField(20);
						JButton editB = new JButton("����");
						JButton cancelB = new JButton("���");

						panel1.add(nameT);
						panel2.add(idNumT);
						panel3.add(phoneT);
						panel3.add(phoneTF);
						panel4.add(addressT);
						panel4.add(addressTF);
						panel5.add(editB);
						panel5.add(cancelB);

						newPanel.setLayout(new GridLayout(5, 1));
						newPanel.add(panel1);
						newPanel.add(panel2);
						newPanel.add(panel3);
						newPanel.add(panel4);
						newPanel.add(panel5);

						// ���� ��ư�� �׼Ǹ����� ����
						editB.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								p.phone = phoneTF.getText();
								p.address = addressTF.getText();
								JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
								w.dispose();
							}
						});

						// ��ҹ�ư�� �׼Ǹ����� ����. â �ݱ�
						cancelB.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								w.dispose();
							}
						});

						c.removeAll(); // ���� ������Ʈ ��� ����
						c.add(newPanel);
						w.setSize(310, 210);
						w.setResizable(false);
						card.previous(c); // Ŭ���ϸ� ���� ī�� ���̾ƿ�����

					} else { // ã�� ����� ���ٸ� ���â
						JOptionPane.showMessageDialog(null, "������ ���� ������ ���� ����� �����ϴ�.");
					}
				}
			});

			// ��ҹ�ư �׼� ������
			cancelB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// ���� â �ݱ�
					// System.exit() �� ����ϸ� ū â�� ����ȴ�. (���� ����)
					// System.exit(0) : ���� ����, System.exit(1) : ������ ����
					w.dispose(); // �̰� ���� ��̴�.
				}
			});

		}
	}

	/******************************************************************/
	@SuppressWarnings("serial")
	// ���ο� â
	class WindowC extends JFrame {
		JLabel nameL = new JLabel("�̸�:");
		JLabel idL = new JLabel("�ֹι�ȣ:");
		JTextField nameT = new JTextField(20);
		JTextField idT1 = new JTextField(8);
		JTextField idT2 = new JTextField(9);

		// �̸�/�ֹι�ȣ ����
		public JTextField getNameT() {
			return nameT;
		}

		public JTextField getIdT1() {
			return idT1;
		}

		public JTextField getIdT2() {
			return idT2;
		}

		// ������
		public WindowC() {
			// ����, ���⼭ setDefaultCloseOperation() ���Ǹ� ���� ���ƾ� �Ѵ�
			// �����ϰ� �Ǹ� �� â�� ������ ��� â�� ���α׷��� ���ÿ� ������
			// ��ó: https://arintvsecond.tistory.com/14

			JPanel editP = new JPanel();
			setContentPane(editP);
			JPanel nameP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JPanel idP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			editP.setLayout(new GridLayout(2, 1));

			editP.add(nameP);
			editP.add(idP);

			editP.setLayout(new GridLayout(2, 1));
			nameP.add(nameL);
			nameP.add(nameT);
			idP.add(idL);
			idP.add(idT1);
			idP.add(idT2);

			// �ֹι�ȣ ���ڼ� ����
			idT1.setName("idT1");
			idT2.setName("idT2");
			idT1.addKeyListener(new TextLimitC());
			idT2.addKeyListener(new TextLimitC());

			setSize(260, 180);
			setLocation(300, 300);
			setResizable(true);
			setVisible(true);
		}

	}

	/******************************************************************/

// ���� ó��
	class ErrC implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, e.getActionCommand() + "���� ���߽��ϴ�!");
		}
	}

	/******************************************************************/
// ���� ��ư Ŭ����
	class ExitC implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("����")) {
				System.exit(0);
			}
		}
	}

	/******************************************************************/

// �ֹε�Ϲ�ȣ ���ڼ� ���� Ŭ����
	class TextLimitC extends KeyAdapter {
		public void keyTyped(KeyEvent ke) {
			JTextField src = (JTextField) ke.getSource();
			if (src.getName().equals("idT1")) {
				if (src.getText().length() >= 6) {
					ke.consume(); // ���ڼ� �ʰ��ϸ� �����
				}
			}

			else if (src.getName().equals("idT2")) {
				if (src.getText().length() >= 7) {
					ke.consume(); // ���ڼ� �ʰ��ϸ� �����
				}
			}

			else {
				System.out.println("�߸������̽��ϴ�.");
			}
		}
	}

}
// public Class ��
