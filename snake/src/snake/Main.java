package snake;

/**
 *
 * @author Marcio Menezes
 */
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        JLabel pontuacao;
        JFrame frame = new JFrame("Jogo da Cobrinha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
