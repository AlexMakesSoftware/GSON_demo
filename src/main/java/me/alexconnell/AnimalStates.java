package me.alexconnell;

import java.util.HashMap;
import java.util.Map;

public class AnimalStates {    
    private Map<Long, DiseaseState> animals;

    // An example of something we don't want to serilise but do want a reference to in this object.
    private transient CustomRandom random;

    public AnimalStates() {
        animals = new HashMap<>();
    }

    public void setRandom(CustomRandom random) {
        this.random = random;
    }

    public static AnimalStates build(int initialInfections, int size, CustomRandom random) {
        
        AnimalStates states = new AnimalStates();
        states.random = random;
        for(long id=0;id<size;id++){
            if(id<initialInfections && random.nextDouble() > 0.5) {
                states.animals.put(id, DiseaseState.INFECTIOUS);
            } else {
                states.animals.put(id, DiseaseState.SUSCEPTIBLE);
            }
        }
        return states;
    }


    public void diagnostic() {
        animals.forEach( (k,v) -> {
            System.out.println(k+" : "+v);
        });

        System.out.println("Seed: "+this.random.getSeed());
    }
}
