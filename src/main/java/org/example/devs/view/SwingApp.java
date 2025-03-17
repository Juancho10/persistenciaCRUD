/*package org.example.devs.view;
import org.example.devs.model.User;
import org.example.devs.repository.IRepository;
import org.example.devs.repository.UserRepository;

import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;


public class SwingApp extends JFrame {

    private final IRepository<User> userIRepository;

    private final JTable userTable;
    /*public SwingApp(IRepository<User> userIRepository) {
        this.userIRepository = userIRepository;
    }*/

    // configurar la ventana
/*
    public SwingApp() {
        setTitle("Gestion de usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,260);

        //crear una tabla para mostrar los empleados
        userTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);

        //crear botones para acciones

        JButton agregar =  new JButton("Agregar");
        JButton actualizar = new JButton("Actualizar");
        JButton eliminar = new JButton("eliminar");

        // configurar el panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agregar);
        buttonPanel.add(actualizar);
        buttonPanel.add(eliminar);
        add(buttonPanel, BorderLayout.SOUTH);

        // establecer estilos para los botones
        agregar.setBackground(new Color(0,153,76));
        agregar.setFont(new Font("Cascadia Mono PL", Font.BOLD, 25));
        agregar.setForeground(Color.white);
        agregar.setFocusPainted(false);

        actualizar.setBackground(new Color(51,51,255));
        actualizar.setFont(new Font("Cascadia Mono PL", Font.BOLD, 25));
        actualizar.setForeground(Color.white);
        actualizar.setFocusPainted(false);

        eliminar.setBackground(new Color(204,0,0));
        eliminar.setFont(new Font("Cascadia Mono PL", Font.BOLD, 25));
        eliminar.setForeground(Color.white);
        eliminar.setFocusPainted(false);

        //crear el objeto repository para acceder a la base de datos

        userIRepository = new UserRepository();

        // cargar empleados iniciales con el metodo de actualización de la tabla

        refreshUsersTable();

        //agregar action listener para los botones

        agregar.addActionListener(e ->{
            try{
                agregarUser();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            };
        });

        actualizar.addActionListener(e -> actualizarEmpleado());

        eliminar.addActionListener(e -> eliminarEmpleado());

    }

    private void refreshUsersTable(){
         //lista de usuarios
        try{
            List<User> userModel = userIRepository.findAll();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Documento");
            model.addColumn("Nombre");
            model.addColumn("Apellido");
            model.addColumn("Correo");
            model.addColumn("Password");
            model.addColumn("RolID");

            for(User user : userModel){
                Object[] rowData = {
                        user.getId(),
                        user.getDocument(),
                        user.getName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getIdRol()
                };
                model.addRow(rowData);
            }
            userTable.setModel(model);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void agregarUser () throws SQLException{
        //Crear un formulario para crear un empleado

        JTextField documentField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField rolIDField = new JTextField();

        Object[] field={
                "Documento:", documentField,
                "Nombre:", nameField,
                "Apellido:",lastNameField,
                "Correo:",emailField,
                "Contraseña:",passwordField,
                "RolID:",rolIDField
        };

        int result = JOptionPane.showConfirmDialog(this,field,"Agregar Empleado",JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION){
            //creamos un nuevo objeto usuario con los datos ingresados
            User user =  new User();
            user.setDocument(documentField.getText());
            user.setName(nameField.getText());
            user.setLastName(lastNameField.getText());
            user.setEmail(emailField.getText());
            user.setPassword(passwordField.getText());
            user.setIdRol(Integer.parseInt(rolIDField.getText()));

            // Guardamos el nuevo usuario en la base de datos
            userIRepository.save(user);

            // Atualizamos la tabla con los nuevos usuarios
            refreshUsersTable();

            JOptionPane.showMessageDialog(this,"Empleado agregado correctamente","Exito",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void actualizarEmpleado(){
        // obtener el ID del empleado para actualizar
        String userIdStr = JOptionPane.showInputDialog(this,"Ingrese el ID del empleado a actualizar","Actualizar Empleado", JOptionPane.INFORMATION_MESSAGE);
        try{
            int userId =  Integer.parseInt(userIdStr);

            //obtener el id desde la base de datos

            User user =  userIRepository.getByID(userId);
            if (user != null){
                //crear un formulario con los datos del empleado
                JTextField documentField = new JTextField(user.getDocument());
                JTextField nameField = new JTextField(user.getName());
                JTextField lastNameField = new JTextField(user.getLastName());
                JTextField emailField = new JTextField(user.getEmail());
                JTextField passwordField = new JTextField(user.getPassword());
                JTextField rolIDField = new JTextField(String.valueOf(user.getIdRol()));

                Object[] field = {
                        "Documento:",documentField,
                        "Nombre:",nameField,
                        "Apellido",lastNameField,
                        "Correo",emailField,
                        "Password",passwordField,
                        "RolId",rolIDField
                };

                int confirmResult = JOptionPane.showConfirmDialog(this,field,"Actualizar Empleado",JOptionPane.OK_CANCEL_OPTION);
                if(confirmResult == JOptionPane.OK_OPTION){
                    //actualizar los datos del empleado con los datos ingresados en el formulario
                    user.setDocument(documentField.getText());
                    user.setName(nameField.getText());
                    user.setLastName(lastNameField.getText());
                    user.setEmail(emailField.getText());
                    user.setPassword(passwordField.getText());
                    user.setIdRol(Integer.parseInt(rolIDField.getText()));

                    // Guardamos los cambios del usuario en la base de datos
                    userIRepository.save(user);

                    // Atualizamos la tabla en la interfaz
                    refreshUsersTable();

                }else{
                    JOptionPane.showMessageDialog(this,"No se encontro el usuario indicado","Error de busqueda",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Ingrese un valos numerico para el ID","Error de busqueda",JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(this,"Error al obtener los datos del empleado del ID indicado","Error de busqueda",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarEmpleado(){
        String userIdStr = JOptionPane.showInputDialog(this,"Ingrese el ID del empleado a actualizar","Actualizar Empleado", JOptionPane.INFORMATION_MESSAGE);
        if(userIdStr != null){
            try{
                int usuariaId = Integer.parseInt(userIdStr);

                //confirmar la eliminación del usuario
                int confirmResult = JOptionPane.showConfirmDialog(this,"¿Estas seguro de eliminar el usuario?","Actualizar Empleado",JOptionPane.OK_CANCEL_OPTION);
                if (confirmResult == JOptionPane.YES_OPTION){
                    userIRepository.delete(usuariaId);

                    // Atualizamos la tabla en la interfaz
                    refreshUsersTable();
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this,"Ingrese un valos numerico para el ID","Error de busqueda",JOptionPane.INFORMATION_MESSAGE);
            }catch (SQLException e){
                JOptionPane.showMessageDialog(this,"Error al obtener los datos del empleado del ID indicado","Error de busqueda",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
*/