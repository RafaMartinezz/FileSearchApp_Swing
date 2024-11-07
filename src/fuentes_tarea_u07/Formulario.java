/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes_tarea_u07;

import java.awt.Color;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 * The Formulario class represents a GUI application that allows users to load a text file,
 * display its content, and search for a specified pattern within the text. 
 * Matching text is highlighted in the output area.
 * This class is built using the Swing framework.
 * 
 * @version 1.0
 */
public class Formulario extends javax.swing.JFrame {

    /**
     * Creates new form Formulario2 and initializes the GUI components.
     */
    public Formulario() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ruta = new javax.swing.JTextField();
        patron = new javax.swing.JTextField();
        btnExaminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        salida = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Patrón de búsqueda"); // Label for the search pattern input

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Salida:"); // Label for output area

        ruta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        // Text field to display the file path

        patron.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        // Text field for entering the search pattern

        btnExaminar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExaminar.setText("Examinar"); // Button to browse files
        btnExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExaminarActionPerformed(evt); // Call method to open file chooser
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBuscar.setText("Buscar"); // Button to search for pattern in file content
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt); // Call method to search for pattern
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Selecciona un archivo"); // Label for file selection prompt

        salida.setColumns(20); // Text area to display the file content
        salida.setRows(5);
        jScrollPane2.setViewportView(salida);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        // Layout settings omitted for brevity
        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Opens a file chooser dialog for selecting a text file. The selected file's content 
     * is loaded and displayed in the text area. Only .txt files are allowed for selection.
     *
     * @param evt the ActionEvent triggered when the "Examinar" button is clicked
     */
    private void btnExaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExaminarActionPerformed
        int seleccion;
        JFileChooser jf = new JFileChooser("."); // Opens file chooser in current directory
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY); // Allows only file selection
        jf.setMultiSelectionEnabled(false); // Single file selection mode
        FileFilter filtro = new FileNameExtensionFilter("Ficheros de texto", "txt"); // Filter for .txt files
        jf.setFileFilter(filtro);

        seleccion = jf.showOpenDialog(jf);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            // If a file is selected, display its path and read its content
            ruta.setText(jf.getSelectedFile().getAbsolutePath());
            FileReader fr = null;
            BufferedReader br = null;
            File archivo = null;
            String linea;
            String contenido = "";
            try {
                archivo = new File(jf.getSelectedFile().getAbsolutePath());
                if (archivo.isFile()) {
                    fr = new FileReader(archivo);
                    br = new BufferedReader(fr);
                    // Read the file line by line and append to content string
                    while ((linea = br.readLine()) != null) {
                        contenido += linea + "\n";
                    }
                    salida.setText(contenido); // Display content in the text area
                } else {
                    // Show warning if no file was selected
                    JOptionPane.showMessageDialog(null, "Debes de seleccionar un fichero", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Ensure the FileReader is closed
                try {
                    if (fr != null) {
                        fr.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnExaminarActionPerformed

    /**
     * Searches for occurrences of the specified pattern in the loaded text and 
     * highlights matches in green.
     *
     * @param evt the ActionEvent triggered when the "Buscar" button is clicked
     */
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarpalabra(salida, patron.getText()); // Call search method with user-defined pattern
    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * Searches for a specified text pattern within a JTextArea, highlighting matching occurrences.
     * 
     * @param area1 the JTextArea where the search is conducted
     * @param texto the search pattern to look for
     */
    public void buscarpalabra(JTextArea area1, String texto) {
        if (texto.length() >= 1) {
            // Set up a highlighter with green color
            DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
            Highlighter h = area1.getHighlighter();
            h.removeAllHighlights(); // Clear any existing highlights
            String text = area1.getText(); // Get the content of the text area
            String caracteres = texto;
            Pattern p = Pattern.compile("(?i)" + caracteres); // Case-insensitive pattern matching
            Matcher m = p.matcher(text);

            // Highlight each match found
            while (m.find()) {
                try {
                    h.addHighlight(m.start(), m.end(), highlightPainter);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            // Show warning if no text is provided for search
            JOptionPane.showMessageDialog(area1, "Debe de indicar el texto a buscar");
        }
    }

    /**
     * Main method to launch the Formulario application.
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Formulario().setVisible(true); // Launches the Formulario GUI
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar; // Button for initiating search
    private javax.swing.JButton btnExaminar; // Button for opening file chooser
    private javax.swing.JLabel jLabel2; // Label for search pattern
    private javax.swing.JLabel jLabel3; // Label for output area
    private javax.swing.JLabel jLabel4; // Label for file selection
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField patron; // Text field for search pattern
    private javax.swing.JTextField ruta; // Text field for file
    private javax.swing.JTextArea salida; // Text area for output

}