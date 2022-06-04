package javaCalculator;

import java.util.*;

public class CalculatorManager {
    public static void main(String [] args) throws InvalidInputException {
        System.out.print("Введите арифметическую операцию из двух чисел в формате 1 + 1 или I + I (через пробел): ");
        Scanner sc = new Scanner(System.in); //Добавляем сканер для ввода выражения
        String mathExpression = sc.nextLine(); //Считываем введенное выражение
        String[] element = mathExpression.split(" "); //Делим выражение на элементы по пробелу

        //Выводим на экран рекомендации по вводу выражения, если введено недостаточно или слишком много символов
        if (element.length > 3) {
            throw new InvalidInputException("Требуется ввести арифметическую операцию с двумя числами. На данный момент введено слишком много символов.");
        } else if (element.length < 3) {
            throw new InvalidInputException("Требуется ввести арифметическую операцию с двумя числами. На данный момент введено недостаточно символов." + "\n" +
                    "Если все в порядке, значит вы забыли поставить пробелы между числами и знаком.");
        }

        int firstNum;
        int secondNum;

        //Если элемент ввода можно записать как число, то записываем, если нет, то превращаем в арабскую цифру (если возможно)
        try {
            firstNum = Integer.parseInt(element[0]);
        } catch (NumberFormatException nfe) {
            firstNum = romanToArab(element[0]);
        }

        try {
            secondNum = Integer.parseInt(element[2]);
        } catch (NumberFormatException nfe) {
            secondNum = romanToArab(element[2]);
        }

        //Добавляем ограничение на ввод чисел, которые меньше 1 или больше 10
        if ((firstNum > 10 || firstNum < 1) || (secondNum > 10 || secondNum < 1)) {
            throw new InvalidInputException("Требуется ввести целые числа в диапазоне от 1 до 10");
        }

        //Добавляем условие, что оба числа должны быть либо арабскими, либо римскими
        boolean flag;
        if ((Character.isDigit(element[0].charAt(0)) && Character.isDigit(element[2].charAt(0))) ||
        (Character.isLetter(element[0].charAt(0)) && Character.isLetter(element[2].charAt(0)))) {
            flag = true;
        } else {
            throw new InvalidInputException("Требуется ввести два числа в римском формате или два числа в арабском формате.");
        }

        //Преобразуем арифметический знак в char
        char operation = element[1].charAt(0);

        //В результирующую переменную помещаем калькулятор
        int result = calculator(firstNum, operation, secondNum);

        //Выводим результат:
        if (Character.isDigit(element[0].charAt(0)) && Character.isDigit(element[2].charAt(0))) {
            System.out.println("Результат вычислений:" + " " + result);
        } else {
            if (result < 1) {
                throw new InvalidInputException("В римской системе счисления нет нуля и отрицательных чисел.");
            } else {
                System.out.println("Результат вычислений:" + " " + arabToRoman(result));
            }
        }
    }
    //Создаем метод для перевода арабских цифр в римские
    private static String arabToRoman (int num) {
        String roman [] = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII",
                "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI",
                "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII",
                "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX",
                "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII",
                "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV",
                "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI",
                "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        final String numroman = roman[num];
        return numroman;
    }
    //Создаем метод для перевода римских цифр в арабские
    private static int romanToArab(String roman) {
        try {
           if (roman.equals("I")) {
               return 1;
           } else if (roman.equals("II")) {
               return 2;
           } else if (roman.equals("III")) {
                return 3;
            } else if (roman.equals("IV")) {
                return 4;
            } else if (roman.equals("V")) {
                return 5;
            } else if (roman.equals("VI")) {
                return 6;
            } else if (roman.equals("VII")) {
                return 7;
            } else if (roman.equals("VIII")) {
                return 8;
            } else if (roman.equals("IX")) {
                return 9;
            } else if (roman.equals("X")) {
                return 10;
            }
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("Неверный формат данных");
        }
        return -1;
    }

    //Создаем метод, с помощью которого будет выполняться арифметическое действие
    private static int calculator(int firstNum, char operation, int secondNum) {
        Integer result = null;
        switch (operation) {
            case '+':
                result = firstNum + secondNum;
                break;
            case '-':
                result = firstNum - secondNum;
                break;
            case '*':
                result = firstNum * secondNum;
                break;
            case '/':
                result = firstNum / secondNum;
                break;
            default:
                throw new InputMismatchException("Некорректный ввод арифметического знака.");
        }

        return result;
    }
}

