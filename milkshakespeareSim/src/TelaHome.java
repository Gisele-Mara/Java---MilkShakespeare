import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
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
    private JPanel filaPanel;

    static ArrayList<Pedido> order = new ArrayList<>();

    static String[] columnNames = { "Id", "Product", "Attendant", "Status" };

    public TelaHome() {

        DefaultTableModel a = new DefaultTableModel(columnNames, 0);
        ordersTable.setModel(a);

        Border border = BorderFactory.createTitledBorder("Fila");
        filaPanel.setBorder(border);

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
                for (Pedido pedido : order) {

                    if (!pedido.isCompleted() && pedido instanceof Library && n == 0) {
                        String messageScreen = "ID: " + pedido.getId() + "\nBook: " + ((Library) pedido).getBook();

                        serveCustomer(pedido, messageScreen, "Junior");

                        n = 1;

                    }
                    updateTable();
                }
            }
        });
        plenoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = 0;
                for (Pedido pedido : order) {

                    if (!pedido.isCompleted() && pedido instanceof Milkshake && n == 0) {
                        String messageScreen = "ID: " + pedido.getId() + "\nMilkshake \nFlavour: " + ((Milkshake) pedido).getFlavour() + "\nSize: " + ((Milkshake) pedido).getSize();
                        serveCustomer(pedido, messageScreen, "Mid-Level");

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
                for (Pedido pedido : order) {

                    if (!pedido.isCompleted() && n == 0) {

                        if (pedido instanceof Milkshake) {
                            String messageScreen = "ID: " + pedido.getId() + "\nMilkshake \nFlavour: " + ((Milkshake) pedido).getFlavour() + "\nSize: " + ((Milkshake) pedido).getSize();

                            serveCustomer(pedido, messageScreen, "Senior");
                            n = 1;


                        }
                        if (pedido instanceof Library) {
                            String messageScreen ="ID: " + pedido.getId() +"\n Book: " + ((Library) pedido).getBook();
                            serveCustomer(pedido, messageScreen, "Senior");
                            n = 1;

                        }

                    }

                    updateTable();

                }
            }
        });
    }

    private void renderFila (Pedido newOrder) {
        DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();

        if (newOrder instanceof Milkshake milkShakeBar) {
            model.addRow(new Object[] { milkShakeBar.getId(), milkShakeBar.getFlavour() + " -  " + milkShakeBar.getSize(), milkShakeBar.getAttendant() , milkShakeBar.isCompleted(), "" });
        } else if (newOrder instanceof Library library) {
            model.addRow(new Object[] { library.getId(), library.getBook(), library.getAttendant(), library.isCompleted(), "" });
        }

    }

    private void updateTable (){
        cleanTable();
        DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();

        for (int i=0; i < order.size(); i++){
            if (order.get(i) instanceof Milkshake milkShakeBar) {
                model.addRow(new Object[] { milkShakeBar.getId(), milkShakeBar.getFlavour() + " - " +  milkShakeBar.getSize(), milkShakeBar.getAttendant(), milkShakeBar.isCompleted(), "" });
            } else if (order.get(i) instanceof Library library) {
                model.addRow(new Object[] { library.getId(), library.getBook(), library.getAttendant(), library.isCompleted(), "" });
            }
        }

    }

    private void cleanTable() {
        DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();
        model.setRowCount(0);
    }

    private void serveCustomer (Pedido finishOrder, String messageScreen, String attendant){
        String[] options = { "Serve", "Cancel" };

        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.blue);
        frame.setBounds(300, 300, 500, 350);
        frame.setVisible(true);


        var selection = JOptionPane.showOptionDialog(frame, messageScreen, "Finish Order",
                    0, 3, new ImageIcon("img/milkshake_2234936.png"), options, options[0]);

            if (selection == 0) {
               finishOrder.finish();
               finishOrder.setAttendant(attendant);
            }


    }
}
