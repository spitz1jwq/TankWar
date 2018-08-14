package GUI2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class TankWar extends JFrame {
	private static final long serialVersionUID = -4752546809421493001L;
	/** ս����� ***/
	public static final int WIDTH = 500;
	/** ս���߶� **/
	public static final int HEIGHT = 400;


	/** ����� */
	boolean LK = false;
	/** �Ϲ��� */
	boolean UK = false;
	/** �ҹ��� */
	boolean RK = false;
	/** �¹��� */
	boolean DK = false;

	Wall Wall1 = new  Wall(200, 100, 20, 200, this);
	
	/** ��ž�̹�� **/
	Tank myTank = new Tank(225, 350, 50, 50, Color.RED, this,100,Wall1);
	/** �л��ļ��� */
	private List<Tank> enenryTanks = new ArrayList<Tank>();
	/** �ӵ����� **/
	public List<Shot> shotList = new ArrayList<Shot>();
	


	/**
	 * ����������
	 * 
	 * @param  margs
	 */
	public static void main(String[] args) {
		TankWar tankWar = new TankWar();
		tankWar.init();

//		RePaintStar rePaintStar = new RePaintStar(tankWar);
//		rePaintStar.start();
		new RePaintStar(tankWar).start();
	}

	/**
	 * ս����ʼ��
	 * 
	 * @author LuXuekun
	 */
	private void init() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(100, 100, WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("̹�˴�ս -V1.0");
		TankKeyListener tkl = new TankKeyListener(myTank, this);
		addKeyListener(tkl);
		enenryTanks.add(myTank);
		for (int i = 0; i < 5; i++) {
			Tank tank = new Tank(50 + (i * 50), 45, 50, 50, Color.GREEN, this,
					false);
			tank.curValue = 50;
			enenryTanks.add(tank);
		}

		setVisible(true);
	}

	/***
	 * ���һö�ڵ�
	 * 
	 * @param s
	 *            ������ڵ�
	 */


	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Wall1.draw(g);
//		myTank.draw(g);
		// ������̹��
		for (int i = 0; i < enenryTanks.size(); i++) {
			Tank tank = enenryTanks.get(i);
			if (tank.isLive) {
				tank.draw(g);
				tank.touchWall(Wall1);
			} else {
				enenryTanks.remove(i);
			}
		}
		// ���ڵ�
		for (int i = 0; i < shotList.size(); i++) {
			Shot shot = shotList.get(i);
			if (shot.isLive) {
				shot.draw(g);
				shot.hitTank(enenryTanks);
				shot.hitWall(Wall1);
			} else {
				shotList.remove(i);
			}

		}
		Color color = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("��ǰ̹������" + this.enenryTanks.size(), 10, 40);
		g.drawString("��ǰ�ڵ�����" + this.shotList.size(), 10, 60);
		g.drawString("��ǰ����ֵ��" + myTank.curValue, 10, 80);
		g.setColor(color);
	}
}

class RePaintStar extends Thread {
	JFrame jframe;

	public RePaintStar(JFrame jframe) {
		this.jframe = jframe;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (jframe) {
				jframe.repaint();
			}
		}
	}
}
