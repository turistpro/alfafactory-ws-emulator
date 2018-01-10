package com.github.javafaker;


public class NameRussian {

    private FakerRussian faker;
    private static final char[] abcCyr =   {'.',' ','а','б','в','г','д','е','ё', 'ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х', 'ц','ч', 'ш','щ','ъ','ы','ь','э', 'ю','я','А','Б','В','Г','Д','Е','Ё', 'Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х', 'Ц', 'Ч','Ш', 'Щ','Ъ','Ы','Ь','Э','Ю','Я','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private static final String[] abcLat = {"."," ","a","b","v","g","d","e","e","zh","z","i","y","k","l","m","n","o","p","r","s","t","u","f","h","ts","ch","sh","sch", "","i", "","e","ju","ja","A","B","V","G","D","E","E","Zh","Z","I","Y","K","L","M","N","O","P","R","S","T","U","F","H","Ts","Ch","Sh","Sch", "","I", "","E","Ju","Ja","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    private String firstName;
    private String lastName;
    private String middleName;

    public NameRussian(FakerRussian faker) {
        this.faker = faker;
        generate();
    }

    private static String transliterate(String message){
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

    private void generate(){
        if(faker.random().nextBoolean()) {
            this.firstName = this.faker.fakeValuesService().resolve("name.male_first_name", this, this.faker);
            this.lastName = this.faker.fakeValuesService().resolve("name.male_last_name", this, this.faker);
            this.middleName = this.faker.fakeValuesService().resolve("name.male_middle_name", this, this.faker);
        } else {
            this.firstName = this.faker.fakeValuesService().resolve("name.female_first_name", this, this.faker);
            this.lastName = this.faker.fakeValuesService().resolve("name.female_last_name", this, this.faker);
            this.middleName = this.faker.fakeValuesService().resolve("name.female_middle_name", this, this.faker);
        }
    }

    public String firstName() {
        return this.firstName;
    }

    public String lastName() {
        return this.lastName;
    }

    public String middleName() {
        return this.middleName;
    }

    public String fullName() {
        return this.lastName + " " + this.firstName + " " + this.middleName;
    }

    public String username() {
        String user;
        int mode = faker.number().numberBetween(0, 2);
        switch (mode) {
            case 1: user = firstName() + "." + lastName();
                break;
            case 2: user = firstName().substring(0, 1) + "." + middleName().substring(0, 1) + "." + lastName();
                break;
            default: user = firstName().substring(0, 1) + "." + lastName();
                break;
        }
        return transliterate(user.toLowerCase());
    }

}
