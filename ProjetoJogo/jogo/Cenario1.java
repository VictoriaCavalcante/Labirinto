package jogo;

import jplay.Keyboard;
import jplay.Mouse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.JOptionPane;
import jplay.Scene;
import jplay.Sound;
import jplay.TileInfo;
import jplay.Time;
import jplay.Window;
import jplay.GameImage;

public class Cenario1
{

	private Window janela;
	private Scene cena;
	Jogador jogador;
	Keyboard teclado;
	Time tempo;	

	public Cenario1(Window window)
	{
		janela = window;
		cena = new Scene();
		cena.loadFromFile("/scn/cenario1.scn"); //arquivo com a matriz do cenário
		jogador = new Jogador(0,50); //posição inicial do jogador

		cena.setDrawStartPos(0, 0);
		cena.addOverlay(jogador);

		teclado = janela.getKeyboard();
		teclado.addKey(KeyEvent.VK_CONTROL);
				
		tempo = new Time(0, 2, 30, 700, 20, new Font(Font.SERIF,Font.BOLD, 18),Color.BLACK, false);

		run();
	}

	boolean perdeu = false;
	boolean ganhou = false;
	boolean voltanota = false;
	boolean voltadce = false;
	boolean voltanota2 = false;
	boolean voltaprofessor = false;
	boolean voltaprofessora = false;
	boolean voltaprova = false;
	boolean voltaredes = false;
	boolean voltasono = false;
	boolean pegarvida = false;

	int vidas = 3;

	Sound meuamigo = new Sound("/sons/meuamigo.wav");

	public void run()
	{	
		Mouse mouse = janela.getMouse();
		boolean executando = true;
		//criando obstáculos e posicionando
		GameImage nota = new GameImage("/imagens/n1.png");
		nota.x = 225;
		nota.y = 50;
		GameImage dce = new GameImage("/imagens/dce.png");
		dce.x = 50;
		dce.y = 50;
		GameImage nota2 = new GameImage("/imagens/n2.png");
		nota2.x = 425;
		nota2.y = 50;
		GameImage professor = new GameImage("/imagens/professor.png");
		professor.x = 775;
		professor.y = 450;
		GameImage professora = new GameImage("/imagens/professora.png");
		professora.x = 250;
		professora.y = 374;
		GameImage prova = new GameImage("/imagens/prova.png");
		prova.x = 450;
		prova.y = 550;
		GameImage redes = new GameImage("/imagens/redes.png");
		redes.x = 675;
		redes.y = 225;
		GameImage sono = new GameImage("/imagens/sono.png");
		sono.x = 700;
		sono.y = 350;
		GameImage troll = new GameImage("/imagens/troll.png");
		troll.x = 725;
		troll.y = 225;
		GameImage formatura = new GameImage("/imagens/formatura.png");
		GameImage voltar = new GameImage("/imagens/voltar.png");
		voltar.x = 20 ; voltar.y = 510;
		GameImage fundo = new GameImage("/imagens/fundo800x600.png");
		GameImage amigo = new GameImage("/imagens/amigo.png");
		amigo.x = 150; amigo.y = 15;
		GameImage vida = new GameImage("/imagens/vida.png");
		vida.x = 425; vida.y = 550;
		
		while(executando)
		{
			cena.draw();
			jogador.draw();

			cena.moveScene(jogador);

			tempo.draw();
			janela.drawText("♥ "+String.valueOf(vidas), 600, 20, Color.BLACK);

			nota.draw();
			dce.draw();
			nota2.draw();
			professor.draw();
			professora.draw();
			prova.draw();
			redes.draw();
			sono.draw();
			vida.draw();

			//condiçoes para a movimentação
			//nota
			if(nota.y == 50)
				voltanota = false;
			else if(nota.y == 200)
				voltanota = true;
			//dce
			if(dce.y == 50)
				voltadce = false;
			else if(dce.y == 125)
				voltadce = true;
			//nota2
			if(nota2.x == 425)
				voltanota2 = false;
			else if(nota2.x == 700)
				voltanota2 = true;
			//professor
			if(professor.y == 450)
				voltaprofessor = false;
			else if(professor.y == 525)
				voltaprofessor = true;
			//professora
			if(professora.y == 374)
				voltaprofessora = false;
			else if(professora.y == 550)
				voltaprofessora = true;
			//prova
			if(prova.x == 450)
				voltaprova = false;
			else if(prova.x  == 600)
				voltaprova = true;
			//rede social
			if(redes.y == 225)
				voltaredes = false;
			else if(redes.y == 300)
				voltaredes = true;
			//sono
			if(sono.x == 700)
				voltasono = false;
			else if(sono.x == 775)
				voltasono = true;

			//movimentação
			if(voltanota == false)
				nota.y = nota.y + 0.5;
			else
				nota.y = nota.y - 0.5;

			if(voltadce == false)
				dce.y = dce.y + 0.25;
			else
				dce.y = dce.y - 0.25;

			if(voltanota2 == false)
				nota2.x = nota2.x + 0.50;
			else
				nota2.x = nota2.x - 0.25;

			if(voltaprofessor == false)
				professor.y = professor.y + 0.50;
			else
				professor.y = professor.y - 0.50;

			if(voltaprofessora == false)
				professora.y = professora.y + 1;
			else
				professora.y = professora.y - 1;

			if(voltaprova == false)
				prova.x = prova.x + 0.5;
			else
				prova.x = prova.x - 0.5;

			if(voltaredes == false)
				redes.y = redes.y + 0.5;
			else
				redes.y = redes.y - 0.5;

			if(voltasono == false)
				sono.x = sono.x + 0.5;
			else
				sono.x = sono.x - 0.5;


			if(controlarCaminho()) //verifica se o jogador chegou ao objetivo
			{
				while(executando)
				{
					formatura.draw();
					voltar.draw();
					janela.update();
					if(mouse.isOverObject(voltar) && mouse.isLeftButtonPressed())
					{
						executando = false;
					}
					
				}
				
				executando = false;
			}
			jogador.move(teclado);

			if(teclado.keyDown(Keyboard.ESCAPE_KEY)) //volta para a tela inicial caso o usuário aperte "ESC" durante o jogo
			{
				executando = false;
			}
			
			if(jogador.collided(vida))//concede uma vida a mais mais o jogador.
			{
				vida.x = -50;
				vidas = vidas + 1;
			}

			if ((jogador.x >= 765 && jogador.x <=800) && (jogador.y <=25))//teleporta o jogador do canto superior direito da tela para canto inferior direito
			{
				new Sound("/sons/entrandonoportal.wav").play();
				jogador.moveTo(jogador.x , 50 , 550);
				jogador.update();
			}

			if(tempo.timeEnded()) //tira uma vida do jogador se o tempo acabar
			{
				JOptionPane.showMessageDialog(null, "Seu tempo acabou.");
				vidas = vidas - 1;
				jogador.x = 0;
				jogador.y = 50;
				tempo = new Time(0, 2, 30, 700, 20, new Font(Font.SERIF,Font.BOLD, 18),Color.BLACK, false);
			}
			if ((jogador.x >= 725 && jogador.x <= 775) && (jogador.y >= 180 && jogador.y <=225))//mostra o obstáculo escondido quando o jogador se aproxima
			{
				troll.draw();
				if(jogador.collided(troll))//tira uma vida se o jogador tentar pegar o atalho
				{
					JOptionPane.showMessageDialog(null, "Nada de pegar atalhos para a graduação.");
					vidas = vidas - 1;
					jogador.x = 0;
					jogador.y = 50;
					tempo = new Time(0, 2, 30, 700, 20, new Font(Font.SERIF,Font.BOLD, 18),Color.BLACK, false);
				}
			}

			//colisões com obstáculos

			if(jogador.collided(nota))
			{
				JOptionPane.showMessageDialog(null, "Você foi atingido.");
				vidas = vidas - 1;
				jogador.x = 0;
				jogador.y = 50;
				tempo = new Time(0, 2, 30, 700, 20, new Font(Font.SERIF,Font.BOLD, 18),Color.BLACK, false);
			}

			if(jogador.collided(dce))
			{
				JOptionPane.showMessageDialog(null, "Você foi atingido.");
				vidas = vidas - 1;
				jogador.x = 0;
				jogador.y = 50;
				tempo = new Time(0, 2, 30, 700, 20, new Font(Font.SERIF,Font.BOLD, 18),Color.BLACK, false);
			}

			if(jogador.collided(nota2))
			{
				JOptionPane.showMessageDialog(null, "Você foi atingido.");
				vidas = vidas - 1;
				jogador.x = 0;
				jogador.y = 50;
				tempo = new Time(0, 2, 30, 700, 20, new Font(Font.SERIF,Font.BOLD, 18),Color.BLACK, false);
			}

			if(jogador.collided(professor))
			{
				JOptionPane.showMessageDialog(null, "Você foi atingido.");
				vidas = vidas - 1;
				jogador.x = 0;
				jogador.y = 50;
				tempo = new Time(0, 2, 30, 700, 20, new Font(Font.SERIF,Font.BOLD, 18),Color.BLACK, false);
			}

			if(jogador.collided(professora))
			{
				JOptionPane.showMessageDialog(null, "Você foi atingido.");
				vidas = vidas - 1;
				jogador.x = 0;
				jogador.y = 50;
				tempo = new Time(0, 2, 30, 700, 20, new Font(Font.SERIF,Font.BOLD, 18),Color.BLACK, false);
			}

			if(jogador.collided(prova))
			{
				JOptionPane.showMessageDialog(null, "Você foi atingido.");
				vidas = vidas - 1;
				jogador.x = 0;
				jogador.y = 50;
				tempo = new Time(0, 2, 30, 700, 20, new Font(Font.SERIF,Font.BOLD, 18),Color.BLACK, false);
			}

			if(jogador.collided(redes))
			{
				JOptionPane.showMessageDialog(null, "Você foi atingido.");
				vidas = vidas - 1;
				jogador.x = 0;
				jogador.y = 50;
				tempo = new Time(0, 2, 30, 700, 20, new Font(Font.SERIF,Font.BOLD, 18),Color.BLACK, false);
			}

			if(jogador.collided(sono))
			{
				JOptionPane.showMessageDialog(null, "Você foi atingido.");
				vidas = vidas - 1;
				jogador.x = 0;
				jogador.y = 50;
				tempo = new Time(0, 2, 30, 700, 20, new Font(Font.SERIF,Font.BOLD, 18),Color.BLACK, false);
			}

			if(vidas == 0)
			{
				meuamigo.play();
				while(executando)
				{
					fundo.draw();
					amigo.draw();
					voltar.draw();
					janela.update();
					if(mouse.isOverObject(voltar) && mouse.isLeftButtonPressed())
					{
						executando = false;
					}
					
				}
				
				executando = false;
			}

			janela.delay(0); //não pode ser utilizado devido o tempo que aparece na tela
			jogador.move(teclado);
			janela.update();
		}
	}

	boolean controlarCaminho()
	{
		Point min = new Point((int)jogador.x, (int)jogador.y);
		Point max = new Point((int)(jogador.x + jogador.width), (int)(jogador.y + jogador.height));

		Vector<TileInfo> tiles = cena.getTilesFromRect(min, max);

		for(int i = 0 ; i < tiles.size() ; i++){
			TileInfo tile = (TileInfo)tiles.elementAt(i);

			if((tile.id == 1) && jogador.collided(tile))
			{ 
				if(jogador.y > tile.y  + tile.height -2)
				{
					jogador.y = tile.y + tile.height;
				}
				else if(tile.y > jogador.y  + jogador.height -2)
				{
					jogador.y = tile.y - jogador.height;
				}
				else if(jogador.x + jogador.width - 2 < tile.x)
				{
					jogador.x = tile.x - jogador.width;
				}
				else 
				{
					jogador.x = tile.x + tile.width;
				}
				new Sound("/sons/colisao.wav").play();
			}
			else if(tile.id == 5 && jogador.collided(tile))
			{
				return true;
			}

		}
		return  false;
	}
}




