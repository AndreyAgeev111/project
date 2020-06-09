import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Paths;

public class TextEditor extends JFrame {
    public TextEditor() {
        initComponents();
    }

    private void initComponents() {
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        JPanel jPanel = new JPanel();
        JTextField textField = new JTextField();
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu filament = new JMenu("File");
        filament.setMnemonic(KeyEvent.VK_F);
        menuBar.add(filament);

        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");

        filament.add(load);
        filament.add(save);
        filament.add(exit);


        setTitle("Text Editor");
        saveButton.setName("SaveButton");
        loadButton.setName("LoadButton");
        textArea.setName("TextArea");
        textField.setName("FilenameField");
        scrollPane.setName("ScrollPane");

        filament.setName("MenuFile");
        load.setName("MenuLoad");
        save.setName("MenuSave");
        exit.setName("MenuExit");

        exit.addActionListener(event -> dispose());
        save.addActionListener(actionEvent -> {
            File file = Paths.get(textField.getText()).toFile();
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print(textArea.getText());
            } catch (IOException e) {
                e.getLocalizedMessage();
            }
        });
        load.addActionListener(actionEvent -> {
            String text = textField.getText();
            File file = Paths.get(text).toFile();

            try {
                FileReader stream = new FileReader(file);
                StringBuilder string = new StringBuilder();
                while (stream.ready()) {
                    string.append((char) stream.read());
                }
                textArea.setText(string.toString());
            } catch (FileNotFoundException e) {
                textArea.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GroupLayout jPanelLayout = new GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
                jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, true)
                                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanelLayout.createSequentialGroup()
                                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(loadButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(82, Short.MAX_VALUE))
        );

        jPanelLayout.setVerticalGroup(
                jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLayout.createSequentialGroup()
                                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(loadButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        pack();

        loadButton.addActionListener( actionEvent -> {
            String text = textField.getText();
            File file = Paths.get(text).toFile();

            try {
                FileReader stream = new FileReader(file);
                StringBuilder string = new StringBuilder();
                while (stream.ready()) {
                    string.append((char) stream.read());
                }
                textArea.setText(string.toString());
            } catch (FileNotFoundException e) {
                textArea.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        saveButton.addActionListener(actionEvent -> {
            File file = Paths.get(textField.getText()).toFile();
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print(textArea.getText());
            } catch (IOException e) {
                e.getLocalizedMessage();
            }
        });
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new TextEditor().setVisible(true));
    }
}
