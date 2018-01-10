package com.github.javafaker;


import java.util.Locale;
import java.util.Random;

public class FakerRussian extends Faker {

    //private NameRussian nameRussian;
    private Russian russian;

    public FakerRussian() {
        this(Locale.ENGLISH);
    }

    public FakerRussian(Locale locale) {
        this(locale, null);
    }

    public FakerRussian(Random random) {
        this(Locale.ENGLISH, random);
    }


    public FakerRussian(Locale locale, Random random) {
        super(locale, random);
        //this.nameRussian = new NameRussian(this);
        this.russian = new Russian(this);
    }

    public NameRussian nameRussian() {
        return new NameRussian(this);
    }

    public Russian russian() {
        return this.russian;
    }




}
