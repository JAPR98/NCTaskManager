package mx.edu.j2se.perez.tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TaskIO {

    /**
     * Writes the tasks from the list to the stream in a binary format
     * described below.
     * @param tasks The task list to be written
     * @param out The OutputStream where the tasks are going to be written
     * @throws IllegalArgumentException whether the tasks or out arguments
     *         are null
     */
    static void writeBinary (AbstractTaskList tasks, OutputStream out) throws
            IllegalArgumentException {
        if (tasks == null || out == null) {
            throw new IllegalArgumentException("The tasks or out arguments " +
                    "mustn't be null");
        }
        try {
            DataOutput output = new DataOutputStream(out);
            output.writeInt(tasks.size());
            for (Task task: tasks) {
                output.writeInt(task.getTitle().length());
                output.writeUTF(task.getTitle());
                if (task.isActive()) {
                    output.writeInt(1);
                } else {
                    output.writeInt(0);
                }
                if (task.isRepeated()) {
                    output.writeInt(task.getRepeatInterval());
                    output.writeLong(task.getStartTime().
                            toEpochSecond(ZoneOffset.UTC));
                    output.writeLong(task.getEndTime().
                            toEpochSecond(ZoneOffset.UTC));
                } else {
                    output.writeInt(0);
                    output.writeLong(task.getStartTime().
                            toEpochSecond(ZoneOffset.UTC));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads tasks from the binary stream to the current task list.
     * @param tasks The task list where the tasks will be stored
     * @param in The InputStream where we will obtain the tasks
     * @throws IllegalArgumentException whether the tasks or in arguments
     *         are null
     */
    static void readBinary(AbstractTaskList tasks, InputStream in) throws
            IllegalArgumentException {
        if (tasks == null || in == null) {
            throw new IllegalArgumentException("The tasks or in arguments " +
                    "mustn't be null");
        }
        try {
            DataInput input = new DataInputStream(in);
            int numberOfTasks = input.readInt();
            for (int i = 0; i < numberOfTasks; i++) {
                input.readInt(); // Reading the task name length
                String name = input.readUTF();
                boolean isActive = input.readInt() != 0;
                int interval = input.readInt();
                if (interval == 0) {
                    LocalDateTime time = LocalDateTime.
                            ofEpochSecond(input.readLong(),0,
                                    ZoneOffset.UTC);
                    Task task = new Task(name, time);
                    task.setActive(isActive);
                    tasks.add(task);
                } else {
                    LocalDateTime start = LocalDateTime.
                            ofEpochSecond(input.readLong(),0,
                                    ZoneOffset.UTC);
                    LocalDateTime end = LocalDateTime.
                            ofEpochSecond(input.readLong(),0,
                                    ZoneOffset.UTC);
                    Task task = new Task(name, start, end, interval);
                    task.setActive(isActive);
                    tasks.add(task);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes tasks from the list to the binary file
     * @param tasks The task list to be written
     * @param file The file where the tasks will be written
     * @throws IllegalArgumentException Whether the tasks or file
     *         arguments are null
     */
    static void write(AbstractTaskList tasks, File file) throws
            IllegalArgumentException {
        if (tasks == null || file == null) {
            throw new IllegalArgumentException("The tasks or file arguments " +
                    "mustn't be null");
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            writeBinary(tasks, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads tasks from the JSON stream to the list.
     * @param tasks The task list where the tasks will be stored
     * @param file The file where we will obtain the task list
     * @throws IllegalArgumentException whether the tasks or in arguments
     *         are null
     */
    static void read(AbstractTaskList tasks, File file) throws
            IllegalArgumentException {
        if (tasks == null || file == null) {
            throw new IllegalArgumentException("The tasks or out arguments " +
                    "mustn't be null");
        }
        try {
            FileInputStream in = new FileInputStream(file);
            TaskIO.readBinary(tasks, in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes tasks from the list to the stream in the JSON format.
     * @param tasks Task list to be written
     * @param out The writer where the tasks are going to be written
     * @throws IllegalArgumentException whether the tasks or in arguments
     *         are null
     */
    static void write(AbstractTaskList tasks, Writer out) throws
            IllegalArgumentException {
        if (tasks == null || out == null) {
            throw new IllegalArgumentException("The tasks or out arguments " +
                    "mustn't be null");
        }
        try {
            Gson Gson = new GsonBuilder().setPrettyPrinting().create();
            for (Task task: tasks) {
                Gson.toJson(task,out);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads tasks from the JSON stream to the list.
     * @param tasks The task list where the tasks will be stored
     * @param in The reader where we will obtain the task list
     * @throws IllegalArgumentException whether the tasks or in arguments
     *         are null
     */
    static void read(AbstractTaskList tasks, Reader in) throws
            IllegalArgumentException {
        if (tasks == null || in == null) {
            throw new IllegalArgumentException("The tasks or out arguments " +
                    "mustn't be null");
        }
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(in);
            reader.setLenient(true);
            while (reader.peek() != JsonToken.END_DOCUMENT) {
                tasks.add(gson.fromJson(reader, Task.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes tasks to the file in JSON format
     * @param tasks  The task list to be written
     * @param file The file where the tasks will be written
     * @throws IllegalArgumentException whether the tasks or file
     *         arguments are null
     */
    static void writeText(AbstractTaskList tasks, File file) throws
            IllegalArgumentException {
        if (tasks == null || file == null) {
            throw new IllegalArgumentException("The tasks or out arguments " +
                    "mustn't be null");
        }
        try {
            Writer wr = new FileWriter(file);
            write(tasks,wr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads tasks from the JSON file
     * @param tasks The task list where the tasks will be stored
     * @param file  The file where we will obtain the task list
     * @throws IllegalArgumentException Whether the tasks or file
     *         arguments are null
     */
    static void readText(AbstractTaskList tasks, File file) throws
            IllegalArgumentException {
        if (tasks == null || file == null) {
            throw new IllegalArgumentException("The tasks or out arguments " +
                    "mustn't be null");
        }
        try {
            Reader rd = new FileReader(file);
            TaskIO.read(tasks, rd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
