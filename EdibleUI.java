import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EdibleUI extends JFrame implements ActionListener {
    private JTextField personField, itemField;
    private JComboBox<String> typeBox;
    private JButton eatBtn, sampleBtn, clearBtn;
    private JTextArea output;

    public EdibleUI() {
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout(5, 5));
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
        personField = new JTextField(15);
        typeBox = new JComboBox<>(new String[] {"Animal", "Apple", "Orange"});
        itemField = new JTextField(15);
        form.add(new JLabel("Person name:"));
        form.add(personField);
        form.add(new JLabel("Food type:"));
        form.add(typeBox);
        form.add(new JLabel("Food name:"));
        form.add(itemField);

        JPanel buttons = new JPanel(new FlowLayout());
        eatBtn = new JButton("Eat");
        sampleBtn = new JButton("Show Sample");
        clearBtn = new JButton("Clear");
        buttons.add(eatBtn);
        buttons.add(sampleBtn);
        buttons.add(clearBtn);

        JPanel top = new JPanel(new BorderLayout());
        top.add(form, BorderLayout.CENTER);
        top.add(buttons, BorderLayout.SOUTH);

        output = new JTextArea(8, 45);
        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);

        pane.add(top, BorderLayout.NORTH);
        pane.add(new JScrollPane(output), BorderLayout.CENTER);

        eatBtn.addActionListener(this);
        sampleBtn.addActionListener(this);
        clearBtn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == eatBtn) {
            String person = personField.getText().trim();
            String item = itemField.getText().trim();
            if (person.isEmpty() || item.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the person and food name.");
                return;
            }
            Edible food = createFood((String) typeBox.getSelectedItem(), item);
            Person p = new Person(person);
            output.append(p.eating(food) + "\n");
        } else if (e.getSource() == sampleBtn) {
            Edible[] objects = {new Animal("Chicken"), new Apple("Red Apple"),
                                new Orange("Navel Orange")};
            Person p1 = new Person("Ali");
            Person p2 = new Person("Sarah");
            for (int i = 0; i < objects.length; i++) {
                if (objects[i] instanceof Animal)
                    output.append(p1.eating(objects[i]) + "\n");
                else
                    output.append(p2.eating(objects[i]) + "\n");
            }
        } else if (e.getSource() == clearBtn) {
            output.setText("");
        }
    }

    // polymorphism: one Edible reference, different objects
    private Edible createFood(String type, String name) {
        if (type.equals("Animal"))
            return new Animal(name);
        else if (type.equals("Apple"))
            return new Apple(name);
        else
            return new Orange(name);
    }

    public static void main(String[] args) {
        EdibleUI frame = new EdibleUI();
        frame.setTitle("Edible");
        frame.setSize(560, 360);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
