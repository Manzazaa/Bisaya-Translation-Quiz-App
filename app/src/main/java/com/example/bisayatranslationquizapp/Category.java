package com.example.bisayatranslationquizapp;

public class Category {
        public static final int ENGTOBIS = 1;
        public static final int BISTOENG = 2;
        public static final int CUSTOM = 3;

        private int id;
        private String name;

        public Category() {
        }

        public Category(String name) {
                this.name = name;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        @Override
        public String toString() {
                return getName();
        }
}