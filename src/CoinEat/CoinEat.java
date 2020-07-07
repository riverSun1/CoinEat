package CoinEat;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class CoinEat extends JFrame implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	private Image backgroundImage = new ImageIcon("images/배경.png").getImage();
	private Image player, coin;
	private int playerX = 0, playerY = 0, sel = 5;
	private int coinX = ((int)(Math.random()*250)+30);
	private int coinY = ((int)(Math.random()*250)+30);
	int score = 0;
	
	public CoinEat() {
		this.setTitle("티끌모아태산");
		this.setBounds(0, 0, 300, 300);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		playerX = this.getWidth()/2;
		playerY = this.getHeight()/2;
		addKeyListener(this);
	}

	@Override
	public void paint(Graphics g) {
		switch (sel) {
		case 1:
			player = Toolkit.getDefaultToolkit().getImage("images/오른쪽.png");
			break;
		case 2:
			player = Toolkit.getDefaultToolkit().getImage("images/오른쪽.png");
			break;
		case 3:
			player = Toolkit.getDefaultToolkit().getImage("images/왼쪽.png");
			break;
		case 4:
			player = Toolkit.getDefaultToolkit().getImage("images/왼쪽.png");
			break;
		case 5:
			player = Toolkit.getDefaultToolkit().getImage("images/앞.png");
			break;
		case 6:
			player = Toolkit.getDefaultToolkit().getImage("images/앞.png");
			break;
		case 7:
			player = Toolkit.getDefaultToolkit().getImage("images/뒤.png");
			break;
		case 8:
			player = Toolkit.getDefaultToolkit().getImage("images/뒤.png");
			break;
		}

		coin = Toolkit.getDefaultToolkit().getImage("images/100coin.png");
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(backgroundImage, 0, 0, null);
		g.drawImage(coin, coinX, coinY ,this);
		g.drawImage(player, playerX - player.getWidth(this)/2, playerY - player.getHeight(this) /2 , this);
		g.drawString("누적금액 : " + score, 190, 55);
		eat();
	}

	public void eat(){
		if( playerX + player.getWidth(this) > coinX && coinX + coin.getWidth(this) > playerX && playerY + player.getHeight(this)/2 > coinY && coinY + coin.getHeight(this)/2 > playerY){
			coinX = (int)(Math.random()*270);
			coinY = (int)(Math.random()*270);
			score+= 100;

			if(score == 1000){
				int m = JOptionPane.showConfirmDialog(this, "다시할래?","끝", JOptionPane.YES_NO_OPTION);

				switch(m){
				case JOptionPane.YES_OPTION:
					playerX = 150;
					playerY = 150;
					coinX = (int)(Math.random()*270);
					coinY = (int)(Math.random()*270);
					score = 0;
					return;
				case JOptionPane.NO_OPTION:
					System.exit(0);
				}
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT){
			sel = ( sel == 1)?2:1;
			playerX = (playerX < getWidth())? playerX +=15 : -player.getWidth(this);
			
		}else if(key == KeyEvent.VK_LEFT){
			sel = ( sel == 3)?4:3;
			playerX-=15;
			if(playerX <= -30) playerX = 300;

		}else if(key == KeyEvent.VK_DOWN){
			sel = ( sel == 5)?6:5;
			playerY+=15;
			if(playerY >= 300) playerY = -30;

		}else if(key == KeyEvent.VK_UP){
			sel = ( sel == 7)?8:7;
			playerY-=15;
			if(playerY < -30) playerY = 300;
		}

		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static void main(String[] args) throws Exception {
		new CoinEat();
	}
}