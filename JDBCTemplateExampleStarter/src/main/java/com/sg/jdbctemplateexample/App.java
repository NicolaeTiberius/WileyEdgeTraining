package com.sg.jdbctemplateexample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


@SpringBootApplication
public class App implements CommandLineRunner{

    //Behind the scenes, Spring has created the JdbcTemplate bean for us using the properties we defined
    @Autowired
    private JdbcTemplate jdbcTemplateObj;
    private static Scanner sc;

    public static void main(String args[]) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        sc = new Scanner(System.in);

        do {
            System.out.println("To-Do List");
            System.out.println("1. Display List");
            System.out.println("2. Add Item");
            System.out.println("3. Update Item");
            System.out.println("4. Remove Item");
            System.out.println("5. Exit");

            System.out.println("Enter an option:");
            String option = sc.nextLine();
            try {
                switch (option) {
                    case "1":
                        displayList();
                        break;
                    case "2":
                        addItem();
                        break;
                    case "3":
                        updateItem();
                        break;
                    case "4":
                        removeItem();
                        break;
                    case "5":
                        System.out.println("Exiting");
                        System.exit(0);
                    default:
                        System.out.println("I don't understand");
                }
            } catch (Exception ex) {
                System.out.println("Error communicating with database");
                System.out.println(ex.getMessage());
                System.exit(0);
            }

        } while (true);
    }

    private void displayList() throws SQLException {
        //executes query and writes it to a result set for when needed to be read out.
        // - does this for every instance of ToDoMapper.
       // The query method expects us to give it at least a String SQL query and a mapper: in this case, our ToDoMapper.
        //If there is additional parameters for our query, those would be added in after the ToDoMapper.
        List<ToDo> todos = jdbcTemplateObj.query("SELECT * FROM todo", new ToDoMapper()); //arraylist of to-do objects
        for (ToDo td : todos) {
            System.out.printf("%s: %s -- %s -- %s\n",
                    td.getId(),
                    td.getTodo(),
                    td.getNote(),
                    td.isFinished());
        }
        System.out.println("");
    }

    //INSERT method.
    private void addItem() throws SQLException {
        System.out.println("Add Item");
        System.out.println("What is the task?");
        String task = sc.nextLine();
        System.out.println("Any additional notes?");
        String note = sc.nextLine();
        jdbcTemplateObj.update("INSERT INTO todo(todo, note) VALUES(?,?)", task, note);
        System.out.println("Add Complete");
    }

//    UPDATE A COLUMN to make changes in the database, this means INSERT, UPDATE and DELETE calls all go through the "update" method.
//    First have to put in our SQL query with question marks for the parameters we got for the user and then add in those variables.
//     Don't need a mapper in this case because we are not expecting results back from the INSERT.
    private void updateItem() throws SQLException {
        System.out.println("Update Item");
        System.out.println("Which item do you want to update?");
        String itemId = sc.nextLine();
        ToDo item = jdbcTemplateObj.queryForObject("SELECT * FROM todo WHERE id = ?", new ToDoMapper(), itemId);
        //displays the current item and asks what to updat
        System.out.println("1. ToDo - " + item.getTodo());
        System.out.println("2. Note - " + item.getNote());
        System.out.println("3. Finished - " + item.isFinished());
        System.out.println("What would you like to change?");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Enter new ToDo:");
                String todo = sc.nextLine();
                item.setTodo(todo);
                break;
            case "2":
                System.out.println("Enter new Note:");
                String note = sc.nextLine();
                item.setNote(note);
                break;
            case "3":
                System.out.println("Toggling Finished to " + !item.isFinished());
                item.setFinished(!item.isFinished());
                break;
            default:
                System.out.println("No change made");
                return;
        }
    }

    //Once we have the ID we can use the JdbcTemplate "update" method to perform the delete.
    private void removeItem() throws SQLException {
        System.out.println("Remove Item");
        System.out.println("Which item would you like to remove?");
        String itemId = sc.nextLine();
        jdbcTemplateObj.update("DELETE FROM todo WHERE id = ?", itemId);
        System.out.println("Remove Complete");
    }



    //PRIVATE INTERNAL CLASS of app class - This means it will only be accessible from the App class.
    //data marshalling for the To-do table.
    //
    //Carries out SQL that will retrieve all the columns from the to-do table.

    //mapping rows.

    //So can only do 1 instance as it is static.

    private static final class ToDoMapper implements RowMapper<ToDo> {

        //cannot be changed by anybody. (static final)
        @Override
        public ToDo mapRow(ResultSet rs, int index) throws SQLException {
            //result set used to iterate through after getting the query from a method it will retrieve these.
            ToDo td = new ToDo();
            td.setId(rs.getInt("id"));
            td.setTodo(rs.getString("todo"));
            td.setNote(rs.getString("note"));
            td.setFinished(rs.getBoolean("finished"));
            return td;
        }
    }

}
