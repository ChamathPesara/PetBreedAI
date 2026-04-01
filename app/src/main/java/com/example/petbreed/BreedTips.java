package com.example.petbreed;

import java.util.HashMap;
import java.util.Map;

public class BreedTips {

    public static String getTip(String breed) {

        Map<String, String> tips = new HashMap<>();

        // 🐶 DOGS
        tips.put("Golden Retriever",
                "Golden Retrievers are highly social. Ensure they get at least 1 hour of exercise daily.");

        tips.put("Labrador Retriever",
                "Labradors are energetic and love swimming. Regular playtime keeps them healthy.");

        tips.put("German Shepherd",
                "German Shepherds are intelligent and need mental stimulation along with physical activity.");

        tips.put("American Bulldog",
                "American Bulldogs are strong and loyal. Needs regular exercise and firm training.");

        tips.put("American Pit Bull Terrier",
                "American Pit Bull Terriers are energetic and affectionate. Requires training and socialization.");

        tips.put("Basset Hound",
                "Basset Hounds are laid-back but stubborn. Needs moderate exercise and patience.");

        tips.put("Beagle",
                "Beagles are curious and active. Requires daily walks and mental stimulation.");

        tips.put("Chihuahua",
                "Chihuahuas are small but bold. Needs warmth, socialization, and gentle care.");

        tips.put("English Cocker Spaniel",
                "English Cocker Spaniels are friendly and active. Requires regular grooming and exercise.");

        tips.put("English Setter",
                "English Setters are gentle and energetic. Needs plenty of outdoor activity.");

        tips.put("German Shorthaired Pointer",
                "German Shorthaired Pointer dogs are very energetic. Requires intense daily exercise.");

        tips.put("Great Pyrenees",
                "Great Pyrenees are calm and protective. Needs space and consistent training.");

        tips.put("Havanese",
                "Havanese dogs are friendly and social. Thrives on companionship and play.");

        tips.put("Japanese Chin",
                "Japanese Chin dogs are quiet and affectionate. Prefers indoor living and comfort.");

        tips.put("Keeshond",
                "Keeshond dogs are lively and friendly. Needs regular grooming and interaction.");

        tips.put("Leonberger",
                "Leonbergers are gentle giant. Requires space, exercise, and grooming.");

        tips.put("Miniature Pinscher",
                "Miniature Pinschers are energetic and alert. Needs active play and supervision.");

        tips.put("Newfoundland",
                "Newfoundland dogs are calm and sweet. Needs space, grooming, and moderate exercise.");

        tips.put("Pomeranian",
                "Pomeranians are lively and fluffy. Needs grooming and regular playtime.");

        tips.put("Pug",
                "Pugs are playful and loving. Needs moderate exercise and cool environments.");

        tips.put("Saint Bernard",
                "Saint Bernard dogs are gentle and patient. Requires space and moderate activity.");

        tips.put("Samoyed",
                "Samoyed dogs are friendly and active. Needs exercise and frequent grooming.");

        tips.put("Scottish Terrier",
                "Scottish Terriers are independent and alert. Needs firm training and daily walks.");

        tips.put("Shiba Inu",
                "Shiba Inu dogs are independent and clean. Requires early training and exercise.");

        tips.put("Staffordshire Bull Terrier",
                "Staffordshire Bull Terriers are strong and affectionate. Needs training and activity.");

        tips.put("Wheaten Terrier",
                "Wheaten Terriers are playful and friendly. Needs grooming and daily exercise.");

        tips.put("Yorkshire Terrier",
                "Yorkshire Terriers are small and lively. Needs grooming and attention.");

        // 🐱 CATS
        tips.put("Persian",
                "Persian cats need daily grooming to maintain their long fur and prevent matting.");

        tips.put("Siamese",
                "Siamese cats are vocal and social. They thrive with attention and interaction.");

        tips.put("Maine Coon",
                "Maine Coons are gentle giants. Provide space and regular brushing.");

        tips.put("Abyssinian",
                "Abyssinians are highly active and curious. Needs daily playtime and mental stimulation.");

        tips.put("Bengal",
                "Bengal cats are energetic and playful. Loves climbing and interactive toys.");

        tips.put("Birman",
                "Birmans are gentle and affectionate. Enjoys companionship and calm environments.");

        tips.put("Bombay",
                "Bombay cats are social and attention-loving. Thrives with human interaction.");

        tips.put("British Shorthair",
                "British Shorthairs are calm and independent. Requires minimal grooming and moderate play.");

        tips.put("Egyptian Mau",
                "Egyptian Maus are fast and agile. Needs space to run and regular activity.");

        tips.put("Ragdoll",
                "Ragdolls are very affectionate and docile. Loves being around people.");

        tips.put("Russian Blue",
                "Russian Blue cats are shy but loyal. Prefers quiet spaces and routine.");

        tips.put("Sphynx",
                "Sphynx cats are hairless and affectionate. Requires regular skin care and warmth.");

        // 🔥 DEFAULT (important)
        if (tips.containsKey(breed)) {
            return tips.get(breed);
        } else {
            return "Wtf is this breed? Try another image.";
        }
    }
}

