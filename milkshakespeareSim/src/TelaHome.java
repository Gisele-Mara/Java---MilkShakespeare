import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
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
    public JTable ordersTable;
    public JScrollPane ordersScroll;

    static ArrayList<Pedido> order = new ArrayList<>();

    static String[] columnNames = { "Id", "Product", "Attendant", "Status" };

    public TelaHome() {

        DefaultTableModel a = new DefaultTableModel(columnNames, 0);
        ordersTable.setModel(a);

        JTableHeader header = ordersTable.getTableHeader();

        header.setFont(new Font("SansSerif", Font.BOLD, 12)); // Optional: Customize header font

        header.setPreferredSize(new Dimension(header.getWidth(), 30)); //
        ordersTable.getColumnModel().getColumn(0).setPreferredWidth(50);

        // Set cell renderer to center-align text in all cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Set alignment to center
        ordersTable.setDefaultRenderer(Object.class, centerRenderer);
        getContentPane().add(painelPrincipal);


        setSize(800, 620);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


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
            renderFila(order.getLast());
            }
        });
        milkshakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                order.add(new Milkshake((order.size() + 1), flavours[(int) (Math.random() * (flavours.length - 1))],
                        sizes[(int) (Math.random() * (sizes.length - 1))]));
                renderFila(order.getLast());
            }
        });
        juniorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int n = 0;
                for(int i = 0; i < order.size(); i++){

                    if(!order.get(i).isConcluido() && order.get(i) instanceof Library && n ==0){

                        order.get(i).concluir();
                        order.get(i).setAttendant("Junior");
                        n=1;

                    }
                    updateTable();
                    }
            }
        });
        plenoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = 0;
                for(int i = 0; i < order.size(); i++){
                    if(!order.get(i).isConcluido() && order.get(i) instanceof Milkshake && n ==0){

                        order.get(i).concluir();
                        order.get(i).setAttendant("Pleno");
                        n = 1;
                    }

                    updateTable();

                }
            }
        });
        seniorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = 0;
                for(int i = 0; i < order.size(); i++){

                    if(!order.get(i).isConcluido() && n == 0){

                        order.get(i).concluir();
                        order.get(i).setAttendant("Senior");

                        n = 1;
                    }

                    updateTable();

                }
            }
        });
    }

    private void renderFila (Pedido newOrder) {
        DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();

        if (newOrder instanceof Milkshake milkShakeBar) {
            model.addRow(new Object[] { milkShakeBar.getId(), milkShakeBar.getFlavour() + " " + milkShakeBar.getSize(), milkShakeBar.getAttendant() , milkShakeBar.isConcluido(), "" });
        } else if (newOrder instanceof Library library) {
            model.addRow(new Object[] { library.getId(), library.getBook(), library.getAttendant(), library.isConcluido(), "" });
        }

    }

    private void updateTable (){
        cleanTable();
        DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();

        for (int i=0; i < order.size(); i++){
            if (order.get(i) instanceof Milkshake milkShakeBar) {
                model.addRow(new Object[] { milkShakeBar.getId(), milkShakeBar.getFlavour() + " " +  milkShakeBar.getSize(), milkShakeBar.getAttendant(), milkShakeBar.isConcluido(), "" });
            } else if (order.get(i) instanceof Library library) {
                model.addRow(new Object[] { library.getId(), library.getBook(), library.getAttendant(), library.isConcluido(), "" });
            }
        }

    }

    private void cleanTable() {
        DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();
        model.setRowCount(0);
    }
}
