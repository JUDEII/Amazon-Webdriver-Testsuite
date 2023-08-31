package utils;

public class Util {

    public String removeSpaces(String text) {
        return text.replace(" ", "");
    }

    public double convertStringToNumber(String price) {
        String numberString = price.replace("$", "");
        return Double.parseDouble(numberString);
    }

    public double concatenatePricesValues(String whole, String fraction){
        String wholeString = removeSpaces(whole);
        String fractionString = removeSpaces(fraction);
        String concatenate = wholeString + "." + fractionString;
        return convertStringToNumber(concatenate);
    }
}
