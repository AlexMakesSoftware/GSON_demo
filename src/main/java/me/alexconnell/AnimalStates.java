package me.alexconnell;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalStates {    
    private Map<Long, DiseaseState> animals;
    private Map<Long, Double> tailLengths;
    private Map<Long, List<VetInspectons>> vetInspections;


    // An example of something we don't want to serilise but do want a reference to in this object.
    private transient CustomRandom random;

    public AnimalStates() {
        animals = new HashMap<>();
        tailLengths = new HashMap<>();
        vetInspections = new HashMap<>();
    }

    public void setRandom(CustomRandom random) {
        this.random = random;
    }

    public static AnimalStates build(int initialInfections, int size, CustomRandom random) {
        
        AnimalStates animalData = new AnimalStates();
        animalData.random = random;
        for(long id=0;id<size;id++){
            if(id<initialInfections && random.nextDouble() > 0.5) {
                animalData.animals.put(id, DiseaseState.INFECTIOUS);
            } else {
                animalData.animals.put(id, DiseaseState.SUSCEPTIBLE);
            }

            animalData.tailLengths.put(id, 10 + (random.nextDouble() * 40));

            int v = ThreadLocalRandom.current().nextInt(0, 5);
            List<VetInspectons> vinsp = new ArrayList<>();
            for(int i=0;i<v;i++){
                vinsp.add(new VetInspectons(randomDate(), "Typical inspection."));
            }
            if(v>0) animalData.vetInspections.put(id,vinsp);
        }
        return animalData;
    }


    private static LocalDate randomDate() {
        int day = ThreadLocalRandom.current().nextInt(1, 28);
        int month = ThreadLocalRandom.current().nextInt(1, 12);
        int year = ThreadLocalRandom.current().nextInt(2006, 2021);
        return LocalDate.of(year, month, day);
    }

    public void diagnostic() {
        animals.forEach( (k,v) -> {
            System.out.println(k+" : "+v);
        });

        System.out.println("Seed: "+this.random.getSeed());
    }
}
