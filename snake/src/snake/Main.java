package snake;

/**
 *
 * @author Marcio Menezes
 */
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        // Executador do game, evite mexer aqui caso não saiba o que está fazendo
        Game game = new Game();
        JFrame frame = new JFrame("Jogo da Cobrinha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
