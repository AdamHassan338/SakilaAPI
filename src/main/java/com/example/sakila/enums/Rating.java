package com.example.sakila.enums;

public enum Rating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17"),
    INVALID("INVALID");

    private String code;

    Rating(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static boolean isValidRating(String s){
        switch (s){
            case "G":
            case "PG":
            case "PG-13":
            case "R":
            case "NC-17":
                return true;
            default:
                return false;
        }
    }

    public static String enumToRating(Rating e){
        switch (e){
            case Rating.G:
                return "G";
            case Rating.PG:
                return "PG" ;
            case Rating.PG13:
                return "PG-13" ;
            case Rating.R:
                return "R" ;
            case Rating.NC17:
                return "NC-17" ;

        }
        return  new String();
    }

    public static Rating ratingToEnum(String s){
        switch (s){
            case "G":
                return Rating.G;
            case "PG":
                return Rating.PG;
            case "PG-13":
                return Rating.PG13;
            case "R":
                return Rating.R;
            case "NC-17":
                return Rating.NC17;

        }
        return Rating.INVALID;
    }
}
