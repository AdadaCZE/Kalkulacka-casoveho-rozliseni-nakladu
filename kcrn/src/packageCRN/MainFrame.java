package packageCRN;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class MainFrame{
	
    private JFrame frame;

    public MainFrame(){
        initialize();
    }

    private void initialize(){
        frame = new JFrame();
        frame.setLayout(null);
        frame.setTitle("Kalkulačka Časového Rozlišení Nákladů");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panelHeader = new JPanel();
        panelHeader.setBounds(200, 0, 600, 140);
        panelHeader.setLayout(new BorderLayout(100, 100));

        JLabel labelHeader = new JLabel("Kalkulačka Časového Rozlišení Nákladů");
        labelHeader.setHorizontalAlignment(SwingConstants.CENTER);
        labelHeader.setFont(new Font("Comic Sans", Font.PLAIN, 30));
        panelHeader.add(labelHeader);

        frame.add(panelHeader);

        JPanel panelIn = new JPanel();
        panelIn.setLayout(new BoxLayout(panelIn, BoxLayout.Y_AXIS));
        panelIn.setBackground(Color.lightGray);
        panelIn.setBounds(0, 0, 200, 140);

        JLabel labelStart = new JLabel("Začátek období(d/m/r): ");
        panelIn.add(labelStart);

        JTextField dateStartText = new JTextField();
        panelIn.add(dateStartText);

        JLabel labelEnd = new JLabel("Konec období(d/m/r): ");
        panelIn.add(labelEnd);

        JTextField dateEndText = new JTextField();
        panelIn.add(dateEndText);

        JLabel labelMoney = new JLabel("Částka(kč): ");
        panelIn.add(labelMoney);

        JTextField MoneyText = new JTextField();
        panelIn.add(MoneyText);

        JButton button = new JButton("Calculate");
        button.setFocusable(false);

        panelIn.add(button);

        frame.add(panelIn);
        
        JPanel panelOut = new JPanel();
        
        button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
                String[] table = ActualMathPart.Rozlisit(dateStartText.getText(), dateEndText.getText(), MoneyText.getText());

                panelOut.removeAll();

                panelOut.setLayout(new GridLayout(0, 3, 0, 0));
                panelOut.setBounds(10, 145, 960, 600);
                panelOut.setBackground(Color.LIGHT_GRAY);
                
                JLabel label1 = new JLabel("Rok");
                label1.setHorizontalAlignment(SwingConstants.LEFT);
                label1.setFont(new Font("Comic Sans", Font.PLAIN, 20));
                panelOut.add(label1);
        
                JLabel label2 = new JLabel("Dny");
                label2.setHorizontalAlignment(SwingConstants.RIGHT);
                label2.setFont(new Font("Comic Sans", Font.PLAIN, 20));
                panelOut.add(label2);
        
                JLabel label3 = new JLabel("Peníze");
                label3.setHorizontalAlignment(SwingConstants.RIGHT);
                label3.setFont(new Font("Comic Sans", Font.PLAIN, 20));
                panelOut.add(label3);

                for (int i = 0; i < table.length; i++) {
                    JLabel newLabel = new JLabel(table[i]);
                    newLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    newLabel.setBorder(new MatteBorder(3, 0, 0, 0, Color.BLACK));
                    newLabel.setFont(new Font("Comic Sans", Font.PLAIN, 20));
                    if (i % 3 == 0){
                        newLabel.setHorizontalAlignment(SwingConstants.LEFT);
                    }
                    else{
                        newLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    }
                    panelOut.add(newLabel);
                }

                panelOut.setBounds(10, 145, 760, table.length * 10);
                panelOut.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

                frame.setVisible(false);
                frame.add(panelOut);
                frame.setVisible(true);
			}

        });
    }

    public void show(){
        this.frame.setVisible(true);
    }

}
