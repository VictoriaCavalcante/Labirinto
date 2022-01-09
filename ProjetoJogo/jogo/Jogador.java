package jogo;

import jplay.Keyboard;
import jplay.Sprite;

public class Jogador extends Sprite
{
	public Jogador(int x, int y)
	{
		super("/imagens/jedi-horizontal22.png", 16); //arquivo de imagem do personagem dividido por 16
		this.x = x;
		this.y = y;
		setTotalDuration(100);

	}



	public void move(Keyboard keyboard)
	{
		moveX(0.3); //velocidade do personagens
		moveY(0.3); 

		if(keyboard.keyDown(Keyboard.LEFT_KEY))
		{
			setSequence(4, 7);
			update();
		}
		else if(keyboard.keyDown(Keyboard.RIGHT_KEY))
		{
			setSequence(8, 11);
			update();
		}

		if(keyboard.keyDown(Keyboard.UP_KEY))
		{
			setSequence(12, 15);
			update();
		}
		else if(keyboard.keyDown(Keyboard.DOWN_KEY))
		{
			setSequence(0, 3);
			update();
		}


	}

}
