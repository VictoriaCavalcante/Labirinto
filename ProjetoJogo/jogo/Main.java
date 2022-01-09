package jogo;

import java.awt.Color;
import java.awt.Font;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Mouse;
import jplay.Window;

public class Main
{
	public static void main(String [] args)
	{
		Window janela = new Window (800, 600);
		GameImage plano = new GameImage("/imagens/fundo800x600.png");
		Keyboard teclado = janela.getKeyboard();
		Mouse mouse = janela.getMouse();
		
		GameImage jogar = new GameImage("/imagens/bjogar1.png");
		jogar.x = 150; jogar.y = 125;
		GameImage instrucoes = new GameImage("/imagens/binstrucoes1.png");
		instrucoes.x = 150 ; instrucoes.y = 275;
		GameImage sobre = new GameImage("/imagens/bsobreojogo1.png");
		sobre.x = 150 ; sobre.y = 425;
		GameImage inicio = new GameImage("/imagens/inicio.gif");
		inicio.x = 162 ; inicio.y = 60;
		
		GameImage voltar = new GameImage("/imagens/voltar.png");
		voltar.x = 20 ; voltar.y = 510;

		boolean executando = true;
		while(executando)
		{
			plano.draw();
			inicio.draw();
			jogar.draw();
			instrucoes.draw();
			sobre.draw();
			
			janela.update();
			
			if(teclado.keyDown(Keyboard.ESCAPE_KEY))
			{
				executando = false;
			}
			if(mouse.isOverObject(jogar) && mouse.isLeftButtonPressed())
			{
				new Cenario1(janela);
			}
			if(mouse.isOverObject(instrucoes) && mouse.isLeftButtonPressed())
			{
				boolean instrucao = true;
				while(instrucao)
				{
					plano.draw();
					voltar.draw();
					
					janela.drawText("- Este jogo é uma metáfora do curso bacharelado em Sistemas de Informação da UFAC.", 75 , 50, Color.white);
					janela.drawText("- O jogo é dividido em duas partes, seu objetivo se formar, alcançando a figura do chapéu de formatura.", 75 , 100, Color.white);
					janela.drawText("- Você deve passar pelo labirinto desviando dos obstáculos, alguns deles são: n1, n2, a bola de sinuca representa o DCE, etc...", 75 , 150, Color.white);
					janela.drawText("- Atenção às pegadinhas durante o jogo e lembre-se:", 75 , 200, Color.white);
					janela.drawText("  NÃO TENTE PEGAR ATALHOS!", 75 , 250, Color.white);
					
					
					if(mouse.isOverObject(voltar) && mouse.isLeftButtonPressed())
					{
						instrucao = false;
					}
					
					janela.update();
				}
			}
			if(mouse.isOverObject(sobre) && mouse.isLeftButtonPressed())
			{
				boolean sobrejogo = true;
				while(sobrejogo)
				{
					plano.draw();
					voltar.draw();
					
					janela.drawText("Este jogo foi desenvolvido para fins acadêmicos", 75 , 50, Color.white);
					janela.drawText("pela aluna Victória Karolina de Lima Cavalcante", 75 , 100, Color.white);
					janela.drawText("do curso bacharelado em Sistemas de Informação", 75 , 150, Color.white);
					janela.drawText("da Universidade Federal do Acre (UFAC)", 75 , 200, Color.white);
					janela.drawText("como requisito do professor Manoel Limeira.", 75 , 250, Color.white);
					janela.drawText("victoriaklcavalcante@gmail.com", 600 , 550, Color.white);
					janela.drawText("07/2019", 600 , 570, Color.white);
					janela.drawText("csi.ufac.br", 600 , 590, Color.white);
					
					
					if(mouse.isOverObject(voltar) && mouse.isLeftButtonPressed())
					{
						sobrejogo = false;
					}
					
					janela.update();
				}
			}
		}
		
		janela.exit();
	}
}
