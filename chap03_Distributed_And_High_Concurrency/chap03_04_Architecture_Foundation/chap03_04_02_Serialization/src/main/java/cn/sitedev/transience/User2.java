package cn.sitedev.transience;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User2 implements Serializable {
    private String name;
    private int age;
    private transient double salary;
    private transient User2 friend;

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.salary = (double) objectInputStream.readObject();
        this.friend = (User2) objectInputStream.readObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(salary);
        objectOutputStream.writeObject(friend);
    }

}
