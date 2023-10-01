package hospital;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.jar.Attributes.Name;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.*;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;

public class Hospital extends JFrame implements ActionListener {

    JButton doctor = new JButton("Doctor");
    JButton doctor_update = new JButton("update");
    JButton doctor_insert = new JButton("Insert");
    JButton doctor_delete = new JButton("Delete");
    JButton doctor_search = new JButton("Search ID");
    JButton doctor_back = new JButton("Back");

    JButton patient = new JButton("Patient");
    JButton patient_update = new JButton("update");
    JButton patient_insert = new JButton("Insert");
    JButton patient_delete = new JButton("Delete");
    JButton patient_search = new JButton("Search ID");
    JButton patient_back = new JButton("Back");

    JButton test = new JButton("Test");
    JButton test_update = new JButton("update");
    JButton test_insert = new JButton("Insert");
    JButton test_delete = new JButton("Delete");
    JButton test_search = new JButton("Search ID");
    JButton test_back = new JButton("Back");
    
    //**frames for each button******
    
    JFrame f_doctor = new JFrame("Doctor");
    JLabel docbg; // to add background in doctor frame

    JFrame f_patient = new JFrame("Patient");
    JLabel pbg;

    JFrame f_test = new JFrame("Test");
    JLabel tbg;
    // labels for secound frames******
    JLabel docName = new JLabel("Name");
    JLabel docSSN = new JLabel("SSN");
    JLabel docSp = new JLabel("Specialzation");

    JLabel patientname = new JLabel("Name");
    JLabel patientSSN = new JLabel("SSN");
    JLabel patientMI = new JLabel("Medical Insurance");
    JLabel patientDA = new JLabel("Date Admitted");
    JLabel patientDateCeck = new JLabel("Date of Ceck Out");

    JLabel testID = new JLabel("Test ID");
    JLabel testname = new JLabel("Test Name");
    JLabel testdate = new JLabel("Test Date");
    JLabel testtime = new JLabel("Test Time");
    JLabel testresult = new JLabel("Test Result");
  
    //************ Text Fields for secound Frames*************
    JTextField t_docname = new JTextField();
    JTextField t_docSSN = new JTextField();
    JTextField t_docSP = new JTextField();
    JTextField docsearch = new JTextField();

    JTextField t_Patientname = new JTextField();
    JTextField t_PatientMI = new JTextField();
    JTextField t_PatientSSN = new JTextField();
    JTextField t_PatientDA = new JTextField();
    JTextField t_patientDateOfCeckOUT = new JTextField();
    JTextField patsearch = new JTextField();

    JTextField t_testID = new JTextField();
    JTextField t_name = new JTextField();
    JTextField t_date = new JTextField();
    JTextField t_time = new JTextField();
    JTextField t_result = new JTextField();
    JTextField testsearch = new JTextField();
    //****************TABELS FOR EACH FRAME******************************
    //doctor table
    JTable t_Doctor;
    String[] columnsNames = {"Name", "Dssn", "Specialization"};
    Object[][] rawsData = {};
    DefaultTableModel doctorTableModel = new DefaultTableModel(rawsData, columnsNames);

    //Patient table 
    JTable t_Patient;
    String[] columnsNames2 = {"P_Name", "SSN", "Medical_insurance", "Date_admitted", "Date_of_check_out"};
    Object[][] rawsData2 = {};
    DefaultTableModel patientTable2Model = new DefaultTableModel(rawsData2, columnsNames2);

    //test table
    JTable t_Test;
    String[] columnsNames3 = { "Test_name","Test_id", "Date_test", "Time_test", "Result"};
    Object[][] rawsData3 = {};
    DefaultTableModel testable3Model = new DefaultTableModel(rawsData3, columnsNames3);

    
  
    Hospital() {
        storedDoctor();
        storedPatient();
        storedTest();
        setLayout(null);
        setBounds(0, 0, 1120, 850); //length for frame
        setTitle("Hospital App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new JLabel(new ImageIcon("F:\\Seound semster\\projects\\background\\main22222.jpg")));

        /*#################  DOCTOR Details  #############################*/
        doctor.setBounds(250, 530, 100, 80); //button size
        doctor.addActionListener(this);
        add(doctor);
        f_doctor.setLayout(null);
        f_doctor.setBounds(0, 0, 1100, 700); //frame apper after click the button
        ImageIcon dbg = new ImageIcon("F:\\Seound semster\\projects\\background\\doctor2.jpg"); //to add background in doctor frame
        docbg = new JLabel("", dbg, JLabel.CENTER);
        docbg.setBounds(0, 0,  1100, 700);
        f_doctor.add(docbg);
        doctor_search.setBounds(230, 99, 90, 30);
        doctor_search.addActionListener(this);
        docbg.add(doctor_search);
        doctor_update.setBounds(50, 500, 90, 40);
        doctor_update.addActionListener(this);
        docbg.add(doctor_update);
        doctor_insert.setBounds(180, 500, 70, 40);
        doctor_insert.addActionListener(this);
        docbg.add(doctor_insert);
        doctor_delete.setBounds(310, 500, 70, 40);
        doctor_delete.addActionListener(this);
        docbg.add(doctor_delete);
        doctor_back.setBounds(450, 500, 70, 40); // button to return you to main page
        doctor_back.addActionListener(this);
        docbg.add(doctor_back); //to show the button in the secound page
        f_doctor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ****doctor page labels*********
        docName.setBounds(90, 170, 93, 93);
        docbg.add(docName);
        docSSN.setBounds(90, 250, 100, 100);
        docbg.add(docSSN);
        docSp.setBounds(80, 290, 100, 185);
        docbg.add(docSp);

        //***doctor page text***********
        docsearch.setBounds(60, 100, 145, 25);
        docsearch.setBackground(Color.LIGHT_GRAY);
        docbg.add(docsearch);
        t_docname.setBounds(150, 207, 200, 20);
        t_docname.setBackground(Color.LIGHT_GRAY);
        docbg.add(t_docname);
        t_docSSN.setBounds(150, 290, 200, 20);
        t_docSSN.setBackground(Color.LIGHT_GRAY);
        docbg.add(t_docSSN);
        t_docSP.setBounds(190, 372, 180, 20);
        t_docSP.setBackground(Color.LIGHT_GRAY);
        docbg.add(t_docSP);
        
        //********doctor table**********
        t_Doctor = new JTable(doctorTableModel);
        t_Doctor.setBounds(500, 50, 400, 400);
        JScrollPane scrollpane = new JScrollPane(t_Doctor);
        scrollpane.setBounds(420, 100, 600, 300);
        docbg.add(scrollpane);


        /*############### PATIENT Details ################################*/
        patient.setBounds(470, 530, 100, 80);
        patient.addActionListener(this);
        add(patient);
        f_patient.setLayout(null);
        f_patient.setBounds(0, 0, 1120, 740);
        ImageIcon patbg = new ImageIcon("F:\\Seound semster\\projects\\background\\pat222.jpg");
        pbg = new JLabel("", patbg, JLabel.CENTER);
        pbg.setBounds(0, 0,1120, 700);
        f_patient.add(pbg);
        patient_search.setBounds(230, 69, 90, 30);
        patient_search.addActionListener(this);
        pbg.add(patient_search);
        patient_update.setBounds(50, 630, 90, 40);
        patient_update.addActionListener(this);
        pbg.add(patient_update);
        patient_insert.setBounds(180, 630, 70, 40);
        patient_insert.addActionListener(this);
        pbg.add(patient_insert);
        patient_delete.setBounds(310, 630, 70, 40);
        patient_delete.addActionListener(this);
        pbg.add(patient_delete);
        patient_back.setBounds(445, 630, 70, 40);
        patient_back.addActionListener(this);
        pbg.add(patient_back);
        f_patient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //******** patient page labels*******
        patientname.setBounds(70, 100, 190, 100);
        pbg.add(patientname);
        patientSSN.setBounds(70, 200, 190, 100);
        pbg.add(patientSSN);
        patientMI.setBounds(70, 280, 190, 100);
        pbg.add(patientMI);
        patientDA.setBounds(70, 390, 190, 100);
        pbg.add(patientDA);
        patientDateCeck.setBounds(70, 470, 190, 100);
        pbg.add(patientDateCeck);

        //*patient page text********
        patsearch.setBounds(50, 70, 145, 25);
        patsearch.setBackground(Color.LIGHT_GRAY);
        pbg.add(patsearch);
        t_Patientname.setBounds(200, 130, 290, 30);
        t_Patientname.setBackground(Color.LIGHT_GRAY);
        pbg.add(t_Patientname);
        t_PatientSSN.setBounds(200, 230, 290, 30);
        t_PatientSSN.setBackground(Color.LIGHT_GRAY);
        pbg.add(t_PatientSSN);
        t_PatientMI.setBounds(200, 315, 290, 30);
        t_PatientMI.setBackground(Color.LIGHT_GRAY);
        pbg.add(t_PatientMI);
        t_PatientDA.setBounds(200, 420, 290, 30);
        t_PatientDA.setBackground(Color.LIGHT_GRAY);
        pbg.add(t_PatientDA);
        t_patientDateOfCeckOUT.setBounds(200, 509, 290, 30);
        t_patientDateOfCeckOUT.setBackground(Color.LIGHT_GRAY);
        pbg.add(t_patientDateOfCeckOUT);
        
        //*********patient table**************
        t_Patient = new JTable(patientTable2Model);
        t_Patient.setBounds(0, 0, 100, 200);
        JScrollPane scrollpane2 = new JScrollPane(t_Patient);
        scrollpane2.setBounds(520, 100, 560, 200);
        pbg.add(scrollpane2);


        /*################## TEST Details ###############################*/
        test.setBounds(700,  530, 100, 80);
        test.addActionListener(this);
        add(test); //button translate you to test page
        f_test.setLayout(null);
        f_test.setBounds(0, 0, 1100, 750); // test frame size 
        ImageIcon testbg = new ImageIcon("F:\\Seound semster\\projects\\background\\test1.jpg");
        tbg = new JLabel("", testbg, JLabel.CENTER);
        tbg.setBounds(0, 0, 1100, 750);
        f_test.add(tbg);
        test_search.setBounds(230, 67, 90, 30);
        test_search.addActionListener(this);
        tbg.add(test_search);
        test_update.setBounds(50, 550, 90, 40);
        test_update.addActionListener(this);
        tbg.add(test_update);
        test_insert.setBounds(175, 550, 70, 40);
        test_insert.addActionListener(this);
        tbg.add(test_insert);
        test_delete.setBounds(305, 550, 70, 40);
        test_delete.addActionListener(this);
        tbg.add(test_delete);
        setLayout(null);
        test_back.setBounds(435, 550, 70, 40); //lengh of button back from test frame
        test_back.addActionListener(this);
        tbg.add(test_back);
        f_test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //test page labels** 
        testname.setBounds(70,110 , 100, 100);
        tbg.add(testname);
        testID.setBounds(70, 180, 100, 100);
        tbg.add(testID);
        testtime.setBounds(70,320 , 100, 100);
        tbg.add(testtime);
        testdate.setBounds(70,250 , 100, 100);
        tbg.add(testdate);
        testresult.setBounds(70, 390, 100, 100);
        tbg.add(testresult);

        //**test page text*****
        testsearch.setBounds(50, 70, 145, 25);
        testsearch.setBackground(Color.LIGHT_GRAY);
        tbg.add(testsearch);
        t_name.setBounds(180,150, 200, 20);
        t_name.setBackground(Color.LIGHT_GRAY);
        tbg.add(t_name);
        t_testID.setBounds(180, 220 , 200, 20);
        t_testID.setBackground(Color.LIGHT_GRAY);
        tbg.add(t_testID);
        t_time.setBounds(180, 359 , 200, 20);
        t_time.setBackground(Color.LIGHT_GRAY);
        tbg.add(t_time);
        t_date.setBounds(180,292, 200, 20);
        t_date.setBackground(Color.LIGHT_GRAY);
        tbg.add(t_date);
        t_result.setBounds(180, 428, 200, 20);
        t_result.setBackground(Color.LIGHT_GRAY);
        tbg.add(t_result);
        
        //test table
        t_Test = new JTable(testable3Model);
        t_Test.setBounds(0, 0, 300, 300);
        JScrollPane scrollpane3 = new JScrollPane(t_Test);
        scrollpane3.setBounds(420, 100, 600, 300);
        tbg.add(scrollpane3);

        setVisible(true);
        
        
        

    }
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://localhost:1433;databaseName=Hospital;integritysecurity=false;"; //connection code
    String user = "team";
    String pass = "1234";

    public static void main(String[] args) {
        Hospital h = new Hospital();
    }

    void storedDoctor() {
        //***** for delete doc**********
        Connection con;
        Statement stmt;
        ResultSet rs;
        try {
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM doctor");
            while (rs.next()) {
                doctorTableModel.addRow(new Object[]{rs.getString("Name"), rs.getString("Dssn"), rs.getString("Specialization")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    void storedPatient() {

        Connection con;
        Statement stmt;
        ResultSet rs;

        try {
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM patient");
            while (rs.next()) {
                patientTable2Model.addRow(new Object[]{rs.getString("P_Name"),
                    rs.getString("SSN"), rs.getString("Medical_insurance"), rs.getString("Date_admitted"),
                    rs.getString("Date_of_check_out")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    void storedTest() {
        Connection con;
        Statement stmt;
        ResultSet rs;
        try {
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM test");
            while (rs.next()) {
                testable3Model.addRow(new Object[]{rs.getString("Test_name"),
                    rs.getString("Test_id"), rs.getString("Date_test"), rs.getString("Time_test"),
                    rs.getString("Result")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //*************************DOCTOR SEARCH******************************************
        if (ae.getSource() == doctor_search) {
            try {
                Class.forName(driver);
                Connection con = DriverManager.getConnection(url, user, pass);
                String sql = "select * from doctor where Dssn=?";

                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, docsearch.getText());
                doctorTableModel.setRowCount(0);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    //get restult of data 
                    String doc_name = rs.getString("Name");
                    t_docname.setText(doc_name);
                    String doc_dssn = rs.getString("Dssn");
                    t_docSSN.setText(doc_dssn);
                    String doc_spec = rs.getString("Specialization");
                    t_docSP.setText(doc_spec);

                    //add data to the model
                    Object[] rowData = {doc_name, doc_dssn, doc_spec};
                    doctorTableModel.addRow(rowData);

                } else {
                    JOptionPane.showMessageDialog(this, "Record Not Found 404");
                }
                t_docSSN.setText("");
                t_docSP.setText("");
                t_docname.setText("");
                docsearch.setText("");

                // Notify the table model about the data change
                doctorTableModel.fireTableDataChanged();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        //*************************DOCTOR DELETE******************************************
        if (ae.getSource() == doctor_delete) {
            try {
                Class.forName(driver);
                Connection con = DriverManager.getConnection(url, user, pass);
                int dssn = Integer.parseInt(t_docSSN.getText());
                String sql = "DELETE FROM doctor WHERE Dssn = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, dssn);
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "DELETE");

                t_docSSN.setText("");
                t_docSP.setText("");
                t_docname.setText("");
                docsearch.setText("");

                doctorTableModel.setRowCount(0);
                storedDoctor();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        //***************************DOCTOR INSERT **********************************************
        if (ae.getSource() == doctor_insert) {
            try {
                Class.forName(driver);
                Connection con = DriverManager.getConnection(url, user, pass);
                String[] rowData = {t_docname.getText(), t_docSSN.getText(), t_docSP.getText()};

                String sql = "INSERT INTO doctor (Name,Dssn,Specialization) VALUES (?, ?, ?)";
                doctorTableModel.addRow(rowData);
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, t_docname.getText());
                stm.setString(2, t_docSSN.getText());
                stm.setString(3, t_docSP.getText());
                stm.executeUpdate();
                for (JTextField textField : new JTextField[]{t_docname, t_docSSN, t_docSP}) {
                    textField.setText("");
                }
                JOptionPane.showMessageDialog(null, "Inserted");
                t_docSSN.setText("");
                t_docSP.setText("");
                t_docname.setText("");
                docsearch.setText("");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        //*************************DOCTOR UPDATE******************************************
        if (ae.getSource() == doctor_update) {
            try {
                Class.forName(driver);
                Connection con = DriverManager.getConnection(url, user, pass);
                String sql = "update doctor set Name=?, Specialization =? where Dssn=?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, t_docname.getText());
                stm.setString(2, t_docSP.getText());
                stm.setString(3, t_docSSN.getText());

                stm.executeUpdate();
                JOptionPane.showMessageDialog(this, "Updated");
                doctorTableModel.setRowCount(0);
                storedDoctor();
                t_docSSN.setText("");
                t_docSP.setText("");
                t_docname.setText("");
                docsearch.setText("");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        /**********PATIENT UPDATE*************/
        if (ae.getSource() == patient_update) {
            try {
                Class.forName(driver);
                Connection con = DriverManager.getConnection(url, user, pass);
                String sql = "update patient set P_Name=?,  Medical_insurance=?,Date_admitted=?,Date_of_check_out=? where SSN=?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, t_Patientname.getText());
                stm.setString(2, t_PatientMI.getText());
                stm.setString(3, t_PatientDA.getText());
                stm.setString(4, t_patientDateOfCeckOUT.getText());
                stm.setString(5, t_PatientSSN.getText());

                stm.executeUpdate();
                JOptionPane.showMessageDialog(this, "Updated");
                t_Patientname.setText("");
                t_PatientSSN.setText("");
                t_PatientMI.setText("");
                t_PatientDA.setText("");
                t_patientDateOfCeckOUT.setText("");
                patientTable2Model.setRowCount(0);
                storedPatient();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        /* ********PATIENT SEARCH*************/
        if (ae.getSource() == patient_search) {
            try {
                Class.forName(driver);
                Connection con = DriverManager.getConnection(url, user, pass);
                String sql = "select * from patient where SSN=?";

                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, patsearch.getText());
                patientTable2Model.setRowCount(0);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
           //************get restult of data**************** 
                    String pat_Name = rs.getString("P_Name");
                    t_Patientname.setText(pat_Name);
                    String pat_SSN = rs.getString("SSN");
                    t_PatientSSN.setText(pat_SSN);
                    String pat_MI = rs.getString("Medical_insurance");
                    t_PatientMI.setText(pat_MI);
                    String pat_DA = rs.getString("Date_admitted");
                    t_PatientDA.setText(pat_DA);
                    String PAT_DOCO = rs.getString("Date_of_check_out");
                    t_patientDateOfCeckOUT.setText(PAT_DOCO);

           //*************add data to the model****************
                    Object[] rowData = {pat_Name, pat_SSN, pat_MI, pat_DA, PAT_DOCO};
                    patientTable2Model.addRow(rowData);
                } else {
                    JOptionPane.showMessageDialog(this, "Record Not Found 404");
                }
                t_Patientname.setText("");
                t_PatientSSN.setText("");
                t_PatientMI.setText("");
                t_PatientDA.setText("");
                t_patientDateOfCeckOUT.setText("");
                patsearch.setText("");
                // Notify the table model about the data change
                patientTable2Model.fireTableDataChanged();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        //*************************PATIENT INSERT******************************************
        if (ae.getSource() == patient_insert) {
            try {
                Class.forName(driver);
                Connection con = DriverManager.getConnection(url, user, pass);
                String[] rowData = {t_Patientname.getText(), t_PatientSSN.getText(), t_PatientMI.getText(),
                    t_PatientDA.getText(), t_patientDateOfCeckOUT.getText()};

                String sql = "INSERT INTO patient (P_Name,SSN,Medical_insurance,Date_admitted,Date_of_check_out) VALUES (?, ?, ?,?,?)";
                patientTable2Model.addRow(rowData);
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, t_Patientname.getText());
                stm.setString(2, t_PatientSSN.getText());
                stm.setString(3, t_PatientMI.getText());
                stm.setString(4, t_PatientDA.getText());
                stm.setString(5, t_patientDateOfCeckOUT.getText());
                stm.executeUpdate();
                for (JTextField textField : new JTextField[]{t_Patientname, t_PatientSSN, t_PatientMI, t_PatientDA, t_patientDateOfCeckOUT}) {
                    textField.setText("");
                }
                JOptionPane.showMessageDialog(null, "Inserted");
                t_Patientname.setText("");
                t_PatientSSN.setText("");
                t_PatientMI.setText("");
                t_PatientDA.setText("");
                t_patientDateOfCeckOUT.setText("");
                patsearch.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        /* ********PATIENT DELETE*************/
        if (ae.getSource() == patient_delete) {
            try {
                Class.forName(driver);
                Connection con = DriverManager.getConnection(url, user, pass);
                int SSN = Integer.parseInt(t_PatientSSN.getText());
                String sql = "DELETE FROM patient WHERE SSN = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, SSN);
                // doctorTableModel.removeRow(t_Doctor.getSelectedRow());
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "DONE");
                t_Patientname.setText("");
                t_PatientSSN.setText("");
                t_PatientMI.setText("");
                t_PatientDA.setText("");
                t_patientDateOfCeckOUT.setText("");
                patsearch.setText("");

                patientTable2Model.setRowCount(0);
                storedPatient();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        /******TEST SEARCH**************/
        if (ae.getSource() == test_search) {
            try {
                Class.forName(driver);
                Connection con = DriverManager.getConnection(url, user, pass);
                String sql = "select * from test where Test_id=?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, testsearch.getText());
                testable3Model.setRowCount(0);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    //get restult of data 
                    String tname = rs.getString("Test_name");
                    t_name.setText(tname);
                    String tID = rs.getString("Test_id");
                    t_testID.setText(tID);
                    String tD = rs.getString("Date_test");
                    t_date.setText(tD);
                    String tT = rs.getString("Time_test");
                    t_time.setText(tT);
                    String tR = rs.getString("Result");
                    t_result.setText(tR);

                    //add data to the model
                    Object[] rowData = {tname, tID, tD, tT, tR};
                t_name.setText("");
                t_testID.setText("");
                t_date.setText("");
                t_time.setText("");
                t_result.setText("");
                testsearch.setText("");

                    testable3Model.addRow(rowData);
                } else { //if you donot find the record show this message
                    JOptionPane.showMessageDialog(this, "Record not found 404");
                }
                // Notify the table model about the data change
                testable3Model.fireTableDataChanged();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        /***********TEST INSERT******************/
        if (ae.getSource() == test_insert) {
            try {
                Class.forName(driver);
                Connection con = DriverManager.getConnection(url, user, pass);
                String[] rowData = { t_name.getText(),t_testID.getText(),  t_date.getText(), t_time.getText(), t_result.getText()};

                String sql = "INSERT INTO test ( Test_name,Test_id ,Date_test,Time_test,Result) VALUES (?,?, ?,?,?)";
                testable3Model.addRow(rowData);
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, t_name.getText());
                stm.setString(2, t_testID.getText());
                stm.setString(4, t_time.getText());
                stm.setString(3, t_date.getText());
                stm.setString(5, t_result.getText());

                stm.executeUpdate();
                for (JTextField textField : new JTextField[]{  t_name,t_testID, t_date, t_time, t_result}) {
                    textField.setText("");
                }
                JOptionPane.showMessageDialog(null, "Inserted");
               t_name.setText("");
                t_testID.setText("");
                t_date.setText("");
                t_time.setText("");
                t_result.setText("");
                testsearch.setText("");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        /*************TEST UPDATE*******************/
        if (ae.getSource() == test_update) {
            try {
                Class.forName(driver);
                Connection con = DriverManager.getConnection(url, user, pass);
                String sql = "update test set Test_name=? ,Date_test=?,Time_test=?,Result=? where Test_id=?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, t_name.getText());
                stm.setString(2, t_date.getText());
                stm.setString(3, t_time.getText());
                stm.setString(4, t_result.getText());
                stm.setString(5, t_testID.getText());

                stm.executeUpdate();
                JOptionPane.showMessageDialog(this, "Updated");
                t_name.setText("");
                t_testID.setText("");
                t_date.setText("");
                t_time.setText("");
                t_result.setText("");
                testsearch.setText("");

                testable3Model.setRowCount(0);
                storedTest();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        /************ TEST DELETE ********************/
        if (ae.getSource() == test_delete) {
            try {
                Class.forName(driver);
                Connection con = DriverManager.getConnection(url, user, pass);
                int testID = Integer.parseInt(t_testID.getText());
                String sql = "DELETE FROM test WHERE Test_id = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, testID);

                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "DONE");
                t_testID.setText("");
                t_name.setText("");
                t_date.setText("");
                t_time.setText("");
                t_result.setText("");
                testsearch.setText("");

                testable3Model.setRowCount(0);
                storedTest();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

        if (ae.getSource() == doctor) {
            setVisible(false);
            f_doctor.setVisible(true);
        }
        if (ae.getSource() == patient) {
            setVisible(false);
            f_patient.setVisible(true);
        }
        if (ae.getSource() == test) {
            setVisible(false);
            f_test.setVisible(true);
        }

        if (ae.getSource() == doctor_back) {
            f_doctor.setVisible(false);
            setVisible(true);
        }

        if (ae.getSource() == patient_back) {
            f_patient.setVisible(false);
            setVisible(true);
        }

        if (ae.getSource() == test_back) {
            f_test.setVisible(false);
            setVisible(true);
        }
    }
}

//mohamed gamal hassan 
//roqia abdelsatar abdelsamii
//abdelrahman osama ahmed
//c1