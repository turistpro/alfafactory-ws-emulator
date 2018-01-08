package com.github.turistpro.alfafactory.pega.wsemul;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.github.turistpro.javafaker.FakerRussian;

import java.util.regex.Pattern;

public class CustomerFactory {

    private Faker faker;

    public CustomerFactory(Faker faker) {
        this.faker = faker;
    }

    private static String transliterate(String message){
        char[] abcCyr =   {'.',' ','а','б','в','г','д','е','ё', 'ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х', 'ц','ч', 'ш','щ','ъ','ы','ь','э', 'ю','я','А','Б','В','Г','Д','Е','Ё', 'Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х', 'Ц', 'Ч','Ш', 'Щ','Ъ','Ы','Ь','Э','Ю','Я','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String[] abcLat = {"."," ","a","b","v","g","d","e","e","zh","z","i","y","k","l","m","n","o","p","r","s","t","u","f","h","ts","ch","sh","sch", "","i", "","e","ju","ja","A","B","V","G","D","E","E","Zh","Z","I","Y","K","L","M","N","O","P","R","S","T","U","F","H","Ts","Ch","Sh","Sch", "","I", "","E","Ju","Ja","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            for (int x = 0; x < abcCyr.length; x++ ) {
                if (message.charAt(i) == abcCyr[x]) {
                    builder.append(abcLat[x]);
                }
            }
        }
        return builder.toString();
    }

    public Customer getFakeCustomer() {
        Customer cus = new Customer();
        cus.setId("A" + faker.number().randomNumber(5, true));
        Name fakerName = faker.name();
        String fullName = fakerName.fullName();
        String[] nameParts = fullName.split(" ");
        cus.setFullName(fullName);
        Pattern reLastName = Pattern.compile("ова$|ов$|ин$|ина$|ев$|ева$");
        if(reLastName.matcher(nameParts[nameParts.length-1]).find()) {
            cus.setFirstName(nameParts[0]);
            if(nameParts.length>2) {
                cus.setMiddleName(nameParts[1]);
                cus.setLastName(nameParts[2]);
            } else {
                cus.setLastName(nameParts[1]);
            }
        } else {
            cus.setLastName(nameParts[0]);
            cus.setFirstName(nameParts[1]);
            if(nameParts.length>2) {
                cus.setMiddleName(nameParts[2]);
            }
        }

        cus.setPhoneNumber(faker.phoneNumber().phoneNumber().replaceAll("\\D+", ""));
        cus.setBirthdate(faker.date().birthday());

        String username = (cus.getLastName() + "." + cus.getFirstName())
                .toLowerCase();
        cus.setEmail(faker.internet().emailAddress(transliterate(username)));
        cus.setInn(new FakerRussian(faker).inn12());
        return cus;
    }
    public Customer getFakeCustomer(Customer sibCus) {
        Customer cus = getFakeCustomer();
        /*if (sibCus.getLastName().length()>0) {
            cus.setLastName(sibCus.getLastName());
        }*/
        return cus;
    }
}
