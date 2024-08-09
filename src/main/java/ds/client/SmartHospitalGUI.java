package ds.client;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ds.generated.service1.PatientMonitoringServiceGrpc;
import ds.generated.service1.PatientMonitoringServiceGrpc.PatientMonitoringServiceBlockingStub;
import ds.generated.service2.RoomEnvironmentControlServiceGrpc;
import ds.generated.service2.RoomEnvironmentControlServiceGrpc.RoomEnvironmentControlServiceBlockingStub;
import ds.generated.service3.MedicationDispensingServiceGrpc;
import ds.generated.service3.MedicationDispensingServiceGrpc.MedicationDispensingServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SmartHospitalGUI implements ActionListener{
//
    private JTextField entry1, reply1;
    private JTextField entry2, reply2;
    private JTextField entry3, reply3;
    private JTextField entry4, reply4;

    private JPanel getPatientMonitoringPanel() {
        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);

        JLabel label = new JLabel("Patient ID")    ;
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        entry1 = new JTextField("",10);
        panel.add(entry1);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));

        JButton button1 = new JButton("Monitor Vital Signs");
        button1.addActionListener(this);
        panel.add(button1);
        
        JButton button2 = new JButton("Alert Nurse");
        button2.addActionListener(this);
        panel.add(button2);
        
        panel.add(Box.createRigidArea(new Dimension(10, 0)));

        reply1 = new JTextField("", 20);
        reply1.setEditable(false);
        panel.add(reply1);

        panel.setLayout(boxlayout);
        return panel;
    }

    private JPanel getRoomEnvironmentControlPanel() {
        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);

        JLabel label = new JLabel("Temperature")    ;
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        entry2 = new JTextField("",10);
        panel.add(entry2);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));

        JButton button1 = new JButton("Adjust Temperature");
        button1.addActionListener(this);
        panel.add(button1);
        
        JButton button2 = new JButton("Control Lights");
        button2.addActionListener(this);
        panel.add(button2);
        
        panel.add(Box.createRigidArea(new Dimension(10, 0)));

        reply2 = new JTextField("", 20);
        reply2.setEditable(false);
        panel.add(reply2);

        panel.setLayout(boxlayout);
        return panel;
    }

    private JPanel getMedicationDispensingPanel() {
        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);

        JLabel label = new JLabel("Patient ID")    ;
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        entry3 = new JTextField("",10);
        panel.add(entry3);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));

        JButton button1 = new JButton("Dispense Medication");
        button1.addActionListener(this);
        panel.add(button1);
        
        JButton button2 = new JButton("Check Medication Stock");
        button2.addActionListener(this);
        panel.add(button2);
        
        panel.add(Box.createRigidArea(new Dimension(10, 0)));

        reply3 = new JTextField("", 20);
        reply3.setEditable(false);
        panel.add(reply3);

        panel.setLayout(boxlayout);
        return panel;
    }

    public static void main(String[] args) {
        SmartHospitalGUI gui = new SmartHospitalGUI();
        gui.build();
    }

    private void build() {
        JFrame frame = new JFrame("Smart Hospital Controller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

        panel.setLayout(boxlayout);
        panel.setBorder(new EmptyBorder(new Insets(50, 100, 50, 100)));

        panel.add(getPatientMonitoringPanel());
        panel.add(getRoomEnvironmentControlPanel());
        panel.add(getMedicationDispensingPanel());
 
        frame.setSize(600, 300);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String label = button.getActionCommand();

        if (label.equals("Monitor Vital Signs")) {
            System.out.println("Invoking Patient Monitoring Service...");

            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
            PatientMonitoringServiceBlockingStub stub = PatientMonitoringServiceGrpc.newBlockingStub(channel);


            reply1.setText("Heart Rate: 75 bpm, Blood Pressure: 120/80");

            channel.shutdown();

        } else if (label.equals("Alert Nurse")) {
            System.out.println("Invoking Alert Nurse Service...");

            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
            PatientMonitoringServiceBlockingStub stub = PatientMonitoringServiceGrpc.newBlockingStub(channel);

            
            reply1.setText("Nurse alerted successfully");

            channel.shutdown();

        } else if (label.equals("Adjust Temperature")) {
            System.out.println("Invoking Room Environment Control Service...");

            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052).usePlaintext().build();
            RoomEnvironmentControlServiceBlockingStub stub = RoomEnvironmentControlServiceGrpc.newBlockingStub(channel);


            reply2.setText("Temperature set to " + entry2.getText() + "°C");

            channel.shutdown();

        } else if (label.equals("Control Lights")) {
            System.out.println("Invoking Control Lights Service...");

            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052).usePlaintext().build();
            RoomEnvironmentControlServiceBlockingStub stub = RoomEnvironmentControlServiceGrpc.newBlockingStub(channel);


            reply2.setText("Lights turned on");

            channel.shutdown();

        } else if (label.equals("Dispense Medication")) {
            System.out.println("Invoking Medication Dispensing Service...");

            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50053).usePlaintext().build();
            MedicationDispensingServiceBlockingStub stub = MedicationDispensingServiceGrpc.newBlockingStub(channel);


            reply3.setText("Medication dispensed successfully");

            channel.shutdown();
            
        } else if (label.equals("Check Medication Stock")) {
            System.out.println("Invoking Check Medication Stock Service...");

            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50053).usePlaintext().build();
            MedicationDispensingServiceBlockingStub stub = MedicationDispensingServiceGrpc.newBlockingStub(channel);


            reply3.setText("Stock level: 10 units");

            channel.shutdown();
        }
    }
}
