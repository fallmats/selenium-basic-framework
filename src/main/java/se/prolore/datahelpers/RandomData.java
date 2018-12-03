package se.prolore.datahelpers;


import java.util.Random;
import java.util.UUID;

public class RandomData {

    public String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return String.valueOf(uuid);
    }

    public String generateUUID(int length) {
        length--;
        if (length > 35 && length <= 0) {
            throw new IllegalArgumentException("felaktigt antal tecken begärt 1-36");
        }
        return generateUUID().substring(0,length);
    }

    public int getRandomInt() {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt();
    }

    public int getRandomInt(int maxValue) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(maxValue);
    }

    public String getRandomNameFromList() {
        Random generate = new Random();
        int randomNumber = generate.nextInt(3);
        String[] name = {"Mats", "Helena", "Viktor"};
        return name[randomNumber];
    }

}
