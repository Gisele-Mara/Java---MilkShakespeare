import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaHome extends JFrame{

    public JPanel painelPrincipal;
    private JButton livroButton;
    private JButton milkshakeButton;
    private JButton juniorButton;
    private JButton plenoButton;
    private JButton seniorButton;
    private JLabel lblFila;

    static ArrayList<Pedido> order = new ArrayList<>();
    static ArrayList<Pedido> waiting = new ArrayList<>();

    public TelaHome() {

        String[] bookTitles = {
                "The Milky Way: A Journey Through Milkshakes",
                "The Secret Recipe: Unraveling the Mysteries of Milkshakes",
                "Beyond Vanilla: Exploring Exotic Milkshake Flavors",
                "The Great Milkshake Caper: A Whipped Cream Whodunit",
                "Milkshake Magic: Stirring Up Sweet Adventures",
                "Chasing Milkshakes: A Road Trip of Flavor",
                "The Milkshake Manifesto: Recipes for a Creamy Revolution",
                "The Milkshake Chronicles: From Straw to Sip",
                "The Milkshake Paradox: Finding Harmony in Flavor",
                "Whirlwind Whips: Spinning Tales of Milkshake Adventures",
                "Milkshake Mastery: Crafting the Perfect Blend",
                "The Art of Froth: Mastering Milkshake Foam",
                "Milkshake Metropolis: A City Built on Creamy Concoctions",
                "A Milkshake for Every Mood: Satisfying Cravings, One Sip at a Time",
                "Penguin Problems: A Bird's Guide to Life's Icy Challenges",
                "Toaster Troubles: A Toast to Mishaps in the Kitchen",
                "The Quest for the Perfect Sock: Adventures in the Dryer Dimension",
                "Banana Drama: A Peel of Laughter",
                "Sock Monkeys in Space: A Yarn of Cosmic Proportions",
                "The Legend of the Exploding Toaster: A Crispy Mystery",
                "The Peculiar Case of the Dancing Broccoli: A Vegetable Whodunit",
                "Tea Time Terrors: Brewed to Perfection, Served with Fear"
        };

        String[] flavours = {
                "Banana", "Classic Vanilla", "Oreo",
                "Blueberry Breakfast", "Fuzzy Navel", "Pumpkin Pie",
                "Chocolate Cherry", "Lime Sherbet", "Purple Cow",
                "Chocolate Peanut Butter", "Malted", "Strawberry Banana",
                "Chocolate", "Old Fashioned Chocolate", "Tropical Breeze",
                "Chunky Monkey", "Old Timer’s Malted", "Whipped Cherry",
                "Classic Jello", "Oregon Chai", "Whoppers Malt"
        };



        String[] sizes = { "Small", "Medium", "Large" };


        livroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                order.add(new Library(order.size() + 1, bookTitles[(int) (Math.random() * (bookTitles.length - 1))]));
            renderFila();
            }
        });
        milkshakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                order.add(new Milkshake((order.size() + 1), flavours[(int) (Math.random() * (flavours.length - 1))],
                        sizes[(int) (Math.random() * (sizes.length - 1))]));
                renderFila();
            }
        });
        juniorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for(int i = 0; i < order.size(); i++){
                    if(!order.get(i).isConcluido() && order.get(i) instanceof Library){

                        order.get(i).concluir();


                    }
                    renderFila ();
                    }
            }
        });
        plenoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for(int i = 0; i < order.size(); i++){
                    if(!order.get(i).isConcluido() && order.get(i) instanceof Milkshake){

                        order.get(i).concluir();

                    }
                    renderFila ();
                }
            }
        });
        seniorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void renderFila () {
        String concatAll = "";

        for (int i = 0; i < order.size(); i++) {
            waiting.add(order.get(i));
            if (!order.get(i).isConcluido()) {
                waiting.remove(order.get(i));
            }
        }
        for (int i = 0; i < waiting.size(); i++) {

//            if (waiting.get(i) instanceof Library) {

                concatAll = concatAll.concat(String.format("ID: %s \nProduct: %s \n    \n", order.get(i).getId(), ((Library) order.get(i)).getBook()));
//            }
            if (waiting.get(i) instanceof Milkshake) {

                concatAll = concatAll.concat(String.format("ID: %s \nProduct: %s \n    \n", order.get(i).getId(), ((Milkshake) order.get(i)).getSize()));

            }
                lblFila.setText(concatAll);

        }


    }

}
